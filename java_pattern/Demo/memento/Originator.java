public class Originator{
    private String state;
    public Originator(String state){
	this.state = state;
    }
    public void setState(String state){
	this.state = state;
    }
    public String getState(){
	return this.state;
    }
    public Memento SaveinMemento(){
	return new Memento(this.state);
    }
    public String getMemento(Memento memento){
	return memento.getState();
    }
}