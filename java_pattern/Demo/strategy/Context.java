public class Context{
    private Strategy strategy;
    public Context(Strategy strategy){
	this.strategy = strategy;
    }
    public void execStrategy(int num1,int num2){
	int answer = this.strategy.doOperation(num1,num2);
	System.out.println(answer);
    }
}