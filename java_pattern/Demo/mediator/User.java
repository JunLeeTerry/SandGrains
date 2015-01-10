public class User{
    private String name;
    private ChatRoom room;
    public User(String name,ChatRoom room){
	this.name = name;
	this.room = room;
    }
    public void setname(String name){
	this.name = name;
    }
    public String getname(){
	return this.name;
    }
    public void sendString(String string){
	room.showString(this,string);
    }
    public String toString(){
	return this.name;
    }
}