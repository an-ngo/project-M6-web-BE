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
    @Query(value = "select * from users u join service_by_provider sbp on u.id = sbp.user_id where sbp.name = (select sbp.name from service_by_provider sbp join order_service_by_provider osbp on sbp.id = osbp.service_by_provider_id join orders o on osbp.orders_id = o.id group by sbp.name order by count(sbp.name) DESC limit 1) limit 6", nativeQuery = true)
    Iterable<User> findTop6HotService();
    @Query(value = "select * from users order by count_time DESC limit 6", nativeQuery = true)
    Iterable<User> top6ProviderHot();
}
