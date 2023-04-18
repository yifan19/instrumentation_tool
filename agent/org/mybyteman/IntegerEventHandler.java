package org.mybyteman;

import com.lmax.disruptor.EventHandler;

public class IntegerEventHandler implements EventHandler<IntegerEvent>
{
    @Override
    public void onEvent(IntegerEvent event, long sequence, boolean endOfBatch)
    {
        // System.out.println("Event#" + sequence + ": " + event);
        // DisruptorBuffer.sendToClient(event.value);
        event.clear();
    }
}