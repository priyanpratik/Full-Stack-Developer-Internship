/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pp;

/**
 *
 * @author coder5
 */
public class Circle extends Shape implements Comparable<Circle>{
    private int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.radius;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Circle other = (Circle) obj;
        if (this.radius != other.radius) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Circle{" + "radius=" + radius + '}';
    }

    @Override
    public double getArea() {
        return 3.14*radius*radius;
    }

    @Override
    public int compareTo(Circle obj) {
        if(this.radius==obj.radius)
            return 0;
        
        if(this.radius>obj.radius)
            return 1;
        
        return -1;
    }
    
    
}
