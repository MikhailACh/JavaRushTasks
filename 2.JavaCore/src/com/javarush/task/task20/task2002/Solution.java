package com.javarush.task.task20.task2002;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = new File("D:/1.txt");
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            User user1 = new User();
            user1.setFirstName("Ivan");
            user1.setLastName("Ivanov");
            user1.setBirthDate(new GregorianCalendar(1980, 11, 25).getTime());
            user1.setCountry(User.Country.RUSSIA);
            user1.setMale(true);

            User user2 = new User();
            user2.setFirstName("Petr");
            user2.setLastName("Petrov");
            user2.setBirthDate(new GregorianCalendar(1990,10,2).getTime());
            user2.setCountry(User.Country.UKRAINE);
            user2.setMale(true);

            User user3 = new User();
            user3.setFirstName("Lana");
            user3.setLastName("Proud");
            user3.setBirthDate(new GregorianCalendar(1980,2,5).getTime());
            user3.setCountry(User.Country.OTHER);
            user3.setMale(false);

            javaRush.users.add(user1);
            javaRush.users.add(user2);
            javaRush.users.add(user3);

            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны
            System.out.println(loadedObject.equals(javaRush));

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF8"));

            String isUsersPresent = users.size() > 0 ? "yes" : "no";
            if (isUsersPresent.equals("yes")) {
                writer.println("yes");
                for (User u : users)
                    writer.println(u.getFirstName() + "," + u.getLastName() + "," + u.getBirthDate().getTime() + "," + u.getCountry() + "," + u.isMale());
            } else writer.println("null");

            writer.flush();
            writer.close();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            if (reader.readLine().equals("yes")) {
                String[] line;
                while (reader.ready()) {
                    line = reader.readLine().split(",");
                    User user = new User();
                    user.setFirstName(line[0]);
                    user.setLastName(line[1]);
                    user.setBirthDate(new Date(Long.parseLong(line[2])));
                    user.setCountry(User.Country.valueOf(line[3]));
                    user.setMale(Boolean.valueOf(line[4]));

                    users.add(user);
                }
            }
            reader.close();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}