package uno;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestCaseProcessor
{
  private final String FILENAME = "/uno/testCases.txt";
  private final String DELIMITER = "-------";
  
  private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(new Throwable().getStackTrace()[0].getClassName());
  private UnoPlayer thePlayer;
  
  public static void main(String[] args)
  {
    try {
      if (args.length != 1) {
        System.out.println("Usage: TestCaseProcessor UnoPlayerClassName.");
        System.exit(1);
      }
      new TestCaseProcessor(args[0]).doIt();
    }
    catch (Exception e) {
      LOGGER.log(java.util.logging.Level.SEVERE, e.toString(), e);
    }
  }
  
  private TestCaseProcessor(String classname) throws Exception {
    thePlayer = ((UnoPlayer)Class.forName("uno." + classname + "UnoPlayer").newInstance());
  }
  
  private void doIt() {
    int numHandsTested = 0;
    try { BufferedReader br = new BufferedReader(new jdk.nashorn.api.scripting.URLReader(getClass().getResource("/uno/testCases.txt")));Throwable localThrowable12 = null;
      try { String handLine = br.readLine();
        while (handLine != null) {
          try { Scanner handLineScanner = new Scanner(handLine).useDelimiter(",");Throwable localThrowable13 = null;
            try { List<Card> hand = getCards(handLineScanner);
              
              String upCardLine = br.readLine();
              try { Scanner upCardLineScanner = new Scanner(upCardLine);Throwable localThrowable14 = null;
                
                try
                {
                  Card upCard = new Card(UnoPlayer.Color.valueOf(upCardLineScanner.next()), UnoPlayer.Rank.valueOf(upCardLineScanner.next()), upCardLineScanner.nextInt());
                  
                  String calledColorLine = br.readLine();
                  UnoPlayer.Color calledColor = UnoPlayer.Color.valueOf(calledColorLine);
                  
                  ArrayList<Integer> validPlays = new ArrayList();
                  String validPlaysLine = br.readLine();
                  try { Scanner validPlaysScanner = new Scanner(validPlaysLine).useDelimiter(",");Throwable localThrowable15 = null;
                    try { while (validPlaysScanner.hasNextInt()) {
                        validPlays.add(Integer.valueOf(validPlaysScanner.nextInt()));
                      }
                      testHand(hand, upCard, calledColor, validPlays);
                    }
                    catch (Throwable localThrowable1)
                    {
                      localThrowable15 = localThrowable1;throw localThrowable1;
                    }
                    finally {}
                  }
                  catch (Exception e)
                  {
                    LOGGER.log(java.util.logging.Level.SEVERE, e.toString(), e);
                  }
                }
                catch (Throwable localThrowable4)
                {
                  localThrowable14 = localThrowable4;throw localThrowable4;





                }
                finally {}





              }
              catch (IOException e)
              {




                LOGGER.log(java.util.logging.Level.SEVERE, e.toString(), e);
              }
              numHandsTested++;
            }
            catch (Throwable localThrowable7)
            {
              localThrowable13 = localThrowable7;throw localThrowable7;








            }
            finally {}







          }
          catch (IOException e)
          {







            LOGGER.log(java.util.logging.Level.SEVERE, e.toString(), e);
          }
          if ((numHandsTested < 100) || (numHandsTested % 100 == 0)) {
            System.out.println(numHandsTested + " test hands passed!");
          }
          
          if ("-------".equals(handLine = br.readLine())) {
            handLine = br.readLine();
          }
        }
      }
      catch (Throwable localThrowable10)
      {
        localThrowable12 = localThrowable10;throw localThrowable10;


















      }
      finally
      {


















        if (br != null) if (localThrowable12 != null) try { br.close(); } catch (Throwable localThrowable11) { localThrowable12.addSuppressed(localThrowable11); } else br.close();
      } } catch (IOException e) { LOGGER.log(java.util.logging.Level.SEVERE, e.toString(), e);
    }
  }
  
  private List<Card> getCards(Scanner handLineScanner) {
    List<Card> hand = new ArrayList();
    while (handLineScanner.hasNext()) {
      String cardString = handLineScanner.next();
      try { Scanner cardStringScanner = new Scanner(cardString);Throwable localThrowable3 = null;
        
        try
        {
          Card card = new Card(UnoPlayer.Color.valueOf(cardStringScanner.next()), UnoPlayer.Rank.valueOf(cardStringScanner.next()), cardStringScanner.nextInt());
          hand.add(card);
        }
        catch (Throwable localThrowable1)
        {
          localThrowable3 = localThrowable1;throw localThrowable1;

        }
        finally
        {

          if (cardStringScanner != null) if (localThrowable3 != null) try { cardStringScanner.close(); } catch (Throwable localThrowable2) { localThrowable3.addSuppressed(localThrowable2); } else cardStringScanner.close();
        } } catch (Exception e) { LOGGER.log(java.util.logging.Level.SEVERE, e.toString(), e);
      }
    }
    return hand;
  }
  




  private void testHand(List<Card> hand, Card upCard, UnoPlayer.Color calledColor, ArrayList<Integer> validPlays)
  {
    int cardPlayed = thePlayer.play(hand, upCard, calledColor, new GameState());
    
    if (!validPlays.contains(Integer.valueOf(cardPlayed))) {
      System.out.println("Whoops -- your play() method has an error!");
      System.out.println("You were given this hand:");
      for (int i = 0; i < hand.size(); i++) {
        System.out.println("  " + i + ". " + hand.get(i));
      }
      
      System.out.println("and the up card was: " + upCard);
      if ((upCard.getRank() == UnoPlayer.Rank.WILD) || 
        (upCard.getRank() == UnoPlayer.Rank.WILD_D4)) {
        System.out.println("and the called color was: " + calledColor);
      }
      System.out.println("and you (wrongly) returned " + cardPlayed + ".");
      
      System.out.print("Valid plays would have included: ");
      for (int i = 0; i < validPlays.size(); i++) {
        System.out.print(validPlays.get(i));
        if (i < validPlays.size() - 1) {
          System.out.print(",");
        }
      }
      System.out.println();
      System.exit(3);
    }
    
    UnoPlayer.Color color = thePlayer.callColor(hand);
    
    if ((color != UnoPlayer.Color.RED) && (color != UnoPlayer.Color.BLUE) && (color != UnoPlayer.Color.GREEN) && (color != UnoPlayer.Color.YELLOW))
    {

      System.out.println("Whoops -- your callColor() method has an error!");
      System.out.println("You were given this hand:");
      for (int i = 0; i < hand.size(); i++) {
        System.out.println("  " + i + ". " + hand.get(i));
      }
      
      System.out.println("and the up card was: " + upCard);
      if ((upCard.getRank() == UnoPlayer.Rank.WILD) || 
        (upCard.getRank() == UnoPlayer.Rank.WILD_D4)) {
        System.out.println("and the called color was: " + calledColor);
      }
      System.out.println("and you (wrongly) returned " + color + ".");
      System.exit(4);
    }
  }
}
