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
import java.util.*;

class area implements Comparator<Rectangle>{

    @Override
    public int compare(Rectangle o1, Rectangle o2) {
        if(o1.getArea()<o2.getArea())
            return -1;
        else if(o1.getArea()>o2.getArea())
            return 1;
        else
            return 0;
    }
    
}

public class RectangleDemo {
    public static void main(String args[]){
        Set<Rectangle> st=new TreeSet<>(new area());
        Rectangle r1=new Rectangle(4,5);
        Rectangle r2=new Rectangle(6,3);
        Rectangle r3=new Rectangle(5,5);
        
        st.add(r1);
        st.add(r2);
        st.add(r3);
        
        for(Rectangle o: st){
            System.out.println(o.getLength()+" "+o.getWidth()+" "+o.getArea());
        }
    }
}
