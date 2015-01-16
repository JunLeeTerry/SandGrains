public class Bstate implements State{
    public void doAction(Context context){
	System.out.println("this is state B");
	context.setState(this);
    }

    public String toString(){
	return "state B";
    }
}