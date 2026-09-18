package com.payroll.modules.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MasterUplinerRepository masterUplinerRepository;
    private final PasswordEncoder passwordEncoder;

    @jakarta.annotation.PostConstruct
    public void seedDefaultUsers() {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = User.builder()
                    .nik("ADM001")
                    .fullName("Administrator")
                    .email("admin@ptdika.com")
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .position("SUPER_ADMIN")
                    .division("IT")
                    .userStatus("ACTIVE")
                    .build();
            userRepository.save(admin);
        }

        if (userRepository.findByUsername("staffkk").isEmpty()) {
            User staff = User.builder()
                    .nik("STF001")
                    .fullName("Staff KK Jasa")
                    .email("staffkk@ptdika.com")
                    .username("staffkk")
                    .password(passwordEncoder.encode("password"))
                    .position("Staff (Kertas Kerja Jasa)")
                    .division("PAYROLL")
                    .userStatus("ACTIVE")
                    .build();
            userRepository.save(staff);
        }
    }

    @Transactional(readOnly = true)
    public Page<UserListResponse> getUserList(String search, Pageable pageable) {
        Page<Object[]> results = userRepository.findUserListNative(search, pageable);
        
        List<UserListResponse> dtoList = results.getContent().stream().map(row -> UserListResponse.builder()
                .id(((Number) row[0]).longValue())
                .nik((String) row[1])
                .email((String) row[2])
                .username((String) row[3])
                .fullName((String) row[4])
                .position((String) row[5])
                .division((String) row[6])
                .upliner((String) row[7])
                .uplinerApproval((String) row[8])
                .status((String) row[9])
                .build()
        ).collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, results.getTotalElements());
    }

    @Transactional(readOnly = true)
    public List<Map<String, String>> getPotentialUpliners(String nik) {
        List<Object[]> results = userRepository.findPotentialUpliners(nik);
        return results.stream().map(row -> {
            Map<String, String> map = new HashMap<>();
            map.put("nik", (String) row[0]);
            map.put("fullName", (String) row[1]);
            return map;
        }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Map<String, String>> getExistingUpliners(String nik) {
        List<Object[]> results = masterUplinerRepository.findExistingUpliners(nik);
        return results.stream().map(row -> {
            Map<String, String> map = new HashMap<>();
            map.put("nik", (String) row[0]);
            map.put("fullName", (String) row[1]);
            return map;
        }).collect(Collectors.toList());
    }

    @Transactional
    public String addUpliner(String nik, String nikUpliner) {
        if (masterUplinerRepository.existsByNikAndNikUpliner(nik, nikUpliner)) {
            return "DUPLICATE";
        }
        User upliner = userRepository.findByNik(nikUpliner)
                .orElseThrow(() -> new RuntimeException("Upliner not found"));
        MasterUpliner masterUpliner = new MasterUpliner();
        masterUpliner.setNik(nik);
        masterUpliner.setNikUpliner(nikUpliner);
        masterUpliner.setNamaUpliner(upliner.getFullName());
        masterUpliner.setCreatedDate(LocalDateTime.now());
        masterUpliner.setCreatedBy("ADMIN");
        masterUplinerRepository.save(masterUpliner);
        return "SUCCESS";
    }

    @Transactional
    public void deleteUpliner(String nik, String nikUpliner) {
        masterUplinerRepository.deleteByNikAndNikUpliner(nik, nikUpliner);
    }

    @Transactional
    public void resetPasswords(List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            userRepository.resetPasswordBulk(ids);
        }
    }

    @Transactional
    public void createUser(UserRequest request) {
        User user = User.builder()
                .nik(request.getNik())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .position(request.getPosition())
                .division(request.getDivision())
                .leader(request.getUpliner())
                .userStatus("ACTIVE")
                .build();
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(Long id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setNik(request.getNik());
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPosition(request.getPosition());
        user.setDivision(request.getDivision());
        user.setLeader(request.getUpliner());
        user.setUserStatus(request.getStatus());
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
