package org.example.newtest;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.newtest.layer.Layout;
import org.example.newtest.point.Neuron;
import org.example.newtest.weight.Weight;

import java.util.List;
import java.util.Random;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Percentron {

    private Layout enterLayer;
    private Layout hiddenLayer;
    private Layout outputLayout;


   static Random random = new Random();

    public static Percentron build(Layout enterLayer, Layout outputLayout, Layout hiddenLayer) {
        boundLayouts(enterLayer, hiddenLayer);
        boundLayouts(hiddenLayer, outputLayout);
        return new Percentron(enterLayer, hiddenLayer, outputLayout);
    }


    private static void boundLayouts(Layout layout1, Layout layout2) {
        for (Neuron neuron : layout1.getNeurons()) {
            for (Neuron hiddenNeuron : layout2.getNeurons()) {
                Weight weight = new Weight(random.nextDouble(), neuron, hiddenNeuron);
                neuron.getOutgoingWeights().add(weight);
                hiddenNeuron.getIncomingWeights().add(weight);
            }
        }
        layout1.setNextLayout(layout2);
    }


    public List<Neuron> count(int[][] inputValues) {

        hiddenLayer.resetNeuronsValues();
        outputLayout.resetNeuronsValues();

        convert(inputValues);
        calcNextLayer(enterLayer);
        calcNextLayer(hiddenLayer);

        return outputLayout.getNeurons();
    }


    private void convert(int[][] inputValues) {

        for (int i = 0; i < inputValues.length; i++) {
            for (int j = 0; j < inputValues[i].length; j++) {
                int value = inputValues[i][j];
                int position = i * inputValues[i].length + j;
                enterLayer.getNeurons().get(position).setValue(value);
            }
        }
    }


    private void calcNextLayer(Layout layout) {
        for (Neuron neuron : layout.getNeurons()) {
            double inputValue = neuron.getValue();
            for (Weight outgoingWeight : neuron.getOutgoingWeights()) {
                double weightValue = outgoingWeight.getValue();
                Neuron rightNeuron = outgoingWeight.getRightNeuron();
                double oldValue = rightNeuron.getValue();
                double newValue = oldValue + inputValue * weightValue;
                rightNeuron.setValue(newValue);
            }
        }
        layout.getNextLayout().calcSigmoids();
    }


}
