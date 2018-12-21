package uno;

import java.io.PrintStream;





















public class Card
{
  public static final boolean PRINT_IN_COLOR = true;
  private UnoPlayer.Color color;
  private UnoPlayer.Rank rank;
  private int number;
  
  public Card(UnoPlayer.Color color, UnoPlayer.Rank rank)
  {
    this(color, rank, -1);
  }
  


  public Card(UnoPlayer.Color color, int number)
  {
    this(color, UnoPlayer.Rank.NUMBER, number);
  }
  


  public Card(UnoPlayer.Color color, UnoPlayer.Rank rank, int number)
  {
    this.color = color;
    this.rank = rank;
    this.number = number;
  }
  




  public String toString()
  {
    String retval = "";
    switch (1.$SwitchMap$uno$UnoPlayer$Color[color.ordinal()]) {
    case 1: 
      retval = retval + "\033[31m";
      break;
    case 2: 
      retval = retval + "\033[33m";
      break;
    case 3: 
      retval = retval + "\033[32m";
      break;
    case 4: 
      retval = retval + "\033[34m";
      break;
    default: 
      retval = retval + "\033[1m";
    }
    
    
    switch (1.$SwitchMap$uno$UnoPlayer$Rank[rank.ordinal()]) {
    case 1: 
      retval = retval + number;
      break;
    case 2: 
      retval = retval + "S";
      break;
    case 3: 
      retval = retval + "R";
      break;
    case 4: 
      retval = retval + "W";
      break;
    case 5: 
      retval = retval + "+2";
      break;
    default: 
      retval = retval + "W4";
    }
    
    
    retval = retval + "\033[37m\033[0m";
    
    return retval;
  }
  



  public int forfeitCost()
  {
    if ((rank == UnoPlayer.Rank.SKIP) || (rank == UnoPlayer.Rank.REVERSE) || (rank == UnoPlayer.Rank.DRAW_TWO))
    {
      return 20;
    }
    if ((rank == UnoPlayer.Rank.WILD) || (rank == UnoPlayer.Rank.WILD_D4)) {
      return 50;
    }
    if (rank == UnoPlayer.Rank.NUMBER) {
      return number;
    }
    System.out.println("Illegal card!!");
    return 55536;
  }
  








  public boolean canPlayOn(Card c, UnoPlayer.Color calledColor)
  {
    return (rank == UnoPlayer.Rank.WILD) || (rank == UnoPlayer.Rank.WILD_D4) || (color == color) || (color == calledColor) || ((rank == rank) && (rank != UnoPlayer.Rank.NUMBER)) || ((number == number) && (rank == UnoPlayer.Rank.NUMBER) && (rank == UnoPlayer.Rank.NUMBER));
  }
  










  public boolean followedByCall()
  {
    return (rank == UnoPlayer.Rank.WILD) || (rank == UnoPlayer.Rank.WILD_D4);
  }
  











  void performCardEffect(Game game)
    throws EmptyDeckException
  {
    switch (1.$SwitchMap$uno$UnoPlayer$Rank[rank.ordinal()]) {
    case 2: 
      game.advanceToNextPlayer();
      game.advanceToNextPlayer();
      break;
    case 3: 
      game.reverseDirection();
      game.advanceToNextPlayer();
      break;
    case 5: 
      nextPlayerDraw(game);
      nextPlayerDraw(game);
      game.advanceToNextPlayer();
      game.advanceToNextPlayer();
      break;
    case 6: 
      nextPlayerDraw(game);
      nextPlayerDraw(game);
      nextPlayerDraw(game);
      nextPlayerDraw(game);
      game.advanceToNextPlayer();
      game.advanceToNextPlayer();
      break;
    case 4: default: 
      game.advanceToNextPlayer();
    }
  }
  
  private void nextPlayerDraw(Game game) throws EmptyDeckException
  {
    int nextPlayer = game.getNextPlayer();
    Card drawnCard;
    try {
      drawnCard = deck.draw();
    } catch (EmptyDeckException e) {
      Card drawnCard;
      game.print("...deck exhausted, remixing...");
      deck.remix();
      drawnCard = deck.draw();
    }
    h[nextPlayer].addCard(drawnCard);
    game.println("  " + h[nextPlayer].getPlayerName() + " draws " + drawnCard + ".");
  }
  




  public UnoPlayer.Color getColor()
  {
    return color;
  }
  




  public UnoPlayer.Rank getRank()
  {
    return rank;
  }
  



  public int getNumber()
  {
    return number;
  }
}
