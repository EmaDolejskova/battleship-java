package org.scrum.psd.battleship.ascii;

import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.scrum.psd.battleship.controller.dto.Letter;
import org.scrum.psd.battleship.controller.dto.Position;
import org.scrum.psd.battleship.controller.dto.BattleField;


@Execution(ExecutionMode.CONCURRENT)
public class MainTest {

    @Test
    public void testParsePosition() {
        Position actual = Main.parsePosition("A1");
        Position expected = new Position(Letter.A, 1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testParsePosition2() {
        //given
        Position expected = new Position(Letter.B, 1);
        //when
        Position actual = Main.parsePosition("B1");
        //then
        Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void testGetRandomPosition() {
        int rows = 4;
        int cols = 4;
        int total = rows * cols;
        ArrayList<Position> randomPositions = new ArrayList<Position>();
                
        BattleField bf = new BattleField(rows, cols);
        
        for (int i = 1; i <= total; i++) {
            Position randomPosition = Main.getRandomPosition(bf);
            System.out.println("New random position: " + randomPosition);
            Assertions.assertFalse(randomPositions.contains(randomPosition));
            randomPositions.add(randomPosition);
        }
        
    }
}
