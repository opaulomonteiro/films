package br.com.films.repository

import groovy.transform.Canonical
import groovy.transform.ToString

@Canonical
@ToString(includeNames = true)
class FilmEntity {
    Integer id
    String year
    String title
    List<String> studios
    List<String> producers
    boolean winner
}