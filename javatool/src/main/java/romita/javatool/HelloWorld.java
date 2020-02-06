package romita.javatool;

public class HelloWorld {
	
	

	int Add(int x, int y){
		
		return x+y;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World");
		HelloWorld hw = new HelloWorld();
		int output = hw.Add(2,3);
		System.out.println(output);
	}


}
