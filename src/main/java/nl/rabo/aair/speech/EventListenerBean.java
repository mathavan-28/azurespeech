package nl.rabo.aair.speech;

import com.microsoft.azure.storage.StorageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;

@Component
@Slf4j
@Profile("test")
public class EventListenerBean {


    @Autowired
    private Environment environment;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationStarup() throws StorageException, IOException, URISyntaxException {
        String service_name = environment.getProperty("azure.cognitive.speech.region-name");
        log.info(service_name);
    }
}
