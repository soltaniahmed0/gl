package com.example.Backend.Adapter;

import com.example.Backend.DTO.MessageDataDTO;
import com.example.Backend.Entity.MessageData;
import com.example.Backend.Entity.Messaging_chanel;
import com.example.Backend.Services.MessagingService;

public class MessageAdapter {

    public static MessageData fromDTO(MessageDataDTO dto, MessagingService messagingService) {
        Messaging_chanel channel = messagingService.GetChannelById(dto.getChannel());
        return new MessageData(
            dto.getSender(),
            dto.getMessage(),
            dto.getMessageDate(),
            channel
        );
    }
}