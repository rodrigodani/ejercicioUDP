import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;
public class ServidorUDP {
	public static void main(String[] args) {
		
		System.out.println("--servidor--");
		try {
			DatagramSocket socketUDP = new DatagramSocket(6543);
			byte[] bufer= new byte[1024];
			
			while(true){
				bufer= Arrays.copyOfRange(bufer,0,socketUDP.getReceiveBufferSize());
				DatagramPacket peticion = new DatagramPacket(bufer, bufer.length);
			
				socketUDP.receive(peticion);
				String mensaje =new String(peticion.getData());
				System.out.println("datos: "+mensaje);
				
				//PARA CONTAR 
				int numeroDePalabras = contarPalabras(mensaje);
				String enviarCadena = numeroDePalabras+"";
				byte[] enviarMensaje= enviarCadena.getBytes();
				DatagramPacket enviar= new DatagramPacket( enviarMensaje,enviarCadena.length(),peticion.getAddress(),peticion.getPort());
				socketUDP.send(enviar);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private static int contarPalabras(String mensaje) {
		mensaje= mensaje.trim();
		return mensaje.split(" +").length;
	}

}
