public abstract class AbstractHander{

    protected int level; 
    AbstractHander nexthander;

    public void setNextHander(AbstractHander nexthander){
	this.nexthander = nexthander;
    }
    public void Handthings(String string,int level){
	if(this.level <= level){
	    write(string);
	}
	if(nexthander != null ){
	    // System.out.println("call the next hander");
	    nexthander.Handthings(string,level);
	}
	//System.out.println(">>>>>");
    }

    abstract public void write(String string);
}