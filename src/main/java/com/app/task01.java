/**
 * task01
 * 1 . Дана строка sql-запроса "select * from students where ".
 * Сформируйте часть WHERE этого запроса, используя StringBuilder.
 * Данные для фильтрации приведены ниже в виде json строки.
 * Если значение null, то параметр не должен попадать в запрос.
 * Параметры для фильтрации: {"name":"Ivanov", "country":"Russia",
 * "city":"Moscow", "age":"null"}
 */
package com.app;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class task01 {
    public static void main(String[] args) throws IOException {

        String sqlRequest = "SELECT * FROM students WHERE";
        StringBuilder fullRequest = new StringBuilder();
        fullRequest.append(sqlRequest);
        String pathSeparator = System.getProperty("file.separator");
        String filePath = String.format("ext%swhereParameters.json",pathSeparator);
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);

        String whereParameters = br.readLine();
        br.close();
        Map<String, String> g = new Gson().fromJson(whereParameters, Map.class);
        
        for (Map.Entry<String, String> entry : g.entrySet()) {
            String key = entry.getKey();
            String val = entry.getValue();
            if (!val.equals("null")) {
                fullRequest.append(String.format(" %s=\'%s\'", key, val));
            }
        }
        fullRequest.append(";");
        System.out.println(fullRequest);
    }
}
