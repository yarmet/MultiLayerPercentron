package org.example.newtest.main;

import org.example.newtest.Percentron;
import org.example.newtest.layer.Layout;
import org.example.newtest.point.Neuron;
import org.example.newtest.sets.StudySet;
import org.example.newtest.study.StudyPercentron;

import java.util.List;


public class SecondMain {


    public static void main(String[] args) {


        Layout enterLayer = new Layout(15);
        Layout hiddenLayer = new Layout(15);
        Layout outLayout = new Layout(10);

        Percentron percentron = Percentron.build(enterLayer, outLayout, hiddenLayer);

        StudyPercentron.study(percentron);

        getAnswer(percentron, StudySet.SIX);
        getAnswer(percentron, StudySet.FOUR);
        getAnswer(percentron, StudySet.ONE);
        getAnswer(percentron, StudySet.EIGHT);
        getAnswer(percentron, StudySet.TWO);
        getAnswer(percentron, StudySet.ZERO);
        getAnswer(percentron, StudySet.FIVE);
        getAnswer(percentron, StudySet.TEST);

    }


    private static void getAnswer(Percentron percentron, StudySet studySet) {
        List<Neuron> neurons = percentron.count(studySet.getInput());
        double res = 0;
        int pos = -1;
        for (int i = 0; i < neurons.size(); i++) {
            Neuron neuron = neurons.get(i);
            double value = neuron.getValue();

            if (value > res) {
                pos = i;
                res = value;
            }
        }
        System.out.println("val " + pos + "   value " + res + "  right ? " + (pos == studySet.getAnswer()));
    }

}
