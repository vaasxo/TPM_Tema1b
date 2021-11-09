package com.tpm.tema1;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

public class RationsResourcePool {

    private final int rationCapacity;
    private final ConcurrentLinkedQueue availableRations;
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

    public synchronized Ration getRation(int memberNo) {

        boolean hasEaten = false;
        // System.out.println("\n\n number of rations" + availableRations.toString() + "\n\n");
        try {
            if (availableRations.isEmpty()) {
                cook();
            }
            if (memberNo == counter) {
                hasEaten = true;
            }
            if (hasEaten) {
                return (Ration) availableRations.poll();
            }
        } finally {
            if (counter == 9 && hasEaten)
                counter = 0;
            else if (hasEaten)
                counter++;
        }

        return null;
    }

    public synchronized void cook() {
        for (int i = 0; i < rationCapacity; i++) {
            Ration currentRation = new Ration();
            availableRations.add(currentRation);
        }
    }
}
