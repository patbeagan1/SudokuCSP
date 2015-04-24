public class mainSudokuFC {
	
	public static void main(String[] args) {
    
    SudokuFC s = new SudokuFC();
    s.printBoard(s.board);
    System.out.println("\nSolving...\n");
    
    if (s.solve()) {
      s.printBoard(s.board);
      System.out.println("hello");
    }
    else{
      System.out.println("\nThis board can't be solved");
    }
    System.out.println("\nNumber of calls to solve: "+s.counter);
  }

}//end Sudoku class
