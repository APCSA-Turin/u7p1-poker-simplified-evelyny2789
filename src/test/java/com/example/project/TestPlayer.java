package com.example.project;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class TestPlayer {
     @Test
    public void testAddCard() {
        Player player = new Player();
        Card card = new Card("A", "♠");
        player.addCard(card);
        assertEquals(1, player.getHand().size());
        assertEquals("A of ♠", player.getHand().get(0).toString());
    }

    @Test
    public void testSortAllCards() {
        Player player = new Player();
        player.addCard(new Card("3", "♠"));
        player.addCard(new Card("5", "♣"));
        
        //adds the community cards 
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("4", "♠"));
        communityCards.add(new Card("8", "♣"));
        communityCards.add(new Card("A", "♦"));
        
        player.playHand(communityCards);
        
        // Now the player should have 5 cards: 2 player cards + 3 community cards
        player.sortAllCards();
        assertEquals("3 of ♠", player.getAllCards().get(0).toString());  // First card should be 3 of ♠
        assertEquals("A of ♦", player.getAllCards().get(4).toString());  // Last card should be A of ♦
    }

    @Test
    public void testRoyalFlush() {
        Player player = new Player();
        player.addCard(new Card("10", "♠"));
        player.addCard(new Card("J", "♠"));
        
        // Community Cards
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("Q", "♠"));
        communityCards.add(new Card("K", "♠"));
        communityCards.add(new Card("A", "♠"));
        
        player.playHand(communityCards);
        String handResult = player.playHand(communityCards);
        
        assertEquals("Royal Flush", handResult);
    }

    @Test
    public void testStraightFlush() {
        Player player = new Player();
        player.addCard(new Card("9", "♠"));
        player.addCard(new Card("10", "♠"));
        
        // Community Cards
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("J", "♠"));
        communityCards.add(new Card("Q", "♠"));
        communityCards.add(new Card("K", "♠"));
        
        player.playHand(communityCards);
        String handResult = player.playHand(communityCards);
        
        assertEquals("Straight Flush", handResult);
    }

    @Test
    public void testFourOfAKindPair() {
        Player player = new Player();
        player.addCard(new Card("9", "♠"));
        player.addCard(new Card("9", "♦"));
        
        // Community Cards
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("9", "♣"));
        communityCards.add(new Card("9", "♥"));
        communityCards.add(new Card("A", "♦"));
        
        player.playHand(communityCards);
        String handResult = player.playHand(communityCards);
        
        assertEquals("Four of a Kind", handResult);
    }

    @Test
    public void testFourOfAKindNoPair() {
        Player player = new Player();
        player.addCard(new Card("A", "♦"));
        player.addCard(new Card("9", "♦"));
        
        // Community Cards
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("9", "♣"));
        communityCards.add(new Card("9", "♥"));
        communityCards.add(new Card("9", "♠"));     
        player.playHand(communityCards);
        String handResult = player.playHand(communityCards);
        
        assertEquals("Four of a Kind", handResult);
    }

    @Test
    public void testFullHouse() {
        Player player = new Player();
        player.addCard(new Card("9", "♠"));
        player.addCard(new Card("9", "♣"));
        
        // Community Cards
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("9", "♦"));
        communityCards.add(new Card("A", "♥"));
        communityCards.add(new Card("A", "♠"));
        
        player.playHand(communityCards);
        String handResult = player.playHand(communityCards);
        
        assertEquals("Full House", handResult);
    }

    @Test
    public void testFlush() {
        Player player = new Player();
        player.addCard(new Card("10", "♠"));
        player.addCard(new Card("J", "♠"));
        
        // Community Cards
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("3", "♠"));
        communityCards.add(new Card("7", "♠"));
        communityCards.add(new Card("A", "♠"));
        
        player.playHand(communityCards);
        String handResult = player.playHand(communityCards);
        
        assertEquals("Flush", handResult);
    }

    @Test
    public void testStraight() {
        Player player = new Player();
        player.addCard(new Card("5", "♠"));
        player.addCard(new Card("7", "♦"));
        
        // Community Cards
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("6", "♠"));
        communityCards.add(new Card("8", "♣"));
        communityCards.add(new Card("9", "♠"));
        
        player.playHand(communityCards);
        String handResult = player.playHand(communityCards);
        
        assertEquals("Straight", handResult);
    }

    @Test
    public void testStraightDifferentSuits() {
        Player player = new Player();
        player.addCard(new Card("10", "♠"));
        player.addCard(new Card("J", "♦"));

        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("9", "♣"));
        communityCards.add(new Card("Q", "♥"));
        communityCards.add(new Card("8", "♠"));
        
        String handResult = player.playHand(communityCards);
        
        assertEquals("Straight", handResult);
    }

    @Test
    public void testThreeOfAKindWithPair() {
        Player player = new Player();
        player.addCard(new Card("8", "♠"));
        player.addCard(new Card("8", "♣"));
        
        // Community Cards
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("8", "♦"));
        communityCards.add(new Card("5", "♠"));
        communityCards.add(new Card("A", "♣"));
        
        player.playHand(communityCards);
        String handResult = player.playHand(communityCards);
        
        assertEquals("Three of a Kind", handResult);
    }

    @Test
    public void testThreeOfAKindNoPair1() { //test in game 
        Player player = new Player();
        player.addCard(new Card("5", "♠"));
        player.addCard(new Card("8", "♦"));
    
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("A", "♣"));
        communityCards.add(new Card("8", "♠"));
        communityCards.add(new Card("8", "♣"));
        
        player.playHand(communityCards);
        String handResult = player.playHand(communityCards);
        
        assertEquals("Three of a Kind", handResult);
    }

    @Test
    public void testThreeOfAKindNoPair2() { //test in game 
        Player player = new Player();
        player.addCard(new Card("5", "♠"));
        player.addCard(new Card("A", "♣"));
    
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("8", "♦"));
        communityCards.add(new Card("8", "♠"));
        communityCards.add(new Card("8", "♣"));
        
        player.playHand(communityCards);
        String handResult = player.playHand(communityCards);
        
        assertEquals("Three of a Kind", handResult);
    }


    @Test
    public void testTwoPairNoPair() {
        Player player = new Player();
        player.addCard(new Card("7", "♠"));
        player.addCard(new Card("8", "♣"));
        
        // Community Cards
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("7", "♦"));
        communityCards.add(new Card("8", "♥"));
        communityCards.add(new Card("A", "♠"));
        
        player.playHand(communityCards);
        String handResult = player.playHand(communityCards);
        
        assertEquals("Two Pair", handResult);
    }

    @Test
    public void testTwoPairWithPair() {
        Player player = new Player();
        player.addCard(new Card("7", "♠"));
        player.addCard(new Card("7", "♦"));
      
        
        // Community Cards
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("8", "♣"));
        communityCards.add(new Card("8", "♥"));
        communityCards.add(new Card("A", "♠"));
        
        player.playHand(communityCards);
        String handResult = player.playHand(communityCards);
        
        assertEquals("Two Pair", handResult);
    }

    @Test
    public void testPair() {
        Player player = new Player();
        player.addCard(new Card("5", "♠"));
        player.addCard(new Card("5", "♦"));
        
        // Community Cards
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("3", "♣"));
        communityCards.add(new Card("7", "♥"));
        communityCards.add(new Card("A", "♠"));
        
        player.playHand(communityCards);
        String handResult = player.playHand(communityCards);
        
        assertEquals("A Pair", handResult);
    }

    @Test
    public void testHighCard() {
        Player player = new Player();
        player.addCard(new Card("A", "♠"));
        player.addCard(new Card("6", "♦"));
        
        // Community Cards
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("5", "♣"));
        communityCards.add(new Card("2", "♠"));
        communityCards.add(new Card("3", "♠"));
        
        player.playHand(communityCards);
        String handResult = player.playHand(communityCards);
        
        assertEquals("High Card", handResult);
    }

    @Test
    public void testNothing() {
        Player player = new Player();
        player.addCard(new Card("3", "♠"));
        player.addCard(new Card("6", "♦"));
        
        // Community Cards
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("5", "♣"));
        communityCards.add(new Card("2", "♠"));
        communityCards.add(new Card("A", "♠"));
        
        player.playHand(communityCards);
        String handResult = player.playHand(communityCards);
        
        assertEquals("Nothing", handResult);
    }


    @Test
    public void testToString() {
        Player player = new Player();
        player.addCard(new Card("J", "♠"));
        player.addCard(new Card("Q", "♦"));
        assertEquals("[J of ♠, Q of ♦]", player.toString());
    }
}
