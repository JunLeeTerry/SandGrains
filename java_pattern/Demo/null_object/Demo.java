public class Demo{
    public static void main(String[] args){
	CustomerFactory cf = new CustomerFactory();
	cf.getCustomer("jli").show();
	cf.getCustomer("yanglei").show();
    }
}