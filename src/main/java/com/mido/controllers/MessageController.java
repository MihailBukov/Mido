package com.mido.controllers;

import com.mido.dtos.MessageDto;
import com.mido.dtos.requests.MessageRequest;
import com.mido.models.Message;
import com.mido.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/message")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/create")
    public ResponseEntity<Void> createMessage(@RequestBody MessageRequest messageRequest) {
        messageService.save(messageRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
