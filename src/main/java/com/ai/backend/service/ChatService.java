package com.ai.backend.service;

import com.ai.backend.payLoad.CricketResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.image.ImageGeneration;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ChatService {

    @Autowired
    private ChatModel chatModel ;

    @Autowired
    private OpenAiImageModel openAiImageModel ;

    public String getResponse ( String InputText ){
        String Response = chatModel.call(InputText) ;
        return Response ;
    }

    public Flux<String> streamResponse (String InputText ){
        Flux<String> Response = chatModel.stream(InputText) ;
        return Response ;
    }

    public CricketResponse generateCricketResponse ( String InputText ) throws JsonProcessingException {
        String promptString = "As a cricket expert answer this question " + InputText + " if the above question now related to the cricket. generate one joke and give error that this question not realted to the cricket. provide a json response that must contain 'content' as a key" + " and response as a value " ;

        ChatResponse cricketResponse = chatModel.call( new Prompt(promptString)) ;
        String response = cricketResponse.getResult().getOutput().toString() ;
        ObjectMapper mapper = new ObjectMapper() ;
        CricketResponse CricketResponse = mapper.readValue(response , CricketResponse.class) ;
        return CricketResponse ;

    }


    //load from any file to the mehtod

    public List<String> generateImages (String imageDesc , String size , int number ) throws IOException {
        String template = this.loadPromptTemplate("prompts/image.txt") ;
        String promptString = this.putValuesInTemplate(template , Map.of(
                "numberofimages" , number + "" ,
                "description " , imageDesc ,
                "size" , size
        )) ;

        ImageResponse imageResponse = openAiImageModel.call(new ImagePrompt(promptString)) ;
        List<String> imageUrls = imageResponse.getResults().stream().map(generation -> generation.getOutput().getUrl()).collect(Collectors.toList());

        return imageUrls ;

    }


    public String loadPromptTemplate ( String fileName ) throws IOException {
        Path filepath = new ClassPathResource(fileName).getFile().toPath() ;
        return Files.readString(filepath) ;
    }


    // put the text file prompt file

    public String putValuesInTemplate ( String template , Map<String , String > variables ){
        for (Map.Entry<String ,String > entry : variables.entrySet() ) {
            template = template.replace("{" + entry.getKey() + "}" , entry.getValue()) ;
        }
        return template ;
    }
}
