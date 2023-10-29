package edu.neu.cs5004.poker;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private ArrayList<Integer> numbers;
    private Random rand = new Random();

    public Deck() {
        this.numbers=new ArrayList<>();
        this.resetDeck();
    }
    public Hand[] dealHands(int numHands) throws IllegalArgumentException {
        if (numHands>10 || numHands<1) {
            throw new IllegalArgumentException();
        }
        Hand hands[] = new Hand[numHands];

        for (int i=0;i<numHands;i++) {
            ArrayList<Card> cards = new ArrayList<>();
            for (int j=0;j<5;j++) {
                int cardNum = rand.nextInt(numbers.size());
                cards.add(new Card(cardNum));
                numbers.remove(cardNum);
            }
            hands[i]=new Hand(cards);
        }
        this.resetDeck();
        return hands;
    }

    private void resetDeck() {
        numbers.clear();
        for (int i=0;i<52;i++) {
        numbers.add(Integer.valueOf(i));
        }
    }
}
