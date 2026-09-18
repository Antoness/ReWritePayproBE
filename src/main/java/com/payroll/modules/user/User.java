package com.payroll.modules.user;

import com.payroll.modules.role.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true)
    private String nik;

    @Column(length = 100)
    private String email;

    @Column(name = "nama", length = 50)
    private String username;

    @Column(name = "full_name", length = 255)
    private String fullName;

    @Column(length = 100)
    private String password;

    @Column(length = 100)
    private String position;

    @Column(length = 100)
    private String division;

    @Column(length = 50)
    private String privilage;

    @Column(length = 100)
    private String leader;

    @Column(name = "user_status", length = 20)
    private String userStatus;

    @Column(name = "business_type", length = 50)
    private String businessType;

    @Column(name = "company_id")
    @Builder.Default
    private Long companyId = 1L;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Builder.Default
    private Set<Role> roles = new HashSet<>();

    public Long getId() { return this.id; }
    public void setId(Long id) { this.id = id; }
    public String getNik() { return this.nik; }
    public void setNik(String nik) { this.nik = nik; }
    public String getEmail() { return this.email; }
    public void setEmail(String email) { this.email = email; }
    public String getUsername() { return this.username; }
    public void setUsername(String username) { this.username = username; }
    public String getFullName() { return this.fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getPassword() { return this.password; }
    public void setPassword(String password) { this.password = password; }
    public String getPosition() { return this.position; }
    public void setPosition(String position) { this.position = position; }
    public String getDivision() { return this.division; }
    public void setDivision(String division) { this.division = division; }
    public String getPrivilage() { return this.privilage; }
    public void setPrivilage(String privilage) { this.privilage = privilage; }
    public String getLeader() { return this.leader; }
    public void setLeader(String leader) { this.leader = leader; }
    public String getUserStatus() { return this.userStatus; }
    public void setUserStatus(String userStatus) { this.userStatus = userStatus; }
    public String getBusinessType() { return this.businessType; }
    public void setBusinessType(String businessType) { this.businessType = businessType; }
    public Set<Role> getRoles() { return this.roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }
}