package com.example.project;
import java.util.ArrayList;


public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c);    //adds the card parameter to the arraylist hand
    }

    public String playHand(ArrayList<Card> communityCards){  
        allCards = new ArrayList<>(hand); //allCards duplicate hand ArrayList
        for (Card card : communityCards) { //Add all community cards
            allCards.add(card);
        }
           
        sortAllCards(); //Sorts all Cards
        int consecutiveCount = 0;
        for(int i = 0; i < allCards.size() - 1; i++) { //Check how many consecutive numbers there rae 
            if(Utility.getRankValue(allCards.get(i).getRank()) == Utility.getRankValue(allCards.get(i + 1).getRank()) - 1) {
                consecutiveCount++;
            }
        }

        int pairCount = 0;
        boolean threeKind = false;
        boolean fourKind = false;
        for(int i = 0; i < findRankingFrequency().size(); i ++) { //Checks how many pairs there are
            if(findRankingFrequency().get(i) == 2) {
                pairCount++;
            }
            else if(findRankingFrequency().get(i) == 3) { //Checks if there is three of a kind
                threeKind = true;
            }
            else if(findRankingFrequency().get(i) == 4) { //Checks if there is four of a kind
                fourKind = true;
            }
        }

        boolean flush = false;
        for(int i = 0; i < findSuitFrequency().size(); i ++) { //Checks if there is flush
            if(findSuitFrequency().get(i) == 5) {
                flush = true;
            }
        }
        if(flush) {
            if(consecutiveCount == 4) { //Checks if there is a straight
                if(Utility.getRankValue(allCards.get(0).getRank()) == 10) { //Check if first of straight is 10
                    return "Royal Flush";
                }
                return "Straight Flush";
            }
            if(fourKind) { //Check if there is a four of a kind in a flush
                return "Four of a Kind";
            }
            else if(pairCount > 0 && threeKind) { //Check if there is a Full House 
                return "Full House";
            }
            return "Flush"; 
        }
        else if(fourKind) { //Checks four of a kind
            return "Four of a Kind";
        }
        else if(pairCount > 0 && threeKind) {//Checks full house
            return "Full House";
        }
        else if(consecutiveCount == 4) { //Checks if there is a straight (four in a row)
            return "Straight";
        }
        else if(threeKind) { //Checks if there is three of a card
            return "Three of a Kind";
        }
        else if(pairCount == 2) { //Checks if there is twp pairs
            return "Two Pair";
        }
        else if (pairCount == 1) { //Checks if there is One Pair
            return "A Pair";
        }

        int playerHigh = 0;
        int communityHigh = 0;
        for(Card card : communityCards) { //Finds the highest card of the Community Cards
            if(Utility.getRankValue(card.getRank()) > communityHigh) {
                communityHigh = Utility.getRankValue(card.getRank());
            }
        }
        for(Card card : hand) { //Finds the highest card of the player's hand cards 
            if(Utility.getRankValue(card.getRank()) > playerHigh) {
                playerHigh = Utility.getRankValue(card.getRank());
            }
        }

        if(playerHigh > communityHigh) { //if the player's hand's card is higher than the highest card of the community cards 
            return "High Card";
        }
        return "Nothing";
    }

    public void sortAllCards(){
        for(int i = 1; i < allCards.size(); i++) { //iterate thorugh all the cards to sort in order 
            int index = i;
            while(index - 1 >= 0 && cardInFront(allCards.get(i), allCards.get(index - 1))) { 
                index--;
            }
            Card temp = allCards.get(i);  
            for(int j = i - 1; j >= index; j --) {
                allCards.set(j + 1, allCards.get(j));
            }
            allCards.set(index, temp);
        }
    }

    public ArrayList<Integer> findRankingFrequency(){ //returns how many of the type of card there is 
        ArrayList<Integer> rankFreq = new ArrayList<Integer>();
        for(int i = 0; i < Utility.getRanks().length; i ++) {
            rankFreq.add(0);
        }
       
        for(int i = 0; i < allCards.size();i ++) {
            int rankVal = Utility.getRankValue(allCards.get(i).getRank());
            rankFreq.set(rankVal - 2, rankFreq.get((rankVal - 2)) + 1);
        }
        return rankFreq; 
    }

    public ArrayList<Integer> findSuitFrequency(){
        ArrayList<Integer> suitFreq = new ArrayList<Integer>();  //create new arraylist to return
        for(int i = 0; i < Utility.getSuits().length; i ++) {
            suitFreq.add(0);
        }    

        for(int i = 0; i < allCards.size();i ++) {   //iterate through all the cards to look for spades
            if(allCards.get(i).getSuit().equals("♠")) {
                suitFreq.set(0, suitFreq.get(0) + 1);

            }
            else if(allCards.get(i).getSuit().equals("♥")) { //iterate through all the cards to look for hearts
                suitFreq.set(1, suitFreq.get(1) + 1);

            }
            else if(allCards.get(i).getSuit().equals("♣")) { //iterate through all the cards to look for clover
                suitFreq.set(2, suitFreq.get(2) + 1);
               
            }
            else if(allCards.get(i).getSuit().equals("♦")) { //iterate through all the cards to look for diamonds
                suitFreq.set(3, suitFreq.get(3) + 1);
               
            }
        }
        return suitFreq;
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }
    private boolean cardInFront(Card c1, Card c2) { //Check if a card has smaller value than the other
        if(Utility.getRankValue(c1.getRank()) < Utility.getRankValue(c2.getRank())) {
            return true;
        }
        return false;
    }


}
