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

import com.rits.cloning.Cloner;
import com.tulasi.sort.Sort;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author Pavan Kumar Tulasi
 */
public final class ImmutableList implements Cloneable {

    final Container container;

    public ImmutableList(final List srcList) throws Exception {
        container = new Container(srcList);
    }

    private void copyValuesIntoList(List src, List dest) {
        for (Object object : src) {
            dest.add(object);
        }
    }

    private List createAndReturnADeepCopyOfGivenList(final List source) throws Exception {
        List tempList = getANewInstanceOfList();
        for (Object obj : source) {
            Object copyOfObject = returnACopyOfObject(obj);
            tempList.add(copyOfObject);
        }
        return tempList;
    }

    private Object returnACopyOfObject(final Object obj) throws Exception {
        Cloner cloner = new Cloner();
        Object newClone = cloner.deepClone(obj);
        return newClone;

    }

    private List getAListWithinGivenBounds(int start, int end, List newList) throws Exception {
        if (start < end) {
            List list = container.getACopyOfSourceList();
            newList.add(returnACopyOfObject(list.get(start)));
            return getAListWithinGivenBounds(++start, end, newList);
        }

        return newList;
    }

    private List getANewInstanceOfList() {
        return new ArrayList();
    }

    private List getReverseOfListWithinGivenBounds(int start, int end, List newList) throws Exception {

        --end;
        if (end >= start) {
            List list = container.getACopyOfSourceList();
            newList.add(returnACopyOfObject(list.get(end)));
            return getReverseOfListWithinGivenBounds(start, end, newList);
        }

        return newList;
    }

    /*
      Returns the first element of a list.  
    */
    public Object head() throws Exception {
        List list = container.getACopyOfSourceList();
        return returnACopyOfObject(list.get(0));
    }

    /*
    Returns a new list with all elements of the original list except the first.
    */
    public List tail() throws Exception {
        List list = container.getACopyOfSourceList();
        int start = 1, end = list.size();
        List newList = getAListWithinGivenBounds(start, end, getANewInstanceOfList());
        return newList;
    }

    /*
    Returns the reverse of a list
    */
    public List reverse() throws Exception {
        List list = container.getACopyOfSourceList();
        if (list.size() == 0) {
            return null;
        }
        List reverseList = getReverseOfListWithinGivenBounds(0, list.size(), getANewInstanceOfList());
        return reverseList;
    }

    /*
    Takes an integer ‘n’ as argument and returns a new list after removing first n elements
    from the list
    */
    public List drop(int n) throws Exception {
        List list = container.getACopyOfSourceList();
        if (n > list.size()) {
            return null;
        }
        List newlist = getAListWithinGivenBounds(n, list.size(), getANewInstanceOfList());
        return newlist;
    }

    /*
     ​Takes an argument and prepends it to the list,
     return the new list with given object as the first element
     */
    public List cons(Object obj) throws Exception {
        List list = container.getACopyOfSourceList();
        List newlist = getANewInstanceOfList();
        newlist.add(obj);
        List tempList = getAListWithinGivenBounds(0, list.size(), getANewInstanceOfList());
        copyValuesIntoList(tempList, newlist);
        return newlist;
    }

    /*
    Takes a predicate(function, lambda or anonymous class) as an argument and returns a
    list only containing the elements which satisfy that predicate
    */
    public <T> List<T> filter(Predicate<T> predicate) throws Exception {
        List tempList = container.getACopyOfSourceList();
        HashMap<String,List> hashmap = Sort.splitIntoIdenticalObjects(tempList);
        List resultList = applyLogicOnEachTypeOfObject(predicate,hashmap);
        return resultList;
    }
    private <T> List applyLogicOnEachTypeOfObject(Predicate<T> predicate, HashMap<String, List> map) {
        List resultList = getANewInstanceOfList();
        for (String key : map.keySet()) {
            List identicalObjects = map.get(key);
            try {
                List temp = (List<T>) identicalObjects.stream().filter(predicate).collect(Collectors.toList());
                copyValuesIntoList(temp, resultList);
            } catch (Exception ex) {
                System.out.println("Exception occured for applying predicate to given " + key + " class");
            }
        }
        return resultList;

    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return null;
    }

    private class Container {

        private final List list;

        Container(final List srcList) throws Exception {
            if (srcList.size() == 0) {
                throw new Exception("list cannot be empty");
            }
            list = getANewInstanceOfList();
            copyValuesIntoList(createAndReturnADeepCopyOfGivenList(srcList), list);
        }

        public List getACopyOfSourceList() throws Exception {
            List copyOfList = createAndReturnADeepCopyOfGivenList(list);
            return copyOfList;
        }

    }

}
