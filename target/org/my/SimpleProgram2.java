package org.my;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

// import org.my.Iteration;

// C1 C2 instrumetation based on 
//https://www.youtube.com/watch?v=JLFjY6Ixct8&t=2145s

public class SimpleProgram2 extends Thread{
    static int blackhole;
    int counter;

    public int numRounds;
    public int numIter;
    public Iteration[] loops_timer;

    public SimpleProgram2(String name, Iteration[] loops_timer, int numRounds, int numIter) {
        super(name);
        this.loops_timer = loops_timer;
        this.numRounds = numRounds;
        this.numIter = numIter;
    }

    public void benchmark(Iteration loop, int numIter) {
        loop.startTimeMs = System.currentTimeMillis();
        long loop_start = System.currentTimeMillis();
        while(counter < numIter) {
            counter++;
            // iter[i].startTimeMs = System.currentTimeMillis();
            // long startNanos = System.nanoTime();
            // the testing is done here
            // inc();
            // long endNanos = System.nanoTime();
            
            // iter[i].durationNs = endNanos - startNanos;
            // Collections.shuffle(Arrays.asList(nums));
        }
        loop.durationNs = System.currentTimeMillis() - loop_start;
        // System.out.println(c);
    }
    
    public void run()
    {
        // System.out.println(Thread.currentThread().getName());

        for (int i = 0; i < numRounds; i++) {
            counter = 0;
            benchmark(this.loops_timer[i], this.numIter);
        }
    }

    public static void main(String[] args) {
        long total_start_time = System.currentTimeMillis();
        Random random = new Random();
        // Iteration[] iter = new Iteration[Integer.parseInt(args[0])];
        int numThreads = Integer.parseInt(args[0]);
        int numRounds = Integer.parseInt(args[1]);
        int numIter = Integer.parseInt(args[2]);
        Thread[] threads = new Thread[numThreads];

        
        for (int i = 0; i < threads.length; i++) {
            Iteration[] loops = new Iteration[numRounds];
            for (int j = 0; j < loops.length; j++) {
                loops[j] = new Iteration();
            }
            threads[i] = new SimpleProgram2( ("counter#" + i), loops, numRounds, numIter);
            threads[i].start();   
        }

        try{
            for (int i = 0; i < threads.length; i++) {
                threads[i].join();
            }
        } catch(Exception ex) {
            System.out.println("Exception has " +
                                "been caught" + ex);
        }
        
        System.out.println("total_time:" + -1*(total_start_time  - System.currentTimeMillis()));
        // System.out.println(loop.durationNs);
        for (int j = 0; j < ((SimpleProgram2) threads[0]).loops_timer.length; j++) {
            for (int i = 0; i < threads.length; i++) {
                System.out.print(((SimpleProgram2) threads[i]).loops_timer[j].durationNs);
                System.out.print(", ");
            }
            System.out.println("");
        }


    }
  
}