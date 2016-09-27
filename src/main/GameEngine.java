package main;

/**
 * Created by family on 9/22/16.
 */
public class GameEngine
{
  public static double r1 = 5;
  public static double r2 = 10;
  public static double r3 = 20;
  public static double r4 = 26;
  public boolean[][][] board = new boolean [32][32][32];
  private boolean[][][] board2 = new boolean[32][32][32];
  private boolean[][][] tempBoard;
  public void initil()
  {
    for(int i=1;i<31;i++)
    {
      for(int j = 1; j<31;j++)
      {
        for(int k=1; k<31; k++)
        {
          if((k+i+j)%3==0)
          {
            board[i][j][k]=true;
          }
          else
          {
            board[i][j][k]=false;
          }
        }
      }
    }
  }
  public void upDate()
  {
    System.out.println("R1 : "+r1+" R2 : "+r2+" R3 : "+r3+" R4 : "+r4);
    for(int i=1;i<31;i++)
    {
      for(int j = 1; j<31;j++)
      {
       for(int k=1; k<31; k++)
       {
        int alive = checkSurrounding(i,j,k);
         if((alive>r1) && (alive <= r2))
         {
           board2[i][j][k] = true;
         }
         if(alive>r3 && alive <= r4)
         {
           board2[i][j][k] = false;
         }
       }
      }
    }
//    System.out.println("THis sucks");
     tempBoard = board2;
     board2 = board;
     board = tempBoard;
  }
  private int checkSurrounding(int i, int j, int k)
  {
    int alive = 0;

      for(int n = -1; n<2; n++)
      {
        for(int m = -1; m<2; m++)
        {
          for(int l = -1; l<2; l++)
          {
            if(n==0 && m == 0 && k==0);
            else
              {
                if(board[i+n][j+m][k+l]) alive++;
              }
          }
        }

      }


    return alive;
  }
}

