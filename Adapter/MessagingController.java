package com.example.Backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Backend.Adapter.MessageDataAdapter; // adapter
import com.example.Backend.Adapter.MessagingChannelAdapter; // adapter
import com.example.Backend.Entity.*;
import com.example.Backend.Services.MessagingService;
import com.example.Backend.Services.NotificationService;


@RequestMapping("/Messaging")
@RestController
public class MessagingController {
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private MessagingService messagingService;
    @Autowired
    public MessagingController(MessagingService messagingService){
        this.messagingService=messagingService;
    }
    @CrossOrigin(origins = "http://localhost:57384")
    @GetMapping("/getUserChannels")
    public List<Messaging_chanelDTO> getUserChannels(@RequestParam Long id){
         return messagingService.getuserMessagingChannels(id);
    }
    @CrossOrigin(origins = "http://localhost:57384")
    @GetMapping("/getChannels")
    public List<Messaging_chanel> getChannels(){
        return messagingService.getMessagingChannels();
    }


    @CrossOrigin(origins = "http://localhost:59838/")
    @PostMapping("/SendMessage")
    public void SendMessage(@RequestBody MessageDataDTO messageDataDTO){
        MessageData msg = MessageDataAdapter.toEntity(messageDataDTO, messagingService); // adapter
        Messaging_chanel channel = msg.getChannel(); // adapter
        Employee user = channel.getUser().getEmployee_id()!=messageDataDTO.getSender().getEmployee_id()?channel.getUser():channel.getUser1();
        messagingService.SendMessage(msg);
        notificationService.sendNotification(messageDataDTO.getSender().getFirstname()+messageDataDTO.getSender().getLastname(),messageDataDTO.getMessage(),user.getDeviceToken());
    }

    @CrossOrigin(origins = "http://localhost:59838/")
    @PostMapping("/AddChannel")
    public Long add(@RequestBody Messaging_chanelDTO dto) {
        Messaging_chanel entity = MessagingChannelAdapter.toEntity(dto);
        return messagingService.addChannel(entity).getChannel_id();
    }


    @CrossOrigin(origins = "http://localhost:59838/")
    @PutMapping("/UpdateChannel")
    public Messaging_chanel updateUnreadMessage(@RequestBody Messaging_chanelDTO messaging_chanelDTO) {
        return messagingService.updateUnreadMessage(messaging_chanelDTO.getChannel_id(),messaging_chanelDTO.getUnreadMessageCount());
    }

}
