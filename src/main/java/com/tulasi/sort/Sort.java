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
package com.tulasi.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Pavan Kumar Tulasi
 */
public class Sort {

    public static HashMap splitIntoIdenticalObjects(List toBeSorted) {
        HashMap<String,List> destList  = new HashMap<String,List>();
        Iterator it = returnAvailableClassesInList(toBeSorted);
        while(it.hasNext()){
            Object obj = it.next();
            List identicalObjects = returnListContainingSameObjects(obj,toBeSorted);
            destList.put(obj.toString(),identicalObjects);
        }
       
        return destList;
    }

    private static List returnListContainingSameObjects(Object requiredObject,List toBeSorted) {
        System.out.println("");
        List list = new ArrayList();
        for(Object object:toBeSorted){
            if(isObjectOfSameClassType(object,requiredObject)){
                list.add(object);
                //System.out.println("addding "+object.getClass());
            }
        }
        return list;
    }

    private static boolean isObjectOfSameClassType(Object src, Object des) {
        String srcClassName = src.getClass().getName();
        String desClassName = des.toString();
       // System.out.println("Comparing "+srcClassName+" "+desClassName);
        if (srcClassName.equals(desClassName)) {
            return true;
        }

        return false;
    }

    public static Iterator returnAvailableClassesInList(List sourceList) {
        Set hash_Set = new HashSet();
        for (Object obj : sourceList) {
            hash_Set.add(obj.getClass().getName());           
        }
        return hash_Set.iterator();
    }

}
