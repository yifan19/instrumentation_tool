package org.mybyteman;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
// import java.util.HashMap;
// import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;

import java.net.*;
import java.io.*;

public class DisruptorBuffer {
    static public Disruptor<IntegerEvent> disruptor;
    static public RingBuffer<IntegerEvent> ringBuffer;
    static public IntegerEventProducer producer;
    static private Socket socket;
    static private ServerSocket server;
    static private PrintWriter out;
    static private OutputStream outStream;

    static public void init() {
        disruptor = new Disruptor<>
            (IntegerEvent::new,
             1024,
             DaemonThreadFactory.INSTANCE,
            //  ProducerType.SINGLE,
             ProducerType.MULTI,
             new BlockingWaitStrategy());

        // consumer behavior
        // disruptor.handleEventsWith(new IntegerEventHandler());
        disruptor.start();

        ringBuffer = disruptor.getRingBuffer();
        producer = new IntegerEventProducer(ringBuffer);
    } 

    static public void startServer() {
        try {
            server = new ServerSocket(8091);
            socket = server.accept();
            outStream = socket.getOutputStream();
            // out = new PrintWriter(socket.getOutputStream(), true);

            // out.println("Hello");
        } catch (IOException e) {
            System.out.println("Server Error");
        }
    }

    static public void sendToClient(int data) {
        ByteBuffer bb = ByteBuffer.allocate(4);
        try {
            bb.putInt(data);
            outStream.write(bb.array());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static public void put(int data) {
        producer.onData(data);
    }
    static public void printAll() {
        System.out.println("remaining cap: " + ringBuffer.remainingCapacity());
        System.out.println(ringBuffer.getCursor());
        System.out.println(ringBuffer.next());
        
    }


}
