package com.example.Backend.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Backend.Repository.RoomRepository;
import com.example.Backend.Entity.Room;
import com.example.Backend.Service.RoomPurposeStrategy;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final List<RoomPurposeStrategy> strategies;

    @Autowired
    public RoomService(RoomRepository roomRepository, List<RoomPurposeStrategy> strategies) {
        this.roomRepository = roomRepository;
        this.strategies = strategies;
    }

    public Room addroom(Room room) {
        strategies.stream()
                .filter(strategy -> strategy.supports(room.getPurpose()))
                .findFirst()
                .ifPresent(strategy -> strategy.applyConfiguration(room));

        return roomRepository.save(room);
    }

    // public void addroom(Room room ){
    //     roomsRepository.save(room);
    //     }


    public List<Room> getrooms() {
        return roomsRepository.findAll();
    }

    public Room getroom(int id) {
        return  roomsRepository.findById(id).orElse(null);
    }

    public Room update(Room room) {

        return roomsRepository.save(room);

    }
    public void deleteroom(int id){
        roomsRepository.deleteById(id);
    }


}
