package com.company;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.*;

public class GraphicOut extends Application {


    public static final String CVS_SPLIT_BY = ",";
    final static String[] SPECTROMETER_NAME= {"Alec", "Felix"};
    final static String[] SOLVENT_NAME= {"CDCl3","Acetone","H2OD2O","DMSO","C6D6"};


    @Override
    public void start(Stage stage) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Main.INPUT_FILE_PATH));
        String line = null, temp = null;
        String[] records = null;
        int CDCl3 = 0,Acetone = 0,H2OD2O = 0,DMSO = 0,C6D6 = 0;

        stage.setTitle("Solvent vs Duration");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> barChart = new BarChart<String,Number>(xAxis,yAxis);

        barChart.setTitle("Solvent Comparison");
        xAxis.setLabel("Solvent ID");
        yAxis.setLabel("Frequency Of Tests");
        XYChart.Series series1 = new XYChart.Series();
        series1.setName(SPECTROMETER_NAME[1]);

        while ((line = bufferedReader.readLine()) != null) {
            if (line.trim().equals("")) {
                System.exit(0);
            } else {
                records = line.split(CVS_SPLIT_BY);
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
            series1.getData().add(new XYChart.Data(SOLVENT_NAME[0], CDCl3));
            series1.getData().add(new XYChart.Data(SOLVENT_NAME[1], Acetone));
            series1.getData().add(new XYChart.Data(SOLVENT_NAME[2], H2OD2O));
            series1.getData().add(new XYChart.Data(SOLVENT_NAME[3], DMSO));
            series1.getData().add(new XYChart.Data(SOLVENT_NAME[4], C6D6));
        }
        Scene scene  = new Scene(barChart,800,600);
        barChart.getData().addAll(series1);
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}