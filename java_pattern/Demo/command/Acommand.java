public class Acommand implements Commandinterface{
    private Receiver receiver;
    public Acommand(Receiver receiver){
	this.receiver = receiver;}
    public void exec(){
	this.receiver.execA();
    }
}