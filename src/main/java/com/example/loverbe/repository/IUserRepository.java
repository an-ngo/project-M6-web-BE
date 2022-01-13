package com.example.loverbe.repository;

import com.example.loverbe.model.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String name);
    User findByEmail(String email);
    Boolean existsByUsername(String username); //username da co trong DB chua, khi tao du lieu
    Boolean existsByEmail(String email); //email da co trong DB chua
    @Query(value = "select * from users where is_status_provider = 'ACTIVITY' or is_status_provider = 'BUSY'", nativeQuery = true)
    Page<User> findAllServiceProvider(Pageable pageable);
}
