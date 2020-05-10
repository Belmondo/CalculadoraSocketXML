package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


import calc.Calculadora;

public class ServerXML {
	
	static ServerSocket serverSocket;
	static Calculadora calc;
	
	public static void main(String [] args) throws IOException {
		
		serverSocket = new ServerSocket(8080);
		calc = new Calculadora();

		try {
			while(true) {
				Socket socket = serverSocket.accept();
				startHandler(socket);
			}
		} finally {
			serverSocket.close();
		}
		
	}

	private static void startHandler(Socket socket) {
		// TODO Auto-generated method stub
		
		Thread thread = new Thread() {
			public void run() {
				try {
					ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
					ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());

					String line = (String) reader.readObject();
					writer.writeObject(TratarXML(line) + "\n");
					writer.flush();
					
				} catch (IOException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					closeSocket();
				}
				
				
			}

			private void closeSocket() {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		thread.start();
		
	}
	
	
	
	public static String TratarXML(String xml){

		List<String> lista = new ArrayList();
		lista =  LimparValores(xml);
		String result = Calcular(lista);
		
		return result;
		
	}
	
	public static List<String> LimparValores(String xml) {
		
		ArrayList listaElementos = new ArrayList();
		

		String s = xml;
		
		int inicioOperacao = s.indexOf("<Operacao>");
		int fimOperacao = s.lastIndexOf("</operacao>");
		
		int inicioOperadorUm = s.indexOf("<operadorUm>");
		int fimnOperadorUm = s.lastIndexOf("</operadorUm>");
		
		int inicioOperadorDois = s.indexOf("<operadorDois>");
		int fimOperadorDois = s.lastIndexOf("</numerDois>");
	
		listaElementos.add(s.substring(inicioOperacao, fimOperacao).split(">")[1]);
		listaElementos.add(s.substring(inicioOperadorUm, fimnOperadorUm).split(">")[1]);
		listaElementos.add(s.substring(inicioOperadorDois, fimOperadorDois).split(">")[1]);
		
		return listaElementos;
		
	}
	
	public static String Calcular(List<String> lista) {
		
		String result ="";
		String operacao=lista.get(0).toString().trim();
		String operadorUm = lista.get(1).trim();
		String operadorDois = lista.get(2).trim();
		
		  //avaliação da operação recebida e direcionamento para cada caso correspondente
        switch(Integer.parseInt(operacao)){
        case 1:
     	   result = ""+ calc.soma(Double.parseDouble(operadorUm),Double.parseDouble(operadorDois));
            break;
        case 2:
     	   result = ""+ calc.subtracao(Double.parseDouble(operadorUm),Double.parseDouble(operadorDois));
            break;
        case 3:
     	   result = ""+ calc.multiplicacao(Double.parseDouble(operadorUm),Double.parseDouble(operadorDois));
            break;
        case 4:
     	   result = ""+ calc.divisao(Double.parseDouble(operadorUm),Double.parseDouble(operadorDois));
            break;
            
        default:
            result = "Não foi selecionada uma opção válida";
            break;
		
        }
        return result; 
	}

}
