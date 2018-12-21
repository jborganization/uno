package uno;

import java.util.List;
import java.util.ArrayList;

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
  public int play(List<Card> hand, Card upCard, Color calledColor, GameState state) {
    ArrayList<Integer> possibilities=new ArrayList<Integer>();

    // si la upCard est numérotée
    if (upCard.getRank()==Rank.NUMBER) {
      // POUR chaque carte en main FAIRE:
      for (int i=0;i<hand.size() ;i++ ) {
        // SI elle est de la même couleur OU a le même numéro que la upCard ALORS ajouter son indice à la liste possibilities
        if ( hand.get(i).getColor()==upCard.getColor() || hand.get(i).getNumber()==upCard.getNumber()) {
          possibilities.add(i);
        }
        System.out.println(possibilities);
      }
      return 1;
    }

    // si la upCard est du rang "SKIP"
    else if (upCard.getRank()==Rank.SKIP) {
      return -1;
    }

    // si la upCard est du rang "REVERSE"
    else if (upCard.getRank()==Rank.REVERSE) {
      return 1;
    }

    // si la upCard est du rang "DRAW_TWO"
    else if (upCard.getRank()==Rank.DRAW_TWO) {
      return -1;
    }

    // si la upCard est du rang "WILD"
    else if (upCard.getRank()==Rank.WILD) {
      return 1;
    }

    // si la upCard est du rang "WILD_D4"
    else if (upCard.getRank()==Rank.WILD_D4) {
      return -1;
    }

    else {
      return 0;
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
