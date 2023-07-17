/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectionFramework;

/**
 *
 * @author coder5
 */
public class Rectangle implements Comparable<Rectangle>{
    private int length;
    private int width;

    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getArea() {
        return this.length*this.width;
    }

    @Override
    public int compareTo(Rectangle obj) {
        String st;
        if(this.length*this.width==obj.length*obj.width)
            return 0;
        
        if(this.length*this.width>obj.length*obj.width)
            return 1;
        
        return -1;
    }
    
    
    
    
}
