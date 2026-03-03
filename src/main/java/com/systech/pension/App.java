package com.systech.pension;


public class App {
    // Employee contributes 6% of salary, employer 6%
    public static double calculateEmployeeContribution(double salary) {
        return salary * 0.06;
    }

    public static double calculateEmployerContribution(double salary) {
        return salary * 0.06;
    }

    public static double calculateTotalContribution(double salary) {
        return calculateEmployeeContribution(salary) 
             + calculateEmployerContribution(salary);
    }

    public static void main(String[] args) {
        double salary = 50000;
        System.out.println("=== Systech Pension Calculator ===");
        System.out.println("Salary: KES " + salary);
        System.out.println("Employee Contribution: KES " 
            + calculateEmployeeContribution(salary));
        System.out.println("Employer Contribution: KES " 
            + calculateEmployerContribution(salary));
        System.out.println("Total Monthly Contribution: KES " 
            + calculateTotalContribution(salary));
    }
}
