public class Demo{
    public static void main(String [] args){
	Leader leader = new Leader(3);
	Teacher teacher = new Teacher(2);
	Perfessor perfessor = new Perfessor(1);
	
	leader.setNextHander(teacher);
	teacher.setNextHander(perfessor);

	AbstractHander hander = leader;
	
	hander.Handthings("jaja",3);
	hander.Handthings("haha",2);
	hander.Handthings("gaga",1);
    }
}