package edu.neu.cs5004.poker;

public class Poker {

    /**
     * This function returns the highest hand in the given array.
     * @return The highest hand
     */

    public Poker() {}
    public Hand highestHand(Hand... hands) {
        Hand highHand=hands[0];
        for (int i=1;i<hands.length;i++) {
            if (hands[i].compareTo(highHand)>0) {
                highHand=hands[i];
            }
        }
        return highHand;
    }
}
