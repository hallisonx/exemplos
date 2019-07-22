package estudo;

public class Aula2Exercicios {
	
	public static void main(String[] args) {
		
		  for(int i=3; i<10; i+=2) {
			  System.out.println(" i : " + i);
		  }
		  
		  
		  int v = 3;
		  while (v <= 12) {
			  
			  if(v % 3 == 1) {
				  System.out.println(v + " mod 3 = " + v%3);
			  }
			  v++;
		  }
		 
	}

}
