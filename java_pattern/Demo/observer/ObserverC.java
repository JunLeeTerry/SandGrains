public class ObserverC extends Observer{
    private Subject subject;
    public ObserverC(Subject sub){
	this.subject = sub;
	this.subject.addobserver(this);
    }
    public void update(){
	System.out.println("this is C update"+this.subject.getState());
    }
}