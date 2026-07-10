package com.payroll.modules.user;

import lombok.Data;

@Data
public class UserRequest {
    private String nik;
    private String fullName;
    private String email;
    private String username;
    private String password;
    private String position;
    private String division;
    private String upliner;
    private String status;
}
