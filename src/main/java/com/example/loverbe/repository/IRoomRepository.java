package com.example.loverbe.repository;

import com.example.loverbe.model.entity.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface IRoomRepository extends JpaRepository<Room,Long> {

}

