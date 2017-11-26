# ImmutableList
Custom ImmutableList Java.
This is a custom list in java which will take array list as a constructor and creates a immutable list out of it.
Provides helper functions to retrieve the data
```
ImmutableList immutableList = new ImmutableList(arrayList);
//immutableList is arraylist of immutable object with a new reference 
```
## head()
	Returns the first element of a list.
### tail()
	Returns a new list with all elements of the original list except the first.
### cons(Object obj)
	Takes an argument and prepends it to the list.
### drop(int n)
	Takes an integer ‘n’ as argument and returns a new list after removing first n elements from the list.
### reverse()
	Returns the reverse of a list.
### filter(predicate<T>)
	Takes a predicate(function, lambda or anonymous class) as an argument and returns a list only containing the elements which satisfy that predicate
    
### Note:
	Each one of the above operations  return a new list with the input list left intact. 
    
 ## Useful links ##   
 https://en.wikipedia.org/wiki/Immutable_object