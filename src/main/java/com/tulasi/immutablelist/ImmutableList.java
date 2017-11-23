/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tulasi.immutablelist;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

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
        try{
            Object clone = obj.getClass().newInstance();
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if(field.get(obj) == null || Modifier.isFinal(field.getModifiers())){
                    continue;
                }
                if(field.getType().isPrimitive() || field.getType().equals(String.class)
                        || field.getType().getSuperclass().equals(Number.class)
                        || field.getType().equals(Boolean.class)){
                    field.set(clone, field.get(obj));
                }else{
                    Object childObj = field.get(obj);
                    if(childObj == obj){
                        field.set(clone, clone);
                    }else{
                        field.set(clone, returnACopyOfObject(field.get(obj)));
                    }
                }
            }
            return clone;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList returnCloneForTheList(int start, int end) throws Exception {
        if (start >= end) {
            throw new Exception("start should not be greater than end");
        }
        ArrayList newList = getAListWithinGivenBounds(start, end, new ArrayList());
        return newList;
    }

    private ArrayList getAListWithinGivenBounds(int start, int end, ArrayList newList) throws Exception {
        if (start < end) {
            newList.add(returnACopyOfObject(list.get(start)));
            return getAListWithinGivenBounds(++start, end, newList);
        }

        return newList;
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
        ArrayList newList = getAListWithinGivenBounds(start, end, new ArrayList());
        return newList;
    }
    public ArrayList reverse() throws Exception{
        if(list.size()==0)
            return null;
        ArrayList reverseList = getReverselist(0, list.size(), new ArrayList());
        return reverseList;
    }
    public ArrayList drop(int index) throws Exception{
        if(index>list.size())
            return null;
        ArrayList newlist = getAListWithinGivenBounds(index, list.size(), new ArrayList());
        return newlist;   
    }
    public ArrayList cons(Object obj) throws Exception{
         ArrayList newlist  = new ArrayList();
         list.add(obj);
       ArrayList tempList = getAListWithinGivenBounds(0, list.size(), new ArrayList());
       for(Object tempObj:tempList)
           list.add(tempObj);
        return newlist;   
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
       return null;
    }

}
