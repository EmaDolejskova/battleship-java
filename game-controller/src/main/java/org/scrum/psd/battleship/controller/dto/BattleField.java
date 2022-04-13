/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scrum.psd.battleship.controller.dto;

import java.awt.PageAttributes;
import java.util.ArrayList;
import java.util.HashSet;
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
    
    private ArrayList<Position> availablePositions;
    public ArrayList<Position> getAllAvailablePositions(){
        return this.availablePositions;
    }
    private ArrayList<Position> allAvailablePositions() {
        ArrayList<Position> ap = new ArrayList<Position>();

        for (int i = 0; i <= this.cols-1; i++) {
            for (int j = 1; j <= this.rows; j++) {
                Position p = new Position(Letter.letterFromNumber(i), j);
                ap.add(p);
//                System.out.println("Added position: " + p);
            }
        }
        
        return ap;
    }
    
    public boolean removePosition(Position p) {
        return this.availablePositions.remove(p);
    }
}
