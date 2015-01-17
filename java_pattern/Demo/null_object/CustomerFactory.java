public class CustomerFactory{
    private final String [] names = {"jli","zwq","gchen"};
    public AbstractCustomer getCustomer(String name){
	for(String finalname:names){
	    if(name.equalsIgnoreCase(finalname)){
		return new RealCustomer(name);
	    }
	}
	return new NullCustomer();
    }
}