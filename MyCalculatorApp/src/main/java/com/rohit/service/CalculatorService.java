package com.rohit.service;

import org.springframework.stereotype.Service;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

@Service
public class CalculatorService 
{
    public String evaluateExpression(String input) 
    {
        try 
        {
            Expression expression = new ExpressionBuilder(input).build();
            double result = expression.evaluate();
            return String.valueOf(result);
        }
        catch (Exception e) 
        {
            return "Invalid Expression";
        }
    }
}
