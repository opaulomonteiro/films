package br.com.films.exception

import groovy.transform.Canonical
import groovy.transform.ToString

@Canonical
@ToString(includeNames = true)
class ErrorMessage {
    String reason
}