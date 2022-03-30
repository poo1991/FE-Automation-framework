package com.domain.app.frameworkConfig;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.appium.java_client.service.local.AppiumDriverLocalService;

public class AppiumServerManager {

    private static AppiumDriverLocalService appiumServer;
    private static final Logger LOG = LogManager.getLogger();

    private static AppiumDriverLocalService getAppiumServerDefault() {
        return AppiumDriverLocalService.buildDefaultService();
    }

    /**
     * This method starts the Appium Server
     */
    public static void startAppiumServer() {
        appiumServer = getAppiumServerDefault();
        System.out.println(appiumServer.isRunning());
        appiumServer.start();
        appiumServer.clearOutPutStreams();
        LOG.info("APPIUM SERVER STARTED:"+appiumServer.getUrl());
    }

    /**
     * This method stops the Appium Server
     */
    public static void stopAppiumServer() {
        appiumServer.stop();
        System.out.println("STOPPED APPIUM SERVER");
        LOG.info("APPIUM SERVER STOPPED");
    }
}
