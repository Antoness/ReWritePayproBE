import java.sql.*;
import java.util.*;

public class TestMainList {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/payroll_db";
        String user = "postgres";
        String pass = "dev@dika";
        
        String sql = "SELECT DISTINCT ms.id, ms.created_date, ms.division AS Division, ms.unit_name AS Unit, ms.position AS Position, ms.branch AS Branch, ms.employee_type AS Employee_Type, TO_CHAR(ms.created_date, 'DD/MM/YYYY') AS Created_Date, TO_CHAR(ms.update_date, 'DD/MM/YYYY') AS Update_Date, ms.created_by AS Created_By, ms.approval AS Status, ms.gaji, ms.bpjs_kesehatan, ms.bp_jamsostek, ms.bpjs_pensiun, ms.asuransi_kesehatan, ms.asuransi_kecelakaan, ms.tunjangan " +
                     "FROM master_salary ms " +
                     "LEFT JOIN users u ON u.id = ms.id_user " +
                     "LEFT JOIN master_pic mp ON mp.master_salary_id = ms.id " +
                     "WHERE ((ms.id_user = 16 AND ms.approval = 'REQUEST') " +
                     "OR (mp.\"user\" = 'Staff HRD' AND ms.approval = 'APPROVED') " +
                     "OR (ms.pic = 'Staff HRD' AND ms.approval = 'APPROVED')) " +
                     "AND (ms.approval != 'DONE') " +
                     "AND ms.division ILIKE '%ALODOKTER%'";
        
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while (rs.next()) {
                System.out.println("id: " + rs.getLong("id"));
                System.out.println("Division: " + rs.getString("Division"));
                System.out.println("Created_By: " + rs.getString("Created_By"));
                System.out.println("Status: " + rs.getString("Status"));
                System.out.println("---------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
