/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scrum.psd.battleship.controller.dto;

import java.util.*;

/**
 *
 * @author arhus
 */
public class BattleField {
    public int rows, cols;
    private final HashSet<Position> availablePositions;
    private final Iterator<Position> availablePositionsIterator;
    
    public BattleField(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.availablePositions = allAvailablePositions();
        this.availablePositionsIterator = this.availablePositions.iterator();
    }

    private HashSet<Position> allAvailablePositions() {
        HashSet<Position> ap = new HashSet<Position>();

        for (int i = 1; i <= this.cols; i++) {
            for (int j = 1; j <= this.rows; j++) {
                Position p = new Position(Letter.letterFromNumber(i), j);
                ap.add(p);
                System.out.println("Added position: " + p);
            }
        }
        
        System.out.println("All positions: " + ap);
        
        return ap;
    }
    
    public void removeLast() {
        this.availablePositionsIterator.remove();
    }
    
    public Position nextPosition() {
        if (this.availablePositionsIterator.hasNext()) {
            return this.availablePositionsIterator.next();
        } else {
            return null;
        }
    }
    
    public boolean hasNextPosition() {
        return this.availablePositionsIterator.hasNext();
    }
}
