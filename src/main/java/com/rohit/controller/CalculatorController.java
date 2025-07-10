package com.rohit.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.rohit.service.CalculatorService;

@Controller
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/")
    public String home(Model model) {
        // if no flash attribute exists, set default values
        if (!model.containsAttribute("expression")) {
            model.addAttribute("expression", "");
        }
        if (!model.containsAttribute("result")) {
            model.addAttribute("result", "");
        }
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam("expression") String expression, RedirectAttributes redirectAttributes) {
        try {
            String preprocessed = preprocessExpression(expression);
            String rawResult = calculatorService.evaluateExpression(preprocessed);

            // Format result: Remove ".0" if it's a whole number
            String formattedResult = rawResult;
            if (rawResult.matches("\\d+\\.0")) {
                formattedResult = rawResult.substring(0, rawResult.indexOf("."));
            }

            redirectAttributes.addFlashAttribute("expression", formattedResult); // show result in input box
            redirectAttributes.addFlashAttribute("result", formattedResult);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("expression", expression);
            redirectAttributes.addFlashAttribute("result", "Error");
        }

        return "redirect:/";
    }


    private String preprocessExpression(String expression) {
        // Pattern: A + B% → A + (A * B / 100)
        Pattern pattern = Pattern.compile("(\\d+(\\.\\d+)?)\\s*([+\\-*/])\\s*(\\d+(\\.\\d+)?)%");
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            String a = matcher.group(1);
            String operator = matcher.group(3);
            String b = matcher.group(4);

            String replacement = switch (operator) {
                case "+" -> a + " + (" + a + " * " + b + " / 100)";
                case "-" -> a + " - (" + a + " * " + b + " / 100)";
                case "*" -> a + " * (" + b + " / 100)";
                case "/" -> a + " / (" + b + " / 100)";
                default -> matcher.group();
            };

            expression = matcher.replaceFirst(replacement);
            matcher = pattern.matcher(expression);
        }

        // Replace 10% → (10/100)
        expression = expression.replaceAll("(\\d+(\\.\\d+)?)%", "($1/100)");
        return expression;
    }
}
