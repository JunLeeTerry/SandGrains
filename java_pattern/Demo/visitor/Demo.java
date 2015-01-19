public class Demo{
    public static void main(String [] args){
	Computer computer = new Computer();
	ComputerDisplayVisitor visitor = new ComputerDisplayVisitor();
	computer.accept(visitor);
    }
}