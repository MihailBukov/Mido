package com.mido.controlers;

import com.mido.dtos.MessageDto;
import com.mido.mappers.MessageMapper;
import com.mido.models.Message;
import com.mido.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class MessageControler {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private MessageService messageService;


    @PostMapping("/api/rating")
    public ResponseEntity<MessageDto> createMessage(@RequestBody MessageDto messageDto) {
        Message message = messageMapper.mapFromDto(messageDto);
        return new ResponseEntity<>(messageMapper.mapToDto(messageService.addMessage(message)), HttpStatus.CREATED);
    }
}
