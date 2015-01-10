public class Demo{
    public static void main (String [] args){
	Mycontainer container = new Mycontainer();
	Iterator iterator = container.getIterator();
	while(iterator.hasNext()){
	    System.out.println(iterator.next());
	}
    }
}