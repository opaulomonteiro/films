package br.com.films.data

import groovy.transform.Canonical
import groovy.transform.ToString

@Canonical
@ToString(includeNames = true)
class ProducerAward {
    String name
    Integer year
    String filmTitle
}