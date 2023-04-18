package org.my;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

// import org.my.Iteration;

// C1 C2 instrumetation based on 
//https://www.youtube.com/watch?v=JLFjY6Ixct8&t=2145s

public class SimpleProgram extends Thread{
    static int blackhole;
    static int counter = 0;

    public static void benchmark(Iteration loop, int numIter) {
        loop.startTimeMs = System.currentTimeMillis();
        while(counter < numIter) {
            counter = counter + 1;

        }
        loop.durationNs = System.currentTimeMillis() - loop.startTimeMs;
    }

    public static void main(String[] args) {
        Random random = new Random();
        // Iteration[] iter = new Iteration[Integer.parseInt(args[0])];
        int numIter = Integer.parseInt(args[1]);
        int numRounds = Integer.parseInt(args[0]);
        int[] nums = new int[200000];
        Iteration[] loops = new Iteration[numRounds];
        for (int i = 0; i < 200000; i++) {
            nums[i] = random.nextInt();
        }
        for (int i = 0; i < loops.length; i++) {
            loops[i] = new Iteration();
        }
        
        for (int i = 0; i < numRounds; i++) {
            counter = 0;
            benchmark(loops[i], numIter);
        }
        // System.out.println(loop.durationNs);
        for (Iteration i: loops) {
            System.out.println(i.durationNs);
        }
        System.out.println(counter);


    }
    public static void inc() {
        counter++;
    }
    public static int sum(int[] a) {
        counter++;
        int ret = 0;
        for (int i = 0; i < a.length; i++) {
            ret += a[i];
        }
        return ret;

    }
}