package com.example.Backend.Service;

import com.example.Backend.Entity.Room;
import com.example.Backend.Enum.RoomPurpose;
import org.springframework.stereotype.Service;

@Service
public class TrainingRoomStrategy implements RoomPurposeStrategy {
    @Override
    public boolean supports(RoomPurpose purpose) {
        return purpose == RoomPurpose.TRAINING;
    }

    @Override
    public void applyConfiguration(Room room) {
        room.setVideo_projector(true);
        room.setHeater(true);
    }
}