public class Leader extends AbstractHander{
    //private int level;
    public Leader(int level){
	this.level = level;

    }
    @Override
    public void write(String string){
	System.out.println("Leader:"+string);
    }
}