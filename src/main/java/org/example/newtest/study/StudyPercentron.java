package org.example.newtest.study;

import org.example.newtest.Percentron;
import org.example.newtest.point.Neuron;
import org.example.newtest.sets.StudySet;
import org.example.newtest.weight.Weight;

import java.util.List;

public class StudyPercentron {


    public static void study(Percentron percentron) {


        for (int t = 0; t < 500000; t++) {
            for (StudySet valueSet : StudySet.values()) {

                if(valueSet == StudySet.TEST) continue;

                int answer = valueSet.getAnswer();
                int[][] input = valueSet.getInput();
                List<Neuron> neurons = percentron.count(input);
                // считаем ошибку для выходного слоя
                for (int i = 0; i < neurons.size(); i++) {
                    Neuron neuron = neurons.get(i);
                    double value = neuron.getValue();

                    double error = i == answer ? value - 1 : value - 0;
                    neuron.setErrorValue(error);
                }
                // считаем ошибку для скрытого слоя. ---------------------------
                for (Neuron neuron : percentron.getHiddenLayer().getNeurons()) {
                    double errSum = 0;
                    for (Weight outgoingWeight : neuron.getOutgoingWeights()) {
                        Neuron rightNeuron = outgoingWeight.getRightNeuron();
                        double weightValue = outgoingWeight.getValue();
                        double errorValue = rightNeuron.getErrorValue();
                        double value = rightNeuron.getValue();
                        double gradient = errorValue * value * (1 - value);
                        errSum += gradient * weightValue;
                    }
                    neuron.setErrorValue(errSum);
                }
                // корректируем веса. ---------------------------
                for (Neuron neuron : percentron.getEnterLayer().getNeurons()) {
                    double value = neuron.getValue();
                    for (Weight outgoingWeight : neuron.getOutgoingWeights()) {
                        double errorValue = outgoingWeight.getRightNeuron().getErrorValue();
                        double weightValue = outgoingWeight.getValue();
                        outgoingWeight.setValue(weightValue - value * errorValue * 0.1);
                    }
                }
                for (Neuron neuron : percentron.getHiddenLayer().getNeurons()) {
                    double value = neuron.getValue();
                    for (Weight outgoingWeight : neuron.getOutgoingWeights()) {
                        double errorValue = outgoingWeight.getRightNeuron().getErrorValue();
                        double weightValue = outgoingWeight.getValue();
                        outgoingWeight.setValue(weightValue - value * errorValue * 0.1);
                    }
                }
            }
        }

    }


}
