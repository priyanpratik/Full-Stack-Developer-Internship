/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.threads;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 *
 * @author coder5
 */

class Q{
    int size=3;
    Queue<Integer> q=new LinkedList<Integer>();
    
    synchronized void put(int val){
        
        while(q.size()==size){
            System.out.println("Queue is full!!! Size=3");
            try{wait();} catch(Exception e){}
        }
        
        System.out.println("Producer produced item no : " + val);
        q.add(val);
        notify();
    }
    
    synchronized void print(){
        
        while(q.size()==0){
            System.out.println("Queue is empty!!! Size=0");
            try{wait();} catch(Exception e){}
        }
        System.out.println("Consumed item no : " + q.peek());
        q.remove();
        notify();
    }
}

class producer implements Runnable{
    Q q;

    public producer(Q q) {
        this.q = q;
    }

    @Override
    public void run() {
        int i=0;
        while(true){
            int val=ProducerConsumer.getRandomNos(100, 200)*10;
            int time=ProducerConsumer.getRandomNos(1, 6)*200;
            q.put(val);
            try{Thread.sleep(time);} catch(Exception e){}
        }
    }
}

class consumer implements Runnable{
    Q q;

    public consumer(Q q) {
        this.q = q;
    }
    
    @Override
    public void run() {
        while(true){
            int time=ProducerConsumer.getRandomNos(1, 6)*200;
            q.print();
            try{Thread.sleep(time);} catch(Exception e){}
        }
    }
}


public class ProducerConsumer {
    public static void main(String str[]){
        Q q=new Q();
        Runnable obj1=new producer(q);
        Runnable obj2=new consumer(q);
        
        Thread t1=new Thread(obj1);
        Thread t2=new Thread(obj2);
        
        t1.start();
        t2.start();
    }
    
    public static Random rnd=new Random(7);
    public static int getRandomNos(int min, int max){
        return rnd.nextInt(max-min)+min+1;
    }
}
