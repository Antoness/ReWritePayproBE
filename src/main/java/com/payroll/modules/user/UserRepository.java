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

    @Query(value = "SELECT id, nik, email, nama, full_name, position, division, leader, privilage, user_status FROM users WHERE " +
           "(:search IS NULL OR LOWER(nama) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(nik) LIKE LOWER(CONCAT('%', :search, '%')))",
           nativeQuery = true)
    Page<Object[]> findUserListNative(@Param("search") String search, Pageable pageable);

    @Query(value = "SELECT nik, full_name FROM users WHERE LOWER(position) LIKE LOWER(CONCAT('%', :position, '%'))", nativeQuery = true)
    List<Object[]> findPotentialUpliners(@Param("position") String position);

    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.id IN :ids")
    void resetPasswordBulk(@Param("ids") List<Long> ids);
}