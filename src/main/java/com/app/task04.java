package com.app;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class task04 {
    static Logger task04Logger;
    static {
        try {
            task04Logger = Logger.getLogger(task04.class.getName());
            ArrayList<String> filePathDirs = new ArrayList<String>();
            filePathDirs.add("ext");
            filePathDirs.add("task04log.txt");
            String logFilePath = getFilePath(filePathDirs).toString();
            FileHandler taskFileHandler = new FileHandler(logFilePath, true);
            task04Logger.addHandler(taskFileHandler);
            SimpleFormatter task04Formatter = new SimpleFormatter();
            taskFileHandler.setFormatter(task04Formatter);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws SecurityException, IOException {

        Scanner myScanner = new Scanner(System.in);
        System.out.println("Введите простое математическое выражение: ");
        String expr = myScanner.nextLine();
        myScanner.close();
        expr = expr.trim();
        expr = expr.replace(" ", "");
        expr = expr.replace("+", " + ");
        expr = expr.replace("-", " - ");
        expr = expr.replace("*", " * ");
        expr = expr.replace("/", " / ");
        String result = calc(expr);

        System.out.printf("Ответ: %s\n", result);

    }

    public static String calc(String expression) {
        /*
         * Выражение рассчитывается рекурсивно (не уверен, что это полноценная рекурсия)
         * - находится первое действие, расчитывается
         * вместо исходного действия в выражение записывается его результат
         * полученное выражение отправляется
         * парсинг выражения частично происходит в отдельном модуле parseExpression,
         * где определяются составные части выражения - числа для расчёта очередного
         * действия и остальные части
         * выражения, которые не участвуют в текущем вычислении
         */
        if (!(expression.contains("+") || expression.contains("-") || expression.contains("*")
                || expression.contains("/"))) {
            task04Logger.info(String.format("Выражение решено, ответ: %s", expression));
            return expression;
        }

        if (expression.contains("*")) {
            task04Logger.info(String.format("Выявлен знак уножения в выражении %s", expression));
            int index = expression.indexOf("*");
            String[] expressionParts = parseExpression(expression, index);

            String midle = String
                    .valueOf(Double.parseDouble(expressionParts[1]) * Double.parseDouble(expressionParts[2]));
            task04Logger.info(String.format("Получен результат: %s", midle));
            expression = String.format("%s %s %s", expressionParts[0], midle, expressionParts[3]);
            expression = expression.trim();

            return calc(expression);
        }
        if (expression.contains("/")) {
            task04Logger.info(String.format("Выявлен знак деления в выражении %s", expression));
            int index = expression.indexOf("/");
            String[] expressionParts = parseExpression(expression, index);
            String midle = String
                    .valueOf(Double.parseDouble(expressionParts[1]) / Double.parseDouble(expressionParts[2]));
            task04Logger.info(String.format("Получен результат: %s", midle));
            expression = String.format("%s %s %s", expressionParts[0], midle, expressionParts[3]);
            expression = expression.trim();

            return calc(expression);
        }
        if (expression.contains("+")) {
            task04Logger.info(String.format("Выявлен знак сложения в выражении %s", expression));
            int index = expression.indexOf("+");
            String[] expressionParts = parseExpression(expression, index);
            String midle = String
                    .valueOf(Double.parseDouble(expressionParts[1]) + Double.parseDouble(expressionParts[2]));
            task04Logger.info(String.format("Получен результат: %s", midle));
            expression = String.format("%s %s %s", expressionParts[0], midle, expressionParts[3]);
            expression = expression.trim();

            return calc(expression);
        }
        if (expression.contains("-")) {
            task04Logger.info(String.format("Выявлен знак вычитания в выражении %s", expression));
            int index = expression.indexOf("-");
            String[] expressionParts = parseExpression(expression, index);
            String midle = String
                    .valueOf(Double.parseDouble(expressionParts[1]) - Double.parseDouble(expressionParts[2]));
            task04Logger.info(String.format("Получен результат: %s", midle));
            expression = String.format("%s %s %s", expressionParts[0], midle, expressionParts[3]);
            expression = expression.trim();

            return calc(expression);
        }
        return expression;
    }

    public static String[] parseExpression(String expression, int signIndex) {
        String[] expressionParts = new String[4];
        expressionParts[0] = expression.substring(0, signIndex - 1);
        expressionParts[3] = expression.substring(signIndex + 2, expression.length());
        if (expressionParts[0].contains(" ")) {
            expressionParts[1] = expressionParts[0].substring(expressionParts[0].lastIndexOf(" ") + 1,
                    expressionParts[0].length());
            expressionParts[0] = expressionParts[0].substring(0, expressionParts[0].lastIndexOf(" "));
        } else {
            expressionParts[1] = expressionParts[0];
            expressionParts[0] = "";
        }
        if (expressionParts[3].contains(" ")) {
            expressionParts[2] = expressionParts[3].substring(0, expressionParts[3].indexOf(" "));
            expressionParts[3] = expressionParts[3].substring(expressionParts[3].indexOf(" ") + 1,
                    expressionParts[3].length());
        } else {
            expressionParts[2] = expressionParts[3];
            expressionParts[3] = "";
        }
        task04Logger.info(String.format("Выявлены части выражения: [%s] [%s]", expressionParts[1], expressionParts[2]));
        return expressionParts;
    }

    public static StringBuilder getFilePath(ArrayList<String> dirs) {
        String separator = System.getProperty("file.separator");
        StringBuilder path = new StringBuilder();
        for (int i = 0; i < dirs.size() - 1; i++) {

            path.append(dirs.get(i));
            path.append(separator);
        }
        path.append(dirs.get(dirs.size() - 1));
        return path;
    }
}
