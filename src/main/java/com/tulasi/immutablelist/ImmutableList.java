/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tulasi.immutablelist;

import com.rits.cloning.Cloner;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Pavan Kumar Tulasi
 */
public final class ImmutableList implements Cloneable{

    private final ArrayList list;

    public ImmutableList(final ArrayList tempList) throws Exception {
        if (tempList.size() == 0) {
            throw new Exception("list cannot be empty");
        }
        list = new ArrayList();
        createADeepCopy(tempList);
    }
    
    private void createADeepCopy(final ArrayList source) throws Exception {
        for (Object obj : source) 
            this.list.add(returnACopyOfObject(obj));
   
    }
    
    private  Object returnACopyOfObject(final Object obj) throws Exception {
        Cloner cloner=new Cloner();
        Object newClone = cloner.deepClone(obj);
        return newClone;

    }
    
    

    private ArrayList returnCloneForTheList(int start, int end) throws Exception {
        if (start >= end) {
            throw new Exception("start should not be greater than end");
        }
        ArrayList newList = getAListWithinGivenBounds(start, end, getANewInstanceOfArrayList());
        return newList;
    }

    private ArrayList getAListWithinGivenBounds(int start, int end, ArrayList newList) throws Exception {
        if (start < end) {
            newList.add(returnACopyOfObject(list.get(start)));
            return getAListWithinGivenBounds(++start, end, newList);
        }

        return newList;
    }
    private ArrayList getANewInstanceOfArrayList(){
        return new ArrayList();
    }
    private ArrayList getReverselist(int start, int end, ArrayList newList) throws Exception {
        
          --end;
        if (end>=start) {         
            newList.add(returnACopyOfObject(list.get(end))); 
            return getReverselist(start, end, newList);
        }

        return newList;
    }

    private void copyObjectIntoList(Object obj) {
        Field[] allFields = obj.getClass().getDeclaredFields();
        for (Field eachFeild : allFields) {
            if (eachFeild.getType().isPrimitive()) {

            }
        }
    }

    public static void main(String args[]) {

    }

    public Object head() throws Exception {

        return returnACopyOfObject(list.get(0));
    }

    public ArrayList tail() throws Exception {
        int start = 1, end = list.size();
        ArrayList newList = getAListWithinGivenBounds(start, end, getANewInstanceOfArrayList());
        return newList;
    }
    public ArrayList reverse() throws Exception{
        if(list.size()==0)
            return null;
        ArrayList reverseList = getReverselist(0, list.size(), getANewInstanceOfArrayList());
        return reverseList;
    }
    public ArrayList drop(int index) throws Exception{
        if(index>list.size())
            return null;
        ArrayList newlist = getAListWithinGivenBounds(index, list.size(), getANewInstanceOfArrayList());
        return newlist;   
    }
    public ArrayList cons(Object obj) throws Exception{
         ArrayList newlist  = getANewInstanceOfArrayList();
         list.add(obj);
       ArrayList tempList = getAListWithinGivenBounds(0, list.size(), getANewInstanceOfArrayList());
       for(Object tempObj:tempList)
           list.add(tempObj);
        return newlist;   
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
       return null;
    }

}
