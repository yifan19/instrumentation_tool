package org.mybyteman;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

public class IntegerEventProducer
{
    private final RingBuffer<IntegerEvent> ringBuffer;

    public IntegerEventProducer(RingBuffer<IntegerEvent> ringBuffer)
    {
        this.ringBuffer = ringBuffer;
    }

    public void onData(int val)
    {
        long sequence = ringBuffer.next(); 
        try
        {
            IntegerEvent event = ringBuffer.get(sequence); 
            event.set(val);  
        }
        finally
        {
            ringBuffer.publish(sequence);
        }
    }
}