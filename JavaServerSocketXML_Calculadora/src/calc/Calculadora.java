package calc;
public class Calculadora {
	//classe de implementa��o dos m�todos da calculadora
    public String sayHello(String nome, String sobrenome) {
        return "Fala "+ nome + " " + sobrenome;
    }
    public double soma(double oper1, double oper2) {
        return oper1 + oper2;
    }
    
    public double subtracao(double oper1, double oper2) {
    	return oper1-oper2;
    }
    public double multiplicacao(double oper1, double oper2) {
    	return oper1*oper2;
    }
    public double divisao(double oper1, double oper2) {
    	
    	return oper1/oper2;
    	
    }
}