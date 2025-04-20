package com.example.Backend.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Backend.Entity.Room;
import com.example.Backend.Repository.RoomRepository;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomsRepository;

    public List<Room> getrooms() {
        return roomsRepository.findAll();
    }

    public void addroom(Room room ){
        if ("MEETING".equals(room.getType())) {
            room.setTv(true);
            room.setWhiteboard(true);
        }
        else if ("TRAINING".equals(room.getType())) {
            room.setProjector(true);
            room.setLaptop(true);
        }
        roomsRepository.save(room);
        }

    public Room getroom(int id) {
        return  roomsRepository.findById(id).orElse(null);
    }

    public Room update(Room room) {
        if ("MEETING".equals(room.getType())) {
            room.setTv(true);
        } 
        return roomsRepository.save(room);

    }
    public void deleteroom(int id){
        roomsRepository.deleteById(id);
    }


}