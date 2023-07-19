package org.example.newtest.point;

import lombok.Getter;
import lombok.Setter;
import org.example.newtest.weight.Weight;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Neuron {

    private double value;
    private double errorValue;


    private final List<Weight> outgoingWeights = new ArrayList<>();
    private final List<Weight> incomingWeights = new ArrayList<>();


    @Override
    public String toString() {
        return "Neuron{" +
                "value=" + value +
                ", errorValue=" + errorValue +
                '}';
    }
}
