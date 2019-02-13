package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.text.ParseException;
import java.util.Map;
import java.util.ResourceBundle;

import static com.javarush.task.task26.task2613.ConsoleHelper.*;
import static com.javarush.task.task26.task2613.CurrencyManipulatorFactory.getManipulatorByCurrencyCode;


class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + ".withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        String currencyCode = askCurrencyCode();
        CurrencyManipulator currencyManipulator = getManipulatorByCurrencyCode(currencyCode);
        writeMessage(res.getString("before"));

        int sum;
        while (true) {
            try {
                writeMessage(res.getString("specify.amount"));
                String input = readString();
                sum = Integer.parseInt(input);

                if (sum <= 0) {
                    writeMessage(res.getString("specify.not.empty.amount"));
                    continue;
                }

                if (!currencyManipulator.isAmountAvailable(sum)) {
                    writeMessage(res.getString("not.enough.money"));
                    continue;
                }

                try {
                    currencyManipulator.withdrawAmount(sum);
                    writeMessage(String.format(res.getString("success.format"), sum, currencyCode));
                    break;
                } catch (NotEnoughMoneyException e) {
                    writeMessage(res.getString("exact.amount.not.available"));
                    continue;
                }
            } catch (NumberFormatException e) {
                e.getCause();
                writeMessage("Invalid currency code, try again:");
            }
        }
        askOperation();
    }
}