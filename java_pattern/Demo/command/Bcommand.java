public class Bcommand implements Commandinterface{
    private Receiver receiver;
    public Bcommand (Receiver receiver){
	this.receiver = receiver;
    }
    
    public void exec(){
	this.receiver.execB();
    }
}
