package client;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

	private Socket client;
	private ObjectOutputStream oos;
	private DataInputStream in;
	
	private List<Object> objectOfSth = new ArrayList<Object>();
	
	public Client() {
		try {
		System.out.println("client: Connecting to localhost on port 8080");
		client = new Socket("localhost", 8080);
		
		System.out.println("client: Just connected to " + client.getRemoteSocketAddress());		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// ------------------------------------------ login or register --------------------------------
	public void registerUser(List<Object> list) {
		// sending data to server
		try {
			// preparing to send data to server
			oos = new ObjectOutputStream(client.getOutputStream());

			oos.writeObject(list);
			
			// receiving data processed by server
	        in = new DataInputStream(client.getInputStream());     
	        System.out.println("client: Server says " + in.readUTF());
	        client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean loginAdmin(List<Object> list) {
		boolean exist = false;
		
		// sending data to server
		try {
			// preparing to send data to server
			oos = new ObjectOutputStream(client.getOutputStream());
						
			oos.writeObject(list);
			
			// receiving data processed by server
	        exist = (boolean) new ObjectInputStream(client.getInputStream()).readObject();
	        client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return exist;
	}
	
	public boolean loginUser(List<Object> list) {
		boolean exist = false;
		
		// sending data to server
		try {
			// preparing to send data to server
			oos = new ObjectOutputStream(client.getOutputStream());
						
			oos.writeObject(list);
			
			// receiving data processed by server
	        exist = (boolean) new ObjectInputStream(client.getInputStream()).readObject();
	        client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return exist;
	}
	
	// ----------------------------------------- operations -------------------------------
	public boolean add(List<Object> list) {
		boolean done = false;
		// sending data to server
		try {		
			// preparing to send data to server
			oos = new ObjectOutputStream(client.getOutputStream());
			oos.writeObject(list);
			
			// receiving data processed by server
			done = (boolean) new ObjectInputStream(client.getInputStream()).readObject();
	        client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return done;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> view(List<Object> list) {
		objectOfSth.clear();
		
		try {
			// preparing to send data to server
			oos = new ObjectOutputStream(client.getOutputStream());			
			oos.writeObject(list);
			
			// receiving data processed by server
			objectOfSth = (List<Object>) new ObjectInputStream(client.getInputStream()).readObject();
	        client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectOfSth;
	}
	
	public void update(List<Object> list) {
		// sending data to server
		try {		
			// preparing to send data to server
			oos = new ObjectOutputStream(client.getOutputStream());
			oos.writeObject(list);
			
			// receiving data processed by server
	        in = new DataInputStream(client.getInputStream());     
	        System.out.println("client: Server says " + in.readUTF());
	        client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(List<Object> list) {
		// sending data to server
		try {		
			// preparing to send data to server
			oos = new ObjectOutputStream(client.getOutputStream());
			oos.writeObject(list);
			
			// receiving data processed by server
	        in = new DataInputStream(client.getInputStream());     
	        System.out.println("client: Server says " + in.readUTF());
	        client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}