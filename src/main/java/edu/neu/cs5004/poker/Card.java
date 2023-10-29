package edu.neu.cs5004.poker;

import java.util.ArrayList;
import java.util.Objects;

public class Card implements Comparable<Card> {

    Suit suit;
    Value value;

    public enum Suit {
        CLUB,
        DIAMOND,
        HEART,
        SPADE
    }

    public enum Value {
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING,
        ACE
    }
    public Card(int num) throws IllegalArgumentException {
        if (num<0 || num>=52) {
            throw new IllegalArgumentException();
        }
        this.suit=Suit.values()[num/13];
        this.value=Value.values()[num%13];
    }

    public Suit getSuit() {return suit;}
    public Value getValue() {return value;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card card)) return false;
        return suit == card.suit && value == card.value;
    }

    @Override
    public int compareTo(Card card) {
        return Integer.compare(this.value.ordinal(),card.getValue().ordinal());
    }

}
