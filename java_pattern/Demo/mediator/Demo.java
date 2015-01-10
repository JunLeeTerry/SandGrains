public class Demo{
    public static void main (String [] args){
	ChatRoom room = new ChatRoom();
	User gchen = new User("gchen",room);
	User jli = new User("jli",room);
	
	gchen.sendString("hello,jli");
	jli.sendString("hi,gchen");
    }
}