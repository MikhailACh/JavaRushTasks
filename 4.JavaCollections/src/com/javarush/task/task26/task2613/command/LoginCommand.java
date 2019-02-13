package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.CashMachine;

import java.util.ResourceBundle;

import static com.javarush.task.task26.task2613.ConsoleHelper.readString;
import static com.javarush.task.task26.task2613.ConsoleHelper.writeMessage;

public class LoginCommand implements Command{
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + ".verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + ".login_en");

    @Override
    public void execute() throws InterruptOperationException {
        writeMessage(res.getString("before"));
        while (true) {
            writeMessage(res.getString("specify.data"));
            String insertedCard = readString();
            String enteredPin = readString();

            if (insertedCard.length() != 12 || enteredPin.length() != 4) {
                writeMessage(res.getString("try.again.with.details"));
                continue;
            }

            if (validCreditCards.containsKey(insertedCard)) {
                if (validCreditCards.getObject(insertedCard).equals(enteredPin)) {
                    writeMessage(String.format(res.getString("success.format"), insertedCard));
                    break;
                } else {
                    writeMessage(res.getString("not.verified.format"));
                    writeMessage(res.getString("try.again.or.exit"));
                }
            }
        }
    }
}