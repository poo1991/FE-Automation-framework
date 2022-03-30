package com.domain.app.helpers;

import com.google.common.collect.ImmutableList;

import com.domain.app.enums.ScrollDirection;
import com.domain.app.frameworkConfig.Base;
import com.domain.app.frameworkConfig.TestContext;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.interactions.Sequence;

import java.net.MalformedURLException;
import java.time.Duration;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;


public class ScrollMobileHelper extends Base {

    private static Duration SCROLL_DUR = Duration.ofMillis(1000);
    private static double SCROLL_RATIO = 0.8;
    private static int ANDROID_SCROLL_DIVISOR = 3;

    public ScrollMobileHelper(TestContext context) throws MalformedURLException {
        super(context);
    }

    private Dimension getWindowSize() {
        return driver.manage().window().getSize();
    }

    protected void swipe(Point start, Point end, Duration duration) {
        AppiumDriver d = (AppiumDriver) driver;
        boolean isAndroid = d instanceof AndroidDriver;

        PointerInput input = new PointerInput(Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(input, 0);
        swipe.addAction(input.createPointerMove(Duration.ZERO, Origin.viewport(), start.x, start.y));
        swipe.addAction(input.createPointerDown(MouseButton.LEFT.asArg()));
        if (isAndroid) {
            duration = duration.dividedBy(ANDROID_SCROLL_DIVISOR);
        } else {
            swipe.addAction(new Pause(input, duration));
            duration = Duration.ZERO;
        }
        swipe.addAction(input.createPointerMove(duration, Origin.viewport(), end.x, end.y));
        swipe.addAction(input.createPointerUp(MouseButton.LEFT.asArg()));
        d.perform(ImmutableList.of(swipe));
    }

    protected void swipe(double startXPct, double startYPct, double endXPct, double endYPct, Duration duration) {
        Dimension size = getWindowSize();
        Point start = new Point((int) (size.width * startXPct), (int) (size.height * startYPct));
        Point end = new Point((int) (size.width * endXPct), (int) (size.height * endYPct));
        swipe(start, end, duration);
    }

    protected void scroll(ScrollDirection dir, double distance) {
        if (distance < 0 || distance > 1) {
            throw new Error("Scroll distance must be between 0 and 1");
        }
        Dimension size = getWindowSize();
        Point midPoint = new Point((int) (size.width * 0.5), (int) (size.height * 0.5));
        int top = midPoint.y - (int) ((size.height * distance) * 0.5);
        int bottom = midPoint.y + (int) ((size.height * distance) * 0.5);
        int left = midPoint.x - (int) ((size.width * distance) * 0.5);
        int right = midPoint.x + (int) ((size.width * distance) * 0.5);
        if (dir == ScrollDirection.UP) {
            swipe(new Point(midPoint.x, top), new Point(midPoint.x, bottom), SCROLL_DUR);
        } else if (dir == ScrollDirection.DOWN) {
            swipe(new Point(midPoint.x, bottom), new Point(midPoint.x, top), SCROLL_DUR);
        } else if (dir == ScrollDirection.LEFT) {
            swipe(new Point(left, midPoint.y), new Point(right, midPoint.y), SCROLL_DUR);
        } else {
            swipe(new Point(right, midPoint.y), new Point(left, midPoint.y), SCROLL_DUR);
        }
    }

    protected void scroll(ScrollDirection dir) {
        scroll(dir, SCROLL_RATIO);
    }

    protected void scroll() {
        scroll(ScrollDirection.DOWN, SCROLL_RATIO);
    }


}

