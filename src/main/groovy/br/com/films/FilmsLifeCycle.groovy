package br.com.films

import groovy.util.logging.Slf4j
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
@Slf4j
class FilmsLifeCycle {

    private DataLoading dataLoading

    FilmsLifeCycle(DataLoading dataLoading) {
        this.dataLoading = dataLoading
    }

    @EventListener([ContextRefreshedEvent])
    void afterStart() {
        log.info("Loading Data into database")
        try {
            dataLoading.execute()
        } catch (Exception exception) {
            log.error("Erro when loading data to database", exception)
        }
    }
}