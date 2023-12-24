package org.example;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server{
    public static void main(String args[]) throws RemoteException {

            // Instantiating the implementation class
            WordDictionaryImpl obj = new WordDictionaryImpl();
            int port = 5432;
            try { // special exception handler for registry creation
                LocateRegistry.createRegistry(port);
                System.out.println("java RMI registry created.");
            } catch (RemoteException e) {
                // do nothing, error means registry already exists
                System.out.println("java RMI registry already exists.");
            }

            String hostname = "127.0.0.1";

            String bindLocation = "rmi://" + hostname + ":" + port + "/WordDictionary";

            try {
                Naming.bind(bindLocation, obj);
                System.out.println("Word Dictionary Server is ready at:" + bindLocation);
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Word Dictionary Server failed: " + e);
            }

    }
}