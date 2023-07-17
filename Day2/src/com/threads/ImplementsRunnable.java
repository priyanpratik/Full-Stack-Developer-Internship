/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.threads;

/**
 *
 * @author coder5
 */

class X implements Runnable{

    @Override
    public void run() {
        for(int i=1;i<=50;i++){
            System.out.println("Thread 1 : " + i);
            try{
                Thread.sleep(1000);
            }
            catch(Exception e){
                
            }
        }
    }
    
}

class Y implements Runnable{

    @Override
    public void run() {
        for(int i=1;i<=50;i++){
            System.out.println("Thread 2 : " + i);
            try{
                Thread.sleep(1000);
            }
            catch(Exception e){
                
            }
        }
    }
    
}

public class ImplementsRunnable {
    public static void main(String args[]){
        Runnable obj1=new X();
        Runnable obj2=new Y();
    
        Thread t1=new Thread(obj1);
        Thread t2=new Thread(obj2);
    
        t1.start();
        t2.start();
    }
}
