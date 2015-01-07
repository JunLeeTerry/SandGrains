public class Demo{
    public static void main (String [] args){
	Employee a = new Employee("a","AAA");
	Employee b = new Employee("b","BBB");
	Employee c = new Employee("c","CCC");
	Employee d = new Employee("d","BBB");
	Employee e = new Employee("e","CCC");
	
	a.add(b);
	a.add(d);
	b.add(c);
	b.add(e);
	
	a.printtoString();
	for(Employee item:a.getsub()){
	    System.out.print("    ");
	    item.printtoString();
	    for(Employee i:item.getsub()){
		System.out.print("        ");
		i.printtoString();
	    }
	}
    }
}
