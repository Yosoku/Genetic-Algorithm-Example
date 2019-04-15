package com.company;
import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

public class Simulation {
    private Population population;
    private String goal;

    public Simulation(String goal,int populationSize) {
        this.goal = goal;
        population = new Population(populationSize,goal.length());
    }

    public void run() {
        while (!population.reachedGoal(goal) && population.getGeneration()<100000) {
            population.calculateFitness(goal);

            population.naturalSelection2(goal);
            population.mutatePopulation();
            Log.log("Current best sample : " + population.getBest().toString());
        }
        population.calculateFitness(goal);
        Log.log("Solution found in " + population.getGeneration()+" generations.");
        Log.log(population.getBest().toString());
    }

    public static void main(String[] args) {
        String goal;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a phrase.Recognizable characters are : " + DNA.alphabet + "\n\n>");
        goal = sc.nextLine();
        Simulation simulation = new Simulation(goal,200);
        simulation.run();
    }

}


