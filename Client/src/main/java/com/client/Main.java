package com.client;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        int rCount = Integer.parseInt(args[0]);
        int wCount = Integer.parseInt(args[1]);
        String url = args[2];
        List<Integer> IDs = Arrays.stream(args[3].split(",")).flatMap(s -> Stream.of(Integer.parseInt(s))).collect(Collectors.toList());


        ThreadEngineImpl threadEngine = new ThreadEngineImpl(rCount, wCount, IDs, url);

        ExecutorService rService = Executors.newFixedThreadPool(rCount);
        ExecutorService wService = Executors.newFixedThreadPool(wCount);

        while (true) {
            try {
                Thread.sleep(11);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rService.execute(threadEngine.createAddRequest());
            wService.execute(threadEngine.createGetRequest());
        }
    }

}


