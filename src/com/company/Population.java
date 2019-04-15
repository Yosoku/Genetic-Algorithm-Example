package com.company;

import java.util.Random;

public class Population {
    private DNA pop[];
    private int size;
    private int generation;

    public Population(int size,int length) {
        this.size = size;
        pop = new DNA[size];
        for (int i = 0; i < this.size; i++) {
            pop[i] = new DNA(length);
        }
        generation = 0;
    }


    public int getSize() {
        return this.size;
    }

    public DNA[] getPop() {
        return this.pop;
    }

    public int getGeneration() {
        return this.generation;
    }
    public void calculateFitness(String goal) {
        for (int i = 0; i < this.size; i++) {
            pop[i].calculateFitness(goal);
        }
    }

    public void mutatePopulation() {
        for (int i = 0; i < this.size; i++) {
            pop[i].mutateGenes();
        }
    }


    public DNA selectParent() {
        int fitSum = this.getFitnessSum();
        int runningSum = 0;
        Random rand = new Random();
        int myrand = rand.nextInt(fitSum);
        for (int i = 0; i < this.size; i++) {
            runningSum+=pop[i].getFitness();
            if ( myrand < runningSum) {
                return pop[i];
            }
        }
        return null; //Will never get here
    }

    private int getFitnessSum() {
        int sum = 0;
        for (int i = 0; i < this.size; i++) {
            sum+=pop[i].getFitness();
        }
        return sum;
    }

    public void naturalSelection() {
        DNA temp[] =new DNA[size];
        for (int i = 0; i < size; i++) {
            DNA parent1 = selectParent();
            DNA parent2 = selectParent();
            temp[i] = parent1.makeLove(parent2);
        }
        this.generation++;
        pop = temp;
    }

    public void naturalSelection2(String goal) {
        DNA temp[] =new DNA[size];
        for (int i = 0; i < size; i++) {
            DNA parent1 = selectParent();
            DNA parent2 = selectParent();
            temp[i] = parent1.makeLove2(parent2,goal);
        }
        this.generation++;
        pop = temp;
    }

    public DNA getBest() {

        int max = -1;
        int index = -1;
        for (int i = 0; i < this.size; i++) {
            if(pop[i].getFitness()>max){
                max = pop[i].getFitness();
                index = i;
            }
        }
        return pop[index];
    }

    public boolean reachedGoal(String goal) {
        for (int i = 0; i < this.size; i++)
            if (pop[i].reachedGoal(goal))
                return true;
        return false;
    }
}
