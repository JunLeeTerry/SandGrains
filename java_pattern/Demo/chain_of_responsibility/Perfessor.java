public class Perfessor extends AbstractHander{
    
    public Perfessor(int level){
	this.level = level;
    }
    @Override
    public void write(String string){
	System.out.println("Perfessor:"+string);
    }
}