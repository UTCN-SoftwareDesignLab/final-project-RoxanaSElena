package com.example.demo.websockets;

import com.example.demo.websockets.dto.Message;
import com.example.demo.websockets.dto.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {
    @MessageMapping("/message") //receive
    @SendTo("/d/message") //respond
    public ResponseMessage welcomeToMyApp(final Message message)
    {
        return new ResponseMessage(message.getMessageContent());
    }
}
