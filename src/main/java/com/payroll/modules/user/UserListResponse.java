package com.payroll.modules.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserListResponse {
    private Long id;
    private String nik;
    private String email;
    private String username;
    private String fullName;
    private String position;
    private String division;
    private String upliner;
    private String uplinerApproval;
    private String status;
}
