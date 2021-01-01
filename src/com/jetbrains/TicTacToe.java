//package com.jetbrains;

import java.util.*;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();

    public static void main(String[] args) {
        char[][] gameBoard = {
                {' ', '|', ' ', '|',' '},
                {'-', '+', '-', '+','-'},
                {' ', '|', ' ', '|',' '},
                {'-', '+', '-', '+','-'},
                {' ', '|', ' ', '|',' '}

        };

        printGameBoard(gameBoard);

        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your placement (1-9): ");

            int playerPos = scan.nextInt();
            while (cpuPositions.contains(playerPos) || playerPositions.contains(playerPos)) {
                System.out.println("Position taken, enter another location!");
                playerPos = scan.nextInt();
            }

            inputPlayerPosition(gameBoard, playerPos, "human");
            playerPositions.add(playerPos);


            //printGameBoard(gameBoard);
            String result = checkWinner();
            if (!result.isEmpty()) {
                System.out.println(result);
                System.out.println();
                printGameBoard(gameBoard);
                break;
            }

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                //System.out.println("Position taken, enter another location!");
                cpuPos = rand.nextInt(9) + 1;
            }

            inputPlayerPosition(gameBoard, cpuPos, "cpu");
            cpuPositions.add(cpuPos);
            printGameBoard(gameBoard);
            String result2 = checkWinner();
            if (!result2.isEmpty()) {
                System.out.println(result2);
                System.out.println();
                printGameBoard(gameBoard);
                break;
            }

        }
    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row: gameBoard) {
            for (char c :  row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void inputPlayerPosition(char[][] gameBoard, int pos, String player) {

        char mark;

        if (player.equals("cpu")) {
            mark = 'O';
        } else if (player.equals("human")) {
            mark = 'X';
        } else {
            mark = '?';
        }

        switch(pos) {
            case 1:
                gameBoard[0][0] = mark;
                break;
            case 2:
                gameBoard[0][2] = mark;
                break;
            case 3:
                gameBoard[0][4] = mark;
                break;
            case 4:
                gameBoard[2][0] = mark;
                break;
            case 5:
                gameBoard[2][2] = mark;
                break;
            case 6:
                gameBoard[2][4] = mark;
                break;
            case 7:
                gameBoard[4][0] = mark;
                break;
            case 8:
                gameBoard[4][2] = mark;
                break;
            case 9:
                gameBoard[4][4] = mark;
                break;
        }
    }

    static String checkWinner() {
        List<List<Integer>>  winCondition = new ArrayList<List<Integer>>();
        List<Integer> upper = Arrays.asList(1, 2, 3);
        List<Integer> middle = Arrays.asList(4, 5, 6);
        List<Integer> low = Arrays.asList(7, 8, 9);
        List<Integer> downLeft = Arrays.asList(1, 4, 7);
        List<Integer> downMid = Arrays.asList(2, 5, 8);
        List<Integer> downRight = Arrays.asList(3, 6, 9);
        List<Integer> leftX = Arrays.asList(1, 5, 9);
        List<Integer> rightX = Arrays.asList(3, 5, 7);

        winCondition.add(upper);
        winCondition.add(middle);
        winCondition.add(low);
        winCondition.add(downLeft);
        winCondition.add(downMid);
        winCondition.add(downRight);
        winCondition.add(leftX);
        winCondition.add(rightX);

        for (List<Integer> l : winCondition) {
            if (playerPositions.containsAll(l)) {
                return "Congratulations You Won!";
            } else if (cpuPositions.containsAll(l)) {
                return "Cpu Wins, You Lost!";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "It's a Tie!";
            }
        }

        return "";
    }
}
