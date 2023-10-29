package edu.neu.cs5004.poker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    Card card;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getError() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class,()->new Card(-1));
        assertThrows(IllegalArgumentException.class,()->new Card(52));
    }

    @Test
    void getSuit() {
        card = new Card(0);
        assertEquals(Card.Suit.CLUB,card.getSuit());
        card = new Card(12);
        assertEquals(Card.Suit.CLUB,card.getSuit());

        card = new Card(13);
        assertEquals(Card.Suit.DIAMOND,card.getSuit());
        card = new Card(25);
        assertEquals(Card.Suit.DIAMOND,card.getSuit());

        card = new Card(26);
        assertEquals(Card.Suit.HEART,card.getSuit());
        card = new Card(38);
        assertEquals(Card.Suit.HEART,card.getSuit());

        card = new Card(39);
        assertEquals(Card.Suit.SPADE,card.getSuit());
        card = new Card(51);
        assertEquals(Card.Suit.SPADE,card.getSuit());
    }

    @Test
    void getValue() {
        card = new Card(0);
        assertEquals(Card.Value.TWO,card.getValue());
        card = new Card(12);
        assertEquals(Card.Value.ACE,card.getValue());

        card = new Card(13);
        assertEquals(Card.Value.TWO,card.getValue());
        card = new Card(25);
        assertEquals(Card.Value.ACE,card.getValue());

        card = new Card(26);
        assertEquals(Card.Value.TWO,card.getValue());
        card = new Card(38);
        assertEquals(Card.Value.ACE,card.getValue());

        card = new Card(39);
        assertEquals(Card.Value.TWO,card.getValue());
        card = new Card(51);
        assertEquals(Card.Value.ACE,card.getValue());
    }

    @Test
    void testEquals() {
        Card card1=new Card(0);
        Card card2=new Card(0);
        for (int i=1;i<52;i++) {
            card1 = new Card(i);
            assertFalse(card1.equals(card2));
            card2 = new Card(i);
            assertTrue(card1.equals(card2));
        }
    }

    @Test
    void compareTo() {
        Card card1=new Card(0);
        Card card2=new Card(0);
        for (int i=1;i<52;i++) {
            card1 = new Card(i);
            assertEquals(i%13>(i-1)%13,card1.compareTo(card2)>0);
            card2 = new Card(i);
            assertEquals(0,card1.compareTo(card2));
        }
    }
}