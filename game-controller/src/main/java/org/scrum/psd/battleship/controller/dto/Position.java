package org.scrum.psd.battleship.controller.dto;

public class Position {
    private Letter column;
    private int row;
    private boolean hit = false;

    public Position() {
        super();
    }

    public Position(Letter column, int row) {
        this();

        this.column = column;
        this.row = row;
    }

    public Letter getColumn() {
        return column;
    }

    public void setColumn(Letter column) {
        this.column = column;
    }

    public boolean getHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Position) {
            Position position = (Position) o;

            if (position == null) {
                return false;
            }

            return position.column.equals(column) && position.row == row;
        }

        return false;
    }
    
    public String toString() {
        return "[" + this.column + ","+this.row+"]";
    }
}
