package uno;

import java.util.ArrayList;
import java.util.List;












public class GameState
{
  private Game theGame;
  private int[] numCardsInHandsOfUpcomingPlayers;
  private UnoPlayer.Color[] mostRecentColorCalledByUpcomingPlayers;
  private int[] totalScoreOfUpcomingPlayers;
  
  GameState()
  {
    numCardsInHandsOfUpcomingPlayers = new int[4];
    mostRecentColorCalledByUpcomingPlayers = new UnoPlayer.Color[4];
    totalScoreOfUpcomingPlayers = new int[4];
  }
  





  GameState(Game game)
  {
    numCardsInHandsOfUpcomingPlayers = new int[scoreboard.getNumPlayers()];
    
    mostRecentColorCalledByUpcomingPlayers = new UnoPlayer.Color[scoreboard.getNumPlayers()];
    
    totalScoreOfUpcomingPlayers = new int[scoreboard.getNumPlayers()];
    
    if (direction == Game.Direction.FORWARDS) {
      for (int i = 0; i < h.length; i++)
      {

        numCardsInHandsOfUpcomingPlayers[i] = h[((currPlayer + i + 1) % scoreboard.getNumPlayers())].size();
        totalScoreOfUpcomingPlayers[i] = scoreboard
          .getScore((currPlayer + i + 1) % scoreboard
          .getNumPlayers());
        mostRecentColorCalledByUpcomingPlayers[i] = mostRecentColorCalled[
        
          ((currPlayer + i + 1) % scoreboard.getNumPlayers())];
      }
      
    } else {
      for (int i = 0; i < 3; i++)
      {


        numCardsInHandsOfUpcomingPlayers[i] = h[((currPlayer - i - 1 + scoreboard.getNumPlayers()) % scoreboard.getNumPlayers())].size();
        totalScoreOfUpcomingPlayers[i] = scoreboard
          .getScore(
          (currPlayer - i - 1 + scoreboard.getNumPlayers()) % scoreboard
          .getNumPlayers());
        mostRecentColorCalledByUpcomingPlayers[i] = mostRecentColorCalled[
        

          ((currPlayer - i - 1 + scoreboard.getNumPlayers()) % scoreboard.getNumPlayers())];
      }
    }
    theGame = game;
  }
  






  public int[] getNumCardsInHandsOfUpcomingPlayers()
  {
    return numCardsInHandsOfUpcomingPlayers;
  }
  






  public int[] getTotalScoreOfUpcomingPlayers()
  {
    return totalScoreOfUpcomingPlayers;
  }
  







  public UnoPlayer.Color[] getMostRecentColorCalledByUpcomingPlayers()
  {
    return mostRecentColorCalledByUpcomingPlayers;
  }
  




  public List<Card> getPlayedCards()
  {
    if (theGame != null) {
      return theGame.deck.getDiscardedCards();
    }
    

    return new ArrayList();
  }
}
