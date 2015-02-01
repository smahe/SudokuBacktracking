/**
 * Created by mahe
 */

//Sudoku solver using backtracking technique

import java.io.InputStreamReader;
import java.lang.*;
import java.util.Scanner;

public class Sudoku{

    private int grid[][];
    private int N;

    public Sudoku(int N,int [][]sudokuGrid){
        grid = new int[N][N];
        grid = sudokuGrid;
        this.N = N;
    }

    public String findUnassigned(){
        for(int row = 0;row<N;row++){
            for(int col = 0;col<N;col++){
                if(grid[row][col] == 0){
                    return row+","+col;
                }
            }
        }
        return null;
    }

    public boolean isUsedInRow(int row,int num){
        for(int col = 0; col<N;col++){
            if(grid[row][col] == num){
                return true;
            }
        }
        return false;
    }

    public boolean isUsedInCol(int col,int num){
        for(int row = 0; row<N;row++){
            if(grid[row][col] == num){
                return true;
            }
        }
        return false;
    }

    public boolean isUsedInBox(int boxStartRow,int boxStartCol,int num){
        for(int row = 0; row<3;row++){
            for(int col = 0; col<3;col++){
                if(grid[row+boxStartRow][col+boxStartCol] == num){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isSafe(int row,int col,int num){
        if(!isUsedInRow(row,num) && ! isUsedInCol(col,num) && !isUsedInBox(row - row %3,col - col%3,num)){
            return true;
        }
        return false;
    }


    public boolean solveSudoku(){
        int currRow,currCol;
        String indexString = findUnassigned();

        if(indexString != null){
            String currPos[] = indexString.split(",");
            currRow = Integer.parseInt(currPos[0]); //Current rowindex
            currCol = Integer.parseInt(currPos[1]); //current colindex
        }else{
            return true; //End. all squares are filled
        }

        for(int num = 1;num<=N;num++){
            //Can number be put here
            if(isSafe(currRow,currCol,num)){
                //Yes so put number
                grid[currRow][currCol] = num;

                if(solveSudoku()){
                    return true;
                }
                //If number causes problem backtrack and assign 0
                grid[currRow][currCol] = 0;
            }
        }
        return false;
    }

    public void print(){

        for(int row = 0; row<N; row++){
            for(int col = 0;col<N; col++){
                System.out.print(""+grid[row][col]);
                System.out.print("\t");
            }
            System.out.print("\n");
        }
    }

    public static void main(String args[]){

//        Scanner input;
//        input = new Scanner(new InputStreamReader(System.in));
//        System.out.println("Enter the sudoku size:");
//
//        int N = input.nextInt();
//        System.out.println("Enter the sudoku elements:");
//
//        int [][]values;
//        values = new int[N][N];
//
//        for(int i = 0;i<N;i++){
//            for(int j = 0;j<N;j++){
//                values[i][j] = input.nextInt();
//            }
//        }

        //Todo Read from  a file input
        int grids[][] = {{3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}};

        Sudoku square = new Sudoku(9,grids);

        if(square.solveSudoku() == true)
            square.print();

    }
}
