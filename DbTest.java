import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbTest {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/payroll_db", "postgres", "dev@dika");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT column_name, data_type FROM information_schema.columns WHERE table_name = 'master_salary';");
            System.out.println("Columns in master_salary:");
            while (rs.next()) {
                System.out.println(rs.getString("column_name") + " - " + rs.getString("data_type"));
            }
            rs = stmt.executeQuery("SELECT conname, pg_get_constraintdef(c.oid) FROM pg_constraint c JOIN pg_namespace n ON n.oid = c.connamespace WHERE conrelid = 'master_salary'::regclass;");
            System.out.println("\nConstraints on master_salary:");
            while (rs.next()) {
                System.out.println(rs.getString(1) + " - " + rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
