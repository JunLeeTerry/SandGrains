public class Astate implements State{
    public void doAction(Context context){
	System.out.println("this is state A");
	context.setState(this);
    }
    
    public String toString(){
	return "state A";
    }
    
}