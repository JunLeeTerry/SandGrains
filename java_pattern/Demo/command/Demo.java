public class Demo {
    public static void main (String[] args){
	Receiver receiver = new Receiver();
	Commandinterface commanda = new Acommand(receiver);
	Commandinterface commandb = new Bcommand(receiver);

	Invoker invoker = new Invoker();
	invoker.takecommand(commanda);
	invoker.takecommand(commandb);
	
	invoker.execcommand();
	
    }
}