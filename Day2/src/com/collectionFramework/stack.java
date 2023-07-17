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
public class stack {
    public static void main(String args[]){
        Stack<Integer> st=new Stack<Integer>();
        st.push(1);
        st.push(2);
        st.push(3);
        st.push(4);
        st.push(5);
        
        while(!st.empty()){
            System.out.println(st.peek());
            st.pop();
        }
    }
}
