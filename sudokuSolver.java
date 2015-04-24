public class sudokuSolver {
	
	public static int boardSize = 9;
	public static int index =0;
	public static int[][] board = {
								
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

	public sudokuSolver() {
	}
			
	public static void main(String args[]){
	  System.out.println("Starting...");
		sudokuSolver s = new sudokuSolver();
    s.printSudoku();
    s.solve(0,0);
    //s.backtrack();
    s.printSudoku();
		System.out.println("Ending.");
	}
	
	public void solve(int row, int col)
   {
      if (row > 8)
      {
         System.out.println(row +" "+ col);
         return;
      }
      if (board[row][col] != 0)
      {
         if (col < 8)
            solve(row, col + 1);
         else
            solve(row + 1, 0);
      }
      else
      {
         for (int i = 0; i < 9; i++)
            if (checkRow(row, i) && checkCol(col, i))
                  //&& checkSquare(row, col, i))
            {
               board[row][col] = i;
               if (col < 8)
                  solve(row, col + 1);
               else
                  solve(row + 1, 0);
            }
         board[row][col] = 0;
      }
   }
   
// 	public void backtrack(){
// 	  int x=0, y=0;
	  
//   	if (board[y][x]==0)
//   	  board[y][x]++;
//       if (checkRow() && checkCol() && checkGrid()){
//         //do nothing because it is correct
//       }
      
        
//           discard (i) and repick other values (i++)
//       }
//     else {
//       while (x < 9) {
//         Proceed to next row grid(nx++, ny)
//         if (x equals 9) {
//           x = 1;
//           proceed to next column grid(nx,ny++)
//           if (ny equals 9) {
//             print solution
//         }
//       }
//     }

// 	}
	
	public boolean checkRow(int row, int num){
	  //if there is another of the same number in the row, backtrack
		for(int i = 0; i < boardSize; i++){
	    if(board[row][i] == num)
	      return false;
		}
		return true;
	}
	
	public boolean checkCol(int col, int num){
	  //if there is another of the same number in the row, backtrack
		for(int i = 0; i < boardSize; i++){
	    if(board[i][col] == num)
	      return false;
		}
		return true;
	}
	
	//printing the current contents of the board
	public void printSudoku(){
 		System.out.println();
	  for(int i = 0; i < boardSize; i++){
			for(int j = 0; j < boardSize; j++){
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}

}//end Sudoku class
