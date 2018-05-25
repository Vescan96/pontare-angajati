package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.criterion.Restrictions;

import database.Connection;
import model.Admin;
import model.Informations;
import model.Observations;
import model.User;

public class Server extends Thread{
	private ServerSocket serverSocket;
	private Connection c;	
	private Socket server;
	
	private List<Object> objectOfSth = new ArrayList<Object>();
	
	public Server() {}
	
    public Server(int port) throws IOException {
       serverSocket = new ServerSocket(port);
    }

    public void run() {
       while(true) {
          try { 
        	  c = new Connection();
        	  // set server
        	  System.out.println("server: Waiting for client on port " + serverSocket.getLocalPort() + "...");
        	  server = serverSocket.accept();
	
        	  System.out.println("server: Just connected to " + server.getRemoteSocketAddress());
	
        	  // read from client
        	  @SuppressWarnings("unchecked")
        	  List<Object> ois = (List<Object>) new ObjectInputStream(server.getInputStream()).readObject(); 	          
          
        	  switch((int)ois.get(0)) {
					// for login or register
					case 0 : registerUser(ois.get(1)); break;
					case 1 : loginAdmin(ois.get(1)); break;
					case 2 : loginUser(ois.get(1)); break;
					
					// user operations
					case 3 : userView(ois.get(1), ois.get(2)); break;
					case 4 : update(ois.get(1), ois.get(2)); break;
					
					// admin operations
					case 5 : add(ois.get(1), ois.get(2), ois.get(3)); break;
					case 6 : view(ois.get(1)); break;
					case 7 : updateByAdmin(ois.get(1), ois.get(2), ois.get(3)); break;
					case 8 : delete(ois.get(1), ois.get(2)); break;
        	  }
        	  
          } catch (SocketTimeoutException s) {
        	  System.out.println("server: Socket timed out!");
        	  break;
	      } catch (Exception e) {
	          e.printStackTrace();
	          break;
	      }
       }
    }
    
 // ----------------------------------------- login or register --------------------------------------   
    public void registerUser(Object object) {  	
    	c.getSession().save(object);
    	c.getSession().getTransaction().commit();
    	c.getSession().close();
    	
    	try {
    	// tell to client that server done
  	  	DataOutputStream out = new DataOutputStream(server.getOutputStream());
  	  	out.writeUTF("server: done");
  
  	  	server.close();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public void loginAdmin(Object object) {
    	boolean exist = true;
    	
    	Admin a = (Admin) object;
    	    		
    	Admin admin = (Admin)c.getSession().load(Admin.class, 1);
		
		if(!admin.getPass().equals(a.getPass()))
			exist = false;
			
    	try {
        	// tell to client that server done
    		ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
      	  	out.writeObject(exist);
      	  	
      	  	server.close();
    	} catch(Exception e) {
        		e.printStackTrace();
    	}
    }
    
    public void loginUser(Object object) {
    	boolean exist = true;
    	
    	User u = (User) object;
		
		List<User> users = c.getSession().createQuery("from User", User.class).list();
		
		if(users.stream().filter(x -> x.getUsername().equals(u.getUsername()) && x.getPassword().equals(u.getPassword()))
				.collect(Collectors.toList())
				.size() == 0)					
			exist = false;
		
		try {
        	// tell to client that server done
    		ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
      	  	out.writeObject(exist);
      	  	
      	  	server.close();
    	} catch(Exception e) {
        		e.printStackTrace();
    	}
    }
    
    // ------------------------------------- user operations ------------------------------------------
    @SuppressWarnings({ "unchecked", "deprecation" })
	public void userView(Object object, Object informationType) {
    	objectOfSth.clear();
    	
    	User user = (User) object;

    	int it = (int) informationType;
    	
    	if(it == 0)	{
    		objectOfSth = (List<Object>) c.getSession()
    									  .createCriteria(Informations.class)
    									  .add(Restrictions.eq("username", user.getUsername()))
    									  .list();
    	}
    	if(it == 1)	{
    		objectOfSth = (List<Object>) c.getSession()
    									  .createCriteria(Informations.class)
    									  .add(Restrictions.eq("username", user.getUsername()))
    									  .list();
    	}
    	if(it == 2)	{
    		objectOfSth = (List<Object>) c.getSession()
    									  .createCriteria(Observations.class)
    									  .add(Restrictions.eq("username", user.getUsername()))
    									  .list();
    	}
    	
    	try {
        	// tell to client that server done
    		ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
      	  	out.writeObject(objectOfSth);
      	  	
      	  	server.close();
    	} catch(Exception e) {
        		e.printStackTrace();
    	}
    }
    
    @SuppressWarnings("deprecation")
	public void update (Object user, Object time) {   	
    	float newTime = (float) time;
    	
    	Informations info = (Informations) c.getSession()
				  							.createCriteria(Informations.class)
				  							.add(Restrictions.eq("username", ((User)user).getUsername())).uniqueResult();
    	
    	float finalTime = info.getWorkedHours() + newTime;

    		c.getSession().createQuery("update Informations set workedHours = " + finalTime).executeUpdate();
    		c.getSession().getTransaction().commit();
    		c.getSession().close();
 
    	try {
        	// tell to client that server done
      	  	DataOutputStream out = new DataOutputStream(server.getOutputStream());
      	  	out.writeUTF("server: done");
      
      	  	server.close();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    // ------------------------------------- admin operations -----------------------------------------
    @SuppressWarnings("deprecation")
	public void add(Object category, Object data, Object categoryType) {
    	boolean done = false;
    	
    	int type = (int) categoryType;
    	
    	if(type == 0) {
	    	c.getSession().save(category);
	    	c.getSession().getTransaction().commit();
	    	c.getSession().close();
	    	
	    	done = true;
    	}
    	if(type == 1) {
    		String[] info = ((String)data).split(",");
    	    		
    		User user = (User) c.getSession()
    							.createCriteria(User.class)
    							.add(Restrictions.eq("username", ((String) category)))
    							.uniqueResult();
    							
    		c.getSession().save(new Informations(user.getId(), user.getUsername(), info[0], info[1], Integer.valueOf(info[2]), Float.valueOf(info[3])));
    		c.getSession().getTransaction().commit();
    		c.getSession().close();
    		
    		done = true;
    	}
    	if(type == 2) {    	    		
    		User user = (User) c.getSession()
    							.createCriteria(User.class)
    							.add(Restrictions.eq("username", ((String) category)))
    							.uniqueResult();
    							
    		c.getSession().save(new Observations(user.getId(), user.getUsername(), ((String) data)));
    		c.getSession().getTransaction().commit();
    		c.getSession().close();
    		
    		done = true;
    	}
    	
    	try {
        	// tell to client that server done
    		ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
      	  	out.writeObject(done);
      	  	
      	  	server.close();
    	} catch(Exception e) {
        		e.printStackTrace();
    	}
    }
    
    @SuppressWarnings("unchecked")
	public void view(Object type) {
    	objectOfSth.clear();
    	
    	int tp = (int) type;
    	
    	if(tp == 0)	objectOfSth = (List<Object>) c.getSession().createQuery("from User").list();
    									  	
    	if(tp == 1)	objectOfSth = (List<Object>) c.getSession().createQuery("from Informations").list();
    									
    	if(tp == 2)	objectOfSth = (List<Object>) c.getSession().createQuery("from Observations").list();
    									 
    	try {
        	// tell to client that server done
    		ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
      	  	out.writeObject(objectOfSth);
      	  	
      	  	server.close();
    	} catch(Exception e) {
        		e.printStackTrace();
    	}
    	
    }
    
    public void updateByAdmin(Object username, Object data, Object categoryType) {
    	int type = (int) categoryType;
    	
    	if(type == 0) {
    		String[] info = ((String) data).split(",");
    		
    		c.getSession()
    		 .createQuery("update User set username = '" + info[0] + "'" + ", password = '" + info[1] + "'" +
    				 	  " where username = '" + ((String) username) + "'")
    		 .executeUpdate();
    		c.getSession().getTransaction().commit();
    		c.getSession().close();
    	}
    	if(type == 1) {
    		String[] info = ((String) data).split(",");
    		
    		c.getSession()
    		 .createQuery("update Informations set name = '" + info[0] + "'" + ", rank = '" + info[1] + "'" +
    				 	  ", salary = " + Integer.valueOf(info[2]) + ", workedHours = " + Float.valueOf(info[3]) +
    				 	  " where username = '" + ((String) username) + "'")
    		 .executeUpdate();
    		c.getSession().getTransaction().commit();
    		c.getSession().close();
    	}
		if(type == 2) {   		
    		c.getSession()
    		 .createQuery("update Observations set observation = '" + ((String) data) + "'" +
    				 	  " where username = '" + ((String) username) + "'")
    		 .executeUpdate();
    		c.getSession().getTransaction().commit();
    		c.getSession().close();
		}
		
		try {
        	// tell to client that server done
      	  	DataOutputStream out = new DataOutputStream(server.getOutputStream());
      	  	out.writeUTF("server: done");
      
      	  	server.close();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @SuppressWarnings("deprecation")
	public void delete(Object username, Object categoryType) {
    	int type = (int) categoryType;
    	
    	if(type == 0) {
    		c.getSession()
 			 .createQuery("delete from User where username = '" + ((String) username) + "'")
 			 .executeUpdate();
    		c.getSession().getTransaction().commit();
    		c.getSession().close();
    	}
    	if(type == 1) {	
    		User user = (User) c.getSession()
    							.createCriteria(User.class)
    							.add(Restrictions.eq("username", ((String) username)))
    							.uniqueResult();
    		
    		c.getSession()
			 .createQuery("delete from Informations where username = '" + ((String) username) + "'" + " and userId = " + user.getId())
			 .executeUpdate();
    		c.getSession().getTransaction().commit();
			c.getSession().close();
    	}
    	if(type == 2) {
    		c.getSession()
			 .createQuery("delete from Observations where username = '" + ((String) username) + "'")
			 .executeUpdate();
    		c.getSession().getTransaction().commit();
			c.getSession().close();
		}
    
    	try {
        	// tell to client that server done
      	  	DataOutputStream out = new DataOutputStream(server.getOutputStream());
      	  	out.writeUTF("server: done");
      
      	  	server.close();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    // ----------------------------------------- main -------------------------------------------------
    public static void main(String [] args) {

    	try {
    		Thread t = new Server(8080);
    		t.start();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }   
}