package com.company;

import java.io.*;
import java.text.ParseException;


public class FilesOut {


    public static final String CVS_SPLIT_BY = ",";

    BufferedReader bufferedReader;
    public String[] records;
    PrintWriter textWriter = new PrintWriter("outTEXT.txt", "UTF-8");
    PrintWriter HTMLWriter = new PrintWriter("outHTML.html", "UTF-8");
    boolean end = false;
    String line = null, temp = null, shortTime = null, longTime = null;
    int count = 0, noOfSamples = 0, percentage = 0, total = 0;
    int shortRecord = 0, longRecord = 0;



    public FilesOut(BufferedReader bufferedReader) throws IOException {
        this.bufferedReader = bufferedReader;
    }

    public void run() throws IOException, ParseException {
        HTMLWriter.println("<html>\n<head>\n<title> Test Results</title>\n</head>\n<h1> Test Results </h1>\n<body>");
        while ((line = bufferedReader.readLine()) != null) {

            if (line.trim().equals("")) {
                System.exit(0);
            } else {
                records = line.split(CVS_SPLIT_BY);
                if (count > 0) {
                    HTMLWriter.println("<p>");
                    writeFile(textWriter);
                    writeFile(HTMLWriter);
                    HTMLWriter.println("</p>");
                }
                if (count >= 1) {
                    temp = records[9].replace(":", "");
                    temp = temp.replaceFirst("^0+(?!$)", "");
                }
                if (count == 1) {
                    shortRecord = Integer.parseInt(temp);
                    longRecord = Integer.parseInt(temp);
                    shortTime = records[9];
                    longTime = records[9];
                }
                if (count > 1 && Integer.parseInt(temp) < shortRecord) {
                    shortRecord = Integer.parseInt(temp);
                    shortTime = records[9];
                } else if (count > 1 && Integer.parseInt(temp) > longRecord) {
                    longRecord = Integer.parseInt(temp);
                    longTime = records[9];
                }
                if (count > 0 && records[34].equals("Alec"))
                    noOfSamples++;
                if (count > 0 && records[11].equals("proton.a.and"))
                    total++;
                count++;
            }
        }
        total = count / total;
        end = true;
        HTMLWriter.println("<p>");
        writeFile(textWriter);
        writeFile(HTMLWriter);
        HTMLWriter.println("</p");
        HTMLWriter.println("</body>\n</html>");
        textWriter.close();
        HTMLWriter.close();
    }


    public void writeFile(PrintWriter printWriter) {
        printWriter.println("[ Sample " + records[0] + " ]");
        if (printWriter.equals(HTMLWriter)) HTMLWriter.println("<br />");
        printWriter.println("Holder no. : " + records[3]);
        if (printWriter.equals(HTMLWriter)) HTMLWriter.println("<br />");
        printWriter.println("Spectrometer ID : " + records[31]);
        if (printWriter.equals(HTMLWriter)) HTMLWriter.println("<br />");
        printWriter.println("Group name : " + records[23]);
        if (printWriter.equals(HTMLWriter)) HTMLWriter.println("<br />");
        printWriter.println("Solvent Name : " + records[28]);
        if (printWriter.equals(HTMLWriter)) HTMLWriter.println("<br />");
        printWriter.println("Experiment Name : " + records[11]);
        printWriter.println();
        if (end){
            printWriter.println();
            HTMLWriter.println("<p>");
            printWriter.println("Shortest Record: " + shortTime + "  Longest Record: " + longTime);
            HTMLWriter.println("</p><p>");
            printWriter.println("No. Of Samples: " + noOfSamples);
            HTMLWriter.println("</p><p>");
            printWriter.println("Percentage: " + total + "%");
            HTMLWriter.println("</p>");
        }

    }







}



