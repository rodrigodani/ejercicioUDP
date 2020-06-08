import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class ClienteUDP {
	public static void main(String[] args) throws IOException{
		System.out.println("--cliente--");
		try {
			DatagramSocket socketUDP = new DatagramSocket();
			int puerto =6543;
			InetAddress host = InetAddress.getByName("localhost");
			
			BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
			String cad;
			cad= sc.readLine();
			
			byte[] mensaje= cad.getBytes();
			DatagramPacket peticon= new DatagramPacket( mensaje,cad.length(),host,puerto);
			socketUDP.send(peticon);
			
			

			byte[] bufer= new byte[1024];
			bufer= Arrays.copyOfRange(bufer,0,socketUDP.getReceiveBufferSize());
			DatagramPacket recibe = new DatagramPacket(bufer, bufer.length);
			socketUDP.receive(recibe);
			String mensajerecibido =new String(recibe.getData());
			System.out.println("la respuesta del servidor es: "+mensajerecibido);
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
}
