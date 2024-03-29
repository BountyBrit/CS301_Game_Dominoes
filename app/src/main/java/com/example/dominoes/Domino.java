package com.example.dominoes;

/** Domino
 * This class has all of the relevant information for a domino
 * The end1 and end2 are the sides of the dominoes with numbers/dots on them
 *
 * @author britdannen
 * @author Alejandro Varela
 * @author Jackson Smith
 *
 */
public class Domino {
    private int end1;
    private int end2;

    /**
     * Sets end1 and end2 for dominos
     *
     * @param end1
     * @param end2
     */
    public Domino(int end1, int end2) {
        this.end1 = end1;
        this.end2 = end2;
    }

    /**
     * Getters and setters for Domino below
     *
     * @return
     */
    public int getEnd1() {
        return end1;
    }

    public int getEnd2() {
        return end2;
    }

    public void setEnd1(int end1) {
        this.end1 = end1;
    }

    public void setEnd2(int end2) {
        this.end2 = end2;
    }

    /** toString
     * Returns end2 and end1 has a string.
     *
     * @return
     */
    @Override
    public String toString() {
        if (end1 == -1){
            return "";
        } else {
            return "[" + end1 + "|" + end2 + "]";
        }
    }

}
