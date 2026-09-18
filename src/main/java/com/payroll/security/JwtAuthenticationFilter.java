package com.payroll.security;

import com.payroll.modules.role.MenuPermission;
import com.payroll.modules.role.MenuPermissionRepository;
import com.payroll.modules.role.Role;
import com.payroll.modules.role.RoleService;
import com.payroll.modules.user.User;
import com.payroll.modules.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;
    private final CustomUserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final MenuPermissionRepository permissionRepository;
    private final RoleService roleService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = resolveToken(request);
        if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
            String username = tokenProvider.getUsernameFromToken(token);
            var userDetails = userDetailsService.loadUserByUsername(username);
            Set<SimpleGrantedAuthority> authorities = new HashSet<>();

            // load user entity to fetch roles & position
            User user = userRepository.findByUsername(username).orElse(null);
            if (user != null) {
                // 1. Direct role associations
                if (user.getRoles() != null && !user.getRoles().isEmpty()) {
                    for (Role role : user.getRoles()) {
                        String roleNameClean = role.getName().toUpperCase().replace(" ", "_");
                        authorities.add(new SimpleGrantedAuthority("ROLE_" + roleNameClean));
                        List<MenuPermission> perms = permissionRepository.findByRoleId(role.getId());
                        for (MenuPermission perm : perms) {
                            if (perm.isCanAccess()) {
                                authorities.add(new SimpleGrantedAuthority("MENU_" + perm.getMenuKey().toUpperCase()));
                                authorities.add(new SimpleGrantedAuthority(perm.getMenuKey().toUpperCase()));
                            }
                        }
                    }
                }

                // 2. Position-based permissions via RoleService
                if (user.getPosition() != null && !user.getPosition().isBlank()) {
                    String posUpper = user.getPosition().toUpperCase().trim();
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + posUpper.replace(" ", "_")));

                    List<MenuPermission> posPerms = roleService.getPermissionsByPosition(user.getPosition());
                    for (MenuPermission perm : posPerms) {
                        if (perm.isCanAccess()) {
                            authorities.add(new SimpleGrantedAuthority("MENU_" + perm.getMenuKey().toUpperCase()));
                            authorities.add(new SimpleGrantedAuthority(perm.getMenuKey().toUpperCase()));
                        }
                    }

                    // Admin / Super Admin / BizOps bypass
                    if (posUpper.contains("ADMIN") || posUpper.contains("BIZOPS") || posUpper.contains("MANAJER") || posUpper.contains("IT")) {
                        authorities.add(new SimpleGrantedAuthority("ROLE_SUPER_ADMIN"));
                        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                        authorities.add(new SimpleGrantedAuthority("MASTER_HOLD"));
                        authorities.add(new SimpleGrantedAuthority("MENU_MASTER_HOLD"));
                        authorities.add(new SimpleGrantedAuthority("MASTER_SETTING_ASSIGN"));
                        authorities.add(new SimpleGrantedAuthority("MENU_MASTER_SETTING_ASSIGN"));
                        authorities.add(new SimpleGrantedAuthority("MASTER_SIGNED"));
                        authorities.add(new SimpleGrantedAuthority("MENU_MASTER_SIGNED"));
                    }
                }
            }

            // Always add basic authenticated authority
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            authorities.add(new SimpleGrantedAuthority("AUTHENTICATED"));

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}