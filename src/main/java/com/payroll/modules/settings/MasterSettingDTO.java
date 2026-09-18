package com.payroll.modules.settings;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MasterSettingDTO {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotBlank(message = "Setting key wajib diisi")
        private String settingKey;

        @NotBlank(message = "Setting value wajib diisi")
        private String settingValue;

        private Integer settingNumber;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private Long companyId;
        private String settingKey;
        private String settingValue;
        private Integer settingNumber;
        private String createdBy;
        private LocalDateTime createdDate;
        private String modifyBy;
        private LocalDateTime modifyDate;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HistoryResponse {
        private Long id;
        private Long companyId;
        private String settingKey;
        private String actionType;
        private String oldValue;
        private String newValue;
        private String actionBy;
        private LocalDateTime actionDate;
    }
}
