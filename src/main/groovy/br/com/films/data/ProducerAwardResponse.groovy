package br.com.films.data

import groovy.transform.Canonical
import groovy.transform.ToString

@Canonical
@ToString(includeNames = true)
class ProducerAwardResponse {
    String producer
    Integer interval
    Integer previousWin
    Integer followingWin
}