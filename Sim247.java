/*
 * This class illustrates the game of life if form of a grid.
 * Compilation:  javac *.java
 * Execution:    java Sim247 n pattern
 * 
 * n is number of iterations
 * pattern is R for random, < for Mazing and T for turtle
 * 
 * Sample output:  java Sim247 100 T
 * 
 * */

public class Sim247 {
	
	private int[][] current; // Initialize 2d list containing all the current states at time t
	private int[][] previous; // Initialize 2d list containing all the previous states at time t-1
	int n; // Initialize number of iterations variable
	int dimensions;  // Initialize dimension variable
	
	public static void main(String[] args) {
				
		int n = Integer.parseInt(args[0]); // Getting number of iteration from command line
		String pattern = args[1];	// Getting type of pattern from command line
		
		Sim247 gameOfLife = new Sim247(n, pattern);	// Creating instance of gameOfLife
	}
	
	Sim247(int n, String pattern){
		dimensions = 100; // This grid is set to be 100 be 100
		current = new int[dimensions][dimensions];   // current states of cells (time t)
		previous = new int[dimensions][dimensions];   // previous states of cells (time t-1)
		
		

		MakeGrid newGrid = new MakeGrid(dimensions,dimensions,7); // Making empty grid with 100 by 100 dimension and 7 magnification
		
		// For loop for setting previous array and current array with all values 0
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < dimensions; j++) {
				current[i][j] = 0;		
				previous[i][j] = 0;
			}
		}
		
		// If the pattern is random then randomly make values of elements in current list 1
		if (pattern.equals("R") || pattern.equals("r")) {
			for(int i = 0; i < dimensions; i++) {
				for(int j = 0; j < dimensions; j++) {
					double decider = Math.random();
					if (decider < 0.5) {
						current[i][j] = 1;
					}					
				}
			}			
		}
		
		// If the pattern is Mazing then make a maze in the current list.
		else if (pattern.equals("M") || pattern.equals("m")) {
			
			current[8][5] = 1;
			current[9][5] = 1;
			current[6][6] = 1;
			current[8][6] = 1;
			current[5][7] = 1;
			current[11][7] = 1;
			current[6][8] = 1;
			current[10][8] = 1;
			current[11][8] = 1;
			current[8][10] = 1;
			current[10][10] = 1;
			current[9][11] = 1;
			
		}
		
		// If the pattern is Mazing then make a turtle in the current list.
		else if (pattern.equals("T") || pattern.equals("t")) {
			current [2][1]=1;
			current [3][1]=1;
			current [4][1]=1;
			current [12][1]=1;
			current [2][2]=1;
			current [3][2]=1;
			current [6][2]=1;
			current [8][2]=1;
			current [9][2]=1;
			current [11][2]=1;
			current [12][2]=1;
			current [4][3]=1;
			current [5][3]=1;
			current [6][3]=1;
			current [11][3]=1;
			current [2][4]=1;
			current [5][4]=1;
			current [7][4]=1;
			current [11][4]=1;
			current [1][5]=1;
			current [6][5]=1;
			current [11][5]=1;
			current [1][6]=1;
			current [6][6]=1;
			current [11][6]=1;
			current [2][7]=1;
			current [5][7]=1;
			current [7][7]=1;
			current [11][7]=1;
			current [4][8]=1;
			current [5][8]=1;
			current [6][8]=1;
			current [11][8]=1;
			current [2][9]=1;
			current [3][9]=1;
			current [6][9]=1;
			current [9][9]=1;
			current [11][9]=1;
			current [12][9]=1;
			current [8][9]=1;
			current [2][10]=1;
			current [3][10]=1;
			current [4][10]=1;
			current [12][10]=1;
		}
		
		// Changing the color of the grid depending on the current list
		newGrid.setColor(current);
		
		// Display the grid
		newGrid.show();
		
		// Copy content of current list to previous.
		copyCurrentToPrevious();
		
		// Iteration over the input number iterations
		for (int i = 0;i <n;i++) {
			
			// Slowing down the code run
			try {
			    Thread.sleep(100);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			
			// Copy content of current list to previous.
			copyCurrentToPrevious();	
			
			// Determine the next state of the list
			stateDetermineLoop();
		
			// Changing the color of the grid depending on the current list
			newGrid.setColor(current);
			
			// Display the grid
			newGrid.show();
			}
		}
	
	// This method does a for loop over the list and calls function to determine next state.
	private void stateDetermineLoop() {
		for(int i = 0; i < dimensions; i++) {
			for(int j = 0; j < dimensions; j++) {
				this.stateDetermine(i, j);
			}
		}
	}
	
	// This method determines the next state of a cell considering the rules of game of life. 
	private void stateDetermine(int XCord, int YCord){
		
		//any live cell with two or three live neighbours lives on to the next generation
		if (previous[XCord][YCord]==1 && (countNeighbour(XCord, YCord)==3 && countNeighbour(XCord, YCord)==2)) {
			current[XCord][YCord]=1;
		}
		//any live cell with fewer than two live neighbours dies, as if by underpopulation
		// any live cell with more than three live neighbours dies, as if by overpopulation
		else if (previous[XCord][YCord]==1 && (countNeighbour(XCord, YCord)<2 || countNeighbour(XCord, YCord)>3)) {
			current[XCord][YCord]=0;
		}
		//any dead cell with exactly three live	neighbours becomes a live cell, as if by reproduction
		else if (previous[XCord][YCord]==0 && (countNeighbour(XCord, YCord) ==3)) {
			current[XCord][YCord]=1;
		}
		
		else {
			current[XCord][YCord]=previous[XCord][YCord];
		}
		
	}
	
	// This method counts living neighbours of a cell.
	private int countNeighbour(int XCord, int YCord){
		int count = 0;
		count += checkValidState(XCord - 1, YCord - 1);
		
		count += checkValidState(XCord, YCord - 1);

		count += checkValidState(XCord + 1, YCord - 1);

		count += checkValidState(XCord - 1, YCord);

		count += checkValidState(XCord + 1, YCord);

		count += checkValidState(XCord - 1, YCord + 1);

		count += checkValidState(XCord, YCord + 1);

		count += checkValidState(XCord + 1, YCord + 1);

		return count;
		
	}
	
	// This method take care of wrapping around
	private int checkValidState(int x, int y) {
		if (x < 0) {
			x = x + dimensions;
		}
		if (x >= dimensions) {
			x = dimensions - x;
		}
		
		if (y < 0) {
			y = y + dimensions;
		}
		if (y >= dimensions) {
			y = dimensions - y;
		}
		return previous[x][y];
		
	}
	
	// This method copy the date from current list to previous list.
	private void copyCurrentToPrevious() {
		for(int i = 0; i < dimensions; i++) {
			for(int j = 0; j < dimensions; j++) {				
				previous[i][j] = current[i][j];		
			}
		}
	}
}
