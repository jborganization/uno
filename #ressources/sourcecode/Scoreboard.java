package uno;




public class Scoreboard
{
  private String[] playerList;
  


  private int[] scores;
  



  Scoreboard(String[] playerList)
  {
    scores = new int[playerList.length];
    for (int i = 0; i < playerList.length; i++) {
      scores[i] = 0;
    }
    this.playerList = playerList;
  }
  





  void addToScore(int player, int points)
  {
    scores[player] += points;
  }
  




  int getScore(int player)
  {
    return scores[player];
  }
  


  public String toString()
  {
    StringBuilder retval = new StringBuilder();
    for (int i = 0; i < scores.length; i++) {
      retval.append("Player #").append(i).append(": ").append(scores[i]).append("\n");
    }
    return retval.toString();
  }
  



  String[] getPlayerList()
  {
    return playerList;
  }
  



  int getNumPlayers()
  {
    return playerList.length;
  }
  





  int getWinner()
  {
    int winner = 0;
    int topScore = scores[0];
    
    for (int i = 1; i < scores.length; i++) {
      if (scores[i] > topScore) {
        topScore = scores[i];
        winner = i;
      }
    }
    return winner;
  }
}
