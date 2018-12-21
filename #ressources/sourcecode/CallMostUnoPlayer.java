package uno;

import java.util.List;

public class CallMostUnoPlayer extends DumbUnoPlayer {
  public CallMostUnoPlayer() {}
  
  public UnoPlayer.Color callColor(List<Card> hand) { return colorWithMost(hand); }
}
