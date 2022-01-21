package com.example.loverbe.repository;

import com.example.loverbe.model.entity.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface IRoomRepository extends JpaRepository<Room,Long> {
    @Query(value = "select * from room r join message m on r.id = m.room_id join users u on m.user_id = u.id where u.id = ? group by r.id", nativeQuery = true)
    Iterable<Room> findAllByUserId(Long id);
}

