package com.domain.app.helpers;

import com.domain.app.utility.ConfigFileReader;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.presentation.PresentationMode;

import java.io.File;
import java.util.ArrayList;

public class ReportHelper {

    ConfigFileReader reader = new ConfigFileReader();
    public static void generateCucumberReport() {
        File reportOutputDirectory = new File("target");
        ArrayList<String> jsonFiles = new ArrayList<>();
        ConfigFileReader reader = new ConfigFileReader();
//        jsonFiles.add("target/Nexus/cucumber.json");
//        jsonFiles.add("target/Pixel/cucumber.json");

        String buildNumber = "1";
        String projectName ="Academy_Automation";

        addConfiguration(reportOutputDirectory,jsonFiles,buildNumber,projectName,reader);

        File file = new File("target/rerun.txt");
        if (!(file.length() == 0)) {
            ArrayList<String> jsonFilesRerun = new ArrayList<>();
            jsonFilesRerun.add("target/cucumberRerun.json");
            buildNumber = "1-Rerun";
            reportOutputDirectory= new File("target/cucumber-html-reports/Rerun");

            addConfiguration(reportOutputDirectory,jsonFilesRerun,buildNumber,projectName,reader);
        }
    }

    public static void addConfiguration(File opDirectory, ArrayList<String> jsonFiles,String buildNumber,String projectName,ConfigFileReader reader) {
        Configuration config = new Configuration(opDirectory, projectName);
//        config.addPresentationModes(PresentationMode.PARALLEL_TESTING);
        config.setBuildNumber(buildNumber);
        config.addClassifications("TestPlatform", reader.getTestPlatform());
        if(reader.getTestPlatform().equalsIgnoreCase("Desktop")) {
            config.addClassifications("Platform", System.getProperty("os.name"));
            config.addClassifications("ApplicationUrl", reader.getApplicationUrl());
            config.addClassifications("Browser", reader.getBrowser().toString());
        }
        else if(reader.getTestPlatform().equalsIgnoreCase("Mobile")) {
            config.addPresentationModes(PresentationMode.PARALLEL_TESTING);
//            config.setQualifier("/Nexus/cucumber.json","Nexus");
//            config.setQualifier("/Pixel/cucumber.json","Pixel");
//            config.addClassifications("Mobile Device Type", reader.getMobileDeviceType().toString());
            config.addClassifications("Appium Server", reader.getAppiumServerUrl());
//            config.addClassifications("Mobile Device Name", reader.getAndroidParams().getDeviceName());
        }

        //This will generate a detailed report. If there are failures it shows the step which failed
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, config);
        reportBuilder.generateReports();


    }
}
