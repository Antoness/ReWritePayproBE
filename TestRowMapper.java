import java.sql.*;
import java.util.*;

public class TestRowMapper {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/payroll_db";
        String user = "postgres";
        String pass = "dev@dika";
        
        String sql = "SELECT al.log_id AS id, TO_CHAR(al.created_at, 'YYYY-MM-DD HH24:MI:SS.US') AS created_date, al.new_values->>'division' AS Division, al.new_values->>'unit_name' AS Unit, al.new_values->>'position' AS Position, al.new_values->>'branch' AS Branch, UPPER(al.new_values->>'employee_type') AS Employee_Type, TO_CHAR(al.created_at, 'DD/MM/YYYY') AS Created_Date, TO_CHAR(al.created_at, 'DD/MM/YYYY') AS Update_Date, al.created_by AS Created_By, al.new_values->>'approval' AS Status, CAST(al.new_values->>'gaji' AS NUMERIC) AS gaji, al.new_values->>'bpjs_kesehatan' AS bpjs_kesehatan, al.new_values->>'bp_jamsostek' AS bp_jamsostek, al.new_values->>'bpjs_pensiun' AS bpjs_pensiun, al.new_values->>'asuransi_kesehatan' AS asuransi_kesehatan, al.new_values->>'asuransi_kecelakaan' AS asuransi_kecelakaan, CAST(al.new_values->>'tunjangan' AS NUMERIC) AS tunjangan, CASE WHEN al.new_values->>'approval'='REQUEST' THEN al.new_values->>'keterangan' ELSE '' END AS Keterangan FROM audit_logs al LEFT JOIN users u ON u.full_name = al.created_by WHERE al.entity_name = 'MasterClient' AND (al.created_by = 'Staff HRD' OR u.leader = 'Staff HRD') AND UPPER(al.new_values->>'division') = UPPER('ALODOKTER')";
        
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();
            for (int i = 1; i <= colCount; i++) {
                System.out.println(rsmd.getColumnLabel(i) + " (" + rsmd.getColumnName(i) + ")");
            }

            while (rs.next()) {
                System.out.println("id: " + rs.getLong("id"));
                System.out.println("Created_Date: " + rs.getString("Created_Date"));
                System.out.println("Update_Date: " + rs.getString("Update_Date"));
                System.out.println("Division: " + rs.getString("Division"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
