package uno;

import java.util.List;

public class DumbUnoPlayer extends StephenUnoPlayer
{
  public DumbUnoPlayer() {}
  
  public int play(List<Card> hand, Card upCard, UnoPlayer.Color calledColor, GameState state) {
    for (int i = 0; i < hand.size(); i++) {
      if (canPlayOn((Card)hand.get(i), upCard, calledColor)) {
        return i;
      }
    }
    return -1;
  }
  
  public UnoPlayer.Color callColor(List<Card> hand) {
    return UnoPlayer.Color.RED;
  }
}
