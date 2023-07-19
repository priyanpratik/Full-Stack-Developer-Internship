/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.multipleObjects;

/**
 *
 * @author coder5
 */
import java.io.*;
public class ExternalArray {

    int size;
    int lastIndex;
    RandomAccessFile file = null;

    public ExternalArray(int size, String path) {
        this.size = size;
        this.lastIndex = -1;
        try {
            file = new RandomAccessFile(path, "rw");
            for (int i = 0; i < size; i++) {
                file.seek(getLoc(i));
                file.writeLong(0);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void put(int ind, int val) {
        if (ind < 0 || ind >= size) {
            System.out.println("IndexOutOfBoundException");
            return;
        }
        try {
            file.seek(getLoc(ind));
            file.writeLong(val);
            if (ind > lastIndex) {
                lastIndex = ind;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void append(int val) {
        if (lastIndex >= size) {
            System.out.println("IndexOutOfBoundException");
            return;
        }
        put(lastIndex+1, val);
    }

    public void forceAppend(int val) {
        size++;
        put(lastIndex+1, val);
    }

    public int get(int ind) {
        int ans = -1;
        try {
            file.seek(getLoc(ind));
            ans = (int)file.readLong();
        } catch (Exception e) {
            return -1;
        }
        return ans;
    }

    private int getLoc(int ind) {
        return 8 * ind;
    }

    public void closeXArray() {
        try {
            file.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
