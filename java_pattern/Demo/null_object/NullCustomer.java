public class NullCustomer extends AbstractCustomer{
    public String getName(){
	return "null name";
    }
    public boolean isNil(){
	return true;
    }
    public void show(){
	System.out.println("this is NullCustomer");
    }
}