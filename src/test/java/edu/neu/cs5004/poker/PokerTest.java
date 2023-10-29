package edu.neu.cs5004.poker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PokerTest {
    Poker poker;
    ArrayList<Card> cards;
    @BeforeEach
    void setUp() {
        poker = new Poker();
        cards = new ArrayList<>();
    }

    @Test
    void highestHand() {
        cards=new ArrayList<>();
        for (int i=0;i<5;i++) {
            cards.add(new Card(i));
        }
        Hand hand=new Hand(cards);
        Hand hand2=new Hand(cards);
        Hand hand3=new Hand(cards);
        hand.setCurrType(Hand.Type.STRAIGHT);
        hand2.setCurrType(Hand.Type.TWO_PAIR);
        hand3.setCurrType(Hand.Type.ROYAL_FLUSH);
        Hand hands[]={hand,hand2,hand3};
        assertEquals(hand3,poker.highestHand(hands));
    }
}