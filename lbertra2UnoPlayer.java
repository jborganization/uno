package uno;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class lbertra2UnoPlayer implements UnoPlayer {

  /**
    * Détermine la carte que notre joueur va jouer et retourne son indice au sein de sa main.
    * Retourne -1 si notre joueur doit passer son tour.
    * @param hand liste des cartes dans la main de notre joueur.
    * @param upCard carte retournée (sur laquelle il faut jouer si cela nous est possible).
    * @param calledColor si la upCard est un jocker, cela nous indique la couleur demandée.
    * @param state permet d'accéder aux informations globales de la partie.
    * @return indice de la carte jouée par notre joueur.
    */
  public int play(List<Card> hand, Card upCard, Color calledColor,GameState state) {
    ArrayList<Integer> numList=new ArrayList<>();
    int higPos=-1;
    int skiPos=-1;
    int revPos=-1;
    int draPos=-1;
    int wilPos=-1;
    int wifPos=-1;
    int besPos=-1;
    int memoire = 0;

    boolean isRed = false;
    boolean isYel = false;
    boolean isGre = false;
    boolean isBlu = false;

    int redCounter = 0;
    int greCounter = 0;
    int bluCounter = 0;
    int yelCounter = 0;
    int numCounter = 0;
    int skiCounter = 0;
    int revCounter = 0;
    int draCounter = 0;
    int wilCounter = 0;
    int wifCounter = 0;

    // POUR chaque carte en main FAIRE:
    for (int i=0;i<hand.size() ;i++ ) {
      if (hand.get(i).getRank()==Rank.NUMBER) {
        // SI elle est de la même couleur OU a le même numéro que la upCard ALORS ajouter son indice à la liste possibilities
        if ( hand.get(i).getNumber()==upCard.getNumber() || hand.get(i).getColor()==upCard.getColor() || hand.get(i).getColor()==calledColor ) {
          numList.add(i);
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
      if (hand.get(i).getColor()==Color.RED) {
        isRed = true;
      }
      if (hand.get(i).getColor()==Color.YELLOW) {
        isYel = true;
      }
      if (hand.get(i).getColor()==Color.GREEN) {
        isGre = true;
      }
      if (hand.get(i).getColor()==Color.BLUE) {
        isBlu = true;
      }
    }

    // SI il y en a, POUR chaque carte numérotée jouable, on cherche l'indice de la carte au plus grand chiffre
    if ( !numList.isEmpty() ) {
      int mem=0;
      for (int i=0;i<numList.size() ;i++ ) {
        if ( hand.get(numList.get(i)).getNumber() >= hand.get(numList.get(mem)).getNumber()  ) {
          mem=i;
        }
      }
      higPos=numList.get(mem);
    }

    for (int i=0;i<state.getPlayedCards().size() ;i++ ) { //Cette boucle sert à compter les cartes jouées par les joueurs
      if (state.getPlayedCards().get(i).getRank()==Rank.NUMBER) {
        numCounter++;
      }
      if (state.getPlayedCards().get(i).getRank()==Rank.SKIP) {
        skiCounter++;
      }
      if (state.getPlayedCards().get(i).getRank()==Rank.REVERSE) {
        revCounter++;
      }
      if (state.getPlayedCards().get(i).getRank()==Rank.DRAW_TWO) {
        draCounter++;
      }
      if (state.getPlayedCards().get(i).getRank()==Rank.WILD) {
        wilCounter++;
      }
      if (state.getPlayedCards().get(i).getRank()==Rank.WILD_D4) {
        wifCounter++;
      }
      if (state.getPlayedCards().get(i).getColor()==Color.RED) {
        redCounter++;
      }
      if (state.getPlayedCards().get(i).getColor()==Color.GREEN) {
        greCounter++;
      }
      if (state.getPlayedCards().get(i).getColor()==Color.BLUE) {
        bluCounter++;
      }
      if (state.getPlayedCards().get(i).getColor()==Color.YELLOW) {
        yelCounter++;
      }
    }
    int rankRed,rankBlue,rankGreen,rankYellow;
    ArrayList<Integer> listRank=new ArrayList<>();
    listRank.add(redCounter);
    listRank.add(yelCounter);
    listRank.add(greCounter);
    listRank.add(bluCounter);

    Collections.sort(listRank);
    for(int k = 0; k< listRank.size() ; k++){
        if(listRank.get(k).equals(redCounter))
            rankRed = k;

        if(listRank.get(k).equals(yelCounter))
            rankYellow = k;
        if(listRank.get(k).equals(greCounter))
            rankGreen = k;
        if(listRank.get(k).equals(bluCounter))
            rankBlue = k;

    }
    if ( !numList.isEmpty() ) {
    // SI on a le plus de cartes rouge
    if (redCounter>=yelCounter && redCounter>=greCounter && redCounter>=bluCounter && isRed == true) {
      for (int i=0;i<numList.size() ;i++ ) {
        if ( hand.get(numList.get(i)).getNumber() >= hand.get(numList.get(memoire)).getNumber() && hand.get(numList.get(i)).getColor() == Color.RED && hand.get(numList.get(memoire)).getColor() == Color.RED) {
          memoire=i;
        }
      }
      besPos=numList.get(memoire);
    }
    // SINON SI on a le plus de cartes jaune
    else if (yelCounter>=redCounter && yelCounter>=greCounter && yelCounter>=bluCounter && isYel == true) {
      for (int i=0;i<numList.size() ;i++ ) {
        if ( hand.get(numList.get(i)).getNumber() >= hand.get(numList.get(memoire)).getNumber() && hand.get(numList.get(i)).getColor() == Color.YELLOW && hand.get(numList.get(memoire)).getColor() == Color.YELLOW) {
          memoire=i;
        }
      }
      besPos=numList.get(memoire);
    }
    // SINON SI on a le plus de cartes vertes
    else if (greCounter>=redCounter && greCounter>=yelCounter && greCounter>=bluCounter && isGre == true) {
      for (int i=0;i<numList.size() ;i++ ) {
        if ( hand.get(numList.get(i)).getNumber() >= hand.get(numList.get(memoire)).getNumber() && hand.get(numList.get(i)).getColor() == Color.GREEN && hand.get(numList.get(memoire)).getColor() == Color.GREEN) {
          memoire=i;
        }
      }
      besPos=numList.get(memoire);
    }
    // SINON SI on a le plus de cartes bleu
    else if (bluCounter>=redCounter && bluCounter>=yelCounter && bluCounter>=greCounter && isBlu == true) {
      for (int i=0;i<numList.size() ;i++ ) {
        if ( hand.get(numList.get(i)).getNumber() >= hand.get(numList.get(memoire)).getNumber() && hand.get(numList.get(i)).getColor() == Color.BLUE && hand.get(numList.get(memoire)).getColor() == Color.BLUE) {
          memoire=i;
        }
      }
      besPos=numList.get(memoire);
    }
    // SINON renvoyer Color.NONE pour déclencher une erreur
    else {
      besPos = higPos;
    }
  }

    // On détermine quelle carte jouer

    // SI le joueur suivant a 2 cartes ou moins
    // if (state.getNumCardsInHandsOfUpcomingPlayers()[0]<=2) {
    //   if (draPos>-1) {
    //     return draPos;
    //   }
    // }


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
    else if (besPos>-1) {
      return besPos;
    }
    else if (higPos>-1) {
      return higPos;
    }
    else {
      return -1;
    }

  }

  /**
    * détermine quelle couleur est demandée par notre joueur s'il pose un jocker.
    * @param hand liste des cartes dans la main de notre joueur.
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
