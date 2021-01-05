package br.com.films.data;

public class ProducerAward {
    String name;
    Integer year;
    String filmTitle;

    public ProducerAward(String name, Integer year, String filmTitle) {
        this.name = name;
        this.year = year;
        this.filmTitle = filmTitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }
}