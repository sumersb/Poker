package edu.neu.cs5004.poker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    Deck deck;
    @BeforeEach
    void setUp() {
        deck = new Deck();
    }

    @Test
    void dealHands() throws IllegalArgumentException{
        for (int i=1;i<11;i++) {
            assertEquals(i,deck.dealHands(i).length);
        }
        assertThrows(IllegalArgumentException.class,()->deck.dealHands(11));
        assertThrows(IllegalArgumentException.class,()->deck.dealHands(0));
    }

    @Test
    void resetDeck() {
    }
}