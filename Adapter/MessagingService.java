package com.example.Backend.Services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Backend.Entity.*;
import com.example.Backend.Repository.MessagingChannelRepository;
import com.example.Backend.Repository.MessagingDataRepository;
import com.example.Backend.Adapter.MessagingChannelAdapter; // adapter

@Service
public class MessagingService {
    @Autowired
    private MessagingChannelRepository messagingChannelRepository;
    @Autowired
    private MessagingDataRepository messagingDataRepository;

    public List<Messaging_chanelDTO> getuserMessagingChannels(Long id) {
        List<Messaging_chanelDTO> res = new ArrayList<>();


        messagingChannelRepository.findByUserIdOrUser1Id(id).forEach(channel -> {
            List<MessageData> messages = messagingDataRepository.findByChannel_Id(channel.getChannel_id());
            Messaging_chanelDTO dto = MessagingChannelAdapter.toDTO(channel, messages); // adapter appliqué
            res.add(dto);
        });

        return res;
    }
    public List<Messaging_chanel> getMessagingChannels() {
        return messagingChannelRepository.findAll();
    }
    public Messaging_chanel GetChannelById(Long id) {
        return messagingChannelRepository.findByChannel_id(id);
    }
    public Messaging_chanel updateUnreadMessage(Long id,int val) {
        Messaging_chanel existingChannel = GetChannelById(id);

        if (existingChannel != null) {
            existingChannel.setUnreadMessageCount(val);

            return messagingChannelRepository.save(existingChannel);
        }


        return null;
    }


    public Messaging_chanel addChannel(Messaging_chanel messaging_chanel){

        return  messagingChannelRepository.save(messaging_chanel);
        }
    public void SendMessage(MessageData messageData){
        messagingDataRepository.save(messageData);
    }



}
