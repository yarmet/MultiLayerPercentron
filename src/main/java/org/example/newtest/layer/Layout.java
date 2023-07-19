package org.example.newtest.layer;

import lombok.Getter;
import lombok.Setter;
import org.example.newtest.point.Neuron;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Layout {

    private final List<Neuron> neurons = new ArrayList<>();

    @Setter
    private Layout nextLayout;

    public Layout(int count) {
        for (int i = 0; i < count; i++) {
            neurons.add(new Neuron());
        }
    }


    public void calcSigmoids() {
        for (Neuron neuron : neurons) {
            double value = neuron.getValue();
            double sigmoid = sigmoid(value);
            neuron.setValue(sigmoid);
        }
    }


    private static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }



    public void resetNeuronsValues(){
        for (Neuron neuron : neurons) {
            neuron.setValue(0);
            neuron.setErrorValue(0);
        }
    }

}
