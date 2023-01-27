package nl.rabo.aair.speech;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@Profile("test")
public class CommandLineAppRunner implements CommandLineRunner {

    @Autowired
    private Environment environment;

    @Override
    public void run(String...args) throws Exception {
//        List<ContractModel> contractsFromPex = azureBlobAdapter.readCSVContent(container_name);
        String service_name = environment.getProperty("azure.cognitive.speech.region-name");
        log.info(service_name);
    }

}
