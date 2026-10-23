package com.example.calculator.controller;

import com.example.calculator.model.BmiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin; // <-- Import this

@RestController
@CrossOrigin(origins = "*") // <-- Add this annotation to fetch data from seperate frontend and backend
public class BmiController {

    @GetMapping("/api/bmi")
    public BmiResponse calculateBmi(
            @RequestParam double weight, // Weight in kilograms (kg)
            @RequestParam double height  // Height in meters (m)
    ) {
        if (height <= 0 || weight <= 0) {
            throw new IllegalArgumentException("Weight and height must be greater than zero.");
        }

        // Calculate BMI: weight (kg) / [height (m)]^2
        double bmi = weight / (height * height);
        String bodyType = determineBodyType(bmi);

        // Round BMI to 2 decimal places
        double roundedBmi = Math.round(bmi * 100.0) / 100.0;

        return new BmiResponse(roundedBmi, bodyType);
    }

    private String determineBodyType(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 25.0) {
            return "Normal weight";
        } else if (bmi >= 25.0 && bmi < 30.0) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}