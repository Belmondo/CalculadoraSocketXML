package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.Socket;



public class ClientXML {
	
public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
		
		Socket clientSocket = new Socket(Inet4Address.getLocalHost().getHostAddress(), 8080);
		System.out.println("Conectado ao Servidor");
		
		ObjectInputStream reader = new ObjectInputStream(clientSocket.getInputStream());
		ObjectOutputStream writer = new ObjectOutputStream(clientSocket.getOutputStream());
		
		
		String strXML;

		strXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	    strXML += "<calculadora>";
	    strXML += "<Operacao> 1 </operacao>";
	    strXML +="<operadorUm> 3 </operadorUm>";
	    strXML +="<operadorDois> 5 </operadorDois>";
	    strXML += "</calculadora>";
	
	    
	    writer.writeObject(strXML);
	    writer.flush();
		
		String line = (String) reader.readObject();
		
		
		System.out.println("Resultado: "+ line);
		
		
		clientSocket.close();
		
	

	}


}
