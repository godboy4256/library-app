package com.group.libraryapp.controller.caculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
// API 진입 지점임을 선언하는 어노테이션

public class CaculatorController {
    @GetMapping("/add") // GET / add
    public int addTwoNumbers(
            CalculatorAddRequest request
    ){
        return  request.getNum1() + request.getNum2();
    }

    @PostMapping("/multiply") // POST / multiply
    public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request){
        return request.getNum1() * request.getNum2();
    }

}


