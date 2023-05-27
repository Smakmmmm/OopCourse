package ru.makhmedov.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CsvToHtmlConverter {
    public static void convertToHTML(String pathToCsv, String pathToHtml) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToCsv));
             PrintWriter printWriter = new PrintWriter(pathToHtml)) {

            printWriter.print("<!DOCTYPE html>" + System.lineSeparator());
            printWriter.print("<html lang=\"en\">" + System.lineSeparator());
            printWriter.print("<head>" + System.lineSeparator());
            printWriter.print("\t<meta charset=\"UTF-8\">" + System.lineSeparator());
            printWriter.print("\t<title>CSV</title>" + System.lineSeparator());
            printWriter.print("</head>" + System.lineSeparator());
            printWriter.print("<body>" + System.lineSeparator());
            printWriter.print("<table>" + System.lineSeparator());

            boolean needRowTag = true;
            boolean needCellTag = true;
            String currentString;

            boolean isQuoteSymbol = false;

            while ((currentString = bufferedReader.readLine()) != null) {
                char currentSymbol;
                char nextSymbol = 0;

                if (currentString.length() == 0) {
                    continue;
                }

                if (needRowTag) {
                    printWriter.print("\t<tr>" + System.lineSeparator());
                }

                if (needCellTag) {
                    printWriter.print("\t\t<td>");
                }

                for (int i = 0; i < currentString.length(); i++) {
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
                        needRowTag = false;
                        needCellTag = false;

                        isQuoteSymbol = true;

                        continue;
                    }

                    if (currentSymbol == '\"' && nextSymbol != '\"') {
                        needRowTag = true;
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
                        printWriter.print("</td>" + System.lineSeparator() + "\t\t<td>");

                        continue;
                    }

                    printWriter.print(currentSymbol);

                    if (isQuoteSymbol && currentString.charAt(currentString.length() - 1) == '\"') {
                        needRowTag = true;
                        needCellTag = true;

                        nextSymbol = 0;

                        continue;
                    }

                    if (isQuoteSymbol && i == currentString.length() - 1) {
                        printWriter.print("<br/>");
                    }
                }

                if (needCellTag) {
                    printWriter.print("</td>" + System.lineSeparator());
                }

                if (needRowTag) {
                    printWriter.print("\t</tr>" + System.lineSeparator());
                }
            }

            printWriter.print("</table>" + System.lineSeparator());
            printWriter.print("</body>" + System.lineSeparator());
            printWriter.print("</html>");
        }
    }

    public static void main(String[] args) {
        String pathToCsv = "InputCsv.csv";
        String pathToHtml = "OutputHtml.html";

        try {
            convertToHTML(pathToCsv, pathToHtml);
        } catch (IOException e) {
            System.out.println("Произошла ошибка. " + e.getMessage());
        }
    }
}
