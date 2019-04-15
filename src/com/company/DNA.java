package com.company;

import java.util.Random;
import java.util.regex.Pattern;

public class DNA {
    /*
        Genes string contains the characters or shit
     */
    private String genes;
    /*
    Length of genes string
     */
    private int length;
    /*
        DNA's fitness value
     */
    private int fitness;
    public static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz,.;!<>\"'";
    private float mutationRate;
    public DNA(int length){
        this.genes = new String();
        this.length = length;
        this.fitness = 0;
        this.mutationRate = 0.01f;

        this.init();

    }

    public DNA(String genes) {
        this.genes = genes;
        this.length = genes.length();
        this.fitness = 0;
        this.mutationRate = 0.01f;
    }

    /*
        Initializes the genes string with random characters
     */
    private void init() {
        Random rand = new Random();
        for (int i = 0; i < this.length; i++) {
            genes+= alphabet.charAt(rand.nextInt(alphabet.length()));
        }
    }



    //---------------------------------Getters & Setters------------------
    public int getLength() {
        return this.length;
    }

    public int getFitness() {
        return this.fitness;
    }
    /*
        @return genes
     */
    @Override
    public String toString() {
        return genes;
    }



    //-------------------------------Important methods----------------------------------------------

    public void calculateFitness(String goal) {
        for (int i = 0; i < this.length; i++) {
            if (genes.charAt(i) == goal.charAt(i)) {
                this.fitness++;

            }
        }

    }
    /*
        Mutating genes is essentially replacing 1 character in genes with a random character
        Uses @replaceChar
     */
    public void mutateGenes() {
        Random rand = new Random();
        for (int i = 0; i < this.length; i++) {
            if (rand.nextFloat() < this.mutationRate) {
                genes = this.replaceChar(genes,alphabet.charAt(rand.nextInt(alphabet.length())),i);
            }
        }
    }

    /*
        Replaces the character of a string @mystr at index @index with a character @mychar
     */
    private String replaceChar(String mystr, char mychar, int index) {

        String temp = "";
        for (int i = 0; i < mystr.length(); i++) {
            if (i == index) {
                temp+=mychar;
            }else
                temp += mystr.charAt(i);
        }
        return temp;
    }


    public DNA makeLove(DNA p2) {
        int midPoint = this.genes.length()/2;
        String newDNA = "";
        for (int i = 0; i < this.genes.length(); i++) {
            if(i<midPoint)
                newDNA += this.genes.charAt(i);
            else
                newDNA += p2.toString().charAt(i);
        }
        return new DNA(newDNA);
    }
    public DNA makeLove2(DNA p2,String goal) {
        String mating = "";
        String newDNA = "";
        for (int i = 0; i < this.genes.length(); i++) {
            if(genes.charAt(i) == goal.charAt(i))
                mating+="1";
            else
                mating += "0";
        }
        for (int i = 0; i < this.genes.length(); i++) {
            if(mating.charAt(i)=='1')
                newDNA += this.genes.charAt(i);
            else
                newDNA += p2.toString().charAt(i);
        }
        return new DNA(newDNA);
    }

    public boolean reachedGoal(String goal) {
        for (int i = 0; i < this.length; i++) {
            if(genes.charAt(i) != goal.charAt(i)){
                return false;
            }
        }

        return true;
    }
}
