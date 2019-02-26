package com.javarush.task.task40.task4005;


import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/* 
Сокетный сервер и клиент
*/

public class Connection implements Closeable {
    private final Socket socket;
    private final ObjectInputStream in;
    private final ObjectOutputStream out;

    // Инициализация ObjectInputStream перед ObjectOutputStream вызовет взимную блокировку,
    // поскольку конструктор ObjectInputStream будет ожидать поток байтов от ObjectOutputStream.open()
    // При этом, поскольку ObjectOutputStream еще не создан, то конструктор последнего будет блокирован,
    //  равно, как и обе стороны данного потока
    public Connection(Socket socket) throws Exception {
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
    }

    public void send(String message) throws Exception {
        System.out.println("Connection: Try write to out");
        out.writeObject(message);
    }

    public String receive() throws Exception {
        System.out.println("Connection: Try read from in");
        return (String) in.readObject();
    }

    @Override
    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
}