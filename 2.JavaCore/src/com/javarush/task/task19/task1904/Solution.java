package com.javarush.task.task19.task1904;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args)throws IOException{
        Scanner fileScanner = new Scanner(new File("D:/1.txt"), "cp1251");
        PersonScanner ps = new PersonScannerAdapter(fileScanner);
        Person man = ps.read();
    }

    public static class PersonScannerAdapter implements PersonScanner {
        private Scanner fileScanner;

        PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException {
            String[] person = null;
            Date birthDate = null;

            // если в файле есть еще строки, то читаем первую из них, делим на части по пробелу и добавляем в строковый массив
            if (fileScanner.hasNext())
                person = fileScanner.nextLine().split(" ");

            // убираем нулевой пробел
            if (person[0].startsWith("\uFEFF"))
                person[0] = person[0].substring(1);

            // парсим последние три члена массива в объект типа Date по заданному образцу
            try {
                birthDate = new SimpleDateFormat("dd MM yyyy").parse(person[3] + " " + person[4] + " " + person[5]);
            } catch (ParseException pe) {pe.getMessage();}

            return new Person(person[1], person[2], person[0], birthDate);
        }

        @Override
        public void close() {
            fileScanner.close();
        }
    }
}
