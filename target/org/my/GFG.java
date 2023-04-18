package org.my;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


public class GFG extends Thread {
    // initiated run method for Thread
    @Override
    public void run()
    {   
        System.out.println(Thread.currentThread().getName());
        System.out.println("Thread Started Running...");
    }
    public static void main(String[] args)
    {
        GFG g1 = new GFG();
 
        // Invoking Thread using start() method
        g1.start();
    }
}