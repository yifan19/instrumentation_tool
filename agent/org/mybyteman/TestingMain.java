package org.mybyteman;


class TestingMain extends Thread {
    @Override
    public void run()
    {
        // System.out.println("Welcome to GeeksforGeeks.");
        BufferWriter.put(10);
    }
    public static void main(String[] args)
    {
        TestingMain[] g = new TestingMain[100];
        for (int i = 0; i < 100; i++) {
            g[i] = new TestingMain();
            // System.out.println("Welcome to GeeksforGeeks.");

            g[i].start(); // starting thread
        }
        try{
            for (int i = 0; i < 100; i++) {
                g[i].join();
            }
        } catch(Exception ex) {
            System.out.println("Exception has " +
                                "been caught" + ex);
        }
        
        BufferWriter.print();
    }
}