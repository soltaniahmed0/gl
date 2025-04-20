package After_Adapter.Adapter;

import java.util.List;

import com.example.Backend.DTO.MessageDataDTO;
import com.example.Backend.Entity.MessageData;
import com.example.Backend.Entity.Messaging_chanel;
import com.example.Backend.Entity.Messaging_chanelDTO;

public class MessagingChannelAdapter {

    // Conversion men DTO (frontend) → Entity (backend)
    public static Messaging_chanel toEntity(Messaging_chanelDTO dto) {
        Messaging_chanel entity = new Messaging_chanel();

        entity.setChannel_id(dto.getChannel_id());
        entity.setUser(dto.getUser());
        entity.setUser1(null); // tu peux ajouter si user1 est dans le DTO
        entity.setUnreadMessageCount(dto.getUnreadMessageCount());

        return entity;
    }

    //Conversion men Entity (backend) → DTO (frontend)
    public static Messaging_chanelDTO toDTO(Messaging_chanel entity, List<MessageData> messages) {
        List<MessageDataDTO> messageDTOs = MessageData.toDTOList(messages); // y7awwel messages
        return new Messaging_chanelDTO(
                entity.getChannel_id(),
                entity.getUser(),
                messageDTOs,
                entity.getUnreadMessageCount()
        );
    }
}
