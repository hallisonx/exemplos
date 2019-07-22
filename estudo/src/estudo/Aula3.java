package estudo;

public class Aula3 {
	
	
	public static void main(String[] args) {
		
		boasVindas();
		
		int resultado = 0;
		
		resultado = somarDoisNumeros(5, 10);
		
		System.out.println("O resultado da soma é: " + resultado);
		
		for ( int b = 0;  b < 12;  b++) {
			System.out.println("O valor de b é: " + b);
		}
		
		int i = 0;
	    while ( i < 10 ) {
	       i = i + 1;
	       System.out.println("O valor de i é: " + i);
	    }    
		
	    int a = 0;
	    do {
	      a++;
	      System.out.println("O valor de a é: " + a);
	    } while ( a <= 10 );
	}
	
	static void boasVindas() {
		System.out.println("Bem vindo! Este programa soma dois números");
	}
	
	static int somarDoisNumeros(int n1, int n2) {
		return n1 + n2;
	}
	
	
	

}
