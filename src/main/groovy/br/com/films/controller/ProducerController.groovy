package br.com.films.controller

import br.com.films.data.ProducerIntervalAwards
import br.com.films.exception.ErrorMessage
import br.com.films.exception.FilmsNotFoundException
import br.com.films.service.FilmsService
import groovy.util.logging.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/producers")
@Slf4j
class ProducerController {

    private FilmsService filmsService

    ProducerController(FilmsService filmsService) {
        this.filmsService = filmsService
    }

    @GetMapping("/awards")
    ResponseEntity getAwards() {
        try {
            ProducerIntervalAwards producerIntervalAwards = filmsService.getProducerAwards()
            return new ResponseEntity<ProducerIntervalAwards>(producerIntervalAwards, HttpStatus.OK)
        } catch (FilmsNotFoundException exception) {
            log.error("Films database is empty", exception)
            return new ResponseEntity<>(new ErrorMessage(reason: exception.getMessage()), HttpStatus.NOT_FOUND)
        } catch (Exception exception) {
            log.error("Error trying to get producers awards", exception)
            return new ResponseEntity<>(new ErrorMessage(reason: exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}