package ru.makhmedov.csv;

import java.io.*;

public class CsvToHtmlConverter {
    public static void convertToHtml(String pathToCsv, String pathToHtml) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToCsv));
             PrintWriter printWriter = new PrintWriter(pathToHtml)) {
            printWriter.println("<!DOCTYPE html>");
            printWriter.println("<html lang=\"en\">");
            printWriter.println("<head>");
            printWriter.println("\t<meta charset=\"UTF-8\">");
            printWriter.println("\t<title>CSV</title>");
            printWriter.println("</head>");
            printWriter.println("<body>");
            printWriter.println("<table>");

            boolean needRowTag = true;
            boolean needCellTag = true;
            String currentString;

            boolean isQuoteSymbol = false;

            while ((currentString = bufferedReader.readLine()) != null) {
                if (currentString.isEmpty()) {
                    continue;
                }

                if (needRowTag) {
                    printWriter.println("\t<tr>");
                }

                if (needCellTag) {
                    printWriter.print("\t\t<td>");
                }

                char nextSymbol = 0;

                for (int i = 0; i < currentString.length(); i++) {
                    char currentSymbol = currentString.charAt(i);

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
                        printWriter.println("</td>");
                        printWriter.print("\t\t<td>");

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
                    printWriter.println("</td>");
                }

                if (needRowTag) {
                    printWriter.println("\t</tr>");
                }
            }

            printWriter.println("</table>");
            printWriter.println("</body>");
            printWriter.print("</html>");
        }
    }

    public static void main(String[] args) {
        try {
            if (args.length != 2) {
                throw new IllegalArgumentException();
            }

            convertToHtml(args[0], args[1]);
        } catch (IllegalArgumentException e) {
            System.out.println("Было передано неправильное количество аргументов. Необходимо: 2. Сейчас их количество: " + args.length + ".");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден. Текущий путь: " + args[0]);
        } catch (IOException e) {
            System.out.println("Произошла ошибка. " + e.getMessage());
        }
    }
}
