import java.util.Scanner;

class SomaInteiros {
 public static void main (String args[]) {
	Scanner entrada = new Scanner(System.in); 
	
	int num1;
	int num2;
	int soma = 0;
	
	System.out.println("Digite um numero: ");
	num1 = entrada.nextInt();
	
	System.out.println("Digite outro numero: ");
	num2 = entrada.nextInt();

	soma = num1 + num2;
	
	System.out.println("Soma = "+soma);
	
	
 }	
}
