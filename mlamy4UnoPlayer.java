
package uno;

import java.util.List;

public class mlamy4UnoPlayer implements UnoPlayer {


    public int play(List<Card> hand, Card upCard, Color calledColor,
        GameState state) {

		int d4=-1;
    	int num;
    	int upNum;
        
        
        
    	Color upCol =upCard.getColor();
    	Rank upRang = upCard.getRank();

        for (int i =0;i<hand.size();i++) // On parcourt la main 
        {
            Card carte= hand.get(i);
        	Color col =carte.getColor();
        	Rank rang =carte.getRank();
        	
        	
            if (upRang==Rank.DRAW_TWO && rang == Rank.DRAW_TWO) {
                return i;
            }
            if ((upRang!=Rank.NUMBER && upRang==rang) || rang!=Rank.NUMBER && upCol==col) {
                return i;
            }

             if ((upRang==Rank.WILD || upRang==Rank.WILD_D4)  && calledColor==col) { 
             //quand la carte au dessus est un joker ou un +4 on cherche une carte de la couleur appelée//   

                return i;
            }
        	else if(rang==Rank.WILD_D4) // Si on trouve un +4, on conserve sa position pour la jouer 
        	{						// Quand on est sûr de ne pas avoir la couleur demandée
        		d4=i;
        	}
            if (rang == Rank.NUMBER) // si c'est un nombre, on prend la valeur
            {
                num = carte.getNumber();    
            }
            else num = -1;
            if (upRang == Rank.NUMBER) // si c'est un nombre, on prend la valeur
            {
                upNum = upCard.getNumber();    
            }
            else upNum=-2;
            if(col == upCol  // on pose la carte si meme couleur
                 ||(rang == Rank.NUMBER && num == upNum)) // meme nombre ou meme rang
            {
            
                return i;

            } 
            else if (rang==Rank.WILD) {
                // en dernier recours on pose un joker//
                return i;

            }


        }
        if( d4>=0) // Apres avoir vérifié si on peut jouer, on vérifie si on a un +4
        {
        	return d4; // et on le pose, 
        }
        else return -1; // sinon on renvoie -1 pour passer son tour 
    }



    public Color callColor(List<Card> hand) {
    	int nbRouge=0;
    	int nbBleu=0;
    	int nbVert=0;
    	int nbJaune=0;
    	for (int i=0; i<hand.size();i++)
    		{
                Card carte= hand.get(i);
    			Color col = carte.getColor();
    			switch (col)
                { // On parcourt la main et on compte le nombre de carte de chaque couleur
    				case RED: nbRouge++; break;
    				case BLUE: nbBleu++; break;
    				case GREEN: nbVert++; break ;
    				case YELLOW: nbJaune++;break ;
    			}

    		} // On demande la couleur dont on a le plus de cartes 
    		if(nbRouge >= nbBleu && nbRouge >= nbVert && nbRouge >= nbJaune)
    		{
    			return Color.RED;
    		}
    		if(nbBleu >= nbRouge && nbBleu >= nbVert && nbBleu >= nbJaune)
    		{
    			return Color.BLUE;
    		}
    		if(nbVert >= nbBleu && nbVert >= nbRouge && nbVert >= nbJaune)
    		{
    			return Color.GREEN;
    		}
    		if(nbJaune >= nbBleu && nbJaune >= nbVert && nbJaune >= nbRouge)
    		{
    			return Color.YELLOW;
    		}


        
            return Color.BLUE;
    }


}
