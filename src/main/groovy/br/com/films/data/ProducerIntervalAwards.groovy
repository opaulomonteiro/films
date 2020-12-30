package br.com.films.data

import groovy.transform.Canonical
import groovy.transform.ToString

@Canonical
@ToString(includeNames = true)
class ProducerIntervalAwards {
    ProducerAwardResponse min
    ProducerAwardResponse max
}