package org.mybyteman;

import com.lmax.disruptor.EventFactory;

public class IntegerEventFactory implements EventFactory<IntegerEvent>
{
    @Override
    public IntegerEvent newInstance()
    {
        return new IntegerEvent();
    }
}
