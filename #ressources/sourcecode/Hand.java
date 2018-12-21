package uno;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;








public class Hand
{
  private static final Logger LOGGER = Logger.getLogger(new Throwable().getStackTrace()[0].getClassName());
  

  private ArrayList<Card> cards;
  

  private UnoPlayer player;
  
  private String playerName;
  

  Hand(String unoPlayerClassName, String playerName)
  {
    try
    {
      player = ((UnoPlayer)Class.forName(unoPlayerClassName).newInstance());
    }
    catch (Exception e) {
      System.out.println("Problem with " + unoPlayerClassName + ".");
      LOGGER.log(Level.SEVERE, e.toString(), e);
      System.exit(1);
    }
    this.playerName = playerName;
    cards = new ArrayList();
  }
  


  void addCard(Card c)
  {
    cards.add(c);
  }
  


  int size()
  {
    return cards.size();
  }
  









  Card play(Game game)
  {
    int playedCard = player.play(cards, game.getUpCard(), calledColor, game
      .getGameState());
    if (playedCard == -1) {
      return null;
    }
    
    return (Card)cards.remove(playedCard);
  }
  








  UnoPlayer.Color callColor()
  {
    return player.callColor(cards);
  }
  



  boolean isEmpty()
  {
    return cards.isEmpty();
  }
  



  public String toString()
  {
    StringBuilder retval = new StringBuilder();
    for (int i = 0; i < cards.size(); i++) {
      retval.append(cards.get(i));
      if (i < cards.size() - 1) {
        retval.append(",");
      }
    }
    return retval.toString();
  }
  



  int countCards()
  {
    int total = 0;
    for (Card card : cards) {
      total += card.forfeitCost();
    }
    return total;
  }
  


  String getPlayerName()
  {
    return playerName;
  }
}
