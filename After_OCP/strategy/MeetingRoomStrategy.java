package com.example.Backend.Service;

import com.example.Backend.Entity.Room;
import com.example.Backend.Enum.RoomPurpose;
import org.springframework.stereotype.Service;

@Service
public class MeetingRoomStrategy implements RoomPurposeStrategy {
    @Override
    public boolean supports(RoomPurpose purpose) {
        return purpose == RoomPurpose.MEETING;
    }

    @Override
    public void applyConfiguration(Room room) {
        room.setTv(true);
        room.setWifi(true);
        room.setSoundproof_room(true);
    }
}
