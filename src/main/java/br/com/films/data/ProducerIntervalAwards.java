package br.com.films.data;

import java.util.List;

public class ProducerIntervalAwards {

    private List<ProducerAwardResponse> min;
    private List<ProducerAwardResponse> max;

    public ProducerIntervalAwards(List<ProducerAwardResponse> min, List<ProducerAwardResponse> max) {
        this.min = min;
        this.max = max;
    }

    public List<ProducerAwardResponse> getMin() {
        return min;
    }

    public void setMin(List<ProducerAwardResponse> min) {
        this.min = min;
    }

    public List<ProducerAwardResponse> getMax() {
        return max;
    }

    public void setMax(List<ProducerAwardResponse> max) {
        this.max = max;
    }
}