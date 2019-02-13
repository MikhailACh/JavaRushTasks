package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

import static com.javarush.task.task26.task2613.ConsoleHelper.writeMessage;
import static com.javarush.task.task26.task2613.CurrencyManipulatorFactory.getAllCurrencyManipulators;

class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + ".info_en");

    @Override
    public void execute() throws InterruptOperationException {
        boolean notEmpty = false;
        writeMessage(res.getString("before"));

        for (CurrencyManipulator currency : getAllCurrencyManipulators())
            if (currency.hasMoney()) {
                if (currency.getTotalAmount() > 0) {
                    writeMessage(currency.getCurrencyCode() + " - " + currency.getTotalAmount());
                    notEmpty = true;
                }
            }

        if (!notEmpty)
            writeMessage(res.getString("no.money"));
    }
}