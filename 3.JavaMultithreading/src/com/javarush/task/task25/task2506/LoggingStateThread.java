package com.javarush.task.task25.task2506;

// Создай класс LoggingStateThread в отдельном файле. Он должен наследовать класс Thread.
//2. Класс LoggingStateThread должен содержать поле нити, за которой он будет следить. Это поле должно инициализироваться через конструктор.
//3. Переопредели метод run в классе LoggingStateThread.
//4. После запуска класс LoggingStateThread должен выводить в консоль изменения состояния переданной нити.
//5. После завершения работы наблюдаемой нити, LoggingStateThread так же должен завершить работу.
public class LoggingStateThread extends Thread {
    Thread thread;

    public LoggingStateThread(Thread target) {
        this.thread = target;
    }

    @Override
    public void run() {
        Enum<Thread.State> currentState = thread.getState();
        System.out.println(currentState);
        super.run();

        while (currentState != State.TERMINATED) {
            State newState = thread.getState();

            if (newState != currentState) {
                System.out.println(thread.getState());
                currentState = newState;
            }
        }
        this.interrupt();
    }
}
