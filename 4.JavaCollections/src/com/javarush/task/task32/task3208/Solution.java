package com.javarush.task.task32.task3208;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/* 
RMI-2
*/
public class Solution {
    public static Registry registry;
    public static final String CAT_BINDINGG_NAME = "CAT";
    public static final String DOG_BINDING_NAME = "DOG";

    //pretend we start rmi client as CLIENT_THREAD thread
    public static Thread CLIENT_THREAD = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                for (String bindingName : registry.list()) {
                    Animal service = (Animal) registry.lookup(bindingName);
                    service.printName();
                    service.say();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            }
        }
    });

    //pretend we start rmi server as SERVER_THREAD thread
    public static Thread SERVER_THREAD = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                Cat cat1 = new Cat("Vaska");
                Dog dog1 = new Dog("Jack");
                registry = LocateRegistry.createRegistry(2099);
                Remote catStub = UnicastRemoteObject.exportObject(cat1, 0);
                Remote dogStub = UnicastRemoteObject.exportObject(dog1, 0);
                registry.bind("CAT", catStub);
                registry.bind("DOG", dogStub);
            } catch (RemoteException re) {
                re.printStackTrace(System.err);
            } catch (AlreadyBoundException abe) {
                abe.printStackTrace(System.err);
            }
            //напишите тут ваш код
        }
    });

    public static void main(String[] args) throws InterruptedException {
        //start rmi server thread
        SERVER_THREAD.start();
        Thread.sleep(1000);
        //start client
        CLIENT_THREAD.start();
    }
}