package com.example.loverbe.repository;

import com.example.loverbe.model.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String name);
    User findByEmail(String email);
    Boolean existsByUsername(String username); //username da co trong DB chua, khi tao du lieu
    Boolean existsByEmail(String email); //email da co trong DB chua
    @Query(value = "select * from users where is_status_provider = 1 or  is_status_provider = 2", nativeQuery = true)
    Page<User> findAllServiceProvider(Pageable pageable);
    @Query(value = "select * from users u join service_by_provider sbp on u.id = sbp.user_id where (u.is_status_provider = 1 or  u.is_status_provider = 2) and sbp.name = (select sbp.name from service_by_provider sbp join order_service_by_provider osbp on sbp.id = osbp.service_by_provider_id join orders o on osbp.orders_id = o.id group by sbp.name order by count(sbp.name) DESC limit 1) limit 6", nativeQuery = true)
    Iterable<User> findTop6HotService();
    @Query(value = "select * from users where is_status_provider = 1 or  is_status_provider = 2 order by count_time DESC limit 6", nativeQuery = true)
    Iterable<User> top6ProviderHot();
    @Query(value = "select * from users where gender='Nam' and is_status_provider = 1 or  is_status_provider = 2 order by count_time DESC limit 4;", nativeQuery = true)
    Iterable<User> find4MenUserTopCountTime();
    @Query(value = "select * from users where gender='Nu' and is_status_provider = 1 or  is_status_provider = 2 order by count_time DESC limit 8", nativeQuery = true)
    Iterable<User> find8MaleUserTopCountTime();
    @Query(value = "select * from users where is_status_provider = 1 or  is_status_provider = 2 order by join_date DESC limit 12", nativeQuery = true)
    Iterable<User> find12TopJoinDate();
    @Query(value = "select * from users where is_status_provider = 1 or  is_status_provider = 2 order by view_count limit 6", nativeQuery = true)
    Iterable<User> find6TopViewPage();
    @Query(value = "select * from users where gender like ? and year_of_birth >= ? and year_of_birth <= ? and country like ? and city like ? and (is_status_provider = 1 or is_status_provider = 2) order by view_count DESC", nativeQuery = true)
    Page<User> searchUserProvider(String gender, Long beforeYear, Long afterYear, String country, String city, Pageable pageable);
    List<User> findAllByLongitudeBetweenAndLatitudeBetween(Double longL, Double longG, Double latL, Double latG);
}
