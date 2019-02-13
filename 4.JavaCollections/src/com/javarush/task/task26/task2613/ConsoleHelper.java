package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// класс для работы с консолью
public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + ".common_en");
    private final static List<Integer> nominals = new ArrayList<>();

    static {
        nominals.add(1);
        nominals.add(2);
        nominals.add(5);
        nominals.add(10);
        nominals.add(20);
        nominals.add(50);
        nominals.add(100);
    }

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException{
        String console = "";
        try {
            console = bis.readLine();
            if (console.equalsIgnoreCase("exit"))
                throw new InterruptOperationException();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return console;
    }

    public static Operation askOperation() throws InterruptOperationException {
        while (true) {
            writeMessage(res.getString("choose.operation"));
            writeMessage( res.getString("operation.INFO") + ": 1;\n" +
                    res.getString("operation.DEPOSIT") + ": 2;\n" +
                    res.getString("operation.WITHDRAW") + ": 3;\n" +
                    res.getString("operation.EXIT") + ": 4\n");
            try {
                String input = readString();
                int number = Integer.parseInt(input);
                if (number == 0)
                    throw new IllegalArgumentException();
                return Operation.getAllowableOperationByOrdinal(number);
            } catch (Exception e) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
        }
    }

    public static String askCurrencyCode()throws InterruptOperationException {
        String code = "";
        writeMessage(res.getString("choose.currency.code"));

        while (true) {
            code = readString();
            if (code.length() == 3)
                break;
            else
                writeMessage(res.getString("invalid.data"));
        }
        return code.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException{
        String[] input = null;
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));

        while (true) {
            input = readString().split(" ");

            int nominal = 0;
            int number = 0;

            try {
                nominal = Integer.parseInt(input[0]);
                if (!nominals.contains(nominal)) {
                    writeMessage(res.getString("invalid.nominal"));
                    continue;
                }

                number = Integer.parseInt(input[1]);
            } catch (Exception e) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }

            if (nominal <= 0 || number <= 0) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            break;
        }

        return input;
    }

    public static void printExitMessage() {
        writeMessage(res.getString("the.end"));
    }
}