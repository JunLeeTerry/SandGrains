public class ObserverB extends Observer{
    private Subject subject;
    public ObserverB(Subject sub){
	this.subject = sub;
	this.subject.addobserver(this);
    }
    public void update(){
	System.out.println("this is B update"+this.subject.getState());
    }
}