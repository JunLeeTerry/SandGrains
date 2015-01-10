import java.util.ArrayList;
public class Invoker{
    private ArrayList<Commandinterface> commandlist = new ArrayList<Commandinterface> ();
    
    public void takecommand(Commandinterface command){
	commandlist.add(command);
    }

    public void execcommand(){
	for(Commandinterface command:commandlist){
	    command.exec();
	}
    }
}