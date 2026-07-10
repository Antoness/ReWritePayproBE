package com.payroll.modules.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
