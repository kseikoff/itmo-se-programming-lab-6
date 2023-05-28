package generators;

import defaultClasses.Coordinates;

import java.util.Scanner;

/**
 * A class that allows the user to create an object of class Coordinates.
 *
 * @author Dmitrii Chebanenko
 */

public class CoordinatesGenerator implements GenerateAble {

    /**
     * Method that creates an object of class Coordinates.
     *
     * @return coordinates
     */

    public Coordinates generate() {
        Coordinates coordinates;
        try {
            coordinates = new Coordinates();
            Scanner consoleInputReader = new Scanner(System.in);

            System.out.print("Enter X coordinate\n$ ");
            long x = Long.parseLong(consoleInputReader.nextLine());
            coordinates.setX(x);

            System.out.print("Enter Y coordinate\n$ ");
            int y = Integer.parseInt(consoleInputReader.nextLine());
            coordinates.setY(y);
        } catch (Exception e) {
            return null;
        }

        return coordinates;
    }

}
