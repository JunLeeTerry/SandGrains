public class Teacher extends AbstractHander{
    //private int level;
    public Teacher(int level){
	this.level = level;
    }
    @Override
    public void write(String string){
	System.out.println("Teacher:"+string);
    }
}