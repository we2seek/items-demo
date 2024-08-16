package com.we2seek.items_demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Integration tests
 */
public class ItemsDemoApplicationIT {

    private static final char EMPTY_CHAR = ' ';
    private static final char FILL_CHAR = '.';
    private static final char[] DOTS = new char[10];

    @BeforeEach
    public void setUp() {
        Arrays.fill(DOTS, EMPTY_CHAR);
    }

    @Test
    public void someFakeTestOne() throws InterruptedException {
        for (int i = 0; i < DOTS.length; i++) {
            for (int j = 0; j <= i; j++) {
                DOTS[j] = FILL_CHAR;
            }
            System.out.println("Test #1" + new String(DOTS));
            Thread.sleep(System.currentTimeMillis() % 1000);
        }

        Thread.sleep(1000);
    }

    @Test
    public void someFakeTestTwo() throws InterruptedException {
        for (int i = 0; i < DOTS.length; i++) {
            for (int j = 0; j <= i; j++) {
                DOTS[j] = FILL_CHAR;
            }
            System.out.println("Test #2" + new String(DOTS));
            Thread.sleep(System.currentTimeMillis() % 1000);
        }

        Thread.sleep(1000);
    }
}
