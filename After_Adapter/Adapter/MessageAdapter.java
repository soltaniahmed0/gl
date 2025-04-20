package After_Adapter.Adapter;

import com.example.Backend.DTO.MessageDataDTO;
import com.example.Backend.Entity.MessageData;
import com.example.Backend.Entity.Messaging_chanel;

import After_Adapter.MessagingService;

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