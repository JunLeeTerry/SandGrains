public class Demo{
    public static void main(String [] args){
	Context context = new Context();
	
	Astate a = new Astate();
	Bstate b = new Bstate();

	a.doAction(context);
	System.out.println(context.getState().toString());
	
	b.doAction(context);
	System.out.println(context.getState().toString());
    }
}