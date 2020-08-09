package ru.koryakin.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static ru.koryakin.springcourse.controllers.SimpleMathOperation.*;

@Controller
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "surname", required = false) String surname,
            Model model
    ) {
        model.addAttribute("message", "Name: " + name + "\nSurname: " + surname);
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodByePage() {
        return "first/goodbye";
    }

    @GetMapping("/calculator")
    public String calculator(
            @RequestParam(value = "a", required = false, defaultValue = "0") int a,
            @RequestParam(value = "b", required = false, defaultValue = "0") int b,
            @RequestParam(value = "action", required = false, defaultValue = "EMPTY") SimpleMathOperation action,
            Model model
    ) {
        StringBuilder result = new StringBuilder();
        switch (action) {
            case ADDITION:
                result.append("Result of additional operation: ").append(a).append(" + ").append(b).append(" = ").append(a + b);
                break;
            case SUBTRACTION:
                result.append("Result of subtraction operation: ").append(a).append(" - ").append(b).append(" = ").append(a - b);
                break;
            case MULTIPLICATIONS:
                try {
                    result.append("Result of multiplications operation: ").append(a).append(" * ").append(b).append(" = ").append(a * b);
                } catch (ArithmeticException exc) {
                    result.append("Умножение на ноль запрещено.");
                }
                break;
            case DIVISION:
                try {
                    result.append("Result of division operation: ").append(a).append(" / ").append(b).append(" = ").append(a / (double) b);
                } catch (ArithmeticException exc) {
                    result.append("Деление на ноль запрещено.");
                }
                break;
            case EMPTY:
                result.append("Operation is not initialized.");
                break;
            default:
                result.append("This operation is not supported.");
                break;
        }
        model.addAttribute("message", result);
        return "first/calculator";
    }

}
