package br.com.films.data;

import java.util.Objects;

public class ProducerAwardResponse {
    private String producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;

    public ProducerAwardResponse(String producer, Integer interval, Integer previousWin, Integer followingWin) {
        this.producer = producer;
        this.interval = interval;
        this.previousWin = previousWin;
        this.followingWin = followingWin;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Integer getPreviousWin() {
        return previousWin;
    }

    public void setPreviousWin(Integer previousWin) {
        this.previousWin = previousWin;
    }

    public Integer getFollowingWin() {
        return followingWin;
    }

    public void setFollowingWin(Integer followingWin) {
        this.followingWin = followingWin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProducerAwardResponse)) return false;
        ProducerAwardResponse that = (ProducerAwardResponse) o;
        return getProducer().equals(that.getProducer()) && getInterval().equals(that.getInterval()) && getPreviousWin().equals(that.getPreviousWin()) && getFollowingWin().equals(that.getFollowingWin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(producer, interval, previousWin, followingWin);
    }

    @Override
    public String toString() {
        return "ProducerAwardResponse{" +
                "producer='" + producer + '\'' +
                ", interval=" + interval +
                ", previousWin=" + previousWin +
                ", followingWin=" + followingWin +
                '}';
    }
}