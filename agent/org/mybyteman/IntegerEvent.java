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

// import java.util.concurrent.locks;

public class IntegerEvent
{
    private long tid;
    public int value;

    public void set(int value)
    {
        this.value = value;
        this.tid = Thread.currentThread().getId();
    }

    public void clear() {
        this.value = 0;
        this.tid = 0xDEADBEAF;
    }

    @Override
    public String toString()
    {
        return "IntegerEvent{" + "tid=" + tid + ", " + "value=" + value + '}';
    }
}