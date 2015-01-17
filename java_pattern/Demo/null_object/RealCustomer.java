public class RealCustomer extends AbstractCustomer{
    private String name;
    public RealCustomer(String name){
	this.name = name;
    }
    public String getName(){
	return this.name;
    }
    public boolean isNil(){
	return false;
    }
    public void show(){
	System.out.println("this is RealCustomer"+this.name);
    }
}