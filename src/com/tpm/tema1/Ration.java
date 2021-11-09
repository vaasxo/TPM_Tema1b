package com.tpm.tema1;

public class Ration {

    // public int numberOfTimesIateWithThisSpoon;

    public Ration() {
        // numberOfTimesIateWithThisSpoon = 0;
    }

    public void eat(int memberNo) {
        // numberOfTimesIateWithThisSpoon++;
        System.out.println("Tribe member " + memberNo + "  eating");
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void cook(){
        System.out.println("Cook is cooking");
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
