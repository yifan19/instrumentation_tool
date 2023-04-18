package org.mybyteman;

import java.lang.instrument.Instrumentation;


public class SimpleAgent {
    public static void premain(String argument, 
                             Instrumentation instrumentation) {
        // DisruptorBuffer.startServer();
        MoodyCamelBuffer.init();
        // DisruptorBuffer.init();
        // MultiDisruptorBuffer.init();
        instrumentation.addTransformer(new MyTransformer());

    }
}
