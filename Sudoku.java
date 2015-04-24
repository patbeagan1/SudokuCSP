public class Sudoku {

 public static int boardsize = 9;
 public static int counter = 0;
 public static int board[][] = {
								
		{0,9,0,	0,0,0,	0,2,5},
		{4,0,5,	2,0,0,	0,0,0},
		{0,6,0,	0,3,0,	0,0,0},
		
		{2,8,0,	3,0,0,	0,0,0},
		{0,0,0,	8,0,1,	0,0,0},
		{3,0,7,	0,0,6,	0,4,8},
		
		{0,0,0,	0,1,0,	0,8,0},
		{0,0,0,	0,0,3,	7,0,2},
		{6,3,0,	0,0,0,	0,9,0}
		
	};

//------------------------------------

  public static void main(String[] args) {
    
    printBoard(board);
    System.out.println("\nSolving...\n");
    
    if (solve(new Block(0, 0))) {
      printBoard(board);
    }
    else{
      System.out.println("\nThis board can't be solved");
    }
    System.out.println("\nNumber of calls to solve: "+counter);
  }

//---------------------------------------
  
  //These are where the constraints are held
  //if this method fails, backtracking occurs
  public static boolean checkConstraints(Block b, int value) {
    
    //Shouldn't occur, debugging
    if (board[b.row][b.col] != 0) {
      System.out.println("Filled.");
      return false;
    }
    
	  //if there is another of the same number in the row, backtrack
    for (int col = 0; col< boardsize; col++) {
      if (board[b.row][col] == value){
        return false;
      }
    }
  
  	//if there is another of the same number in the col, backtrack
    for (int row = 0; row< boardsize; row++) {
      if (board[row][b.col] == value){
        return false;
      }
    }
  
    //prepping the 3*3 grid params
    //this part is tricky with an N size board, so hardcoding
    int x1 = 3 * (b.row / 3);
    int y1 = 3 * (b.col / 3);
    int x2 = x1 + 2;
    int y2 = y1 + 2;
  
    //if there is another of the same number in the grid, backtrack
    for(int x = x1; x <= x2; x++){
      for(int y = y1; y <= y2; y++){
        if (board[x][y] == value){
          return false;
        }
      }
    }
      
    //the number actually works, no backtracking is needed
    return true;
  }

  //This is to make iteration through 2d easier
  static Block getNextBlock(Block current) {

    int row = current.row;
    int col = current.col;
    col++;
    
    //if we reach the end of the row, move down one row
    if (col > 8) {
      col = 0;
      row++;
    }

    //if we run out of rows, we are done.
    if (row > 8){
      return null;
    }

    //returning the next block in the board
    Block next = new Block(row, col);
    return next;
  }
  
  //recursive method to get the correct number in the block
  
  static boolean solve(Block current) {
    
    //updating the global counter
    counter++;
    
    //if getnext says that there are no more blocks, it must be solved
    if (current == null){
      return true;
    }
        
    //if  the current block is not equal to 0, it was already solved
    //moving on
    if (board[current.row][current.col] != 0) {
      return solve(getNextBlock(current));
    }
  
    //checking againts constraints, only continue to solve if not valid
    for (int i = 1; i <= boardsize; i++) {
      boolean valid = checkConstraints(current, i);
    
      if (!valid){
        continue;
      }

      //backtracked
      board[current.row][current.col] = i;
    
      //trying a new route
      boolean solved = solve(getNextBlock(current));
      if (solved){
        return true;
      }
      
      //if the whole route is a dead end,backtrack
      else{
        board[current.row][current.col] = 0;
      }
    }
    //can't be solved
    return false;
  }

  //printing the board
  static void printBoard(int board[][]) {
    for (int row = 0; row < boardsize; row++) {
      for (int col = 0; col < boardsize; col++){
        System.out.print(board[row][col]);
      }
      System.out.println();
    }
  }
  
}//end sudoku