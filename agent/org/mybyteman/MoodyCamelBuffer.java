package org.mybyteman;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
// import java.util.HashMap;
// import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import java.nio.ByteBuffer;

import java.net.*;
import java.io.*;

public class MoodyCamelBuffer {
    
    static {
        System.loadLibrary("native");
    }

    static public void init() {
    } 

    static public void startServer() {
    }

    static public void sendToClient(int data) {
    }

    static public native void put(int data);
    static public native void printAll();

}
