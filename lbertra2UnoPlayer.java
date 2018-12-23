package uno;

import java.util.List;
import java.util.ArrayList;

public class lbertra2UnoPlayer implements UnoPlayer {

  /**
    * Détermine si j'ai une couleur donnée dans ma main.
    * @param hand liste des cartes dans ma main.
    * @return vrai si je possède la couleur, faux le cas échéant.
    */
  public boolean isColorHold(List<Card> hand, Color color) {

    for ( int i=0 ; i<hand.size() ; i++ ) {
      if (hand.get(i).getColor()==color) {
        return true;
      }
    }
    return false;
  }

  /**
    * Détermine si le joueur précédent a beaucoup de cartes.
    * @param state permet d'accéder aux informations globales de la partie.
    * @param defcon limite au delà de laquelle on considère le nombre de carte comme beaucoup.
    * @return vrai si le joueur précédent a beaucoup de cartes, faux sinon.
    */
  public boolean prevIsDanger(GameState state, int defcon) {
    if (state.getNumCardsInHandsOfUpcomingPlayers()[2]>defcon) {
      return true;
    }
    return false;
  }

  /**
    * Détermine si le joueur suivant s'approche de la victoire.
    * @param state permet d'accéder aux informations globales de la partie.
    * @param defcon limite en deçà de laquelle on considère que le joueur s'approche de la victoire.
    * @return vrai si le joueur précédent s'approche de la victoire, faux sinon.
    */
  public boolean nextIsFinishing(GameState state, int defcon) {
    if (state.getNumCardsInHandsOfUpcomingPlayers()[0]<=defcon) {
      return true;
    }
    return false;
  }

  /**
    * Détermine si un joueur autre que le suivant s'approche de la victoire.
    * @param state permet d'accéder aux informations globales de la partie.
    * @param defcon limite en deçà de laquelle on considère que le joueur s'approche de la victoire.
    * @return vrai si un joueur autre que le suivant s'approche de la victoire, faux sinon.
    */
  public boolean someoneIsFinishing(GameState state, int defcon) {
    if ( state.getNumCardsInHandsOfUpcomingPlayers()[1]<=defcon
    || state.getNumCardsInHandsOfUpcomingPlayers()[2]<=defcon ) {
      return true;
    }
    return false;
  }

  /**
    * Détermine si le numéro de carte donné apparait dans la couleur donnée,
    * dans les cartes jouées.
    * @param state permet d'accéder aux informations globales de la partie.
    * @param color couleur donnée.
    * @param number numéro de carte donné.
    * @return vrai si le numéro donné apparait dans la couleur donnée, faux sinon.
    */
  public boolean isPlayed(GameState state, Color color, int number) {

    for ( int i=0 ; i<state.getPlayedCards().size() ; i++ ) {
      if ( state.getPlayedCards().get(i).getColor()==color
      && state.getPlayedCards().get(i).getNumber()==number) {
        return true;
      }
    }
    return false;
  }

  /**
    * Détermine quelle carte numérotée jouable présente dans ma main il m'est préférable de poser.
    * @param hand liste des cartes dans ma main.
    * @param validCards liste des indices des cartes numérotées jouables.
    * @param state permet d'accéder aux informations globales de la partie.
    * @return indice de la carte numérotée la plus avantageuse
    */
  public int bestNumChoice(List<Card> hand, ArrayList<Integer> validCards, GameState state) {
    ArrayList<Integer> firstChoiceCards=new ArrayList<>();
    ArrayList<Integer> secondChoiceCards=new ArrayList<>();

    if ( !isColorHold(hand,Color.RED)
    || !isColorHold(hand,Color.YELLOW)
    || !isColorHold(hand,Color.GREEN)
    || !isColorHold(hand,Color.BLUE)) {
      if ( !isColorHold(hand,Color.RED) ) {
        for ( int i=0 ; i<validCards.size() ; i++ ) {
          if(isPlayed(state,Color.RED,hand.get(validCards.get(i)).getNumber())
          && hand.get(validCards.get(i)).getColor()==callColor(hand) ) {
            firstChoiceCards.add(validCards.get(i));
          }
          else {
            secondChoiceCards.add(validCards.get(i));
          }
        }
      }
      if ( !isColorHold(hand,Color.YELLOW) ) {
        for ( int i=0 ; i<validCards.size() ; i++ ) {
          if (  isPlayed( state , Color.YELLOW, hand.get(validCards.get(i)).getNumber() )
          && hand.get(validCards.get(i)).getColor()==callColor(hand) ) {
            firstChoiceCards.add(validCards.get(i));
          }
          else {
            secondChoiceCards.add(validCards.get(i));
          }
        }
      }
      if ( !isColorHold(hand,Color.GREEN) ) {
        for ( int i=0 ; i<validCards.size() ; i++ ) {
          if (  isPlayed( state , Color.GREEN, hand.get(validCards.get(i)).getNumber() )
          && hand.get(validCards.get(i)).getColor()==callColor(hand) ) {
            firstChoiceCards.add(validCards.get(i));
          }
          else {
            secondChoiceCards.add(validCards.get(i));
          }
        }
      }
      if ( !isColorHold(hand,Color.BLUE) ) {
        for ( int i=0 ; i<validCards.size() ; i++ ) {
          if (  isPlayed( state , Color.BLUE, hand.get(validCards.get(i)).getNumber() )
          && hand.get(validCards.get(i)).getColor()==callColor(hand) ) {
            firstChoiceCards.add(validCards.get(i));
          }
          else {
            secondChoiceCards.add(validCards.get(i));
          }
        }
      }
    }

    else {
      for ( int i=0 ; i<validCards.size() ; i++ ) {
        if ( hand.get(validCards.get(i)).getColor()==callColor(hand) ) {
          firstChoiceCards.add(validCards.get(i));
        }
        else {
          secondChoiceCards.add(validCards.get(i));
        }
      }
    }

    int bestMem=0;

    if ( !firstChoiceCards.isEmpty() ) {
      for ( int i=0 ; i<firstChoiceCards.size() ; i++ ) {
        if ( hand.get(firstChoiceCards.get(i)).getNumber()
        >= hand.get(firstChoiceCards.get(bestMem)).getNumber() ) {
          bestMem=i;
        }
      }
      return firstChoiceCards.get(bestMem);
    }
    else if ( !secondChoiceCards.isEmpty() ) {
      for ( int i=0 ; i<secondChoiceCards.size() ; i++ ) {
        if ( hand.get(secondChoiceCards.get(i)).getNumber()
        >= hand.get(secondChoiceCards.get(bestMem)).getNumber() ) {
          bestMem=i;
        }
      }
      return secondChoiceCards.get(bestMem);
    }
    else {
      return -1;
    }
  }

  /**
    * Détermine quelle carte de rang donné jouable présente dans ma main
    * il m'est préférable de poser.
    * @param hand liste des cartes dans ma main.
    * @param validRanCards liste des indices des cartes de ce rang et jouables.
    * @param state permet d'accéder aux informations globales de la partie.
    * @param rank rang des cartes de la liste.
    * @return indice de la carte numérotée la plus avantageuse
    */
  public int bestRanChoice(List<Card> hand, ArrayList<Integer> validRanCards) {

    if ( !validRanCards.isEmpty() ) {
      for ( int i=0 ; i<validRanCards.size() ; i++ ) {
        if ( hand.get(validRanCards.get(i)).getColor()==callColor(hand) ) {
          return validRanCards.get(i);
        }
      }
      return validRanCards.get(0);
    }
    else {
      return -1;
    }
  }

  /**
    * Détermine la carte que je vais jouer en retournant son indice
    * au sein de ma main.
    * Retourne -1 pour passer mon tour si je ne peux rien jouer.
    * @param hand liste des cartes de ma main.
    * @param upCard carte retournée (sur laquelle je dois jouer si cela m'est possible).
    * @param calledColor si la upCard est un jocker, cela m'indique la couleur demandée.
    * @param state permet d'accéder aux informations globales de la partie.
    * @return indice de la carte jouée au sein de ma main.
    */
  public int play(List<Card> hand, Card upCard, Color calledColor,GameState state) {

    ArrayList<Integer> validNumCards=new ArrayList<>();
    ArrayList<Integer> validSkiCards=new ArrayList<>();
    ArrayList<Integer> validRevCards=new ArrayList<>();
    ArrayList<Integer> validDraCards=new ArrayList<>();
    int wilPos=-1;
    int wifPos=-1;

    // POUR chaque carte en main FAIRE:
    for (int i=0;i<hand.size() ;i++ ) {
      if (hand.get(i).getRank()==Rank.NUMBER) {
        // SI elle est de la même couleur OU a le même numéro que la upCard
        // ALORS ajouter son indice à la liste possibilities
        if ( hand.get(i).getNumber()==upCard.getNumber()
        || hand.get(i).getColor()==upCard.getColor()
        || hand.get(i).getColor()==calledColor ) {
          validNumCards.add(i);
        }
      }
      else if ( hand.get(i).getRank()==Rank.SKIP
      && ( upCard.getRank()==Rank.SKIP
      || hand.get(i).getColor()==upCard.getColor()
      || hand.get(i).getColor()==calledColor ) ) {
        validSkiCards.add(i);
      }
      else if ( hand.get(i).getRank()==Rank.REVERSE
      && ( upCard.getRank()==Rank.REVERSE
      || hand.get(i).getColor()==upCard.getColor()
      || hand.get(i).getColor()==calledColor ) ) {
        validRevCards.add(i);
      }
      else if ( hand.get(i).getRank()==Rank.DRAW_TWO
      && ( upCard.getRank()==Rank.DRAW_TWO
      || hand.get(i).getColor()==upCard.getColor()
      || hand.get(i).getColor()==calledColor ) ) {
        validDraCards.add(i);
      }
      else if ( hand.get(i).getRank()==Rank.WILD ) {
        wilPos=i;
      }
      else if ( hand.get(i).getRank()==Rank.WILD_D4 ) {
        wifPos=i;
      }
    }

    int bestNumChoice=bestNumChoice(hand,validNumCards,state);
    int bestSkiChoice=bestRanChoice(hand, validSkiCards);
    int bestRevChoice=bestRanChoice(hand, validRevCards);
    int bestDraChoice=bestRanChoice(hand, validDraCards);

    if ( prevIsDanger(state,5) ) {
      if (bestRevChoice>-1) {
        return bestRevChoice;
      }
      else if (bestNumChoice>-1) {
        return bestNumChoice;
      }
      else if (bestSkiChoice>-1) {
        return bestSkiChoice;
      }
      else if (bestDraChoice>-1) {
        return bestDraChoice;
      }
      else if (wilPos>-1) {
        return wilPos;
      }
      else if (wifPos>-1) {
        return wifPos;
      }

      else {
        return -1;
      }
    }

    if ( nextIsFinishing(state,2) ) {
      if (bestDraChoice>-1) {
        return bestDraChoice;
      }
      else if (wifPos>-1) {
        return wifPos;
      }
      else if (wilPos>-1) {
        return wilPos;
      }
      else if (bestSkiChoice>-1) {
        return bestSkiChoice;
      }
      else if (bestRevChoice>-1) {
        return bestRevChoice;
      }
      else if (bestNumChoice>-1) {
        return bestNumChoice;
      }
      else {
        return -1;
      }
    }

    if ( someoneIsFinishing(state,2) ) {
      if (wifPos>-1) {
        return wifPos;
      }
      else if (wilPos>-1) {
        return wilPos;
      }
      else if (bestRevChoice>-1) {
        return bestRevChoice;
      }
      else if (bestSkiChoice>-1) {
        return bestSkiChoice;
      }
      else if (bestDraChoice>-1) {
        return bestDraChoice;
      }
      else if (bestNumChoice>-1) {
        return bestNumChoice;
      }
      else {
        return -1;
      }
    }

    if (bestNumChoice>-1) {
      return bestNumChoice;
    }
    else if (bestSkiChoice>-1) {
      return bestSkiChoice;
    }
    else if (bestRevChoice>-1) {
      return bestRevChoice;
    }
    else if (bestDraChoice>-1) {
      return bestDraChoice;
    }
    else if (wilPos>-1) {
      return wilPos;
    }
    else if (wifPos>-1) {
      return wifPos;
    }
    else {
      return -1;
    }

  }

  /**
    * Détermine quelle couleur je demande si je pose pose un jocker.
    * @param hand liste des cartes de ma main.
    * @return la couleur demandée au joueur suivant.
    */
  public Color callColor(List<Card> hand) {

    int redCounter = 0;
    int yelCounter = 0;
    int greCounter = 0;
    int bluCounter = 0;

    for ( int i=0 ; i<hand.size() ; i++ ) {
      if (hand.get(i).getColor()==Color.RED) {
        redCounter++;
      }
      else if (hand.get(i).getColor()==Color.YELLOW) {
        yelCounter++;
      }
      else if (hand.get(i).getColor()==Color.GREEN) {
        greCounter++;
      }
      else if (hand.get(i).getColor()==Color.BLUE) {
        greCounter++;
      }
    }

    // SI il y a plus de rouge
    if (redCounter>=yelCounter
    && redCounter>=greCounter
    && redCounter>=bluCounter) {
      return Color.RED;
    }
    // SINON SI il y a plus de jaune
    else if (yelCounter>=redCounter
    && yelCounter>=greCounter
    && yelCounter>=bluCounter) {
      return Color.YELLOW;
    }
    // SINON SI il y a le plus de vert
    else if (greCounter>=redCounter
    && greCounter>=yelCounter
    && greCounter>=bluCounter) {
      return Color.GREEN;
    }
    // SINON c'est qu'il y a le plus de bleu OU que je n'ai plus que des WILD ou WILD_D4 en main.
    else {
      return Color.BLUE;
    }
  }
}
