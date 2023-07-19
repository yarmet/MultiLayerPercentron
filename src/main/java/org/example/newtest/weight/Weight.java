package org.example.newtest.weight;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.newtest.point.Neuron;

@Getter
@Setter
@AllArgsConstructor
public class Weight {

    private double value;
    private Neuron leftNeuron;
    private Neuron rightNeuron;


    @Override
    public String toString() {
        return "Weight{" +
                "value=" + value +
                '}';
    }
}
