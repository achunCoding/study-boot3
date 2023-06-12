package cn.wycfight.cloud.resource.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessagesController {

    @RequestMapping("/messages")
    public String[] getMessages() {
        return new String[] {"Message 1", "Message 2", "Message 3"};
    }
}
