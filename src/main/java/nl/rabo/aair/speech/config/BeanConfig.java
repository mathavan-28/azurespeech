package nl.rabo.aair.speech.config;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageCredentials;
import com.microsoft.azure.storage.StorageCredentialsAccountAndKey;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechRecognizer;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

@Configuration
public class BeanConfig {

    @Autowired
    private Environment environment;

    @Bean
    public SpeechConfig speechConfigClient() throws URISyntaxException, StorageException, InvalidKeyException {
//        StorageCredentials storageCredentials= new
//                StorageCredentialsAccountAndKey(environment.getProperty("azure.storage.account-name"),
//                environment.getProperty("azure.storage.account-key")
//                );
//
//        CloudStorageAccount storageAccount = new CloudStorageAccount(storageCredentials, true);
//        return storageAccount.createCloudBlobClient();
////        CloudStorageAccount storageAccount = CloudStorageAccount.parse(environment.getProperty("azure.storage.ConnectionString"));
////        return storageAccount.createCloudBlobClient();
        SpeechConfig speechConfigClient = SpeechConfig.fromSubscription(environment.getProperty("azure.cognitive.speech.account-key"),
                environment.getProperty("azure.cognitive.speech.region-name"));
        speechConfigClient.setSpeechRecognitionLanguage("en-US");
        return speechConfigClient;
    }

    @Bean
    public SpeechRecognizer getSpeechRecognizer() throws StorageException, InvalidKeyException, URISyntaxException {
        AudioConfig audioConfig = AudioConfig.fromDefaultMicrophoneInput();
        return new SpeechRecognizer(speechConfigClient(), audioConfig);
    }



}