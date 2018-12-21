package uno;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;














public class Game
{
  private static final Logger LOGGER = Logger.getLogger(new Throwable().getStackTrace()[0].getClassName());
  static final int INIT_HAND_SIZE = 7;
  Deck deck;
  Hand[] h;
  private Card upCard;
  
  public static enum Direction {
    FORWARDS,  BACKWARDS;
    



    private Direction() {}
  }
  


  Direction direction;
  

  int currPlayer;
  

  UnoPlayer.Color calledColor;
  

  Scoreboard scoreboard;
  

  UnoPlayer.Color[] mostRecentColorCalled;
  

  Game(Scoreboard scoreboard, ArrayList<String> playerClassList)
  {
    this.scoreboard = scoreboard;
    deck = new Deck();
    h = new Hand[scoreboard.getNumPlayers()];
    
    mostRecentColorCalled = new UnoPlayer.Color[scoreboard.getNumPlayers()];
    try {
      for (int i = 0; i < scoreboard.getNumPlayers(); i++)
      {
        h[i] = new Hand((String)playerClassList.get(i), scoreboard.getPlayerList()[i]);
        for (int j = 0; j < 7; j++) {
          h[i].addCard(deck.draw());
        }
      }
      upCard = deck.draw();
      while (upCard.followedByCall()) {
        deck.discard(upCard);
        upCard = deck.draw();
      }
    }
    catch (Exception e) {
      System.out.println("Can't deal initial hands!");
      System.exit(1);
    }
    direction = Direction.FORWARDS;
    
    currPlayer = new Random().nextInt(scoreboard.getNumPlayers());
    calledColor = UnoPlayer.Color.NONE;
  }
  





  int getNextPlayer()
  {
    if (direction == Direction.FORWARDS) {
      return (currPlayer + 1) % scoreboard.getNumPlayers();
    }
    
    if (currPlayer == 0) {
      return scoreboard.getNumPlayers() - 1;
    }
    
    return currPlayer - 1;
  }
  





  void advanceToNextPlayer()
  {
    currPlayer = getNextPlayer();
  }
  



  void reverseDirection()
  {
    if (direction == Direction.FORWARDS) {
      direction = Direction.BACKWARDS;
    }
    else {
      direction = Direction.FORWARDS;
    }
  }
  





  public void play()
  {
    println("Initial upcard is " + upCard + ".");
    try {
      for (;;) {
        print(h[currPlayer].getPlayerName() + " (" + h[currPlayer] + ")");
        
        Card playedCard = h[currPlayer].play(this);
        if (playedCard == null)
        {
          Card drawnCard = getCard();
          h[currPlayer].addCard(drawnCard);
          print(" has to draw (" + drawnCard + ").");
          playedCard = h[currPlayer].play(this);
        }
        if (playedCard != null) {
          print(" plays " + playedCard + " on " + upCard + ".");
          deck.discard(upCard);
          upCard = playedCard;
          if (upCard.followedByCall()) {
            calledColor = h[currPlayer].callColor();
            mostRecentColorCalled[currPlayer] = calledColor;
            print(" (and calls " + calledColor + ").");
          }
          else
          {
            calledColor = UnoPlayer.Color.NONE;
          }
        }
        if (h[currPlayer].isEmpty()) {
          int roundPoints = 0;
          for (int j = 0; j < scoreboard.getNumPlayers(); j++) {
            roundPoints += h[j].countCards();
          }
          println("\n" + h[currPlayer].getPlayerName() + " wins! (and collects " + roundPoints + " points.)");
          
          scoreboard.addToScore(currPlayer, roundPoints);
          println("---------------\n" + scoreboard);
          return;
        }
        if (h[currPlayer].size() == 1) {
          print(" UNO!");
        }
        println("");
        if (playedCard != null) {
          playedCard.performCardEffect(this);
        }
        else {
          advanceToNextPlayer();
        }
      }
      return;
    } catch (EmptyDeckException e) {
      System.out.println("Deck exhausted! This game is a draw.");
    }
    catch (Exception e) {
      LOGGER.log(Level.SEVERE, e.toString(), e);
    }
  }
  
  private Card getCard() throws EmptyDeckException {
    Card drawnCard;
    try {
      drawnCard = deck.draw();
    } catch (Exception e) {
      Card drawnCard;
      print("...deck exhausted, remixing...");
      deck.remix();
      drawnCard = deck.draw();
    }
    return drawnCard;
  }
  
  void print(String s) {
    if (UnoSimulation.printVerbose) {
      System.out.print(s);
    }
  }
  
  void println(String s) {
    if (UnoSimulation.printVerbose) {
      System.out.println(s);
    }
  }
  




  GameState getGameState()
  {
    return new GameState(this);
  }
  



  Card getUpCard()
  {
    return upCard;
  }
}
