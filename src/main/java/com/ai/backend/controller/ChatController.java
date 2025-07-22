package com.ai.backend.controller;



import com.ai.backend.payLoad.CricketResponse;
import com.ai.backend.service.ChatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService ;

//    @PostMapping
//    public String chat(@RequestBody String message) {
//        return chatModel.call(message) ;
//    }


    @GetMapping("/stream")
    public Flux<String> getStreamResponse ( String InputText ) {
        Flux<String> response = chatService.streamResponse(InputText) ;
        return response;
    }


    @GetMapping
    public String getResponse ( String message ){
        String response = chatService.getResponse(message) ;
        return response ;
    }


    @GetMapping("/cricketBot")
    public ResponseEntity<CricketResponse> getCricketResponse (
            @RequestParam("inputText") String inputText
    ) throws JsonProcessingException {
        CricketResponse cricketResponse = chatService.generateCricketResponse(inputText);
        return ResponseEntity.ok(cricketResponse) ;
    }


    @GetMapping("/images")
    public List<String> generatrImages(
            @RequestParam ("imageDescription") String imageDesc ,
            @RequestParam ("size") String size ,
            @RequestParam ("numberofimages") int numbers
    ) throws IOException {
        return chatService.generateImages(imageDesc,size,numbers) ;
    }



}
