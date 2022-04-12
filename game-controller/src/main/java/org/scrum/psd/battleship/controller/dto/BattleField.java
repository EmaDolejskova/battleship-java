/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scrum.psd.battleship.controller.dto;

import java.awt.PageAttributes;
import java.util.TreeSet;

/**
 *
 * @author arhus
 */
public class BattleField {
    public int rows, cols;
    
    public BattleField(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.availablePositions = allAvailablePositions();
    }
    
    private TreeSet<Position> availablePositions;
    
    public TreeSet<Position> allAvailablePositions() {
        TreeSet<Position> ap = new TreeSet<Position>();

        for (int i = 1; i <= this.cols; i++) {
            for (int j = 1; j <= this.rows; j++) {
                Position p = new Position(Letter.letterFromNumber(i), j);
                ap.add(p);
                System.out.println("Added position: " + p);
            }
        }
        
        return ap;
    }
    
    public boolean removePosition(Position p) {
        return this.availablePositions.remove(p);
    }
}
