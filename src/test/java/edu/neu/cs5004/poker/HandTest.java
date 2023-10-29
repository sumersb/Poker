package edu.neu.cs5004.poker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {
    Hand hand;
    Card card;
    ArrayList<Card> cards = new ArrayList<>();

    @BeforeEach
    void setUp() {
    }

    @Test
    void flushAndStraight() {
        for (int i=0;i<5;i++) {
            card = new Card(i);
            cards.add(card);
        }
        // Current hand 2,3,4,5,6 all same suit
        hand = new Hand(cards);
        assertEquals(Hand.Type.STRAIGHT_FLUSH,hand.getCurrType());
        assertEquals(Card.Value.SIX,hand.getCurrCard());
        cards.set(4,new Card(12));
        // Current hand A,2,3,4,5 all same suit
        hand = new Hand(cards);
        assertEquals(Hand.Type.STRAIGHT_FLUSH,hand.getCurrType());
        assertEquals(Card.Value.FIVE,hand.getCurrCard());

        cards.set(0,new Card(14));
        hand = new Hand(cards);
        assertEquals(Hand.Type.PAIR,hand.getCurrType());
        assertEquals(Card.Value.THREE,hand.getCurrCard());

        //Current hand 3,4,4,5,A

        cards.clear();
        for (int i=8;i<13;i++) {
            Card card = new Card(i);
            cards.add(card);
        }
        // Current hand 10,J,Q,K,A all same suit
        hand = new Hand(cards);
        assertEquals(Hand.Type.ROYAL_FLUSH,hand.getCurrType());
        assertEquals(Card.Value.ACE,hand.getCurrCard());

        cards.set(4,new Card(20));

        // Current hand 9,10,J,Q,K         9 is different suit
        hand = new Hand(cards);
        assertEquals(Hand.Type.STRAIGHT,hand.getCurrType());
        assertEquals(Card.Value.KING,hand.getCurrCard());

        cards.set(4,new Card(19));
        // Current hand 8,10,J,Q,K      K high
        hand = new Hand(cards);
        assertEquals(Hand.Type.SINGLE,hand.getCurrType());
        assertEquals(Card.Value.KING,hand.getCurrCard());

    }

    @Test
    void multipleCards() {
        cards.add(new Card(5));
        for (int i=0;i<4;i++) {
            cards.add(new Card(6+13*i));
        }
        //current hand 7,8,8,8,8
        hand = new Hand(cards);
        assertEquals(Hand.Type.FOUR_OF_A_KIND,hand.getCurrType());
        assertEquals(Card.Value.EIGHT,hand.getCurrCard());

        cards.set(1,new Card(5+13));
        //current hand 7,7,8,8,8
        hand = new Hand(cards);
        assertEquals(Hand.Type.FULL_HOUSE,hand.getCurrType());
        assertEquals(Card.Value.EIGHT,hand.getCurrCard());

        cards.set(2,new Card(5+13+13));
        //current hand 7,7,7,8,8
        hand = new Hand(cards);
        assertEquals(Hand.Type.FULL_HOUSE,hand.getCurrType());
        assertEquals(Card.Value.SEVEN,hand.getCurrCard());

        cards.set(4,new Card(12));
        //current hand 7,7,7,8,A
        hand = new Hand(cards);
        assertEquals(Hand.Type.THREE_OF_A_KIND,hand.getCurrType());
        assertEquals(Card.Value.SEVEN,hand.getCurrCard());

        cards.set(2,new Card(25));
        //current hand 7,7,8,A,A
        hand = new Hand(cards);
        assertEquals(Hand.Type.TWO_PAIR,hand.getCurrType());
        assertEquals(Card.Value.ACE,hand.getCurrCard());

        cards.set(0,new Card(0));
        // current hand 2,7,8,A,A
        hand = new Hand(cards);
        assertEquals(Hand.Type.PAIR,hand.getCurrType());
        assertEquals(Card.Value.ACE,hand.getCurrCard());
    }

    @Test
    void compareTo() {
        for (int i=0;i<5;i++) {
            cards.add(new Card(i));
        }
        hand = new Hand(cards);
        Hand hand2= new Hand(cards);
        //Checks case where 1 hand type value is higher then another
        for (int i=Hand.Type.values().length-1;i>=0;i--) {
            hand.setCurrType(Hand.Type.values()[i]);
            for (int j=i-1;j>=0;j--) {
                hand2.setCurrType(Hand.Type.values()[j]);
                assertTrue(hand.compareTo(hand2)>0);
            }
        }

        for (int i=Hand.Type.values().length-1;i>=0;i--) {
            hand.setCurrType(Hand.Type.values()[i]);
            hand2.setCurrType(Hand.Type.values()[i]);
            for (int j=Card.Value.values().length-1;j>0;j--) {
                hand.setCurrCard(Card.Value.values()[j]);
                for (int k=j-1;k>=0;k--) {
                    hand2.setCurrCard(Card.Value.values()[k]);
                    assertTrue(hand.compareTo(hand2)>0);
                }
            }
        }
        cards.clear();
        //Special cases TWO PAIR case
        cards.add(new Card(12));
        cards.add(new Card(25));
        cards.add(new Card(7));
        cards.add(new Card(20));
        cards.add(new Card(5));

        //Current hand A,A,9,9,7
        hand = new Hand(cards);
        assertEquals(Hand.Type.TWO_PAIR,hand.getCurrType());
        assertEquals(Card.Value.ACE,hand.getCurrCard());
        cards.set(3,new Card(18));
        cards.set(2,new Card(11));
        //Current hand2 A,A,K,7,7
        hand2 = new Hand(cards);
        assertEquals(Hand.Type.TWO_PAIR,hand.getCurrType());
        assertEquals(Card.Value.ACE,hand.getCurrCard());
        assertTrue(hand.compareTo(hand2)>0);

        cards.clear();

        for (int i=0;i<5;i++) {
            cards.add(new Card(2*i));
        }
        //Current hand 2,4,6,8,10
        hand=new Hand(cards);
        cards.set(0,new Card(1));
        hand2=new Hand(cards);
        assertTrue(hand.compareTo(hand2)<0);

        hand2=hand;
        assertTrue(hand.compareTo(hand2)==0);


    }

    @Test
    void Equals() {
        for (int i=0;i<5;i++) {
            cards.add(new Card(i));
        }
        Hand hand1 = new Hand(cards);
        Hand hand2 = new Hand(cards);
         for (int i=5;i<52;i++) {
             cards.add(new Card(i));
             cards.remove(0);
             hand1 = new Hand(cards);
             assertFalse(hand1.equals(hand2));
             hand2 = new Hand(cards);
             assertTrue(hand1.equals(hand2));
         }
    }

    @Test
    void getCards() {
        for (int i=0;i<5;i++) {
            cards.add(new Card(i));
        }
        hand = new Hand(cards);
        for (int i=0;i<52;i++) {
            cards.add(new Card(i));
            cards.remove(0);
            hand.setCards(cards);
            assertEquals(cards,hand.getCards());
        }
    }

    @Test
    void setCards() {
        for (int i=0;i<5;i++) {
            cards.add(new Card(i));
        }
        hand = new Hand(cards);
        for (int i=0;i<52;i++) {
            cards.add(new Card(i));
            cards.remove(0);
            hand.setCards(cards);
            assertEquals(cards,hand.getCards());
        }
    }

    @Test
    void getCurrType() {
        for (int i=0;i<5;i++) {
            cards.add(new Card(i));
        }
        hand = new Hand(cards);
        for (int i=0;i<Hand.Type.values().length;i++) {
            hand.setCurrType(Hand.Type.values()[i]);
            assertEquals(Hand.Type.values()[i],hand.getCurrType());
        }
    }

    @Test
    void setCurrType() {
        for (int i=0;i<5;i++) {
            cards.add(new Card(i));
        }
        hand = new Hand(cards);
        for (int i=0;i<Hand.Type.values().length;i++) {
            hand.setCurrType(Hand.Type.values()[i]);
            assertEquals(Hand.Type.values()[i],hand.getCurrType());
        }
    }

    @Test
    void getCurrCard() {
        for (int i=0;i<5;i++) {
            cards.add(new Card(i));
        }
        hand = new Hand(cards);
        for (int i=0;i<Hand.Type.values().length;i++) {
            hand.setCurrCard(Card.Value.values()[i]);
            assertEquals(Card.Value.values()[i],hand.getCurrCard());
        }
    }

    @Test
    void setCurrCard() {
        for (int i=0;i<5;i++) {
            cards.add(new Card(i));
        }
        hand = new Hand(cards);
        for (int i=0;i<Hand.Type.values().length;i++) {
            hand.setCurrCard(Card.Value.values()[i]);
            assertEquals(Card.Value.values()[i],hand.getCurrCard());
        }
    }

}