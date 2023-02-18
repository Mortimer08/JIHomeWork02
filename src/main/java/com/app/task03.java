/*
 * 3* . Дана json строка (можно сохранить в файл и читать из файла)

[{"фамилия":"Иванов","оценка":"5","предмет":"Математика"},
{"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},
{"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]

Написать метод(ы), который распарсит json и, используя StringBuilder,
создаст строки вида: Студент [фамилия] получил [оценка] по предмету [предмет].

Пример вывода:

Студент Иванов получил 5 по предмету Математика.

Студент Петрова получил 4 по предмету Информатика.

Студент Краснов получил 5 по предмету Физика.
 */

package com.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class task03 {
    public static void main(String[] args) throws IOException {
        String pathSeparator = System.getProperty("file.separator");
        String filePath = String.format("ext%stask03data.json", pathSeparator);
        FileReader fr = new FileReader(filePath);
        StringBuilder s = jsonParse(fr);
        System.out.println(s);
    }

    /*
     * Данные из файла считываются построчно
     * Строка, содержащая : разбивается на две части, очищается от кавычек, запятых и крайних пробелов
     * В зависимости от содержания левой части строки (до :) формируется строка, которую добавляем к результату
     */
    public static StringBuilder jsonParse(FileReader localfr) throws IOException {
        StringBuilder parsedText = new StringBuilder();
        String inputString;
        BufferedReader br = new BufferedReader(localfr);
        while ((inputString = br.readLine()) != null) {
            if (inputString.contains("{")) {
                while (!(inputString = br.readLine()).contains("}")) {
                    if (inputString.contains(":")) {
                        String[] line = inputString.split(":");
                        line[0] = line[0].replace("\"", "").replace(",", "").trim();
                        line[1] = line[1].replace("\"", "").replace(",", "").trim();

                        if (line[0].equals("фамилия")) {
                            parsedText.append(String.format("Студент %s", line[1]));
                        }
                        if (line[0].equals("оценка")) {
                            parsedText.append(String.format(" получил %s", line[1]));
                        }
                        if (line[0].equals("предмет")) {
                            parsedText.append(String.format(" по предмету %s\n", line[1]));
                        }
                    }

                }

            }

        }
        return parsedText;
    }
}
