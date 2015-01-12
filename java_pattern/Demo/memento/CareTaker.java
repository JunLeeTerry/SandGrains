import java.util.ArrayList;
public class CareTaker{
    private ArrayList<Memento> list = new ArrayList<Memento>();
    public Memento get(int index){
	return this.list.get(index);
    }
    public void add(Memento memento){
	this.list.add(memento);
    }
}
