public class ComputerDisplayVisitor implements ComputerPartVisitor{
    public void visit(Keyboard keyboard){
	System.out.println("visit keyboard");
    }
    public void visit(Mouse mouse){
	System.out.println("visit mouse");
    }
    public void visit(Computer computer){
	System.out.println("visit computer");
    }
}