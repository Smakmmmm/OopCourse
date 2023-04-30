package ru.makhmedov.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CSV {
    public static void convertToHTML(String filePath, String pathToSave) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
             PrintWriter printWriter = new PrintWriter(pathToSave)) {

            printWriter.print("<!DOCTYPE html>");
            printWriter.print("<html lang =\"en\">");
            printWriter.print("<head>");
            printWriter.print("<meta charset=\"UTF-8\">");
            printWriter.print("<title>CSV</title>");
            printWriter.print("</head>");
            printWriter.print("<body>");
            printWriter.print("<table>");

            char currentSymbol;
            char nextSymbol = 0;
            boolean needStringTag = true;
            boolean needCellTag = true;
            String currentString;

            boolean isQuoteSymbol = false;

            while ((currentString = bufferedReader.readLine()) != null) {
                if (currentString.length() == 0) {
                    printWriter.print("<tr></tr>");

                    continue;
                }

                if (needStringTag) {
                    stringTag(0, printWriter);
                }

                if (needCellTag) {
                    cellTag(0, printWriter);
                }

                int i = 0;

                for (; i < currentString.length(); i++) {
                    currentSymbol = currentString.charAt(i);

                    if (currentSymbol == '<') {
                        printWriter.print("&lt;");

                        continue;
                    }

                    if (currentSymbol == '>') {
                        printWriter.print("&gt;");

                        continue;
                    }

                    if (currentSymbol == '&') {
                        printWriter.print("&amp;");

                        continue;
                    }

                    if (i < currentString.length() - 1 && isQuoteSymbol) {
                        nextSymbol = currentString.charAt(i + 1);
                    }

                    if (currentSymbol == '\"' && !isQuoteSymbol) {
                        needStringTag = false;
                        needCellTag = false;

                        isQuoteSymbol = true;

                        continue;
                    }

                    if (currentSymbol == '\"' && nextSymbol != '\"') {
                        needStringTag = true;
                        needCellTag = true;

                        isQuoteSymbol = false;

                        continue;
                    }

                    if (currentSymbol == '\"') {
                        printWriter.print(currentSymbol);
                        i++;
                        nextSymbol = 0;

                        continue;
                    }

                    if (currentSymbol == ',' && !isQuoteSymbol) {
                        printWriter.print("</td><td>");

                        continue;
                    }

                    printWriter.print(currentSymbol);

                    if (isQuoteSymbol && i == currentString.length() - 1) {
                        printWriter.print("<br/>");
                    }
                }

                if (needCellTag) {
                    cellTag(currentString.length(), printWriter);
                }

                if (needStringTag) {
                    stringTag(currentString.length(), printWriter);
                }
            }

            printWriter.print("</table>");
            printWriter.print("</body>");
            printWriter.print("</html>");
        }
    }

    public static void stringTag(int symbolIndex, PrintWriter printWriter) {
        if (symbolIndex == 0) {
            printWriter.print("<tr>");

            return;
        }

        printWriter.print("</tr>");
    }

    public static void cellTag(int symbolIndex, PrintWriter printWriter) {
        if (symbolIndex == 0) {
            printWriter.print("<td>");

            return;
        }

        printWriter.print("</td>");
    }

    public static void main(String[] args) {
        try {
            convertToHTML("CSV/src/ru/makhmedov/csv/InputCSV.csv",
                    "CSV/src/ru/makhmedov/csv/OutputHTML.html");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
