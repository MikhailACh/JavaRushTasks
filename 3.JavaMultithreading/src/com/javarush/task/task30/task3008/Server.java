package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.javarush.task.task30.task3008.ConsoleHelper.readInt;
import static com.javarush.task.task30.task3008.ConsoleHelper.writeMessage;
import static com.javarush.task.task30.task3008.MessageType.*;

public class Server {
    static private Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message) {
        for (Map.Entry<String, Connection> pair : connectionMap.entrySet()) {
            try {
                pair.getValue().send(message);
            } catch (IOException e) {
                writeMessage(String.format("Couldn't send message user %s", pair.getKey()));
            }
        }
    }

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            writeMessage("Input server port: ");
            serverSocket = new ServerSocket(readInt());
            writeMessage("The server was started");
            while (true) {
                Socket current = serverSocket.accept();
                new Handler(current).start();
            }
        } catch (IOException e) {
            try {
                serverSocket.close();
            } catch (IOException e1) {
                writeMessage("Server socket closing exception");
            }
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }
        
        public void run() {
            String remoteAddress = socket.getRemoteSocketAddress().toString();
            writeMessage("Connection established with " + remoteAddress);       // сообщение, что установлено новое соединение с удаленным адресом

            try(Connection newConnection = new Connection(socket))
            {
                String newUser = serverHandshake(newConnection);                // рукопожатие с клиентом
                sendBroadcastMessage(new Message(USER_ADDED, newUser));         // рассылаем всем участникам чата информацию о новом участнике
                notifyUsers(newConnection, newUser);                            // сообщаем новому участнику о существующих участниках
                serverMainLoop(newConnection, newUser);                         // запускаем главный цикл обработки сообщений сервером
                connectionMap.remove(newUser);                                  // удаляем запись для этого участника (т.к. он вышел)
                sendBroadcastMessage(new Message(USER_REMOVED, newUser));       // разослать всем остальным участникам сообщение об этом
            } catch (IOException e) {
                writeMessage("An error occured while exhange with remote address!");
            } catch (ClassNotFoundException cnfe) {
                writeMessage("Handshake with server failed!");
            }

            writeMessage("Connecion with " + remoteAddress + " closed.");       // сообщение, информирующее что соединение с удаленным адресом закрыто
        }

        // рукопожатие клиент-сервер
        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            Message answer;
            String user;

            while (true) {
                connection.send(new Message(NAME_REQUEST));                     // Сформировать и отправить команду запроса имени пользователя
                answer = connection.receive();                                  // Получить ответ клиента

                if (answer.getType() == USER_NAME) {                            // Проверить, что получена команда с именем пользователя
                    user = answer.getData();                                    // Достать из ответа имя
                    if (!user.equals("") && !connectionMap.containsKey(user)) { // проверить, что оно не пустое и пользователь с таким именем еще не подключен
                        connectionMap.put(user, connection);                    // Добавить нового пользователя и соединение с ним в connectionMap
                        connection.send(new Message(NAME_ACCEPTED));            // Отправить клиенту команду информирующую, что его имя принято
                        return user;
                    }
                }
            }
        }

        // отправка клиенту (новому участнику) информации об остальных клиентах (участниках) чата
        private void notifyUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> pair : connectionMap.entrySet()) {
                String name = pair.getKey();

                if (!name.equals(userName))
                    connection.send(new Message(USER_ADDED, name));
            }
        }

        // главный цикл обработки сообщений сервером
        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            Message incoming = null;
            while (true) {
                incoming = connection.receive();                                // Принимаем сообщение клиента

                if (incoming.getType() != TEXT) {                               // Если принятое сообщение - не текст (тип TEXT)
                    writeMessage("This message's not a text");
                } else {                                                        // формируем сообщение
                    String s = userName+ ": " + incoming.getData();
                    sendBroadcastMessage(new Message(TEXT, s));                 // Отправляем сообщение всем клиентам
                }
            }
        }
    }
}