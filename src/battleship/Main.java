package battleship;
import java.util.Scanner;
import java.lang.Exception;
import java.util.Arrays;

class Battleship {
    public String[][] field = {
            {"-", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
            {"A", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"B", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"C", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"D", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"E", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"F", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"G", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"H", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"I", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"J", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"}
    };
    public String[][] shipMap = {
            {"-", "-", "-", "-", "-"},
            {"-", "-", "-", "-"},
            {"-", "-", "-"},
            {"-", "-", "-"},
            {"-","-"}
    };
    public String[][] fogField = {
            {"-", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
            {"A", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"B", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"C", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"D", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"E", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"F", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"G", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"H", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"I", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"J", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"}
    };

    public boolean validate(String c1, String c2, String ship) throws Exception {
        // Validating field
        /*
            - Coordinates are within array bounds
            - c1 and c2 are horizontal or vertical only...
            - c1 < c2; according to the length if not... swap
            - Aircraft should be 5 battleship should be 4 and so on.
            - Updating coordinates should not overlap on existing 'o's
         */

        // Breaking up the variables:
        char row1 = Character.toUpperCase(c1.charAt(0));
        int column1 = Integer.parseInt(c1.substring(1));

        char row2 = Character.toUpperCase(c2.charAt(0));
        int column2 = Integer.parseInt(c2.substring(1));


        // Checking condition 1:
        if (!('A' <= row1 && 'J' >= row1 && 'A' <= row2 && 'J' >= row2 && 1 <= column1 && 10 >= column1 && 1 <= column2 && 10 >= column2)) {
            throw new Exception("Invalid Co-ordinates.");
        }

        // Checking condition 2:
        String location = "";
        if (row1 == row2) {
            location = "horizontal";
        } else if (column1 == column2) {
            location = "vertical";
        } else {
            throw new Exception("Wrong ship location!");
        }
        // Checking condition 3:
        char tempRow;
        int tempColumn;
        if (location == "horizontal" && column1 > column2) {
            tempColumn = column1;
            column1 = column2;
            column2 = tempColumn;
        } else if (location == "vertical" && row1 > row2) {
            tempRow = row1;
            row1 = row2;
            row2 = tempRow;
        }
        // Checking condition 4:
        int shipLength = 0;
        if (location == "horizontal") {
            shipLength = column2 - column1 + 1;
        } else if (location == "vertical") {
            shipLength = (int) row2 - (int) row1 + 1;
        }
        switch (ship) {
            case "aircraft":
                if (shipLength != 5) throw new Exception("Wrong length of the Aircraft Carrier");
                break;
            case "battleship":
                if (shipLength != 4) throw new Exception("Wrong length of the Battleship");
                break;
            case "submarine":
                if (shipLength != 3) throw new Exception("Wrong length of the Submarine");
                break;
            case "cruiser":
                if (shipLength != 3) throw new Exception("Wrong length of the Cruiser");
                break;
            case "destroyer":
                if (shipLength != 2) throw new Exception("Wrong length of the Destroyer");
                break;
        }

        // Checking condition 5:
        if (location == "horizontal") {
            for (int i = column1; i <= column2; i++) {
                try {
                    if (field[row1 - 64][i] == "o") {
                        throw new Exception("You placed it too close to another one.");
                    } else if (field[row1 - 65][i] == "o") {
                        throw new Exception("You placed it too close to another one.");
                    } else if (field[row1 - 63][i] == "o") {
                        throw new Exception("You placed it too close to another one.");
                    } else if (field[row1 - 64][i + 1] == "o") {
                        throw new Exception("You placed it too close to another one.");
                    } else if (field[row1 - 64][i - 1] == "o") {
                        throw new Exception("You placed it too close to another one.");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }
        } else if (location == "vertical") {
            for (int i = row1 - 64; i <= row2 - 64; i++) {
                try {

                    if (field[i][column1] == "o") {
                        throw new Exception("You placed it too close to another one.");
                    } else if (field[i + 1][column1] == "o") {
                        throw new Exception("You placed it too close to another one.");
                    } else if (field[i - 1][column1] == "o") {
                        throw new Exception("You placed it too close to another one.");
                    } else if (field[i][column1 + 1] == "o") {
                        throw new Exception("You placed it too close to another one.");
                    } else if (field[i][column1 - 1] == "o") {
                        throw new Exception("You placed it too close to another one.");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }
        }

        // Updating shipMap
        if (location == "horizontal"){
            if (ship == "aircraft"){
                for (int i = 0, j = column1; i < 5; i++, j++) {
                    shipMap[0][i] = String.format("%s%d",row1, j);
//                    System.out.println(shipMap[0][i]);
                }
                System.out.println(Arrays.toString(shipMap[0]));
            }
            if (ship == "battleship"){
                for (int i = 0, j = column1; i < 4; i++, j++) {
                    shipMap[1][i] = String.format("%s%d",row1, j);
//                    System.out.println(shipMap[0][i]);
                }
                System.out.println(Arrays.toString(shipMap[1]));
            }
            if (ship == "submarine"){
                for (int i = 0, j = column1; i < 3; i++, j++) {
                    shipMap[2][i] = String.format("%s%d",row1, j);
//                    System.out.println(shipMap[0][i]);
                }
                System.out.println(Arrays.toString(shipMap[2]));
            }
            if (ship == "cruiser"){
                for (int i = 0, j = column1; i < 3; i++, j++) {
                    shipMap[3][i] = String.format("%s%d",row1, j);
//                    System.out.println(shipMap[0][i]);
                }
                System.out.println(Arrays.toString(shipMap[3]));
            }
            if (ship == "destroyer"){
                for (int i = 0, j = column1; i < 2; i++, j++) {
                    shipMap[4][i] = String.format("%s%d",row1, j);
//                    System.out.println(shipMap[0][i]);
                }
                System.out.println(Arrays.toString(shipMap[4]));
            }
        }
        else {
            if (ship == "aircraft") {
                for (int i = 0, j = row1; i < 5; i++, j++) {
                    shipMap[0][i] = String.format("%c%d", j, column1);
                }
                System.out.println(Arrays.toString(shipMap[0]));
            }
            if (ship == "battleship") {
                for (int i = 0, j = row1; i < 4; i++, j++) {
                    shipMap[1][i] = String.format("%c%d", j, column1);
                }
                System.out.println(Arrays.toString(shipMap[1]));
            }
            if (ship == "submarine") {
                for (int i = 0, j = row1; i < 3; i++, j++) {
                    shipMap[2][i] = String.format("%c%d", j, column1);
                }
                System.out.println(Arrays.toString(shipMap[2]));
            }
            if (ship == "cruiser") {
                for (int i = 0, j = row1; i < 3; i++, j++) {
                    shipMap[3][i] = String.format("%c%d", j, column1);
                }
                System.out.println(Arrays.toString(shipMap[3]));
            }
            if (ship == "destroyer") {
                for (int i = 0, j = row1; i < 2; i++, j++) {
                    shipMap[4][i] = String.format("%c%d", j, column1);
                }
                System.out.println(Arrays.toString(shipMap[4]));
            }
        }
        return true;
    }
    public void updateField(String[][] field, String c1, String c2) {
        // Update field Array.
        // Breaking up the variables:
        char row1 = Character.toUpperCase(c1.charAt(0));
        int column1 = Integer.parseInt(c1.substring(1));

        char row2 = Character.toUpperCase(c2.charAt(0));
        int column2 = Integer.parseInt(c2.substring(1));

        // Checking condition 2:
        String location = "";
        if (row1 == row2) {
            location = "horizontal";
        } else if (column1 == column2) {
            location = "vertical";
        }

        // Checking condition 3:
        char tempRow;
        int tempColumn;
        if (location == "horizontal" && column1 > column2) {
            tempColumn = column1;
            column1 = column2;
            column2 = tempColumn;
        } else if (location == "vertical" && row1 > row2) {
            tempRow = row1;
            row1 = row2;
            row2 = tempRow;
        }

        // Updating field
        if (location == "horizontal") {
            for (int i = column1; i <= column2; i++) {
                field[row1 - 64][i] = "o";
            }
        } else if (location == "vertical") {
            for (int i = row1 - 64; i <= row2 - 64; i++) {
                field[i][column1] = "o";
            }
        }
    }
    public void printField() {
        for (int i = 0; i < 11; i++) {
            System.out.println(Arrays.toString(field[i])
                    .replace(",", "")
                    .replace("[", "")
                    .replace("]", ""));        }
    }
    public void printFogField() {
        for (int i = 0; i < 11; i++) {
            System.out.println(Arrays.toString(fogField[i])
                    .replace(",", "")
                    .replace("[", "")
                    .replace("]", ""));        }
    }
    public boolean takeShot(String shot, Battleship player) throws Exception {
        char row1 = Character.toUpperCase(shot.charAt(0));
        int column1 = Integer.parseInt(shot.substring(1));
        if (!('A' <= row1 && 'J' >= row1 && 1 <= column1 && 10 >= column1)) {
            System.out.println(row1 + " " + column1);
            throw new Exception("Invalid Co-ordinates.");
        }

        if (player.field[row1 - 64][column1] == "o") {
            player.field[row1 - 64][column1] = "X";
            fogField[row1 - 64][column1] = "X";
            // Validate game over:
            boolean flag = true;
            for (int i = 0; i < 11; i++) {
                for (int j = 0; j < 11; j++) {
                    if (player.field[i][j] == "o") {
                        flag = false;
                    }
                }
            }
            if (flag == true) {
                gameOver();
            }
            return true;
        } else {
            player.field[row1 - 64][column1] = "M";
            fogField[row1 - 64][column1] = "M";
            return false;
        }
    }
    public boolean validateShipSunk(Battleship player) {
        boolean flag = true;
        for(int i = 0; i < 5; i++) {
            for (String x : player.shipMap[i]) {
//                System.out.println("Shipmap : " + x);
                int row = (int)Character.toUpperCase(x.charAt(0)) - 64;
                int column = Integer.parseInt(x.substring(1));
                if (fogField[row][column] != "X" ) {
                    flag = false;
                    break;
                }
            }
            if (flag == true) {
                System.out.println("You sank a ship!");
                return true;
            }
            flag = true;
        }
        return false;
    }
    public void gameOver() {
        printFogField();
        System.out.println("You sank the last ship. You won. Congratulations!");
        System.exit(0);
    }
    public void setTheBattlefield() {

        Scanner scanner = new Scanner(System.in);
        String c1 = "";
        String c2 = "";
        boolean flag = false;

        printField();
        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
        while (true) {
            c1 = scanner.next();
            c2 = scanner.next();
            try {
                flag = validate(c1, c2, "aircraft");
                if (flag) {
                    updateField(field, c1, c2);
//                    String A1 = c1;
//                    String A2 = c2;
                    break;
                }
            } catch (Exception e) {
                System.out.printf("Error! %s Try again:", e.getMessage());
            }
        }
        printField();
        System.out.println("Enter the coordinates of the Battleship (4 cells):");
        while (true) {
            c1 = scanner.next();
            c2 = scanner.next();
            try {
                flag = validate(c1, c2, "battleship");
                if (flag) {
                    updateField(field, c1, c2);
//                    String B1 = c1;
//                    String B2 = c2;
                    break;
                }
            } catch (Exception e) {
                System.out.printf("Error! %s Try again:", e.getMessage());
            }
        }
        printField();
        System.out.println("Enter the coordinates of the Submarine (3 cells):");
        while (true) {
            c1 = scanner.next();
            c2 = scanner.next();
            try {
                flag = validate(c1, c2, "submarine");
                if (flag) {
                    updateField(field, c1, c2);
//                    String S1 = c1;
//                    String S2 = c2;
                    break;
                }
            } catch (Exception e) {
                System.out.printf("Error! %s Try again:", e.getMessage());
            }
        }
        printField();
        System.out.println("Enter the coordinates of the Cruiser (3 cells):");
        while (true) {
            c1 = scanner.next();
            c2 = scanner.next();
            try {
                flag = validate(c1, c2, "cruiser");
                if (flag) {
                    updateField(field, c1, c2);
//                    String C1 = c1;
//                    String C2 = c2;
                    break;
                }
            } catch (Exception e) {
                System.out.printf("Error! %s Try again:", e.getMessage());
            }
        }

        printField();
        System.out.println("Enter the coordinates of the Destroyer (2 cells):");
        while (true) {
            c1 = scanner.next();
            c2 = scanner.next();
            try {
                flag = validate(c1, c2, "destroyer");
                if (flag) {
                    updateField(field, c1, c2);
//                    String D1 = c1;
//                    String D2 = c2;
                    break;
                }
            } catch (Exception e) {
                System.out.printf("Error! %s Try again:", e.getMessage());
            }
        }
        printField();

    }
}

public class Main {
    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);
        Battleship player1 = new Battleship();
        Battleship player2 = new Battleship();

        System.out.println("Player 1, place your ships on the game field");
        player1.setTheBattlefield();
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();

        System.out.println("Player 2, place your ships to the game field");
        player2.setTheBattlefield();
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();

        while (true) {
            player1.printFogField();
            System.out.println("-----------------------");
            player1.printField();
            System.out.println();
            System.out.println("Player 1, it's your turn:");
            String shot = scanner.nextLine();
            try {
                if (player1.takeShot(shot, player2)) {
//                    player1.printFogField();
                    if (!player1.validateShipSunk(player2)) {
                        System.out.println("You hit a ship!");
                    }
//                    printField();
                } else {
//                    player1.printFogField();
                    System.out.println("You missed!");
//                    printField();
                }
            } catch (Exception e) {
                System.out.printf("Error! %s Try again:",e.getMessage());
            } finally {
                System.out.println("Press Enter and pass the move to another player");
                scanner.nextLine();
            }

            player2.printFogField();
            System.out.println("-----------------------");
            player2.printField();
            System.out.println();
            System.out.println("Player 2, it's your turn:");
            shot = scanner.nextLine();
            System.out.println(shot);
            try {
                if (player2.takeShot(shot, player1)) {
//                    player2.printFogField();
                    if (!player2.validateShipSunk(player1)) {
                        System.out.println("You hit a ship!");
                    }
//                    printField();
                } else {
//                    player2.printFogField();
                    System.out.println("You missed!");
//                    printField();
                }
            } catch (Exception e) {
                System.out.printf("Error! %s Try again:",e.getMessage());
            } finally {
                System.out.println("Press Enter and pass the move to another player");
                scanner.nextLine();
            }
        }
    }
}