package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Message;
import com.example.demo.repository.MessageRepository;

@Service
public class MessageService {
    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public Message create(String text, int number) {
        Message message = new Message(text, number);
        return repository.save(message);
    }

    public List<Message> getAll() {
        return repository.findAll();
    }
}
