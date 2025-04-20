package com.example.Backend.Service;

import com.example.Backend.Entity.Room;
import com.example.Backend.Enum.RoomPurpose;

public interface RoomPurposeStrategy {
    boolean supports(RoomPurpose purpose);
    void applyConfiguration(Room room);
}
