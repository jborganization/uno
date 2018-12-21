package uno;

import java.util.List;





public abstract interface UnoPlayer
{
  public abstract int play(List<Card> paramList, Card paramCard, Color paramColor, GameState paramGameState);
  
  public abstract Color callColor(List<Card> paramList);
  
  public static enum Color
  {
    RED,  YELLOW,  GREEN,  BLUE,  NONE;
    private Color() {} } public static enum Rank { NUMBER,  SKIP,  REVERSE,  DRAW_TWO,  WILD,  WILD_D4;
    
    private Rank() {}
  }
}
