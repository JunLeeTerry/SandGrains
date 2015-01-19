public class Computer implements ComputerPart{
    private ComputerPart [] parts = {new Mouse(),new Keyboard()};
    public void accept(ComputerPartVisitor visitor){
	for(ComputerPart part:parts){
	    part.accept(visitor);
	}
	visitor.visit(this);
    }
}