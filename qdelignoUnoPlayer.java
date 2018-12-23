package uno;
import java.util.List;

public class qdelignoUnoPlayer implements UnoPlayer {

    public int play(List<Card> hand, Card upCard, Color calledColor,
        GameState state) {

            Rank rangCarteMain,rangCarteJeu;
            int numCarteMain=100,numCarteJeu=100,main=hand.size(),var;
            Color colCarteMain=Color.NONE,colCarteJeu=Color.NONE;
            int[] previousTab = state.getNumCardsInHandsOfUpcomingPlayers();
            int[] currentTab = state.getNumCardsInHandsOfUpcomingPlayers();

            // Je crois en l'âme des cartes

                // Tests sur le rang de la carte du milieu
                rangCarteJeu = upCard.getRank();
                switch(rangCarteJeu){
	                case NUMBER : // On prend le numéro et la couleur
	                    numCarteJeu = upCard.getNumber();
	                    colCarteJeu= upCard.getColor();
	                break;
	                case WILD : // On attribue un numéro et la calledColor
	                    numCarteJeu=10;
	                    colCarteJeu=calledColor;
	                break;
	                case WILD_D4 : // On attribue un numéro et la calledColor
	                    numCarteJeu=11;
	                    colCarteJeu=calledColor;
	                break;
	                case DRAW_TWO : // On attribue un numéro et on prend la couleur
	                  	numCarteJeu=12;
	                  	colCarteJeu=upCard.getColor();
	                break;
	                case SKIP : // On attribue un numéro et on prend la couleur
	                      numCarteJeu=13;
	                      colCarteJeu=upCard.getColor();
	                break;
	                case REVERSE : // On attribue un numéro et on prend la couleur
	                  	colCarteJeu=upCard.getColor();
	                  	numCarteJeu=14;
	                break;
                }

                for (int i=0;i<hand.size();i++) {
	                // Tests sur le rang des cartes de la main
	                rangCarteMain = hand.get(i).getRank();
	                switch(rangCarteMain){
	                    case NUMBER :
	                    	numCarteMain = hand.get(i).getNumber();
	                    	colCarteMain = hand.get(i).getColor();
	                    break;
	                    case WILD :
		                    numCarteMain=numCarteJeu;
		                    colCarteMain=colCarteJeu;
		                break;
		                case WILD_D4 :
		                    numCarteMain=numCarteJeu;
		                    colCarteMain=colCarteJeu;
		                break;
		                case DRAW_TWO :
		                  	numCarteMain=12;
		                  	colCarteMain=hand.get(i).getColor();
		                break;
		                case SKIP :
		                    colCarteMain=hand.get(i).getColor();
		                    numCarteMain=13;                
		                break;
		                case REVERSE :
		                  	colCarteMain=hand.get(i).getColor();
		                  	numCarteMain=14;
		                break;
	                }

	            // Test si on peut jouer les cartes :
                  	// Si le numéro ou la couleur correspond
                    if (numCarteMain==numCarteJeu || colCarteMain==colCarteJeu)
                    	// On retourne la première carte qu'on peut jouer
                    	return i;
                    // Sinon si on a un WILD
                    else if (rangCarteMain==Rank.WILD)
                    	return i;
                    // Ou si on a un WILD_D4
                    else if (rangCarteMain==Rank.WILD_D4)
                    	return i;
            	}
		        // Sinon on passe
		        return -1;
    } // Fin méthode play

    public Color callColor(List<Card> hand) {
    	int nbJaunes=0,nbRouges=0,nbBleues=0,nbVertes=0;
    	
    	// On compte le nombre de cartes de chaque couleur
    	for (int i=0;i<hand.size();i++) {
    		Color colCarteMain = hand.get(i).getColor();
    		switch (colCarteMain){
    			case YELLOW :
    				nbJaunes ++;
    			break;
    			case RED :
    				nbRouges ++;
    			break;
    			case BLUE :
    				nbBleues ++;
    			break;
    			case GREEN :
    				nbVertes ++;
    			break;
    		}
    	}

    	// On fait un arbre de choix pour choisir la couleur la plus présente dans la main
    	if (nbJaunes>=nbRouges) {
    		if (nbJaunes>nbBleues) {
    			if (nbJaunes>=nbVertes) 
    				return Color.YELLOW;
    			else
    				return Color.GREEN;
    		}
    		else if (nbBleues>=nbVertes) 
    			return Color.BLUE;
    		else
    			return Color.GREEN;
    	}
    	else {
    		if (nbRouges>=nbBleues) {
	    		if (nbRouges>=nbVertes) 
	    			return Color.RED;
	    		else
	    			return Color.GREEN;
	    	}
	    	else if (nbBleues>=nbVertes) 
    			return Color.BLUE;
    		else
    			return Color.GREEN;
    	}

    } // Fin méthode callColor

} // Fin classe