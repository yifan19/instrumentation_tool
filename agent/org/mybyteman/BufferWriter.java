package org.mybyteman;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
// import java.util.HashMap;
// import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.nio.ByteBuffer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// import java.util.concurrent.locks;

public class BufferWriter {
    // A data structure that maps  Thread_id -> Array
    // static public Map <Long, ByteBuffer> bufMap = new HashMap<>();
    static public  ByteBuffer[] bufMap = new ByteBuffer[200];
    
    static private final ReadWriteLock readWriteLock
        = new ReentrantReadWriteLock();
    static private final Lock readLock = readWriteLock.readLock();
    static private final Lock writeLock = readWriteLock.writeLock();

    static public void put(int data) {
        Thread curthread = Thread.currentThread();
        int tid = (int)curthread.getId();
        ByteBuffer buf;
        readLock.lock();
        // synchronized(bufMap) {
            buf = bufMap[tid];
            if (buf == null){
                // allocate a 10kB buffer
                buf = ByteBuffer.allocate(1024*10);
                bufMap[tid] = buf;
            }
        readLock.unlock();
        // }
        /* resetting (ring buffer effect) */
        /* assume for now integers */
        if (buf.position() + 4 >  buf.capacity()) {
            buf.position(0);
            buf.mark();
        }
        buf.putInt(data);
    }
    static public void print() {
        long tid = Thread.currentThread().getId();
        // for (Map.Entry<Long, ByteBuffer> entry : bufMap.entrySet()) {
        //     System.out.println("Key = " + entry.getKey() +
        //                         ", Value = " + entry.getValue());
        // }
        for (ByteBuffer entry : bufMap){
            System.out.println(entry);
        }
    }



}
