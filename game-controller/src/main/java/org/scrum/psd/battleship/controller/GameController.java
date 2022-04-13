package org.scrum.psd.battleship.controller;

import org.scrum.psd.battleship.controller.dto.Color;
import org.scrum.psd.battleship.controller.dto.Letter;
import org.scrum.psd.battleship.controller.dto.Position;
import org.scrum.psd.battleship.controller.dto.Ship;

import java.util.*;
import java.util.function.Predicate;

public class GameController {

    private static List<Ship> shipsList = initializeShips(true);

    public static boolean checkIsHit(Collection<Ship> ships, Position shot) {
        if (ships == null) {
            throw new IllegalArgumentException("ships is null");
        }

        if (shot == null) {
            throw new IllegalArgumentException("shot is null");
        }

        for (Ship ship : ships) {
            for (Position position : ship.getPositions()) {
                if (position.equals(shot)) {
                    position.setHit(true);
                    return true;
                }
            }
        }

        return false;
    }

    public static List<Ship> initializeShips(boolean isMock) {
        if (isMock) {
            return Arrays.asList(
                    new Ship("Patrol Boat", 3, Color.ORANGE),
                    new Ship("Second Patrol Boat", 3, Color.YELLOW));
        } else {
            return Arrays.asList(
                    new Ship("Aircraft Carrier", 5, Color.CADET_BLUE),
                    new Ship("Battleship", 4, Color.RED),
                    new Ship("Submarine", 3, Color.CHARTREUSE),
                    new Ship("Destroyer", 3, Color.YELLOW),
                    new Ship("Patrol Boat", 2, Color.ORANGE));
        }
    }

    private static void setPositionsToShip(Ship ship, boolean isMock) {
        while (ship.getPositions().size() == 0) {
            List<Position> positionList = createPositions(ship, isMock);
            if (validatePositions(positionList, isMock)) {
                ship.positions.addAll(positionList);
            }
        }
    }

    private static boolean validatePositions(List<Position> positionList, boolean isMock) {
        int boardSize = isMock ? 7 : 3;
        boolean valueHolder = true;

        for (Position position : positionList) {
            if (position.getRow() > boardSize || position.getColumn().ordinal() > boardSize) {
                valueHolder = false;
                break;
            }
            for (Ship ship : shipsList) {
                Predicate<Position> streamsPredicate = item -> item.getColumn() == position.getColumn() && item.getRow() == position.getRow();
                if (ship.positions.stream().anyMatch(streamsPredicate)) {
                    valueHolder = false;
                    break;
                }
            }
        }

        return valueHolder;

    }

    private static List<Position> createPositions(Ship ship, boolean isMock) {
        List<Position> listOfCreatedPositions = new ArrayList<Position>();
        Random rand = new Random();
        int xPos = rand.nextInt(isMock ? 7 : 3);
        int yPos = rand.nextInt(isMock ? 7 : 3);
        boolean horizontalOrVertical = getRandomBoolean();

        for (int i = 0; i < ship.getSize(); i++) {
            if (horizontalOrVertical) {
                listOfCreatedPositions.add(new Position(Ship.letterFromNumber(xPos + i), yPos));
            } else {
                listOfCreatedPositions.add(new Position(Ship.letterFromNumber(xPos), yPos + i));
            }
        }

        return listOfCreatedPositions;
    }

    public static List<Ship> generatedShips(boolean isMock) {
        for (Ship ship : shipsList) {
            setPositionsToShip(ship, isMock);
        }
        System.out.println(shipsList);
        System.out.println(shipsList.stream().findFirst().get().getPositions().stream().findFirst().get().getRow());
        System.out.println(shipsList.stream().findFirst().get().getPositions().stream().findFirst().get().getColumn());
        return shipsList;
    }

    private static boolean getRandomBoolean() {
        return Math.random() < 0.5;
    }

    public static boolean isShipValid(Ship ship) {
        return ship.getPositions().size() == ship.getSize();
    }

    public static Position getRandomPosition(int size) {
        Random random = new Random();
        Letter letter = Letter.values()[random.nextInt(size)];
        int number = random.nextInt(size);
        Position position = new Position(letter, number);
        return position;
    }
}
