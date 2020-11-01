package ru.city.city89.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.city.city89.domain.Message;
import ru.city.city89.domain.Views;
import ru.city.city89.exceptions.NotFoundException;
import ru.city.city89.repo.MessageRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageRepo messageRepo;

    @Autowired
    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

//    private int counter = 4;
//    // темы здесь должны быть!
//    private List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
//        add(new HashMap<String, String>() {{ put("id", "1"); put("text", "Hello world"); }});
//        add(new HashMap<String, String>() {{ put("id", "2"); put("text", "Some message"); }});
//        add(new HashMap<String, String>() {{ put("id", "3"); put("text", "Another message"); }});
//    }};

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Message> list() {
        return messageRepo.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message getOne(@PathVariable("id") Message message) {
        return message;
    }

//    private Map<String, String> getMessage(@PathVariable String id) {
//        return messages.stream()
//                .filter(msg -> msg.get("id").equals(id))
//                .findFirst()
//                .orElseThrow(NotFoundException::new);
//    }

    @PostMapping
    public Message create(@RequestBody Message message) {
//        message.put("id", String.valueOf(counter++));
//        messages.add(message);
//        return message;
        message.setCreated(LocalDateTime.now());
        return messageRepo.save(message);
    }

    @PutMapping("{id}")
    public Message update(@PathVariable("id") Message messageFromDb,
                                      @RequestBody Message message) {
//        Map<String, String> messageFromDb = getMessage(id);
//        messageFromDb.putAll(message);
//        messageFromDb.put("id", id);
        BeanUtils.copyProperties(message, messageFromDb, "id");

        return messageRepo.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {
//        Map<String, String> message = getMessage(id);
//        messages.remove(message);
        messageRepo.delete(message);
    }

}
