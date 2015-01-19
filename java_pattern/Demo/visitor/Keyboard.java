public class Keyboard implements ComputerPart{
    public void accept(ComputerPartVisitor visitor){
	visitor.visit(this); 
    }
}