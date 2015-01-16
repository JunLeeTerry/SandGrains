public class ObserverA extends Observer{
    private Subject subject;
    public ObserverA(Subject sub){
	this.subject = sub;
	this.subject.addobserver(this);
    }
    public void update(){
	System.out.println("this is A update"+this.subject.getState());
    }
}