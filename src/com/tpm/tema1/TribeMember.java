package com.tpm.tema1;

import java.util.concurrent.locks.ReentrantLock;

public class TribeMember implements Runnable {

    private final RationsResourcePool rationsResourcePool;
    private final boolean isCook;
    private boolean isDone;
    private final ReentrantLock lock = new ReentrantLock();
    private final int memberNo;
    private int timesEaten;
    private int timesCooked;

    TribeMember(RationsResourcePool rationsResourcePool, boolean isCook, int memberNo) {
        this.rationsResourcePool = rationsResourcePool;
        this.isCook = isCook;
        this.memberNo = memberNo;
    }

    @Override
    public void run() {
        while (timesEaten < 10) {
            try {
                lock.lock();
                Ration ration = rationsResourcePool.getRation(memberNo);

                if (ration != null) {
                    if (isCook) {
                        System.out.println("Cook is cooking");
                        try {
                            ration.cook();
                            if(timesCooked == 19)
                                return;
                        } finally {
                            exit();
                        }
                    } else {
                        try {
                            ration.eat(memberNo);
                            timesEaten += 1;
                        } finally {
                            exit();
                        }
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

    private void exit() {
        isDone = true;
    }
}
