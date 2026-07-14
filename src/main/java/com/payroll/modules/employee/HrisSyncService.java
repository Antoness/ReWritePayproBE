package com.payroll.modules.employee;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.payroll.config.RabbitMQConfig;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HrisSyncService {

    private final JdbcTemplate jdbcTemplate;
    private final RestTemplate restTemplate;
    private final RabbitTemplate rabbitTemplate;

    @Value("${hris.api.employee.data}")
    private String dataUrl;

    @Value("${hris.api.employee.contract}")
    private String contractUrl;

    @Value("${hris.api.key}")
    private String apiKey;

    @Value("${hris.api.auth}")
    private String apiAuth;

    public HrisSyncService(JdbcTemplate jdbcTemplate, RabbitTemplate rabbitTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rabbitTemplate = rabbitTemplate;
        this.restTemplate = new RestTemplate();
    }

    public void syncHrisData(String username, String labelTanggalUpdate) {
        try {
            // 1. Fetch API Data
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-API-KEY", apiKey);
            headers.set("Authorization", apiAuth);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            log.info("Fetching data from HRIS...");
            ResponseEntity<String> dataResponse = restTemplate.exchange(dataUrl, HttpMethod.GET, entity, String.class);
            String dataJson = dataResponse.getBody();
            if(dataJson == null) dataJson = "{}";

            // 2. Insert into audit_logs (so we can get Last Update date)
            log.info("Inserting to audit_logs for Last Update tracking...");
            jdbcTemplate.update("INSERT INTO audit_logs (action_type, created_by, created_at) VALUES ('SYNC_HRIS', ?, NOW())", username);
            
            // Inject the username who triggered the sync into the JSON payload
            if (dataJson.trim().startsWith("{")) {
                dataJson = "{\"sync_by\": \"" + username + "\", " + dataJson.trim().substring(1);
            }

            // 3. Publish to RabbitMQ
            log.info("Publishing HRIS data to RabbitMQ queue: {}", RabbitMQConfig.QUEUE_HR_EMPLOYEE_SYNC);
            rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_HR_EMPLOYEE_SYNC, dataJson);
            
            log.info("HRIS Data successfully published to RabbitMQ. Background worker will process it.");
        } catch (Exception e) {
            log.error("Failed to fetch or publish HRIS data: {}", e.getMessage(), e);
            throw new RuntimeException("Gagal sinkronisasi data dari HRIS", e);
        }
    }
}
