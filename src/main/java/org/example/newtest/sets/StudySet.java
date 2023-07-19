package org.example.newtest.sets;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StudySet {

    ZERO(Digits.ZERO, 0),
    ONE(Digits.ONE, 1),
    TWO(Digits.TWO, 2),
    THREE(Digits.THREE, 3),
    FOUR(Digits.FOUR, 4),
    FIVE(Digits.FIVE, 5),
    SIX(Digits.SIX, 6),
    SEVEN(Digits.SEVEN, 7),
    EIGHT(Digits.EIGHT, 8),
    NINE(Digits.NINE, 9),


    TEST(Digits.TEST, 100);

    private final int[][] input;
    private final int answer;
}
