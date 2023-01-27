package nl.rabo.aair.speech.service;

import com.microsoft.cognitiveservices.speech.CancellationDetails;
import com.microsoft.cognitiveservices.speech.CancellationReason;
import com.microsoft.cognitiveservices.speech.ResultReason;
import com.microsoft.cognitiveservices.speech.SpeechRecognitionResult;
import com.microsoft.cognitiveservices.speech.SpeechRecognizer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@Component
public class AzureSpeechAdapter {

    @Autowired
    SpeechRecognizer speechRecognizer;
//
//
//    private final String FILE_NAME = "EDB.AGRM.3120.DATA";
//
//    private final static String PROCESSED_FILE_FOLDER = "PROCESSED";

    public void recognizeFromMicrophone() throws IOException, ExecutionException, InterruptedException {

       log.info("Speak into your microphone.");
        Future<SpeechRecognitionResult> task = speechRecognizer.recognizeOnceAsync();
        SpeechRecognitionResult speechRecognitionResult = task.get();

        if (speechRecognitionResult.getReason() == ResultReason.RecognizedSpeech) {
            log.info("RECOGNIZED: Text= {}" , speechRecognitionResult.getText());
        } else if (speechRecognitionResult.getReason() == ResultReason.NoMatch) {
            log.info("NOMATCH: Speech could not be recognized.");
        } else if (speechRecognitionResult.getReason() == ResultReason.Canceled) {
            CancellationDetails cancellation = CancellationDetails.fromResult(speechRecognitionResult);
            log.info("CANCELED: Reason= {}" , cancellation.getReason());

            if (cancellation.getReason() == CancellationReason.Error) {
                log.info("CANCELED: ErrorCode= {}" , cancellation.getErrorCode());
                log.info("CANCELED: ErrorDetails= {}" , cancellation.getErrorDetails());
                log.info("CANCELED: Did you set the speech resource key and region values?");
            }
        }
        System.exit(0);
    }

    public String getToken(String subscriptionKey){
    return "hi";
    }
}
