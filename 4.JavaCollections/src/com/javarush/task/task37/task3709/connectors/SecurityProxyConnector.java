package com.javarush.task.task37.task3709.connectors;

import com.javarush.task.task37.task3709.security.SecurityChecker;
import com.javarush.task.task37.task3709.security.SecurityCheckerImpl;

public class SecurityProxyConnector implements Connector{
    public SimpleConnector simpleConnector;
    public SecurityChecker securityChecker = new SecurityCheckerImpl();

    public SecurityProxyConnector(String resourceString) {
        simpleConnector = new SimpleConnector(resourceString);
    }

    // Метод connect класса SecurityProxyConnector должен выполнять проверку безопасности с помощью вызова метода performSecurityCheck у объекта типа SecurityChecker.
    // Если проверка безопасности была пройдена, должно быть выполнено подключение.
    // Если проверка безопасности не была пройдена, подключение не должно быть выполнено.
    // Класс SecurityProxyConnector должен поддерживать интерфейс Connector.
    @Override
    public void connect() {
        if (securityChecker.performSecurityCheck())
            simpleConnector.connect();

    }
}
