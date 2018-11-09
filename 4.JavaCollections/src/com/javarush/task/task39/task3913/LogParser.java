package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.nio.file.Path;
import java.util.*;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private List<String[]> stringList;

    public LogParser(Path logDir) {
        stringList = Helper.stringsReader(logDir);
    }

    // "146.34.15.5 Eduard Petrovich Morozko 05.01.2021 20:22:55 DONE_TASK 48 FAILED".

    //1.2.1. Метод getNumberOfUniqueIPs(Date after, Date before) должен возвращать количество уникальных IP адресов за выбранный период.
    //Здесь и далее, если в методе есть параметры Date after и Date before, то нужно возвратить данные касающиеся только данного периода (включая даты after и before).
    //Если параметр after равен null, то нужно обработать все записи, у которых дата меньше или равна before.
    //Если параметр before равен null, то нужно обработать все записи, у которых дата больше или равна after.
    //Если и after, и before равны null, то нужно обработать абсолютно все записи (без фильтрации по дате).
    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    // 1.2.2. Метод getUniqueIPs() должен возвращать множество, содержащее все не повторяющиеся IP.
    // Тип, в котором будем хранить IP, будет String.
    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> IPs = new HashSet<>();
        List<String[]> cutDates = Helper.dateCutter(stringList, after, before);

        for (String[] s : cutDates) {
            String ip = s[0];
            IPs.add(ip);
        }

        return IPs;
    }

    // 1.2.3. Метод getIPsForUser() должен возвращать IP, с которых работал переданный пользователь.
    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> IPs = new HashSet<>();
        List<String[]> cutDates = Helper.dateCutter(stringList, after, before);

        for (String[] s : cutDates) {
            if (s[1].equals(user))
                IPs.add(s[0]);
        }

        return IPs;
    }

    // 1.2.4. Метод getIPsForEvent() должен возвращать IP, с которых было произведено переданное событие
    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> IPs = new HashSet<>();
        List<String[]> cutDates = Helper.dateCutter(stringList, after, before);

        for (String[] s : cutDates) {
            String current = Helper.taskNumberSeparator(s[3]);
            if (current.equals(event.toString()))
                IPs.add(s[0]);
        }

        return IPs;
    }

    // 1.2.5. Метод getIPsForStatus() должен возвращать IP, события с которых закончилось переданным статусом.
    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> IPs = new HashSet<>();
        List<String[]> cutDates = Helper.dateCutter(stringList, after, before);

        for (String[] s : cutDates) {
            if (s[4].equals(status.toString()))
                IPs.add(s[0]);
        }

        return IPs;
    }

    //2.1. Метод getAllUsers() должен возвращать всех пользователей.
    @Override
    public Set<String> getAllUsers() {
        Set<String> users = new HashSet<>();

        for (String[] s : stringList) {
            users.add(s[1]);
        }

        return users;
    }

    //2.2. Метод getNumberOfUsers() должен возвращать количество уникальных пользователей.
    @Override
    public int getNumberOfUsers(Date after, Date before) {
        List<String[]> usersByDate = Helper.dateCutter(stringList, after, before);
        Set<String> uniqueUsers = new HashSet<>();

        for (String[] s : usersByDate) {
            uniqueUsers.add(s[1]);
        }

        return uniqueUsers.size();
    }

    //2.3. Метод getNumberOfUserEvents() должен возвращать количество (уникальных) событий от определенного пользователя.
    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        List<String[]> eventsByDate = Helper.dateCutter(stringList, after, before);
        Set<String> eventsByUser = new HashSet<>();

        for (String[] s : eventsByDate) {
            String current = Helper.taskNumberSeparator(s[3]);
            if (s[1].equals(user))
                eventsByUser.add(current);
        }

        return eventsByUser.size();
    }

    //2.4. Метод getUsersForIP() должен возвращать пользователей с определенным IP.
    //Несколько пользователей могут использовать один и тот же IP.
    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        List<String[]> usersByDate = Helper.dateCutter(stringList, after, before);
        Set<String> usersByThisIP = new HashSet<>();

        for (String[] s : usersByDate) {
            if (s[0].equals(ip))
                usersByThisIP.add(s[1]);
        }

        return usersByThisIP;
    }

    //2.5. Метод getLoggedUsers() должен возвращать пользователей, которые были залогинены.
    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        List<String[]> usersByDate = Helper.dateCutter(stringList, after, before);
        Set<String> loggedUsers = new HashSet<>();

        for (String[] s : usersByDate) {
            String current = Helper.taskNumberSeparator(s[3]);
            if (current.equals(Event.LOGIN.toString()))
                loggedUsers.add(s[1]);
        }

        return loggedUsers;
    }

    //2.6. Метод getDownloadedPluginUsers() должен возвращать пользователей, которые скачали плагин.
    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        List<String[]> usersByDate = Helper.dateCutter(stringList, after, before);
        Set<String> downloadedPluginUsers = new HashSet<>();

        for (String[] s : usersByDate) {
            String current = Helper.taskNumberSeparator(s[3]);
            if (current.equals(Event.DOWNLOAD_PLUGIN.toString())) {
                downloadedPluginUsers.add(s[1]);
            }
        }

        return downloadedPluginUsers;
    }

    //2.7. Метод getWroteMessageUsers() должен возвращать пользователей, которые отправили сообщение.
    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        List<String[]> usersByDate = Helper.dateCutter(stringList, after, before);
        Set<String> wroteMessageUsers = new HashSet<>();

        for (String[] s : usersByDate) {
            String current = Helper.taskNumberSeparator(s[3]);
            if (current.equals(Event.WRITE_MESSAGE.toString())) {
                wroteMessageUsers.add(s[1]);
            }
        }

        return wroteMessageUsers;
    }

    //2.8. Метод getSolvedTaskUsers(Date after, Date before) должен возвращать пользователей, которые решали любую задачу.
    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        List<String[]> usersByDate = Helper.dateCutter(stringList, after, before);
        Set<String> solvedTaskUsers = new HashSet<>();

        for (String[] s : usersByDate) {
            String current = Helper.taskNumberSeparator(s[3]);
            if (current.equals(Event.SOLVE_TASK.toString())) {
                solvedTaskUsers.add(s[1]);
            }
        }

        return solvedTaskUsers;
    }

    //2.9. Метод getSolvedTaskUsers(Date after, Date before, int task) должен возвращать пользователей, которые решали задачу с номером task.
    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        List<String[]> usersByDate = Helper.dateCutter(stringList, after, before);
        Set<String> solvedTaskUsers = new HashSet<>();

        for (String[] s : usersByDate) {
            if (s[3].equals(Event.SOLVE_TASK.toString()+ " " + task)) {
                solvedTaskUsers.add(s[1]);
            }
        }

        return solvedTaskUsers;
    }

    //2.10. Метод getDoneTaskUsers(Date after, Date before) должен возвращать пользователей, которые решили любую задачу.
    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        List<String[]> usersByDate = Helper.dateCutter(stringList, after, before);
        Set<String> doneTaskUsers = new HashSet<>();

        for (String[] s : usersByDate) {
            String current = Helper.taskNumberSeparator(s[3]);
            if (current.equals(Event.DONE_TASK.toString()) /*&& s[4].equals(Status.OK.toString())*/) {
                doneTaskUsers.add(s[1]);
            }
        }

        return doneTaskUsers;
    }

    //2.11. Метод getDoneTaskUsers(Date after, Date before, int task) должен возвращать пользователей, которые решили задачу с номером task.
    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        List<String[]> usersByDate = Helper.dateCutter(stringList, after, before);
        Set<String> doneTaskUsers = new HashSet<>();

        for (String[] s : usersByDate) {
            if (s[3].equals(Event.DONE_TASK.toString()+ " " + task) /*&& s[4].equals(Status.OK.toString())*/) {
                doneTaskUsers.add(s[1]);
            }
        }

        return doneTaskUsers;
    }

    // 3.1. Метод getDatesForUserAndEvent() должен возвращать даты, когда определенный пользователь произвел определенное событие.
    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        List<String[]> usersByDate = Helper.dateCutter(stringList, after, before);
        Set<Date> datesForUserAndEvent = new HashSet<>();

        for (String[] s : usersByDate) {
            String current = Helper.taskNumberSeparator(s[3]);
            if (s[1].equals(user) && current.equals(event.toString()))
                datesForUserAndEvent.add(Helper.dateParser(s[2]));
        }

        return datesForUserAndEvent;
    }

    //3.2. Метод getDatesWhenSomethingFailed() должен возвращать даты, когда любое событие не выполнилось (статус FAILED).
    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        List<String[]> usersByDate = Helper.dateCutter(stringList, after, before);
        Set<Date> datesWhenSomethingFailed = new HashSet<>();

        for (String[] s : usersByDate) {
            if (s[4].equals(Status.FAILED.toString()))
                datesWhenSomethingFailed.add(Helper.dateParser(s[2]));
        }

        return datesWhenSomethingFailed;
    }

    //3.3. Метод getDatesWhenErrorHappened() должен возвращать даты, когда любое событие закончилось ошибкой (статус ERROR).
    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        List<String[]> usersByDate = Helper.dateCutter(stringList, after, before);
        Set<Date> datesWhenErrorHappened = new HashSet<>();

        for (String[] s : usersByDate) {
            if (s[4].equals(Status.ERROR.toString()))
                datesWhenErrorHappened.add(Helper.dateParser(s[2]));
        }

        return datesWhenErrorHappened;
    }

    //3.4. Метод getDateWhenUserLoggedFirstTime() должен возвращать дату, когда пользователь залогинился впервые за указанный период. Если такой даты в логах нет - null.
    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        List<String[]> usersByDate = Helper.dateCutter(stringList, after, before);
        Date dateWhenUserLoggedTask = new Date(Long.MAX_VALUE);

        for (String[] s : usersByDate) {
            if (s[3].equals(Event.LOGIN.toString()) && s[1].equals(user)) {
                Date current = Helper.dateParser(s[2]);
                if (current.getTime() < dateWhenUserLoggedTask.getTime())
                    dateWhenUserLoggedTask = current;
            }
        }

        if (dateWhenUserLoggedTask.getTime() != Long.MAX_VALUE)
            return dateWhenUserLoggedTask;
        else return null;
    }

    //3.5. Метод getDateWhenUserSolvedTask() должен возвращать дату, когда пользователь впервые попытался решить определенную задачу. Если такой даты в логах нет - null.
    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        List<String[]> usersByDate = Helper.dateCutter(stringList, after, before);
        Date dateWhenUserSolvedTask = new Date(Long.MAX_VALUE);

        for (String[] s : usersByDate) {
            if (s[3].equals(Event.SOLVE_TASK.toString() + " " + task) && s[1].equals(user)) {
                Date current = Helper.dateParser(s[2]);
                if (current.getTime() < dateWhenUserSolvedTask.getTime())
                    dateWhenUserSolvedTask = current;
            }
        }

        if (dateWhenUserSolvedTask.getTime() != Long.MAX_VALUE)
            return dateWhenUserSolvedTask;
        else return null;
    }

    //3.6. Метод getDateWhenUserDoneTask() должен возвращать дату, когда пользователь впервые решил определенную задачу. Если такой даты в логах нет - null.
    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        List<String[]> usersByDate = Helper.dateCutter(stringList, after, before);
        Date dateWhenUserDoneTask = new Date(Long.MAX_VALUE);

        for (String[] s : usersByDate) {
            if (s[3].equals(Event.DONE_TASK.toString() + " " + task) && s[1].equals(user)) {
                Date current = Helper.dateParser(s[2]);
                if (current.getTime() < dateWhenUserDoneTask.getTime())
                    dateWhenUserDoneTask = current;
            }
        }

        if (dateWhenUserDoneTask.getTime() != Long.MAX_VALUE)
            return dateWhenUserDoneTask;
        else return null;
    }

    //3.7. Метод getDatesWhenUserWroteMessage() должен возвращать даты, когда пользователь написал сообщение.
    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        List<String[]> usersByDate = Helper.dateCutter(stringList, after, before);
        Set<Date> datesWhenUserWroteMessage = new HashSet<>();

        for (String[] s : usersByDate) {
            if (s[3].equals(Event.WRITE_MESSAGE.toString()) && s[1].equals(user))
                datesWhenUserWroteMessage.add(Helper.dateParser(s[2]));
        }

        return datesWhenUserWroteMessage;
    }

    //3.8. Метод getDatesWhenUserDownloadedPlugin() должен возвращать даты, когда пользователь скачал плагин.
    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        List<String[]> usersByDate = Helper.dateCutter(stringList, after, before);
        Set<Date> datesWhenUserDownloadedPlugin = new HashSet<>();

        for (String[] s : usersByDate) {
            if (s[3].equals(Event.DOWNLOAD_PLUGIN.toString()) && s[1].equals(user))
                datesWhenUserDownloadedPlugin.add(Helper.dateParser(s[2]));
        }

        return datesWhenUserDownloadedPlugin;
    }

    // 4.1. Метод getNumberOfAllEvents() должен возвращать количество событий за указанный период.
    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    //4.2. Метод getAllEvents() должен возвращать все события за указанный период.
    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        List<String[]> eventsByDate = Helper.dateCutter(stringList, after, before);
        Set<Event> allEvents = new HashSet<>();

        for (String[] s : eventsByDate) {
            allEvents.add(Event.valueOf(Helper.taskNumberSeparator(s[3])));
        }

        return allEvents;
    }

    //4.3. Метод getEventsForIP() должен возвращать события, которые происходили с указанного IP.
    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        List<String[]> eventsByDate = Helper.dateCutter(stringList, after, before);
        Set<Event> eventsForIP = new HashSet<>();

        for (String[] s : eventsByDate) {
            if (s[0].equals(ip))
                eventsForIP.add(Event.valueOf(Helper.taskNumberSeparator(s[3])));
        }

        return eventsForIP;
    }

    //4.4. Метод getEventsForUser() должен возвращать события, которые инициировал определенный пользователь.
    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        List<String[]> eventsByDate = Helper.dateCutter(stringList, after, before);
        Set<Event> eventsForUser = new HashSet<>();

        for (String[] s : eventsByDate) {
            if (s[1].equals(user))
                eventsForUser.add(Event.valueOf(Helper.taskNumberSeparator(s[3])));
        }

        return eventsForUser;
    }

    //4.5. Метод getFailedEvents() должен возвращать события, которые не выполнились.
    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        List<String[]> eventsByDate = Helper.dateCutter(stringList, after, before);
        Set<Event> failedEvents = new HashSet<>();

        for (String[] s : eventsByDate) {
            if (s[4].equals(Status.FAILED.toString()))
                failedEvents.add(Event.valueOf(Helper.taskNumberSeparator(s[3])));
        }

        return failedEvents;
    }

    //4.6. Метод getErrorEvents() должен возвращать события, которые завершились ошибкой.
    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        List<String[]> eventsByDate = Helper.dateCutter(stringList, after, before);
        Set<Event> errorEvents = new HashSet<>();

        for (String[] s : eventsByDate) {
            if (s[4].equals(Status.ERROR.toString()))
                errorEvents.add(Event.valueOf(Helper.taskNumberSeparator(s[3])));
        }

        return errorEvents;
    }

    //4.7. Метод getNumberOfAttemptToSolveTask() должен возвращать количество попыток
    //решить определенную задачу.
    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        List<String[]> eventsByDate = Helper.dateCutter(stringList, after, before);
        int numberOfAttempts = 0;

        for (String[] s : eventsByDate) {
            if (s[3].equals(Event.SOLVE_TASK.toString() + " " + task))
                numberOfAttempts++;
        }

        return numberOfAttempts;
    }

    //4.8. Метод getNumberOfSuccessfulAttemptToSolveTask() должен возвращать количество
    //успешных решений определенной задачи.
    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        List<String[]> eventsByDate = Helper.dateCutter(stringList, after, before);
        int numberOfAttempts = 0;

        for (String[] s : eventsByDate) {
            if (s[3].equals(Event.DONE_TASK.toString() + " " + task))
                numberOfAttempts++;
        }

        return numberOfAttempts;
    }

    //4.9. Метод getAllSolvedTasksAndTheirNumber() должен возвращать мапу (номер_задачи : количество_попыток_решить_ее).
    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        List<String[]> eventsByDate = Helper.dateCutter(stringList, after, before);
        Map<Integer, Integer> tasks = new HashMap<>();

        for (String[] s : eventsByDate) {
            int taskNumber, attemptsNumber;
            if (s[3].contains(Event.SOLVE_TASK.toString())) {
                taskNumber = Integer.parseInt(Helper.eventSeperator(s[3]));

                if (tasks.containsKey(taskNumber)) {
                    attemptsNumber = tasks.get(taskNumber) + 1;
                    tasks.put(taskNumber, attemptsNumber);
                } else {
                    tasks.put(taskNumber, 1);
                }
            }
        }

        return tasks;
    }

    //4.10. Метод getAllDoneTasksAndTheirNumber() должен возвращать мапу (номер_задачи : сколько_раз_ее_решили).
    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        List<String[]> eventsByDate = Helper.dateCutter(stringList, after, before);
        Map<Integer, Integer> tasks = new HashMap<>();

        for (String[] s : eventsByDate) {
            int taskNumber, attemptsNumber;
            if (s[3].contains(Event.DONE_TASK.toString())) {
                taskNumber = Integer.parseInt(Helper.eventSeperator(s[3]));

                if (tasks.containsKey(taskNumber)) {
                    attemptsNumber = tasks.get(taskNumber) + 1;
                    tasks.put(taskNumber, attemptsNumber);
                } else {
                    tasks.put(taskNumber, 1);
                }
            }
        }

        return tasks;
    }

    // 1.Вызов метода execute("get ip") класса LogParser должен возвращать множество (Set<String>) содержащее все уникальные IP адреса.
    // 2.Вызов метода execute("get user") класса LogParser должен возвращать множество (Set<String>) содержащее всех уникальных пользователей.
    // 3.Вызов метода execute("get date") класса LogParser должен возвращать множество (Set<Date>) содержащее все уникальные даты.
    // 4.Вызов метода execute("get event") класса LogParser должен возвращать множество (Set<Event>) содержащее все уникальные события.
    // 5.Вызов метода execute("get status") класса LogParser должен возвращать множество (Set<Status>) содержащее все уникальные статусы.
    @Override
    public Set<Object> execute(String query) {
        Set set = new HashSet<>();

        switch (query) {
            case "get ip" :
                set = getUniqueIPs(null, null);
                break;
            case "get user" :
                set = getAllUsers();
                break;
            case "get date":
                for (String[] s : stringList) {
                    set.add(Helper.dateParser(s[2]));
                };
                break;
            case "get event":
                for (String[] s : stringList) {
                    String event = Helper.taskNumberSeparator(s[3]);
                    set.add(Event.valueOf(event));
                }
                break;
            case "get status":
                for (String[] s : stringList) {
                    set.add(Status.valueOf(s[4]));
                }
                break;
            default:
                set = Helper.queryParser(stringList, query);
        }

        return set;
    }
}