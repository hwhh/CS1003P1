package com.company;

import java.io.*;



public class ExperimentReader {


    public class Experiment {
        int sampleID, holderNumber, spectrometerID;
        String groupName, solventName, experimentName;

    }

    public static final String CVS_SPLIT_BY = ",";


    private BufferedReader bufferedReader;
    private Experiment nextExperiment = null;
    private String[] records;
    String shortest, longest;
    int Noah,Marcus,Robbin,Felix,Alec,Hector, totalDuration, CDCl3, Acetone, H2OD2O, DMSO, C6D6;
    double count = 0, total = 0;


    public ExperimentReader(String inputFileName) throws FileNotFoundException {
        bufferedReader = new BufferedReader(new FileReader(inputFileName));
    }

    //Get the next line of the input file and add contents to array
    private void findNextExperiment() throws IOException {
        String line = bufferedReader.readLine();
        if (line != null) {
            records = line.split(CVS_SPLIT_BY);
            nextExperiment = new Experiment();
            assignValues(records);
        }

    }

    private void spectrometerCount(String[] records){
        switch(Integer.parseInt(records[33])) {
            case (1):Noah++;
                break;
            case (2):Marcus++;
                break;
            case (3):Robbin++;
                break;
            case (4):Felix++;
                break;
            case (5):Alec++;
                break;
            case (6):Hector++;
                break;
        }

    }

    public void solventName(String[] records){
        switch (records[27]){
            case ("CDCl3"):CDCl3++;
                break;
            case("Acetone"):Acetone++;
                break;
            case("H2O+D2O"):H2OD2O++;
                break;
            case ("DMSO"):DMSO++;
                break;
            case ("C6D6"):C6D6++;
                break;
        }

    }



    private void assignValues(String[] records) throws IOException {
        //Ignore the first line of the input file
        if (count == 0){
            count++;
            findNextExperiment();
        }
        else{
            //Functionality to add summary statistics to the output file
            if (count==1){
                shortest=records[9];
                longest=records[9];
            }
            if (count > 0 && records[11].equals("proton.a.and"))
                total++;
            //Set the correct values from the array to the attributes of the experiment class
            nextExperiment.sampleID = Integer.parseInt(records[0]);
            nextExperiment.holderNumber = Integer.parseInt(records[3]);
            nextExperiment.spectrometerID = Integer.parseInt(records[33]);
            nextExperiment.groupName = records[23];
            nextExperiment.solventName = records[28];
            nextExperiment.experimentName = records[11];
            compareTime(timeToInt(records[9]));
            count++;
            spectrometerCount(records);

        }
    }


    //Coverts the duration data from the input file
    public static String intToTime(int time){
        int count = 0;
        String s = null;
        while (time>86400){
            time = time - 86400;
            count++;
        }
        int hours = time / 3600;
        int minutes = (time % 3600) / 60;
        int seconds = time % 60;
        if (count==1){
            s="1 day and ";
        }
        else if (count>1){
            s=count+" days and ";
        }
        return s+String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }


    //Coverts the duration data from the input file
    public static int timeToInt(String time){
        int hour = 3600;
        int min = 60;
        String[] times = time.split(":");
        return (Integer.parseInt(times[0]) * hour) + (Integer.parseInt(times[1]) * min) + Integer.parseInt(times[2]);
    }

    //Compares the duration data to find longest and shortest experiment times
    public void compareTime(int time){
        if (time<timeToInt(shortest))
            shortest=records[9];
        else if (time>timeToInt(longest))
            longest = records[9];
        totalDuration+=time;
    }


    //Check to see whether there are any more experiments in the input file
    public boolean hasNextExperiment() throws IOException {
        if (nextExperiment == null)
            findNextExperiment();
        return (nextExperiment != null);
    }

    //Clear the data in the experiments object
    public Experiment getNextExperiment() {
        Experiment experiment = nextExperiment;
        nextExperiment = null;
        return experiment;
    }



}


