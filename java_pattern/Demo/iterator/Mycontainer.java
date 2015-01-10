public class Mycontainer implements Container{
    private String[] string = new String[]{"a","b","c"};
    public Iterator getIterator(){
	return new MyInterator();
    }
    private class MyInterator implements Iterator{
	private int index = 0;
	public boolean hasNext(){
	    if(index < string.length){
		return true;
	    }
	    else
		return false;
	}
	public Object next(){
	    if(hasNext()){
		return string[index++];
	    }
	    else 
		return null;
	}
    }
}