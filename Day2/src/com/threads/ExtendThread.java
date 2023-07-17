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
class A extends Thread {

    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Thread 1 : " + i);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
        }
    }
}

class B extends Thread {

    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Thread 2 : " + i);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
        }
    }
}

public class ExtendThread {

    public static void main(String args[]) {
        A obj1 = new A();
        B obj2 = new B();

        obj1.start();
        obj2.start();
    }
}
