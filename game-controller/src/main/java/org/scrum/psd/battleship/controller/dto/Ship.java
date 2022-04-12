package org.scrum.psd.battleship.controller.dto;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private boolean isPlaced;
    private String name;
    private int size;
    public List<Position> positions;
    private Color color;

    public Ship() {
        this.positions = new ArrayList<>();
    }

    public Ship(String name, int size) {
        this();

        this.name = name;
        this.size = size;
    }

    public Ship(String name, int size, List<Position> positions) {
        this(name, size);

        this.positions = positions;
    }

    public Ship(String name, int size, Color color) {
        this(name, size);

        this.color = color;
    }


    public void addPosition(String input) {
        if (positions == null) {
            positions = new ArrayList<>();
        }

        try {
            Letter letter = Letter.valueOf(input.toUpperCase().substring(0, 1));
            int number = Integer.parseInt(input.substring(1));
            positions.add(new Position(letter, number));
        } catch (NumberFormatException nfe) {
            System.out.println("Enter position with letter and number!");
        }

    }

    public void addPosition(int inputHorizontal, int inputVertical) {
        if (positions == null) {
            positions = new ArrayList<>();
        }

        try {
            Letter letter = letterFromNumber(inputHorizontal);
            int number = inputVertical;
            positions.add(new Position(letter, number));
        } catch (NumberFormatException nfe) {
            System.out.println("Enter position with letter and number!");
        }

    }

    public static Letter letterFromNumber(int number) {
        switch (number) {
            case 0:
                return Letter.A;
            case 1:
                return Letter.B;
            case 2:
                return Letter.C;
            case 3:
                return Letter.D;
            case 4:
                return Letter.E;
            case 5:
                return Letter.F;
            case 6:
                return Letter.G;
            case 7:
                return Letter.H;
            default:
                return Letter.A;
        }
    }

    public boolean isDestroyed() {
        for (Position position : positions) {
            if (!position.getHit()) {
                return false;
            }
        }
        return true;
    }

    // TODO: property change listener implementieren

    public boolean isPlaced() {
        return isPlaced;
    }

    public void setPlaced(boolean placed) {
        isPlaced = placed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return name;
    }
}
