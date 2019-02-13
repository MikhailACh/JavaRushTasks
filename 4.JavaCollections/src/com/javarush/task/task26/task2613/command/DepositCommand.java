package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

import static com.javarush.task.task26.task2613.ConsoleHelper.writeMessage;
import static com.javarush.task.task26.task2613.CurrencyManipulatorFactory.getManipulatorByCurrencyCode;

class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + ".deposit_en");

    @Override
    public void execute() throws InterruptOperationException {
        String currencyCode = null;
        writeMessage(res.getString("before"));
        try {
            currencyCode = ConsoleHelper.askCurrencyCode();
            CurrencyManipulator currencyManipulator = getManipulatorByCurrencyCode(currencyCode);

            String[] s = ConsoleHelper.getValidTwoDigits(currencyCode);
            int nominal = Integer.parseInt(s[0]);
            int total = Integer.parseInt(s[1]);

            currencyManipulator.addAmount(nominal, total);
            writeMessage(String.format(res.getString("success.format"), nominal * total, currencyCode));
        } catch (NumberFormatException e) {
            writeMessage(res.getString("invalid.data"));
        }
    }
}