package com.tpm.tema1;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

public class RationsResourcePool {

    private final int rationCapacity;
    private final ConcurrentLinkedQueue availableRations;
    private ReentrantLock lock;
    private int counter;

    public RationsResourcePool(int numberOfRations) {

        availableRations = new ConcurrentLinkedQueue();
        rationCapacity = numberOfRations;
        counter = 0;

        for (int i = 0; i < rationCapacity; i++) {
            Ration currentRation = new Ration();
            availableRations.add(currentRation);
        }
    }

    public Ration getRation(int memberNo) {

        // System.out.println("\n\n number of rations" + availableRations.toString() + "\n\n");

        if (availableRations.isEmpty() && memberNo == counter) {
            cook();
            if (counter == rationCapacity)
                counter = 0;
            else
                counter++;
        }

        return (Ration) availableRations.poll();
    }

    public void cook() {
        for (int i = 0; i < rationCapacity; i++) {
            Ration currentRation = new Ration();
            availableRations.add(currentRation);
        }
    }
}
