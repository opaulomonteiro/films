package br.com.films.data;

public class ProducerIntervalAwards {
    ProducerAwardResponse min;
    ProducerAwardResponse max;

    public ProducerIntervalAwards(ProducerAwardResponse min, ProducerAwardResponse max) {
        this.min = min;
        this.max = max;
    }

    public ProducerAwardResponse getMin() {
        return min;
    }

    public void setMin(ProducerAwardResponse min) {
        this.min = min;
    }

    public ProducerAwardResponse getMax() {
        return max;
    }

    public void setMax(ProducerAwardResponse max) {
        this.max = max;
    }
}