//package org.example;
//
//public class XorExample {
//    double[] enters;
//    double[] hidden;
//    double[][] wEH;
//    double[] wHO;
//
//    double[][] patterns = {
//            {0, 0},
//            {1, 0},
//            {0, 1},
//            {1, 1}
//    };
//
//    double[] answers = {0, 1, 1, 0};
//
//
//    public XorExample() {
//        enters = new double[patterns[0].length];
//        hidden = new double[2];
//        wEH = new double[enters.length][hidden.length];
//        wHO = new double[hidden.length];
//        init();
//        study();
//        for (int p = 0; p < patterns.length; p++) {
//            for (int i = 0; i < enters.length; i++) {
//                enters[i] = patterns[p][i];
//            }
//
//            System.out.println(countOuter());
//        }
//    }
//
//
//    public void init() {
//        for (int i = 0; i < wEH.length; i++) {
//            for (int j = 0; j < wEH[i].length; j++) {
//                wEH[i][j] = Math.random() * 0.3 + 0.1;
//            }
//        }
//        for (int i = 0; i < wHO.length; i++) {
//            wHO[i] = Math.random() * 0.3 + 0.1;
//        }
//    }
//
//
//    public double countOuter() {
//        for (int i = 0; i < hidden.length; i++) {
//            hidden[i] = 0;
//            for (int j = 0; j < hidden.length; j++) {
//                hidden[i] += enters[j] * wEH[j][i];
//            }
//            if (hidden[i] > 0.5) {
//                hidden[i] = 1;
//            } else {
//                hidden[i] = 0;
//            }
//        }
//        double outer = 0;
//        for (int i = 0; i < hidden.length; i++) {
//            outer += hidden[i] * wHO[i];
//        }
//        return outer > 0.5 ? 1 : 0;
//    }
//
//
//    public void study() {
//        double[] errors = new double[hidden.length];
//        double gError;
//
//        do {
//            gError = 0;
//            for (int p = 0; p < patterns.length; p++) {
//                for (int i = 0; i < enters.length; i++) {
//                    enters[i] = patterns[p][i];
//                }
//                double error = answers[p] - countOuter();
//                gError += Math.abs(error);
//                for (int i = 0; i < hidden.length; i++) {
//                    errors[i] = error * wHO[i];
//                }
//                for (int i = 0; i < enters.length; i++) {
//                    for (int j = 0; j < hidden.length; j++) {
//                        wEH[i][j] += 0.1 * errors[j] * enters[i];
//                    }
//                }
//                for (int i = 0; i < hidden.length; i++) {
//                    wHO[i] += 0.1 * error * hidden[i];
//                }
//            }
//        } while (gError != 0);
//
//    }
//
//
//    public static void main(String[] args) {
//        XorExample xorExample = new XorExample();
//    }
//
//}