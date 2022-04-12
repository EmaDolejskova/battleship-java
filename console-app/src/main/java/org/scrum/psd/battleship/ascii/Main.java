package org.scrum.psd.battleship.ascii;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
import org.scrum.psd.battleship.controller.GameController;
import org.scrum.psd.battleship.controller.dto.Letter;
import org.scrum.psd.battleship.controller.dto.Position;
import org.scrum.psd.battleship.controller.dto.Ship;
import org.scrum.psd.battleship.controller.dto.BattleField;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static List<Ship> myFleet;
    public static List<Ship> enemyFleet;
    private static ColoredPrinter console;
    public static boolean isMock = true;

    private static BattleField battleField = isMock ? new BattleField(4, 4) : new BattleField(8, 8);

    public static void main(String[] args) {
        if (args.length > 0) {
            Main.isMock = Boolean.valueOf(args[0]);
        }
        console = new ColoredPrinter.Builder(1, false).background(Ansi.BColor.BLACK).foreground(Ansi.FColor.WHITE).build();

        console.setForegroundColor(Ansi.FColor.MAGENTA);
        console.println("                                     |__");
        console.println("                                     |\\/");
        console.println("                                     ---");
        console.println("                                     / | [");
        console.println("                              !      | |||");
        console.println("                            _/|     _/|-++'");
        console.println("                        +  +--|    |--|--|_ |-");
        console.println("                     { /|__|  |/\\__|  |--- |||__/");
        console.println("                    +---------------___[}-_===_.'____                 /\\");
        console.println("                ____`-' ||___-{]_| _[}-  |     |_[___\\==--            \\/   _");
        console.println(" __..._____--==/___]_|__|_____________________________[___\\==--____,------' .7");
        console.println("|                        Welcome to Brikulnici Battleship               BB-61/");
        console.println(" \\_________________________________________________________________________|");
        console.println("");
        console.setForegroundColor(Ansi.FColor.WHITE);
        console.println("Mock is " + (isMock ? "enabled" : "disabled"));

        InitializeGame();
        StartGame();
    }

    private static void StartGame() {
        Scanner scanner = new Scanner(System.in);
        startPrint();
        do {
            playersTurnPrint();
            Position position = parsePosition(scanner.next());
            boolean isHit = GameController.checkIsHit(enemyFleet, position);
            if (isHit) console.setForegroundColor(Ansi.FColor.RED);
            else console.setForegroundColor(Ansi.FColor.BLUE);
            if (isHit) {
                beep();
                hitPrint();
            }
            separatorPrint();
            console.println(isHit ? "Yeah ! Nice hit !" : "You missed ;( ");
            separatorPrint();
            if (getDestroyedShips(false, enemyFleet).size() == 0) {
                console.println("Yeah ! You WON !");
                System.exit(0);
            }
            console.setForegroundColor(Ansi.FColor.WHITE);
            // Computer is trying hit with Random to position
//            position = getRandomPosition();

            position = getComputerRandomPosition(battleField);
            isHit = GameController.checkIsHit(myFleet, position);
            console.println("");
            if (isHit) console.setForegroundColor(Ansi.FColor.RED);
            else console.setForegroundColor(Ansi.FColor.BLUE);
            console.println(String.format("Computer shoot in %s%s and %s", position.getColumn(), position.getRow(), isHit ? "hit your ship !" : "miss"));

            if (isHit) {
                beep();
                hitPrint();
            }

            separatorPrint();
            console.setForegroundColor(Ansi.FColor.WHITE);
        } while (true);
    }

    private static void startPrint() {
        console.print("\033[2J\033[;H");
        console.println("                  __");
        console.println("                 /  \\");
        console.println("           .-.  |    |");
        console.println("   *    _.-'  \\  \\__/");
        console.println("    \\.-'       \\");
        console.println("   /          _/");
        console.println("  |      _  /\" \"");
        console.println("  |     /_\'");
        console.println("   \\    \\_/");
        console.println("    \" \"\" \"\" \"\" \"");
    }

    private static void playersTurnPrint() {
        List<Ship> enemySunkFleet = getDestroyedShips(true, enemyFleet);
        List<Ship> enemyRemainingFleet = getDestroyedShips(false, enemyFleet);

        console.setForegroundColor(Ansi.FColor.GREEN);
        console.println("");
        console.println("Player, it's your turn");
        console.println("Ships to destroy: " + enemyRemainingFleet.toString());
        console.println("Ships destroyed:" + enemySunkFleet.toString());
        console.println("-=ACTION=-: Enter coordinates for your shot :");
        separatorPrint();
        console.setForegroundColor(Ansi.FColor.WHITE);
    }

    private static List<Ship> getDestroyedShips(boolean destroyed, List<Ship> fleet) {
        List<Ship> newFleetList = new ArrayList<>();
        for (Ship ship : fleet) {
            if (ship.isDestroyed() == destroyed) {
                newFleetList.add(ship);
            }
        }
        return newFleetList;
    }

    private static void hitPrint() {
        console.println("                \\         .  ./");
        console.println("              \\      .:\" \";'.:..\" \"   /");
        console.println("                  (M^^.^~~:.'\" \").");
        console.println("            -   (/  .    . . \\ \\)  -");
        console.println("               ((| :. ~ ^  :. .|))");
        console.println("            -   (\\- |  \\ /  |  /)  -");
        console.println("                 -\\  \\     /  /-");
        console.println("                   \\  \\   /  /");
    }


    private static void separatorPrint() {
        console.println("----------------------------------------");
    }

    private static void beep() {
        console.print("\007");
    }

    protected static Position parsePosition(String input) {
        Letter letter = Letter.valueOf(input.toUpperCase().substring(0, 1));
        int number = Integer.parseInt(input.substring(1));
        return new Position(letter, number);
    }

    public static Position getRandomPosition() {
        return getRandomPosition(battleField.rows, battleField.cols);
    }

    public static Position getRandomPosition(int rows, int cols) {
        Random random = new Random();
        Letter letter = Letter.values()[random.nextInt(cols)];
        int number = random.nextInt(rows);
        Position position = new Position(letter, number);
        return position;
    }

    public static Position getRandomPosition(BattleField bf) {
        Random random = new Random();
        Letter letter = Letter.values()[random.nextInt(bf.cols)];
        int number = random.nextInt(bf.rows);
        Position position = new Position(letter, number);
        boolean success = bf.removePosition(position);
        System.out.println("Remove of " + position + " succeeded? " + success);
        return position;
    }

    public static Position getComputerRandomPosition(BattleField bf) {
//        int size = bf.getAllAvailablePositions().size();
//        for (int i = 0; i < size; i++) {
//            System.out.println("size="+ bf.getAllAvailablePositions().size());
            int index = (int) (Math.random() * bf.getAllAvailablePositions().size());
            Position now = bf.getAllAvailablePositions().get(index);
//            System.out.println("Random Element is :" + now);
            bf.removePosition(now);
            return now;
//        }
    }

    private static void InitializeGame() {
        InitializeMyFleet();

        InitializeEnemyFleet();
    }

    private static void InitializeMyFleet() {
        Scanner scanner = new Scanner(System.in);
        myFleet = GameController.initializeShips(isMock);

        console.println("Please position your fleet (Game board has size from A to H and 1 to 8) :");

        for (Ship ship : myFleet) {
            console.println("");
            console.println(String.format("Please enter the positions for the %s (size: %s)", ship.getName(), ship.getSize()));
            for (int i = 1; i <= ship.getSize(); i++) {
                console.println(String.format("Enter position %s of %s (i.e A3):", i, ship.getSize()));

                String positionInput = scanner.next();
                ship.addPosition(positionInput);
            }
        }
    }

    private static void InitializeEnemyFleet() {
        enemyFleet = GameController.generatedShips(isMock);
        if (!isMock) {
            enemyFleet.get(0).getPositions().add(new Position(Letter.B, 4));
            enemyFleet.get(0).getPositions().add(new Position(Letter.B, 5));
            enemyFleet.get(0).getPositions().add(new Position(Letter.B, 6));
            enemyFleet.get(0).getPositions().add(new Position(Letter.B, 7));
            enemyFleet.get(0).getPositions().add(new Position(Letter.B, 8));

            enemyFleet.get(1).getPositions().add(new Position(Letter.E, 6));
            enemyFleet.get(1).getPositions().add(new Position(Letter.E, 7));
            enemyFleet.get(1).getPositions().add(new Position(Letter.E, 8));
            enemyFleet.get(1).getPositions().add(new Position(Letter.E, 9));

            enemyFleet.get(2).getPositions().add(new Position(Letter.A, 3));
            enemyFleet.get(2).getPositions().add(new Position(Letter.B, 3));
            enemyFleet.get(2).getPositions().add(new Position(Letter.C, 3));

            enemyFleet.get(3).getPositions().add(new Position(Letter.F, 8));
            enemyFleet.get(3).getPositions().add(new Position(Letter.G, 8));
            enemyFleet.get(3).getPositions().add(new Position(Letter.H, 8));

            enemyFleet.get(4).getPositions().add(new Position(Letter.C, 5));
            enemyFleet.get(4).getPositions().add(new Position(Letter.C, 6));
        } else {
            enemyFleet.get(0).getPositions().add(new Position(Letter.A, 5));
            enemyFleet.get(0).getPositions().add(new Position(Letter.A, 6));
            enemyFleet.get(1).getPositions().add(new Position(Letter.B, 5));
            enemyFleet.get(1).getPositions().add(new Position(Letter.B, 6));

        }
    }
}
