package br.com.films;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class FilmsLifeCycle {

    private final Logger logger = LoggerFactory.getLogger(FilmsLifeCycle.class);
    private final DataLoading dataLoading;

    public FilmsLifeCycle(DataLoading dataLoading) {
        this.dataLoading = dataLoading;
    }


    @EventListener(classes = {ContextRefreshedEvent.class})
    public void handleContextStart() {
        logger.info("Loading Data into database");
        try {
            dataLoading.execute();
        } catch (Exception exception) {
            logger.error("Erro when loading data to database", exception);
        }
    }
}