public class Demo{
    public static void main(String [] args){
	Subject subject = new Subject();
	
	ObserverA a = new ObserverA(subject);
	ObserverB b = new ObserverB(subject);
	ObserverC c = new ObserverC(subject);
	
	subject.setState(3);
	subject.notifyAllObserver();

	subject.setState(7);
	subject.notifyAllObserver();
    }
}