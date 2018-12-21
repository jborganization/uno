package uno;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;











public class Deck
{
  private static final Logger LOGGER = Logger.getLogger(new Throwable().getStackTrace()[0].getClassName());
  




  private static final int NUMBER_OF_DUP_REGULAR_CARDS = 2;
  



  private static final int NUMBER_OF_DUP_ZERO_CARDS = 1;
  



  private static final int NUMBER_OF_DUP_SPECIAL_CARDS = 2;
  



  private static final int NUMBER_OF_WILD_CARDS = 4;
  



  private static final int NUMBER_OF_WILD_D4_CARDS = 4;
  



  private static final int SHUFFLE_FACTOR = 1;
  



  private ArrayList<Card> cards = new ArrayList();
  private ArrayList<Card> discardedCards = new ArrayList();
  
  private Random rand;
  

  Deck()
  {
    rand = new Random();
    fillDeck();
    shuffle();
  }
  
  private void fillDeck() {
    for (int i = 1; i <= 9; i++) {
      for (int j = 0; j < 2; j++) {
        cards.add(new Card(UnoPlayer.Color.RED, i));
        cards.add(new Card(UnoPlayer.Color.YELLOW, i));
        cards.add(new Card(UnoPlayer.Color.BLUE, i));
        cards.add(new Card(UnoPlayer.Color.GREEN, i));
      }
    }
    
    for (int j = 0; j < 1; j++) {
      cards.add(new Card(UnoPlayer.Color.RED, 0));
      cards.add(new Card(UnoPlayer.Color.YELLOW, 0));
      cards.add(new Card(UnoPlayer.Color.BLUE, 0));
      cards.add(new Card(UnoPlayer.Color.GREEN, 0));
    }
    for (int j = 0; j < 2; j++) {
      cards.add(new Card(UnoPlayer.Color.RED, UnoPlayer.Rank.SKIP));
      cards.add(new Card(UnoPlayer.Color.YELLOW, UnoPlayer.Rank.SKIP));
      cards.add(new Card(UnoPlayer.Color.GREEN, UnoPlayer.Rank.SKIP));
      cards.add(new Card(UnoPlayer.Color.BLUE, UnoPlayer.Rank.SKIP));
      cards.add(new Card(UnoPlayer.Color.RED, UnoPlayer.Rank.REVERSE));
      cards.add(new Card(UnoPlayer.Color.YELLOW, UnoPlayer.Rank.REVERSE));
      cards.add(new Card(UnoPlayer.Color.GREEN, UnoPlayer.Rank.REVERSE));
      cards.add(new Card(UnoPlayer.Color.BLUE, UnoPlayer.Rank.REVERSE));
      cards.add(new Card(UnoPlayer.Color.RED, UnoPlayer.Rank.DRAW_TWO));
      cards.add(new Card(UnoPlayer.Color.YELLOW, UnoPlayer.Rank.DRAW_TWO));
      cards.add(new Card(UnoPlayer.Color.GREEN, UnoPlayer.Rank.DRAW_TWO));
      cards.add(new Card(UnoPlayer.Color.BLUE, UnoPlayer.Rank.DRAW_TWO));
    }
    for (int i = 0; i < 4; i++) {
      cards.add(new Card(UnoPlayer.Color.NONE, UnoPlayer.Rank.WILD));
    }
    for (int i = 0; i < 4; i++) {
      cards.add(new Card(UnoPlayer.Color.NONE, UnoPlayer.Rank.WILD_D4));
    }
  }
  




  private void shuffle()
  {
    for (int i = 0; i < 1 * cards.size(); i++) {
      int x = rand.nextInt(cards.size());
      int y = rand.nextInt(cards.size());
      Card temp = (Card)cards.get(x);
      cards.set(x, cards.get(y));
      cards.set(y, temp);
    }
  }
  


  private boolean isEmpty()
  {
    return cards.isEmpty();
  }
  



  Card draw()
    throws EmptyDeckException
  {
    if (cards.isEmpty()) {
      throw new EmptyDeckException();
    }
    return (Card)cards.remove(0);
  }
  



  void discard(Card c)
  {
    discardedCards.add(c);
  }
  



  void remix()
  {
    cards.addAll(discardedCards);
    discardedCards.clear();
    shuffle();
  }
  




  ArrayList<Card> getDiscardedCards()
  {
    return discardedCards;
  }
  
  public static void main(String[] args) {
    System.out.println("test Deck.");
    Deck d = new Deck();
    while (!d.isEmpty()) {
      try {
        System.out.println(d.draw());
      }
      catch (Exception e) {}
      LOGGER.log(Level.SEVERE, e.toString(), e);
    }
  }
}
