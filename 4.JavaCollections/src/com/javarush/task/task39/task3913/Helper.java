package com.javarush.task.task39.task3913;

import java.io.*;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Helper {
    // читаем файлы из папки logDir
    public static List<String[]> stringsReader(Path logDir) {
        BufferedReader reader;
        List<String[]> strings = new ArrayList<>();
        File[] folderEntries = logDir.toFile().listFiles();

        if (folderEntries.length == 0)
            return strings;

        try {
            for (File entry : folderEntries)
            {
                if (entry.isFile() && entry.getName().endsWith(".log"))
                {
                    String s;
                    reader = new BufferedReader(new FileReader(entry));

                    while ((s = reader.readLine()) != null) {
                        strings.add(s.split("\\t"));
                    }
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found!");
        } catch (IOException ioe) {
            System.out.println("Can't read the file!");
        }

        return strings;
    }

    /*public static List<String[]> dateCutter(List<String[]> stringList, Date after, Date before) {
        List<String[]> cutForDates = new ArrayList<>();

        if (after == null && before == null) {
            after = new Date(0);
            before = new Date(Long.MAX_VALUE);
        } else if (before == null) {
            before = new Date(Long.MAX_VALUE);
        } else if (after == null) {
            after = new Date(0);
        }

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date current;

        for (String[] log : stringList) {
            String s = log[2];
            try {
                current = format.parse(s);
                if (current.getTime() >= after.getTime() && current.getTime() <= before.getTime())
                    cutForDates.add(log);
            } catch (ParseException pe) {
                System.out.println("Date parsing error!");
            }
        }

        return cutForDates;
    }*/

    public static List<String[]> dateCutter(List<String[]> stringList, Date after, Date before) {
        List<String[]> cutForDates = new ArrayList<>();

        if (after == null && before == null) {
            after = new Date(0);
            before = new Date(Long.MAX_VALUE);
        } else if (before == null) {
            before = new Date(Long.MAX_VALUE);
        } else if (after == null) {
            after = new Date(0);
        }

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date current;

        for (String[] log : stringList) {
            String s = log[2];
            try {
                current = format.parse(s);
                if (current.getTime() > after.getTime() && current.getTime() < before.getTime())
                    cutForDates.add(log);
            } catch (ParseException pe) {
                System.out.println("Date parsing error!");
            }
        }

        return cutForDates;
    }

    public static Date dateParser(String s) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(s);
        } catch (ParseException pe) {
            System.out.println("Can't parse date in /getDatesForUserAndEvent()/");
        }

        return date;
    }

    public static String taskNumberSeparator(String s3) {
        return s3.contains(" ") ?
                s3.substring(0, s3.lastIndexOf(" ")) : s3;
    }

    public static String eventSeperator(String s3) {
        return s3.split(" ")[1];
    }

    // String value = "get ip for user = "Eduard Petrovich Morozko"
    // String query = "get ip for user = "Eduard Petrovich Morozko" and date between "11.12.2013 0:00:00" and "03.01.2014 23:59:59""
    public static Set<Object> queryParser(List<String[]> stringList, String query) {
        Set<Object> result = new HashSet<>();
        Map<String, Integer> logModel = new HashMap<>();
        logModel.put("ip", 0);
        logModel.put("user",1);
        logModel.put("date", 2);
        logModel.put("event", 3);
        logModel.put("status", 4);

        String[] q = query.split(" ");
        // ["0 = get, 1 = event, 2 = for, 3 = date, ...″]

        int fromIndex = logModel.get(q[3]);
        String to = q[1];
        int toIndex = logModel.get(to);
        String value = query.split("\"")[1].replaceAll("″", "");

        if (query.contains("and date between")) {
            String[] values = query.split("\"");
            // [0 = get ip for user, 1 = Eduard Petrovich Morozko, 2 = and date between, 3 = 11.12.2013 0:00:00, 4 = and, 5 = 03.01.2014 23:59:59″]
            Date after = dateParser(values[3]);
            Date before = dateParser(values[5].replaceAll("″", ""));
            stringList = dateCutter(stringList, after, before);
        }

        for (String[] s : stringList) {
            String current;
            if (fromIndex == 3)
                current = taskNumberSeparator(s[3]);
            else current = s[fromIndex];

            if (current.equals(value)) {
                switch (to) {
                    case "date": {
                        result.add(dateParser(s[toIndex]));
                        break;
                    }
                    case "event": {
                        result.add(Event.valueOf(taskNumberSeparator(s[toIndex])));
                        break;
                    }
                    case "status": {
                        result.add(Status.valueOf(s[toIndex]));
                        break;
                    }
                    default:
                        result.add(s[toIndex]);
                }
            }
        }

        return result;
    }
}