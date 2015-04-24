public class soln {

 public static int boardsize = 9;
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

  static class Block {

    int row, col;

    public Block(int row, int col) {
      super();
      this.row = row;
      this.col = col;
    }
  };

  public static boolean isValid(Block b, int value) {

    if (board[b.row][b.col] != 0) {
      throw new RuntimeException(
        "Cannot call for cell which already has a value");
    }
  
    for (int col = 0; col< 9; col++) {
      if (board[b.row][col] == value){
        return false;
      }
    }
  
    for (int row = 0; row< 9; row++) {
      if (board[row][b.col] == value){
        return false;
      }
    }
  
    int x1 = 3 * (b.row / 3);
    int y1 = 3 * (b.col / 3);
    int x2 = x1 + 2;
    int y2 = y1 + 2;
  
    for(int x = x1; x <= x2; x++){
      for(int y = y1; y <= y2; y++){
        if (board[x][y] == value){
          return false;
        }
      }
    }
      
    return true;
  }
 
  static Block getNextCell(Block current) {

    int row = current.row;
    int col = current.col;
    col++;
    if (col > 8) {
      col = 0;
      row++;
    }

    if (row > 8){
      return null;
    }

    Block next = new Block(row, col);
    return next;
  }
 
  static boolean solve(Block current) {
      
    if (current == null){
      return true;
    }
        
    if (board[current.row][current.col] != 0) {
      return solve(getNextCell(current));
    }
  
    for (int i = 1; i <= 9; i++) {
      boolean valid = isValid(current, i);
    
      if (!valid){
        continue;
      }
  
      board[current.row][current.col] = i;
    
      boolean solved = solve(getNextCell(current));
      if (solved){
        return true;
      }
      else{
        board[current.row][current.col] = 0;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    boolean solved = solve(new Block(0, 0));
    if (!solved) {
      System.out.println("SUDOKU cannot be solved.");
      return;
    }
    System.out.println("SOLUTION\n");
    printBoard(board);
  }

   static void printBoard(int board[][]) {
    for (int row = 0; row < boardsize; row++) {
      for (int col = 0; col < boardsize; col++){
        System.out.print(board[row][col]);
     }
     System.out.println();
    }
  }
}