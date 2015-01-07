import java.util.ArrayList;
public class Employee{
    private String name;
    private String dept;
    private ArrayList<Employee> subordinates;
    
    public Employee(String name , String dept){
	this.name = name;
	this.dept = dept;
	subordinates = new ArrayList<Employee>();
    }

    public void add(Employee e){
	subordinates.add(e);
    }

    public void remove(Employee e){
	subordinates.remove(e);}

    public ArrayList<Employee> getsub(){
	return subordinates;}

    public void printtoString(){
	System.out.println(this.name+"&"+this.dept);
    }
}
