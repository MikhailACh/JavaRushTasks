package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;

import static com.javarush.task.task30.task3008.ConsoleHelper.*;
import static com.javarush.task.task30.task3008.MessageType.*;

public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;

    // запрашивает (у пользователя) ввод адреса сервера у пользователя и возвращает введенное значение.
    protected String getServerAddress() {
        writeMessage("Please enter the server address: ");
        return readString();
    }

    // запрашивает (у пользователя) ввод порта сервера и возвращать его
    protected int getServerPort() {
        writeMessage("Please enter the server port: ");
        return readInt();
    }

    // запрашивает (у пользователя) и возвращает имя пользователя
    protected String getUserName() {
        return readString();
    }

    // в данной реализации возвращает true (всегда отправляем текст, введенный в консоль).
    protected boolean shouldSendTextFromConsole() {
        return true;
    }

    // создает и возврает новый объект класса SocketThread.
    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    // создает новое текстовое сообщение, используя переданный текст и отправляет его серверу
    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(TEXT, text));
        } catch (IOException e) {
            writeMessage("Can't send message");
            clientConnected = false;
        }
    }

    public void run() {
        SocketThread socketThread = getSocketThread();              // создаем новый сокетный поток
        socketThread.setDaemon(true);                               // помечаем как демон
        socketThread.start();                                       // запускаем

        synchronized (this) {
            try {
                this.wait();                                        // Заставить текущий поток ожидать, пока он не получит нотификацию из другого потока.
                                                                    // когда поток дождался notify() проверяем, подключен ли клиент
                if (clientConnected)
                    writeMessage("Соединение установлено");

                if (!clientConnected)
                    writeMessage("Произошла ошибка во время работы клиента");

                while (clientConnected) {                           // пока клиент подключен
                    String text = readString();                     // Считываем сообщения с консоли
                    if (text.equals("exit"))                        // Если будет введена команда 'exit' - завершаем чтение сообщений
                        break;
                    if (shouldSendTextFromConsole())                // если метод shouldSendTextFromConsole() возвращает true,
                        sendTextMessage(text);                      // отправляем считанный текст с помощью метода sendTextMessage().
                }
            } catch (InterruptedException e) {
                writeMessage("Connection interrupted!");            // если во время ожидания возникнет исключение, сообщяем об этом пользователю
                System.exit(1);                              // и выходим из программы.
            }
        }
    }

    public class SocketThread extends Thread {
        // выводит текст message в консоль.
        protected void processIncomingMessage(String message) {
            writeMessage(message);
        }

        // выводит в консоль, что участник userName присоединился к чату
        protected void informAboutAddingNewUser(String userName) {
            writeMessage(userName + " joined to the chat");
        }

        // выводит в консоль, что участник userName покинул чат
        protected void informAboutDeletingNewUser(String userName) {
            writeMessage(userName + " left the chat");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            Client.this.clientConnected = clientConnected;          // устанавливает значение clientConnected внешнего объекта Client
            synchronized (Client.this) {                            // синхронизация на уровне текущего объекта внешнего класса
                Client.this.notify();                               // оповещает (пробуждать ожидающий) основной поток класса Client.
            }
        }

        // метод представляет клиента серверу
        protected void clientHandshake() throws IOException, ClassNotFoundException {
            Message message;

            // в цикле получаем сообщения
            while (true) {
                message = connection.receive();
                MessageType type = message.getType();

                if (type == NAME_REQUEST)                               // если сервер запросил имя
                    connection.send(new Message(USER_NAME, getUserName()));
                else
                    if (type == NAME_ACCEPTED) {                        // если сервер принял имя
                    notifyConnectionStatusChanged(true);  // извещаем (пробуждаем) основной поток
                    break;
                } else
                    throw new IOException("Unexpected MessageType");
            }
        }

        // метод будет реализовывать главный цикл обработки сообщений сервера
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            Message message;

            while (true) {
                message = connection.receive();
                MessageType type = message.getType();

                if (type == TEXT) {
                    processIncomingMessage(message.getData());
                } else if (type == USER_ADDED) {
                    informAboutAddingNewUser(message.getData());
                } else if (type == USER_REMOVED) {
                    informAboutDeletingNewUser(message.getData());
                } else
                    throw new IOException("Unexpected MessageType");
            }
        }

        @Override
        public void run() {
            String address = getServerAddress();
            int port = getServerPort();
            try {
                Socket socket = new Socket(address, port);
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            } catch (IOException | ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }
        }
    }

    public static void main(String[] args) {
        new Client().run();
    }
}