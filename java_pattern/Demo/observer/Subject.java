import java.util.ArrayList;
public class Subject{
    private ArrayList<Observer> list = new ArrayList<Observer> ();
    private int state;
    public void addobserver(Observer observer){
	list.add(observer);
    }
    public void notifyAllObserver(){
	for (Observer observer:list){
	    observer.update();
	}
    }
    public void setState(int i){
	this.state = i;
    }
    public int getState(){
	return this.state;
    }
    
}