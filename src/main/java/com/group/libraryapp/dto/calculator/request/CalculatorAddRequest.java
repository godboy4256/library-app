package com.group.libraryapp.dto.calculator.request;

public class CalculatorAddRequest {
    private final int num1;
    private final int num2;

    public CalculatorAddRequest(int num2, int num1) {
        this.num2 = num2;
        this.num1 = num1;
    }

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }
}
