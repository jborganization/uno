package uno;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;











public class UnoSimulation
{
  static boolean printVerbose = true;
  









  public static final String PLAYER_FILENAME = "players.txt";
  









  private static final Logger LOGGER = Logger.getLogger(new Throwable().getStackTrace()[0].getClassName());
  




  private static ArrayList<String> playerNames = new ArrayList();
  private static ArrayList<String> playerClasses = new ArrayList();
  



  public UnoSimulation() {}
  


  public static void main(String[] args)
  {
    int numGames = 0;
    if ((args.length != 1) && (args.length != 2)) {
      System.out.println("Usage: UnoSimulation numberOfGames [verbose|quiet].");
      System.exit(1);
    }
    numGames = Integer.valueOf(args[0]).intValue();
    if ((args.length == 2) && (args[1].equals("quiet"))) {
      printVerbose = false;
    }
    if ((args.length == 2) && (args[1].equals("verbose"))) {
      printVerbose = true;
    }
    try {
      loadPlayerData();
      Scoreboard s = new Scoreboard((String[])playerNames.toArray(new String[0]));
      for (int i = 0; i < numGames; i++) {
        Game g = new Game(s, playerClasses);
        g.play();
      }
      System.out.println(s);
    }
    catch (Exception e) {
      LOGGER.log(Level.SEVERE, e.toString(), e);
    }
  }
  
  private static void loadPlayerData() throws Exception {
    try { BufferedReader br = new BufferedReader(new FileReader("players.txt"));Throwable localThrowable6 = null;
      try { String playerLine = br.readLine();
        while (playerLine != null) {
          try { Scanner line = new Scanner(playerLine).useDelimiter(",");Throwable localThrowable7 = null;
            try { playerNames.add(line.next());
              playerClasses.add("uno." + line.next() + "UnoPlayer");
              playerLine = br.readLine();
            }
            catch (Throwable localThrowable1)
            {
              localThrowable7 = localThrowable1;throw localThrowable1;
            }
            finally {}
          }
          catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
          }
        }
      }
      catch (Throwable localThrowable4)
      {
        localThrowable6 = localThrowable4;throw localThrowable4;




      }
      finally
      {



        if (br != null) if (localThrowable6 != null) try { br.close(); } catch (Throwable localThrowable5) { localThrowable6.addSuppressed(localThrowable5); } else br.close();
      } } catch (IOException e) { LOGGER.log(Level.SEVERE, e.toString(), e);
    }
  }
}
