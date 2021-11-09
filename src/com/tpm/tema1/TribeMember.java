package com.tpm.tema1;

import java.util.concurrent.locks.ReentrantLock;

public class TribeMember implements Runnable {

    private final RationsResourcePool rationsResourcePool;
    private final boolean isCook;
    private boolean isDone;
    private final ReentrantLock lock = new ReentrantLock();
    private final int memberNo;

    TribeMember(RationsResourcePool rationsResourcePool, boolean isCook, int memberNo) {
        this.rationsResourcePool = rationsResourcePool;
        this.isCook = isCook;
        this.memberNo = memberNo;
    }

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                Ration ration = rationsResourcePool.getRation(memberNo);

                if (isCook) {
                    System.out.println("Cook is cooking");
                    try {
                        ration.cook();
                    } finally {
                        exit();
                    }
                } else {
                    try {
                        ration.eat(memberNo);
                    } finally {
                        exit();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private void exit() {
        isDone = true;
    }
}
