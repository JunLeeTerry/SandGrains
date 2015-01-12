public class Demo{
    public static void main(String [] args){
	Originator o1 = new Originator("originator 1");
	Originator o2 = new Originator("originator 2");
	Originator o3 = new Originator("originator 3");
	
	CareTaker caretaker = new CareTaker();
	caretaker.add(o1.SaveinMemento());
	caretaker.add(o2.SaveinMemento());
	caretaker.add(o3.SaveinMemento());
	
	Memento m1 = caretaker.get(0);
	Memento m2 = caretaker.get(1);
	System.out.println(m1.getState());
	System.out.println(m2.getState());
    }
}
