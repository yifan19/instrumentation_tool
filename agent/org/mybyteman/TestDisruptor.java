package org.mybyteman;
import java.nio.ByteBuffer;

public class TestDisruptor {

    public static void main(String[] args) throws Exception
    {
        DisruptorBuffer.init();

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (int l = 0; true; l++)
        {
            bb.putInt(0, l);
            DisruptorBuffer.put(bb.getInt(0));
            Thread.sleep(1000);
        }
    }
}