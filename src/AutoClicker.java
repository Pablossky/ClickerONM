package org.ea;

import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.ThreadLocalRandom;

public class AutoClicker {

    public static void main(String[] args) {
        try {

            AtomicBoolean loop = new AtomicBoolean(true);
            int minGame = 500;
            int maxGame = 1100;
            int randomNumGame = ThreadLocalRandom.current().nextInt(minGame, maxGame + 1);
            int minWaiting = 4000;
            int maxWaiting = 5000;
            int randomNumWaiting = ThreadLocalRandom.current().nextInt(minWaiting, maxWaiting + 1);
            int minRelease = 250;
            int maxRelease = 450;
            int randomNumRelease = ThreadLocalRandom.current().nextInt(minRelease, maxRelease + 1);

            //Random X
            int minThrow = 600;
            int maxThrow = 1200;

            int startingCoordX = 1374;
            int startingCoordY = 764;

            int choosingCoordX = 972;
            int choosingCoordY = 637;

            int playingCoordY = 522;

            int exitCoordX = 486;
            int exitCoordY = 498;

            // Create a CompletableFuture for user input
            CompletableFuture<String> futureInput = new CompletableFuture<>();

            // Start a new thread to read user input
            new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                String input = scanner.next();
                futureInput.complete(input);
            }).start();

            // Wait for user input
            futureInput.thenAccept(input -> {
                    loop.set(false);
                });

            while (true) {
                if(!loop.get()){break;}

                Robot r = new Robot();
                int button = InputEvent.BUTTON1_DOWN_MASK;
                int j = 0;
                PointerInfo localisation = MouseInfo.getPointerInfo();
                Point cursorPos = localisation.getLocation();
                int x = (int) cursorPos.getX();
                int y = (int) cursorPos.getY();
                System.out.print(y + "   ");
                System.out.print(x + "   ");
                Thread.sleep(randomNumWaiting);

                //Starting Game
                r.mouseMove(startingCoordX, startingCoordY);
                Thread.sleep(randomNumWaiting);
                System.out.println("Starting...");
                r.mousePress(button);
                Thread.sleep(randomNumRelease);
                r.mouseRelease(button);
                Thread.sleep(2000);

                //Choosing Character
                r.mouseMove(choosingCoordX, choosingCoordY);
                Thread.sleep(2000);
                System.out.println("Choosing...");
                r.mousePress(button);
                Thread.sleep(randomNumRelease);
                r.mouseRelease(button);
                Thread.sleep(2000);
                r.mouseMove(startingCoordX, startingCoordY);
                System.out.println("Accept");
                r.mousePress(button);
                Thread.sleep(randomNumRelease);
                r.mouseRelease(button);
                Thread.sleep(2000);

                //Game

                Thread.sleep(randomNumWaiting);
                for (j = 0; j < 80; j++) {
                    if (j<50) {
                        r.mouseMove((ThreadLocalRandom.current().nextInt(minThrow, maxThrow + 1)), playingCoordY);
                        System.out.println("Throw " + j);
                        r.mousePress(button);
                        Thread.sleep(150);
                        r.mouseRelease(button);
                        Thread.sleep(randomNumGame-200);
                    }
                    else {
                        r.mouseMove((ThreadLocalRandom.current().nextInt(minThrow, maxThrow + 1)), playingCoordY);
                        System.out.println("Throw " + j);
                        r.mousePress(button);
                        Thread.sleep(200);
                        r.mouseRelease(button);
                        Thread.sleep(randomNumGame+200);
                    }
                }
                Thread.sleep(randomNumWaiting);

                //Exit
                Thread.sleep(randomNumWaiting);
                r.mouseMove(exitCoordX, exitCoordY);
                Thread.sleep(randomNumWaiting);
                System.out.println("Ending...");
                r.mousePress(button);
                Thread.sleep(randomNumRelease);
                r.mouseRelease(button);
                Thread.sleep(2000);
                }
            //System.exit(0);
        }
         catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
