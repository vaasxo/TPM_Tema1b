package com.tpm.tema1;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Instant start = Instant.now();

        final int TribeMembers = 10;
        final int AvailableRations = 5;

        List<Future<?>> futures = new ArrayList<>();
        final ExecutorService executorService = Executors.newFixedThreadPool(TribeMembers);

        final RationsResourcePool rationsResourcePool = new RationsResourcePool(AvailableRations);

        Future<?> f = executorService.submit(new TribeMember(rationsResourcePool, true, 9));
        futures.add(f);

        for (int i = 0; i < TribeMembers; i++) {
            f = executorService.submit(new TribeMember(rationsResourcePool, false, i));
            futures.add(f);
        }

        Thread.sleep(100);

        boolean allDone = true;
        for (Future<?> future : futures) {
            allDone &= future.isDone();
        }

        Instant end = Instant.now();
        System.out.print(Duration.between(start, end));

        if (allDone) {
            System.exit(1);
        } else {
            System.exit(2);
        }
    }
}
