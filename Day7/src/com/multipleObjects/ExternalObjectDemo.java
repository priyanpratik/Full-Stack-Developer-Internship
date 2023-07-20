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
public class ExternalObjectDemo {
    
    
    public static void main(String args[]) throws IOException{
        ExternalObjectArray arr=new ExternalObjectArray(10, "/home/coder5/Desktop/", "ObjectFile");
        Employee emp1=new Employee("Amit",35,'M');
        Employee emp2=new Employee("Riya",32,'F');
        Employee emp3=new Employee("Pratik",30,'M');
        Employee emp4=new Employee("Navneet",32,'M');
        Employee emp5=new Employee("Ankita",36,'F');
        
        arr.put(0, emp1);
        arr.append(emp2);
        arr.put(5, emp3);
        arr.put(9, emp4);
        arr.forceAppend(emp5);
        
        Employee e1=(Employee) arr.get(0);
        try{ e1.print(); } catch(Exception e){ System.out.println("No object found!!!"); } 
        
        Employee e2=(Employee) arr.get(1);
        try{ e2.print(); } catch(Exception e){ System.out.println("No object found!!!"); } 
        
        Employee e3=(Employee) arr.get(5);
        try{ e3.print(); } catch(Exception e){ System.out.println("No object found!!!"); } 
        
        Employee e4=(Employee) arr.get(9);
        try{ e4.print(); } catch(Exception e){ System.out.println("No object found!!!"); } 
        
        Employee e5=(Employee) arr.get(10);
        try{ e5.print(); } catch(Exception e){ System.out.println("No object found!!!"); } 
        
//        Employee e6=(Employee) arr.get(4);
//        try{ e6.print(); } catch(Exception e){ System.out.println("No object found!!!"); }        
        
        
        
        arr.closeXArray();
    }
}
