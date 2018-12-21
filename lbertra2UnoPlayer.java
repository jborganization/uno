package uno;

import java.util.List;

public class lbertra2UnoPlayer implements UnoPlayer {

  /**
    * Détermine la carte que notre joueur va jouer et retourne son indice au sein de sa main.
    * Retourn -1 si notre joueur doit passer son tour.
    * @param hand liste des cartes dans la main de notre joueur
    * @param upCard carte retournée (sur laquelle il faut jouer si cela nous est possible)
    * @param calledColor si la upCard est un jocker, cela nous indique la couleur demandée
    * @param state permet d'accéder aux informations globales de la partie
    * @return indice de la carte jouée par notre joueur
    */
  public int play(List<Card> hand, Card upCard, Color calledColor, GameState state) {
    //List<Card> possibilities;

    if (upCard.getRank()==Rank.NUMBER) {
      return 0;
    }

    else if (upCard.getRank()==Rank.SKIP) {
      return -1;
    }

    else if (upCard.getRank()==Rank.REVERSE) {
      return 0;
    }

    else if (upCard.getRank()==Rank.DRAW_TWO) {
      return -1;
    }

    else if (upCard.getRank()==Rank.WILD) {
      return 0;
    }

    else if (upCard.getRank()==Rank.WILD_D4) {
      return -1;
    }

    else {
      return 0;
    }
  }

  /**
    * détermine quelle couleur est demandée par notre joueur s'il pose un jocker
    * @param hand liste des cartes dans la main de notre joueur
    * @return la couleur demandée au joueur suivant
    */
  public Color callColor(List<Card> hand) {

      // Votre code ici
      return null;
  }

}
