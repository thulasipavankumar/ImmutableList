/*
MIT License

Copyright (c) 2017 Pavan Kumar Tulasi

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package com.tulasi.immutablelist;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author pavan kumar tulasi
 *
 */
public class TestClass {

    private String name;
    private int age = 0;

    public static Predicate<TestClass> isAgeMoreThan(Integer age) {
        return p -> p.getAge() > age;
    }

    public static Predicate<TestClass> isEqualsName(String name) {
        return p -> p.getName().equals(name);
    }

    public void set(int i, String name) {

        this.name = name;
        this.age = i;
    }
    public void setName(String name){
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public String getName() {
        return this.name;
    }

    public static void main(String args[]) throws Exception {
        TestClass s = new TestClass();
        s.set(10, "pavan");
        TestClass s1 = new TestClass();
        s1.set(11, "kumar");
        TestClass s2 = new TestClass();
        s2.set(12, "tulasi");
        TestClass s3 = new TestClass();
        s3.set(13, "blah blah");
        List abc = new ArrayList();
        abc.add(s);
        abc.add(s1);
        abc.add(s2);
        abc.add(s3);
        abc.add(42);
        abc.add("the answer to the universe");
        abc.add("naruto ");
        abc.add("gravity falls");
        ImmutableList list = new ImmutableList(abc);
        s3.setName("changed");
        //xyz = "blahblash";
        printList(list.reverse());
        printList(list.tail());
        printList(list.drop(1));
        printList(list.filter(isEqualsName("index")));
        printList(list.filter(isAgeMoreThan(10)));

    }

    public static void printList(List list) {
        System.out.println("" + list.size());
        for (int i = 0; i < list.size(); i++) {
            Object tempObj = list.get(i);
            if(tempObj instanceof TestClass){
                printName((TestClass)tempObj);
            }else{
                System.out.println("object type is " + tempObj
                    + " " + tempObj.toString() + " " + tempObj.getClass());
            }
        }
    }
    public static void printName(TestClass tempObj){
        
            System.out.println("" + tempObj.getName());
        
    }
}
