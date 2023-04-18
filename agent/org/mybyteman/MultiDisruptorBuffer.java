package org.mybyteman;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
// import java.util.HashMap;
// import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import com.lmax.disruptor.dsl.MultiDisruptor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;

import java.net.*;
import java.io.*;

public class MultiDisruptorBuffer {
    static public MultiDisruptor<IntegerEvent> disruptor;

    static public void init() {
        disruptor = new MultiDisruptor<>
            (IntegerEvent::new,
             1024,
             DaemonThreadFactory.INSTANCE,
             ProducerType.SINGLE,
             new BlockingWaitStrategy());

        // consumer behavior
        // disruptor.handleEventsWith(new IntegerEventHandler());
        // disruptor.start();

        // ringBuffer = disruptor.getRingBuffer();
        // producer = new IntegerEventProducer(ringBuffer);
    } 

    static public void startServer() {
    }

    static public void sendToClient(int data) {
    }

    static public void put(int data) {
        RingBuffer<IntegerEvent> ringBuffer = disruptor.getRingBuffer();

        long sequence = ringBuffer.next();
        try
        {
            IntegerEvent e = ringBuffer.get(sequence);
            e.set(data);
            // Do some work with the event.
        }
        finally
        {
            ringBuffer.publish(sequence);
        }
        
    }
    static public void printAll() {
        disruptor.getStats();
        
        
    }


}
