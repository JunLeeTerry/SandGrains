public class Demo{
    public static void main(String [] args){
	StrategyAdd add = new StrategyAdd();
	StrategyMultiply mul = new StrategyMultiply();
	StrategySubstract sub = new StrategySubstract();

	Context context = new Context(add);
	context.execStrategy(3,2);
	context = new Context(mul);
	context.execStrategy(3,2);
	context = new Context(sub);
	context.execStrategy(3,2);
    }
}