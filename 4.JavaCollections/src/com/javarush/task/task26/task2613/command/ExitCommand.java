package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

import static com.javarush.task.task26.task2613.ConsoleHelper.askOperation;
import static com.javarush.task.task26.task2613.ConsoleHelper.readString;
import static com.javarush.task.task26.task2613.ConsoleHelper.writeMessage;

class ExitCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + ".exit_en");

    @Override
    public void execute() throws InterruptOperationException{
        writeMessage(res.getString("exit.question.y.n"));

        try {
            String answer = readString();

            if (answer.equalsIgnoreCase("y"))
                writeMessage(res.getString("thank.message"));
        } catch (InterruptOperationException ioe) {
            throw new InterruptOperationException();
        }
    }
}