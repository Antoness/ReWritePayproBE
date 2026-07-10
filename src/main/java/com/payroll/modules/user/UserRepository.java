package com.payroll.modules.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername(String username);
    Optional<User> findByNik(String nik);

    @Query(value = "SELECT a.id, " +
            "a.nik AS nik, " +
            "a.email AS email, " +
            "a.nama AS username, " +
            "a.full_name AS fullName, " +
            "a.position AS position, " +
            "a.division AS division, " +
            "a.leader AS upliner, " +
            "STRING_AGG(mu.nama_upliner, ', ') as uplinerApproval, " +
            "a.user_status AS status " +
            "FROM users a " +
            "LEFT JOIN master_upliner mu ON a.nik = mu.nik " +
            "WHERE (:search IS NULL OR " +
            "      a.nik LIKE %:search% OR " +
            "      a.email LIKE %:search% OR " +
            "      a.nama LIKE %:search% OR " +
            "      a.full_name LIKE %:search% OR " +
            "      a.position LIKE %:search% OR " +
            "      a.division LIKE %:search% OR " +
            "      a.leader LIKE %:search%) " +
            "GROUP BY a.id, a.nik, a.email, a.nama, a.full_name, a.position, a.division, a.leader, a.user_status", 
            countQuery = "SELECT count(*) FROM users a WHERE (:search IS NULL OR a.nik LIKE %:search% OR a.full_name LIKE %:search%)",
            nativeQuery = true)
    Page<Object[]> findUserListNative(@Param("search") String search, Pageable pageable);

    @Query(value = "SELECT DISTINCT nik, full_name FROM users " +
            "WHERE position NOT LIKE 'Staff%' " +
            "AND full_name != (SELECT DISTINCT COALESCE(leader, '') FROM users WHERE nik = :nik) " +
            "ORDER BY full_name ASC", nativeQuery = true)
    List<Object[]> findPotentialUpliners(@Param("nik") String nik);

    // RESET PASSWORD BULK QUERY
    @Modifying
    @Query(value = "UPDATE users " +
            "SET password = " +
            "    CASE " +
            "        WHEN EXISTS (SELECT 1 FROM employee_update_data WHERE nik = users.nik) " +
            "        THEN MD5((SELECT REPLACE(date_of_birth,'-','') FROM employee_update_data WHERE nik = users.nik)) " +
            "        ELSE password " +
            "    END " +
            "WHERE id IN :ids", nativeQuery = true)
    void resetPasswordBulk(@Param("ids") List<Long> ids);
}
