package com.droidlogic.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

@Controller
@RequestMapping(path = "/weather")
@Slf4j
public class HTTPJsonReceiver {

    @Value("${outputFileFolder}")
    private String outputFileFolder;

    private static final String FILE_NAME="weather_";
    private static final String FORMAT=".txt";

    @RequestMapping(method = RequestMethod.POST)
    public void receiveJsonAsFile(@RequestBody String json){
        File file = new File(outputFileFolder+File.separator+FILE_NAME+System.currentTimeMillis()+FORMAT);
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))){
            bufferedWriter.write(json);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, path="")
    public ResponseEntity defaultMessage() {
        return ResponseEntity.ok().build();
    }
}
