package com.company;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

public class Main {

    public static final String INPUT_FILE_PATH = "data-medium.csv";




    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(INPUT_FILE_PATH));
            FilesOut newFile = new FilesOut(bufferedReader);
            newFile.run();

            GraphicOut.main(args);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }


}

