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
