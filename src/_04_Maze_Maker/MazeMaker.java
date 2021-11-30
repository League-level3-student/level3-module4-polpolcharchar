//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


package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

    private static int rows;
    private static int cols;

    private static Maze maze;
    
    

    private static Random randGen = new Random();
    private static Stack<Cell> uncheckedCells = new Stack<Cell>();

    public static Maze generateMaze(int r, int c) {
        rows = r;
        cols = c;
        maze = new Maze(rows, cols);

        // 1. Pick a random cell along the border and remove its exterior wall.
        //    This will be the starting point. Then select a random cell along
        //    the opposite wall and remove its exterior wall. This will be the
        //    finish line.
        
        
        if(randGen.nextBoolean()) {
        	maze.cells[0][randGen.nextInt(maze.cells[0].length)].setNorthWall(false);
        	maze.cells[maze.cells.length-1][randGen.nextInt(maze.cells[maze.cells.length-1].length)].setSouthWall(false);
        }else {
        	maze.cells[randGen.nextInt(maze.cells.length)][0].setWestWall(false);
        	maze.cells[randGen.nextInt(maze.cells.length)][maze.cells[0].length-1].setEastWall(false);
        }
        
        // 2. select a random cell in the maze to start 
        
        
        // 3. call the selectNextPath method with the randomly selected cell
        selectNextPath(maze.cells[randGen.nextInt(maze.cells.length)][randGen.nextInt(maze.cells[0].length)]);

        return maze;
    }

    // 4. Complete the selectNextPathMethod
    private static void selectNextPath(Cell currentCell) {
    	
        // A. SET currentCell as visited
    	currentCell.setBeenVisited(true);

        // B. check for unvisited neighbors using the cell
    	if(getUnvisitedNeighbors(currentCell).size() > 0) {
    		// C. if has unvisited neighbors,

            // C1. select one at random.
    		
    		Cell randCell = getUnvisitedNeighbors(currentCell).get(randGen.nextInt(getUnvisitedNeighbors(currentCell).size()));
    		
            // C2. push it to the stack

    		uncheckedCells.push(randCell);
    		
    		
            // C3. remove the wall between the two cells
    		
//    		if(currentCell.getCol() > randCell.getCol()) {
//    			currentCell.setWestWall(false);
//    		}else if(currentCell.getCol() < randCell.getCol()) {
//    			currentCell.setEastWall(false);
//    		}else if(currentCell.getRow() > randCell.getRow()) {
//    			currentCell.setNorthWall(false);
//    		}else if(currentCell.getRow() < randCell.getRow()) {
//    			currentCell.setSouthWall(false);
//    		}else {
//    			System.out.println("Error!!!");
//    		}
    		removeWalls(currentCell, randCell);

            // C4. make the new cell the current cell and SET it as visited

    		currentCell = randCell;
    		
            // C5. call the selectNextPath method with the current cell
    		selectNextPath(currentCell);
    	}

        


        // D. if all neighbors are visited

        // D1. if the stack is not empty
    	else if(uncheckedCells.size() > 0){
    		currentCell = uncheckedCells.pop();
    		selectNextPath(currentCell);
    	}

        // D1a. pop a cell from the stack

        // D1b. make that the current cell

        // D1c. call the selectNextPath method with the current cell

    }

    // This method will check if c1 and c2 are adjacent.
    // If they are, the walls between them are removed.
    private static void removeWalls(Cell c1, Cell c2) {
        if (c1.getRow() == c2.getRow()) {
            if (c1.getCol() > c2.getCol()) {
                c1.setWestWall(false);
                c2.setEastWall(false);
            } else {
                c1.setEastWall(false);
                c2.setWestWall(false);
            }
        } else {
            if (c1.getRow() > c2.getRow()) {
                c1.setNorthWall(false);
                c2.setSouthWall(false);
            } else {
                c1.setSouthWall(false);
                c2.setNorthWall(false);
            }
        }
    }

    // This method returns a list of all the neighbors around the specified
    // cell that have not been visited. There are up to 4 neighbors per cell.
    //          1
    //       2 cell 3
    //          4
    private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
        int row = c.getRow();
        int col = c.getCol();

        ArrayList<Cell> unvisitedNeighbors = new ArrayList<Cell>();

        if (row > 0 && !maze.getCell(row - 1, col).hasBeenVisited()) {
            unvisitedNeighbors.add(maze.getCell(row - 1, col));
        }

        if (col > 0 && !maze.getCell(row, col - 1).hasBeenVisited()) {
            unvisitedNeighbors.add(maze.getCell(row, col - 1));
        }

        if (row < rows - 1 && !maze.getCell(row + 1, col).hasBeenVisited()) {
            unvisitedNeighbors.add(maze.getCell(row + 1, col));
        }

        if (col < cols - 1 && !maze.getCell(row, col + 1).hasBeenVisited()) {
            unvisitedNeighbors.add(maze.getCell(row, col + 1));
        }

        return unvisitedNeighbors;
    }
}
