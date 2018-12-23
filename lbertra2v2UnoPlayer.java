package uno;

import java.util.List;
import java.util.ArrayList;

public class lbertra2v2UnoPlayer implements UnoPlayer {

  /**
    * Détermine la couleur la plus jouée sur les deux précédents tours.
    * @param state permet d'accéder aux informations globales de la partie.
    * @return couleur la plus jouée sur les deux précédents tours.
    */
  public Color mostPlayedColor(GameState state, int maxCountedTours) {

    int redCounter = 0;
    int yelCounter = 0;
    int greCounter = 0;
    int bluCounter = 0;

    for ( int i=state.getPlayedCards().size()-1, tour=0 ; i>=0 && tour<maxCountedTours ; i-- , tour++ ) {
      if (state.getPlayedCards().get(i).getColor()==Color.RED) {
        redCounter++;
      }
      else if (state.getPlayedCards().get(i).getColor()==Color.YELLOW) {
        yelCounter++;
      }
      else if (state.getPlayedCards().get(i).getColor()==Color.GREEN) {
        greCounter++;
      }
      else if (state.getPlayedCards().get(i).getColor()==Color.BLUE) {
        greCounter++;
      }
    }

    // SI il y a plus de rouge
    if (redCounter>=yelCounter && redCounter>=greCounter && redCounter>=bluCounter) {
      return Color.RED;
    }
    // SINON SI il y a plus de jaune
    else if (yelCounter>=redCounter && yelCounter>=greCounter && yelCounter>=bluCounter) {
      return Color.YELLOW;
    }
    // SINON SI il y a le plus de vert
    else if (greCounter>=redCounter && greCounter>=yelCounter && greCounter>=bluCounter) {
      return Color.GREEN;
    }
    // SINON (c'est qu'il y a le plus de bleu !)
    else {
      return Color.BLUE;
    }
  }

  /**
    * Détermine la couleur que je possède le plus de fois dans ma main.
    * @param hand liste des cartes dans ma main.
    * @return couleur que je possède le plus de fois dans ma main.
    */
  public Color mostHoldColor(List<Card> hand) {

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
    if (redCounter>=yelCounter && redCounter>=greCounter && redCounter>=bluCounter) {
      return Color.RED;
    }
    // SINON SI il y a plus de jaune
    else if (yelCounter>=redCounter && yelCounter>=greCounter && yelCounter>=bluCounter) {
      return Color.YELLOW;
    }
    // SINON SI il y a le plus de vert
    else if (greCounter>=redCounter && greCounter>=yelCounter && greCounter>=bluCounter) {
      return Color.GREEN;
    }
    // SINON (c'est qu'il y a le plus de bleu !)
    else {
      return Color.BLUE;
    }
  }

  /**
    * Détermine si j'ai une couleur dans ma main.
    * @param hand liste des cartes dans ma main.
    * @return vrai si je possède la couleur et faux le cas échéant.
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
    * Détermine si le joueur précédent a beaucoup de cartes
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

  //INUTILE DEBUT
  /**
    * Construit la liste des numéros des cartes jouées d'une couleur donnée.
    * @param state permet d'accéder aux informations globales de la partie.
    * @param color couleur donnée.
    * @return liste des numéros des cartes jouées de la couleur donnée.
    */
  public ArrayList<Integer> thisColorPlayedNumbers(GameState state, Color color) {
    ArrayList<Integer> list=new ArrayList<>();

    for ( int i=0 ; i<state.getPlayedCards().size() ; i++ ) {
      if ( state.getPlayedCards().get(i).getColor()==color && state.getPlayedCards().get(i).getRank()==Rank.NUMBER) {
        list.add(state.getPlayedCards().get(i).getNumber());
      }
    }
    return list;
  }
  // INUTILE FIN

  /**
    * Détermine si le numéro de carte donné apparait dans la couleur donnée,
    * dans les cartes jouées.
    */
  public boolean isPlayed(GameState state, Color color, int number) {

    for ( int i=0 ; i<state.getPlayedCards().size() ; i++ ) {
      if ( state.getPlayedCards().get(i).getColor()==color && state.getPlayedCards().get(i).getNumber()==number) {
        return true;
      }
    }
    return false;
  }

  /**
    * Détermine quelle carte numérotée jouable présente dans ma main il est préférable de poser
    * @param hand liste des cartes dans la main de notre joueur.
    * @param validNumCards liste des indices des cartes numérotées jouables.
    * @param state permet d'accéder aux informations globales de la partie.
    * @return indice de la carte numérotée la plus avantageuse
    */
  public int bestNumChoice(List<Card> hand, ArrayList<Integer> validNumCards, GameState state) {
    ArrayList<Integer> firstChoiceCards=new ArrayList<>();
    ArrayList<Integer> secondChoiceCards=new ArrayList<>();

    if ( !isColorHold(hand,Color.RED) || !isColorHold(hand,Color.YELLOW) || !isColorHold(hand,Color.GREEN) || !isColorHold(hand,Color.BLUE)) {
      if ( !isColorHold(hand,Color.RED) ) {
        for ( int i=0 ; i<validNumCards.size() ; i++ ) {
          if (  isPlayed( state , Color.RED, hand.get(validNumCards.get(i)).getNumber() ) && hand.get(validNumCards.get(i)).getColor()==mostHoldColor(hand) ) {
            firstChoiceCards.add(validNumCards.get(i));
          }
          else {
            secondChoiceCards.add(validNumCards.get(i));
          }
        }
      }
      if ( !isColorHold(hand,Color.YELLOW) ) {
        for ( int i=0 ; i<validNumCards.size() ; i++ ) {
          if (  isPlayed( state , Color.YELLOW, hand.get(validNumCards.get(i)).getNumber() ) && hand.get(validNumCards.get(i)).getColor()==mostHoldColor(hand) ) {
            firstChoiceCards.add(validNumCards.get(i));
          }
          else {
            secondChoiceCards.add(validNumCards.get(i));
          }
        }
      }
      if ( !isColorHold(hand,Color.GREEN) ) {
        for ( int i=0 ; i<validNumCards.size() ; i++ ) {
          if (  isPlayed( state , Color.GREEN, hand.get(validNumCards.get(i)).getNumber() ) && hand.get(validNumCards.get(i)).getColor()==mostHoldColor(hand) ) {
            firstChoiceCards.add(validNumCards.get(i));
          }
          else {
            secondChoiceCards.add(validNumCards.get(i));
          }
        }
      }
      if ( !isColorHold(hand,Color.BLUE) ) {
        for ( int i=0 ; i<validNumCards.size() ; i++ ) {
          if (  isPlayed( state , Color.BLUE, hand.get(validNumCards.get(i)).getNumber() ) && hand.get(validNumCards.get(i)).getColor()==mostHoldColor(hand) ) {
            firstChoiceCards.add(validNumCards.get(i));
          }
          else {
            secondChoiceCards.add(validNumCards.get(i));
          }
        }
      }
    }

    else {
      for ( int i=0 ; i<validNumCards.size() ; i++ ) {
        if ( hand.get(validNumCards.get(i)).getColor()==mostHoldColor(hand) ) {
          firstChoiceCards.add(validNumCards.get(i));
        }
        else {
          secondChoiceCards.add(validNumCards.get(i));
        }
      }
    }

    int bestMem=0;

    if ( !firstChoiceCards.isEmpty() ) {
      for ( int i=0 ; i<firstChoiceCards.size() ; i++ ) {
        if ( hand.get(firstChoiceCards.get(i)).getNumber() >= hand.get(firstChoiceCards.get(bestMem)).getNumber() ) {
          bestMem=i;
        }
      }
      return firstChoiceCards.get(bestMem);
    }
    else if ( !secondChoiceCards.isEmpty() ) {
      for ( int i=0 ; i<secondChoiceCards.size() ; i++ ) {
        if ( hand.get(secondChoiceCards.get(i)).getNumber() >= hand.get(secondChoiceCards.get(bestMem)).getNumber() ) {
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
    * Détermine la carte que je vais jouer en retournant son indice au sein de ma main.
    * Retourne -1 pour passer mon tour si je ne peux rien jouer.
    * @param hand liste des cartes de ma main.
    * @param upCard carte retournée (sur laquelle je dois jouer si cela m'est possible).
    * @param calledColor si la upCard est un jocker, cela m'indique la couleur demandée.
    * @param state permet d'accéder aux informations globales de la partie.
    * @return indice de la carte jouée au sein de ma main.
    */
  public int play(List<Card> hand, Card upCard, Color calledColor,GameState state) {

    ArrayList<Integer> validNumCards=new ArrayList<>();
    int skiPos=-1;
    int revPos=-1;
    int draPos=-1;
    int wilPos=-1;
    int wifPos=-1;

    // POUR chaque carte en main FAIRE:
    for (int i=0;i<hand.size() ;i++ ) {
      if (hand.get(i).getRank()==Rank.NUMBER) {
        // SI elle est de la même couleur OU a le même numéro que la upCard ALORS ajouter son indice à la liste possibilities
        if ( hand.get(i).getNumber()==upCard.getNumber() || hand.get(i).getColor()==upCard.getColor() || hand.get(i).getColor()==calledColor ) {
          validNumCards.add(i);
        }
      }
      else if ( hand.get(i).getRank()==Rank.SKIP && ( upCard.getRank()==Rank.SKIP || hand.get(i).getColor()==upCard.getColor() || hand.get(i).getColor()==calledColor ) ) {
        skiPos=i;
      }
      else if ( hand.get(i).getRank()==Rank.REVERSE && ( upCard.getRank()==Rank.REVERSE || hand.get(i).getColor()==upCard.getColor() || hand.get(i).getColor()==calledColor ) ) {
        revPos=i;
      }
      else if ( hand.get(i).getRank()==Rank.DRAW_TWO && ( upCard.getRank()==Rank.DRAW_TWO || hand.get(i).getColor()==upCard.getColor() || hand.get(i).getColor()==calledColor ) ) {
        draPos=i;
      }
      else if ( hand.get(i).getRank()==Rank.WILD ) {
        wilPos=i;
      }
      else if ( hand.get(i).getRank()==Rank.WILD_D4 ) {
        wifPos=i;
      }
    }

  if (skiPos>-1) {
    return skiPos;
  }
  else if (revPos>-1) {
    return revPos;
  }
  else if (draPos>-1) {
    return draPos;
  }
  else if (wilPos>-1) {
    return wilPos;
  }
  else if (wifPos>-1) {
    return wifPos;
  }
  else if (bestNumChoice(hand,validNumCards,state)>-1) {
    return bestNumChoice(hand,validNumCards,state);
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

    int redCounter=0;
    int yelCounter=0;
    int greCounter=0;
    int bluCounter=0;

    // POUR chaque carte en main FAIRE:
    for (int i=0;i<hand.size();i++ ) {
      // SI elle est rouge, incrémenter redCounter
      if (hand.get(i).getColor()==Color.RED) {
        redCounter++;
      }
      // SI elle est jaune, incrémenter yelCounter
      if (hand.get(i).getColor()==Color.YELLOW) {
        yelCounter++;
      }
      // SI elle est verte, incrémenter greCounter
      if (hand.get(i).getColor()==Color.GREEN) {
        greCounter++;
      }
      // SI elle est bleu, incrémenter bluCounter
      if (hand.get(i).getColor()==Color.BLUE) {
        bluCounter++;
      }
    }

    // SI on a le plus de cartes rouge
    if (redCounter>=yelCounter && redCounter>=greCounter && redCounter>=bluCounter) {
      return Color.RED;
    }
    // SINON SI on a le plus de cartes jaune
    else if (yelCounter>=redCounter && yelCounter>=greCounter && yelCounter>=bluCounter) {
      return Color.YELLOW;
    }
    // SINON SI on a le plus de cartes vertes
    else if (greCounter>=redCounter && greCounter>=yelCounter && greCounter>=bluCounter) {
      return Color.GREEN;
    }
    // SINON SI on a le plus de cartes bleu
    else if (bluCounter>=redCounter && bluCounter>=yelCounter && bluCounter>=greCounter) {
      return Color.BLUE;
    }
    // SINON renvoyer Color.NONE pour déclencher une erreur
    else {
      return Color.NONE;
    }
  }
}
