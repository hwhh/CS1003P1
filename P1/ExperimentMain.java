package com.company;


import java.io.*;

public class ExperimentMain {

    public static final String INPUT_FILE_PATH = "data-smal.csv";


    public static PrintWriter printWriter (String fileName, String format) throws FileNotFoundException, UnsupportedEncodingException {
        return new PrintWriter(fileName, format);
    }


    public static void main(String[] args) {
        try{
            //Create new experimentReader object which handels reading in file and extracting the correct data
            ExperimentReader experimentReader = new ExperimentReader(INPUT_FILE_PATH);
            //Create the new print writters which populate the output files
            PrintWriter TextWriter = printWriter("TextOut.txt","UTF-8");
            //Loop for as long as there is the required data in the input file
            while (experimentReader.hasNextExperiment()) {
                //Create a new object for each experiment which contains the data for each experiment
                ExperimentReader.Experiment e = experimentReader.getNextExperiment();
                TextWriter.println("");
                writeFile(TextWriter, e, "");
                TextWriter.println("");
            }
            addStats(TextWriter, experimentReader, "");
            TextWriter.close();
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


    //Add the statistical data to the end of the files
    public static void addStats(PrintWriter printWriter, ExperimentReader e, String s){
        printWriter.println("Longest duration " +e.longest +s);
        printWriter.println("Shortest duration " +e.shortest +s);
        printWriter.println("The number of samples that were run on the spectrometer Alec: " + e.noOfSamples +s);
        printWriter.println(Math.round((((e.total / e.count)*100)*100.0)/100.0) + "% of samples that were tested using the experiment proton.a.and " +s);

    }
    //Add the experiment data to the files
    public static void writeFile(PrintWriter printWriter, ExperimentReader.Experiment e, String s) {
        printWriter.println("");
        printWriter.println("Experiment Name " + e.sampleID +s);
        printWriter.println("Holder no. : "  + e.holderNumber+s);
        printWriter.println("Spectrometer ID : " + e.spectrometerID+s);
        printWriter.println("Group name : " + e.groupName+s);
        printWriter.println("Solvent Name : " + e.solventName+s);
        printWriter.println("Experiment Name : " + e.experimentName+s);
        printWriter.println("");

    }

}




