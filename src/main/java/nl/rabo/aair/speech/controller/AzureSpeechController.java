package nl.rabo.aair.speech.controller;

import nl.rabo.aair.speech.service.AzureSpeechAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/apis")
public class AzureSpeechController {

    @Autowired
    private AzureSpeechAdapter azureSpeechAdapter;

    @Autowired
    private Environment environment;

    @GetMapping("/transcribe")
    public ResponseEntity doVoiceProcess() throws IOException, ExecutionException, InterruptedException {
        azureSpeechAdapter.recognizeFromMicrophone();
        return ResponseEntity.ok("Hello Speech!..");
    }

    @GetMapping("/getToken")
    public ResponseEntity getToken(@RequestParam String subscriptionKey) throws IOException, ExecutionException, InterruptedException {
        azureSpeechAdapter.getToken(subscriptionKey);
        return ResponseEntity.ok("Hello Speech!..");
    }
}