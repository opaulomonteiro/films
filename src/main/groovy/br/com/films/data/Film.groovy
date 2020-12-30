package br.com.films.data

import groovy.transform.Canonical
import groovy.transform.ToString

@Canonical
@ToString(includeNames = true)
class Film {
    String id
    String year
    String title
    List<String> studios
    List<String> producers
    boolean winner
}