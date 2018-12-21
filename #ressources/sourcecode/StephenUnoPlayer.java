package uno;

import java.util.List;

abstract class StephenUnoPlayer implements UnoPlayer
{
  StephenUnoPlayer() {}
  
  UnoPlayer.Color colorWithMost(List<Card> hand) {
    int[] numCardsOfColor = new int[4];
    for (int i = 0; i < 4; i++) {
      numCardsOfColor[i] = 0;
    }
    for (Card aHand : hand) {
      if (aHand.getColor() == UnoPlayer.Color.RED) {
        numCardsOfColor[0] += 1;
      }
      if (aHand.getColor() == UnoPlayer.Color.YELLOW) {
        numCardsOfColor[1] += 1;
      }
      if (aHand.getColor() == UnoPlayer.Color.GREEN) {
        numCardsOfColor[2] += 1;
      }
      if (aHand.getColor() == UnoPlayer.Color.BLUE) {
        numCardsOfColor[3] += 1;
      }
    }
    int mostCardColor = 0;
    for (int i = 0; i < 4; i++) {
      if (numCardsOfColor[i] > numCardsOfColor[mostCardColor]) {
        mostCardColor = i;
      }
    }
    switch (mostCardColor) {
    case 0: 
      return UnoPlayer.Color.RED;
    case 1: 
      return UnoPlayer.Color.YELLOW;
    case 2: 
      return UnoPlayer.Color.GREEN;
    case 3: 
      return UnoPlayer.Color.BLUE;
    }
    System.out.println("Non-existent color!");
    return UnoPlayer.Color.NONE;
  }
  

  boolean canPlayOn(Card card, Card upCard, UnoPlayer.Color calledColor)
  {
    return (card.getRank() == UnoPlayer.Rank.WILD) || 
      (card.getRank() == UnoPlayer.Rank.WILD_D4) || 
      (card.getColor() == upCard.getColor()) || 
      (card.getColor() == calledColor) || 
      ((card.getRank() == upCard.getRank()) && 
      (card.getRank() != UnoPlayer.Rank.NUMBER)) || (
      (card.getNumber() == upCard.getNumber()) && 
      (card.getRank() == UnoPlayer.Rank.NUMBER) && 
      (upCard.getRank() == UnoPlayer.Rank.NUMBER));
  }
}
