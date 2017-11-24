/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tulasi.immutablelist;

import java.util.ArrayList;

/**
 *
 * @author pavan kumar tulasi
 * 
 */
public class TestClass {

    String name;

    TestClass() {

    }

    public void set(int i, String name) {

        this.name = name;
    }

    public static void main(String args[]) throws Exception {
        TestClass s = new TestClass();
        s.set(0, "index");
        TestClass s1 = new TestClass();
        s1.set(1, "index1");
        TestClass s2 = new TestClass();
        s2.set(2, "index2");
        TestClass s3 = new TestClass();
        s3.set(3, "index3");
        ArrayList abc = new ArrayList();
        
        String xyz = "xyz";
        abc.add(s);
        abc.add(s1);
        abc.add(s2);
        abc.add(s3);
        //abc.add(10);
        //abc.add("ashahsdhashd");
        
        
        
        ImmutableList list = new ImmutableList(abc);
        s3.name = "changed";
        //xyz = "blahblash";
        
        ArrayList abc1 = list.reverse();
        printList(list.reverse());
         printList(list.tail());
        //printList(list.drop(1));
        
        
    }

    public static void printList(ArrayList list) {
        System.out.println(""+list.size());
        for (int i=0;i<list.size();i++) {
            Object tempObj = list.get(i);
            System.out.println("object type is "+tempObj
                    +" "+tempObj.toString()+" "+tempObj.getClass());
            System.out.println(""+((TestClass)tempObj).name);
                    
        }

    }
}
