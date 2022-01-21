package com.example.loverbe.service.Implement;


import com.example.loverbe.model.entity.room.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

@Service
public class MessageProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProcessor.class);

    private List<Consumer<Message>> listeners = new CopyOnWriteArrayList<>();

    public void register(Consumer < Message > listener) {
        listeners.add(listener);
        LOGGER.info("Added a listener, for a total of {} listener{}", listeners.size(), listeners.size() > 1 ? "s" : "");
    }

    // TODO FBE implement unregister

    public void process(Message event) {
        System.out.println("Processing: " + event);
        listeners.forEach(c -> c.accept(event));
    }
}
