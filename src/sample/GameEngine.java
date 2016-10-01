package sample;

/**
 * Kevin Omidvaran
 * Where all the logic of the game happens
 */
class GameEngine
{
  double r1 = 5;
  double r2 = 10;
  double r3 = 20;
  double r4 = 26;
  boolean[][][] board = new boolean[32][32][32];
  private boolean[][][] board2 = new boolean[32][32][32];

  /*
    initializes the 1 board
   */
  void initial1()
  {
    r1 = 1;
    r2 = 5;
    r3 = 5;
    r4 = 26;
    for (int i = 1; i < 31; i++)
    {
      for (int j = 1; j < 31; j++)
      {
        for (int k = 1; k < 31; k++)
        {
          if ((i + j + k % 60 == 0))
          {
            board[i][j][k] = true;
          }
          else
          {
            board[i][j][k] = true;
          }

        }
      }
    }
  }

  /*
     initializes the 2 board
    */
  void initial2()
  {
    r1 = 1;
    r2 = 20;
    r3 = 20;
    r4 = 26;

    for (int i = 1; i < 31; i++)
    {
      for (int j = 1; j < 31; j++)
      {
        for (int k = 1; k < 31; k++)
        {
          board[i][j][k] = false;
        }
      }
    }
    board[15][15][15] = true;
    board[16][15][15] = true;
  }

  /*
   initializes the 3 board
  */
  void initial3()
  {
    r1 = 26;
    r2 = 20;
    r3 = 1;
    r4 = 26;

    for (int i = 1; i < 31; i++)
    {
      for (int j = 1; j < 31; j++)
      {
        for (int k = 1; k < 31; k++)
        {
          board[i][j][k] = true;
        }
      }
    }
    board[15][15][15] = true;
    board[16][15][15] = true;
  }

  /*
   initializes the 4 board
  */
  void initial4()
  {
    r1 = 1;
    r2 = 6;
    r3 = 6;
    r4 = 26;

    for (int i = 1; i < 31; i++)
    {
      for (int j = 1; j < 31; j++)
      {
        for (int k = 1; k < 31; k++)
        {
          board[i][j][k] = false;
        }
      }
    }
    board[15][15][15] = true;
    board[15][16][15] = true;
    board[15][15][16] = true;
    board[15][14][15] = true;
    board[15][15][14] = true;
    board[16][15][15] = true;
    board[14][15][15] = true;


  }

  /*
   initializes the 5 board
  */
  void initial5()
  {
    r1 = 2;
    r2 = 6;
    r3 = 1;
    r4 = 26;

    for (int i = 1; i < 31; i++)
    {
      for (int j = 1; j < 31; j++)
      {
        for (int k = 1; k < 31; k++)
        {
          board[i][j][k] = false;
        }
      }
    }
    board[30][30][30] = true;
    board[29][30][30] = true;
    board[28][30][30] = true;

    board[1][1][1] = true;
    board[2][1][1] = true;
    board[3][1][1] = true;


  }

  void upDate()
  {
    for (int i = 1; i < 31; i++)
    {
      for (int j = 1; j < 31; j++)
      {
        for (int k = 1; k < 31; k++)
        {
          int alive = checkSurrounding(i, j, k);
          if ((alive > r1) && (alive <= r2))
          {
            board2[i][j][k] = true;
          }
          else if (alive > r3 && alive <= r4)
          {
            board2[i][j][k] = false;
          }
          else
          {
            board2[i][j][k] = board[i][j][k];
          }
        }
      }
    }


    boolean[][][] tempBoard = board2;
    board2 = board;
    board = tempBoard;
  }

  int checkSurrounding(int i, int j, int k)
  {
    int alive = 0;

    for (int n = -1; n < 2; n++)
    {
      for (int m = -1; m < 2; m++)
      {
        for (int l = -1; l < 2; l++)
        {
          if (!(n == 0 && m == 0 && k == 0))
          {
            if (board[i + n][j + m][k + l]) alive++;
          }
        }
      }

    }


    return alive;
  }
}

