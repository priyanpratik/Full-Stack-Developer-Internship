/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template indexFile, choose Tools | Templates
 * and open the template in the editor.
 */
package com.multipleObjects;

/**
 *
 * @author coder5
 */
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExternalObjectArray {

    private static byte[] convertToByteArray(Object obj) {
        byte[] b1 = {};
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(obj);
            b1 = bos.toByteArray();
            bos.close();
            out.close();
        } catch (Exception e) {
            
        }
        return b1;
    }

    private static Object byteArrayToObject(byte[] b) {
        Object obj = null;
        if (b.length == 0) {
            return obj;
        }
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(b);
            ObjectInputStream in = new ObjectInputStream(bis);
            obj = in.readObject();
            in.close();
            bis.close();
        } catch (IOException ioe) {
        } catch (ClassNotFoundException ex) {
        }
        return obj;
    }
    /////////////////////////////////////////////////////////////////////////////////////////

    private long size;
    long lastIndex;
    RandomAccessFile indexFile = null;
    RandomAccessFile objectFile = null;

    public long getSize() {
        return size;
    }

    public ExternalObjectArray(long size, String path, String fileName) {
        this.size = size;
        this.lastIndex = -1;
        try {
            indexFile = new RandomAccessFile(path + fileName + ".idx", "rw");
            objectFile = new RandomAccessFile(path + fileName + ".dat", "rw");

            for (int i = 0; i < this.size; i++) {
                initIndexLoc(i);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    
    private void initIndexLoc(int idx) throws IOException {
        long idxLoc = getIndexLoc(idx);
        this.indexFile.seek(idxLoc);
        indexFile.writeLong(-1);
        indexFile.writeLong(-1);
    }
    
    private long getIndexLoc(long idx) {
        return 16 * idx;
    }    

    /////////////////////////////////////////////////////////////////////////////////////////
    public void put(long ind, Object obj) throws IOException {
        if (ind < 0 || ind >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        byte[] data = convertToByteArray(obj);
        put1(ind, data);
        if (ind > lastIndex) {
            lastIndex = ind;
        }
    }
    
    private void put1(long idx, byte[] data) throws IOException {
        long dataPos = updateData(data);        
        updateIndex(idx, dataPos, data.length);
    }

    private void updateIndex(long ind, long dataPos, long byteArrayLength) throws IOException {
        long lvIdxLoc = getIndexLoc(ind);
        indexFile.seek(lvIdxLoc);
        indexFile.writeLong(dataPos);
        indexFile.writeLong(byteArrayLength);
    }
    
    private long updateData(byte[] b) throws IOException {
        long lvDataPos = objectFile.length();
        objectFile.seek(lvDataPos);
        objectFile.write(b);
        return lvDataPos;
    }


    public void append(Object obj) throws IOException {
        put(lastIndex + 1, obj);
    }

    public void forceAppend(Object obj) throws IOException {
        size += 2;
        put(lastIndex + 1, obj);
    }

    public Object get(int ind) throws IOException {
        Object obj = get1(ind);
        return obj;
    }
    
    private Object get1(long ind) throws IOException{
        Object obj=null;
        byte b[] = getByteArray(ind);
        obj = byteArrayToObject(b);
        return obj;
    }

    public void closeXArray() {
        try {
            indexFile.close();
            objectFile.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private long getLoc(long ind) {
        return 16 * ind;
    }

    private byte[] getByteArray(long ind) throws IOException {
        indexFile.seek(getLoc(ind));
        long start = indexFile.readLong();
        indexFile.seek(getLoc(ind) + 8);
        long size = indexFile.readLong();
        objectFile.seek(start);
        byte b[] = new byte[(int) size];
        objectFile.read(b);
        return b;
    }

}
