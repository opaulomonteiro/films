package br.com.films.controller;


import br.com.films.data.ProducerIntervalAwards;
import br.com.films.exception.ErrorMessage;
import br.com.films.exception.FilmsNotFoundException;
import br.com.films.service.FilmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producers")
public class ProducerController {

    private final Logger logger = LoggerFactory.getLogger(ProducerController.class);
    private final FilmsService filmsService;

    public ProducerController(FilmsService filmsService) {
        this.filmsService = filmsService;
    }

    @GetMapping("/awards")
    public ResponseEntity getAwards() {
        try {
            ProducerIntervalAwards producerIntervalAwards = filmsService.getProducerAwards();
            return new ResponseEntity<>(producerIntervalAwards, HttpStatus.OK);
        } catch (FilmsNotFoundException exception) {
            logger.error("Films database is empty", exception);
            return new ResponseEntity<>(new ErrorMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            logger.error("Error trying to get producers awards", exception);
            return new ResponseEntity<>(new ErrorMessage(exception.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}