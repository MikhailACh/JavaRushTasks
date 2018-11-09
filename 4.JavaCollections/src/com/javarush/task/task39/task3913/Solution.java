package com.javarush.task.task39.task3913;

import java.io.File;
import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("D:/logs/"));
        /*for (File f : new File("D:/logs/").listFiles()) {
            System.out.println(f.getName() + " " + f.length());
        }*/
        /*System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
        System.out.println(logParser.getUniqueIPs(null, new Date()));
        System.out.println(logParser.getIPsForUser("Amigo", null, new Date()));
        System.out.println(logParser.getIPsForEvent(Event.DONE_TASK,null, null));
        System.out.println(logParser.getIPsForStatus(Status.FAILED, null, new Date()));
        System.out.println("----------------------------------------------------------------");
        System.out.println(logParser.getAllUsers());
        System.out.println(logParser.getNumberOfUsers(null, new Date()));
        System.out.println(logParser.getNumberOfUserEvents("Amigo", null, new Date()));
        System.out.println(logParser.getUsersForIP("127.0.0.1", null, new Date()));
        System.out.println(logParser.getLoggedUsers(null, new Date()));
        System.out.println(logParser.getDownloadedPluginUsers(null, new Date()));
        System.out.println(logParser.getWroteMessageUsers(null, new Date()));
        System.out.println(logParser.getSolvedTaskUsers(null, null));
        System.out.println(logParser.getSolvedTaskUsers(null, null, 18));
        System.out.println(logParser.getDoneTaskUsers(null, null));
        System.out.println(logParser.getDoneTaskUsers(null, null, 15));
        System.out.println("----------------------------------------------------------------");
        System.out.println(logParser.getDatesForUserAndEvent("Amigo", Event.LOGIN, null, null));
        System.out.println(logParser.getDatesWhenSomethingFailed(null, null));
        System.out.println(logParser.getDatesWhenErrorHappened(null, null));
        System.out.println(logParser.getDateWhenUserLoggedFirstTime("Amigo", null, null));
        System.out.println(logParser.getDateWhenUserSolvedTask("Amigo", 18,null, null));
        System.out.println(logParser.getDateWhenUserDoneTask("Vasya Pupkin", 15,null, null));
        System.out.println(logParser.getDatesWhenUserWroteMessage("Vasya Pupkin", null, null));
        System.out.println(logParser.getDatesWhenUserDownloadedPlugin("Eduard Petrovich Morozko", null, null));
        System.out.println("----------------------------------------------------------------");
        System.out.println(logParser.getNumberOfAllEvents(null, null));
        System.out.println(logParser.getAllEvents(null, null));
        System.out.println(logParser.getEventsForIP("127.0.0.1", null, null));
        System.out.println(logParser.getEventsForUser("Vasya Pupkin", null, null));
        System.out.println(logParser.getFailedEvents(null, null));
        System.out.println(logParser.getErrorEvents(null, null));
        System.out.println(logParser.getNumberOfAttemptToSolveTask(18, null, null));
        System.out.println(logParser.getNumberOfSuccessfulAttemptToSolveTask(48, null, null));
        System.out.println(logParser.getAllSolvedTasksAndTheirNumber(null, null));
        System.out.println(logParser.getAllDoneTasksAndTheirNumber(null, null));
        System.out.println("----------------------------------------------------------------");
        System.out.println(logParser.execute("get ip"));
        System.out.println(logParser.execute("get user"));
        System.out.println(logParser.execute("get date"));
        System.out.println(logParser.execute("get event"));
        System.out.println(logParser.execute("get status"));
        System.out.println("----------------------------------------------------------------");
        System.out.println(logParser.execute("get ip for user = \"Vasya Pupkin″"));
        System.out.println("----------------------------------------------------------------");
        System.out.println(logParser.execute("get ip for user = \"Amigo″"));
        System.out.println("----------------------------------------------------------------");
        System.out.println(logParser.execute("get ip for event = \"DONE_TASK″"));
        System.out.println("----------------------------------------------------------------");
        System.out.println(logParser.execute("get ip for event = \"SOLVE_TASK″"));
        System.out.println(logParser.execute("get ip for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\""));*/
        System.out.println(logParser.execute("get ip for event = \"SOLVE_TASK\" and date between \"11.12.2013 0:00:00\" and \"31.01.2014 23:59:59\""));
        System.out.println(logParser.execute("get ip for status = \"OK\" and date between \"11.12.2013 0:00:00\" and \"31.01.2014 23:59:59\""));
        System.out.println(logParser.execute("get date for event = \"SOLVE_TASK\" and date between \"11.12.2013 0:00:00\" and \"31.01.2014 23:59:59\""));
    }
}