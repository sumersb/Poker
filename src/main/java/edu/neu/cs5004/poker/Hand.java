package edu.neu.cs5004.poker;

import java.util.*;

public class Hand implements Comparable<Hand> {



    ArrayList<Card> cards;
    Hand.Type currType;
    Card.Value currCard;


    public Hand(ArrayList<Card> cards) {
        this.cards=new ArrayList<>();
        for (int i=0;i<cards.size();i++) {
            this.cards.add(cards.get(i));
        }
        this.cards.sort(Comparator.naturalOrder());
        currType=Type.SINGLE;
        currCard=this.cards.get(cards.size()-1).getValue();
        this.straightAndFlush();
        this.cardCount();
    }

    private void straightAndFlush() {
        boolean straight=true;
        boolean flush=true;
        for (int i=1;i<cards.size();i++) {
            if (cards.get(i-1).getSuit()!=cards.get(i).getSuit()) {
                flush=false;
            }
            if (flush) {
                currType=Type.FLUSH;
                currCard=cards.get(4).getValue();
            }
        }
        if (cards.get(3).getValue()== Card.Value.FIVE && cards.get(4).getValue() == Card.Value.ACE ) {
            for (int i=1;i<cards.size()-1;i++) {
                if (cards.get(i - 1).getValue().ordinal() != cards.get(i).getValue().ordinal() - 1) {
                    straight = false;
                }
            }
            if (straight) {
                currType=Type.STRAIGHT;
                currCard=cards.get(3).getValue();
            }
        } else {
            for (int i = 1; i < cards.size(); i++) {
                if (cards.get(i - 1).getValue().ordinal() != cards.get(i).getValue().ordinal() - 1) {
                    straight = false;
                }
            }
            if (straight) {
                currType=Type.STRAIGHT;
                currCard=cards.get(4).getValue();
            }
        }
        if (flush && straight) {
            if (cards.get(cards.size()-2).getValue()== Card.Value.KING) {
                currType = Type.ROYAL_FLUSH;
            } else {
                currType = Type.STRAIGHT_FLUSH;
            }
        }

    }

    private void cardCount() {
        HashMap<Card.Value,Integer> cardCount = new HashMap<>();
        for (int i=0;i<cards.size();i++) {
            Card card = cards.get(i);
            cardCount.put(card.getValue(),cardCount.getOrDefault(card.getValue(),0)+1);
            if (cardCount.get(card.getValue())==4) {
                currType=Type.FOUR_OF_A_KIND;
                currCard=card.getValue();
            } else if (cardCount.get(card.getValue())==3) {
                if (currType==Type.TWO_PAIR) {
                    currType=Type.FULL_HOUSE;
                } else {
                    currType=Type.THREE_OF_A_KIND;
                }
                currCard=card.getValue();
            } else if (cardCount.get(card.getValue())==2) {
                if (currType==Type.THREE_OF_A_KIND) {
                    currType=Type.FULL_HOUSE;
                } else if (currType==Type.PAIR) {
                    currType=Type.TWO_PAIR;
                    currCard=card.getValue();
                } else {
                    currType=Type.PAIR;
                    currCard=card.getValue();
                }
            }
        }
    }


    public enum Type {
        SINGLE,
        PAIR,
        TWO_PAIR,
        THREE_OF_A_KIND,
        STRAIGHT,
        FLUSH,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        STRAIGHT_FLUSH,
        ROYAL_FLUSH
    }

    @Override
    public int compareTo(Hand o) {
        Integer compare;
        compare=Integer.compare(currType.ordinal(),o.getCurrType().ordinal());
        if (compare!=0) {return compare;} else {
            compare= Integer.compare(currCard.ordinal(),o.getCurrCard().ordinal());
            if (compare!=0) {return compare;}
            if (currType==Type.TWO_PAIR) {
                compare=cards.get(1).compareTo(o.getCards().get(1));
                if (compare!=0) {return compare;}
            }
        }
        for (int i=cards.size()-1;i>=0;i--) {
            compare=cards.get(i).compareTo(o.getCards().get(i));
            if (compare!=0) {return compare;}
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hand hand)) return false;
        return cards.equals(hand.cards) && currType == hand.currType && currCard == hand.currCard;
    }

    public ArrayList<Card> getCards() {return cards;}

    public void setCards(ArrayList<Card> cards) {this.cards = cards;}

    public Type getCurrType() {return currType;}

    public void setCurrType(Type currType) {this.currType = currType;}

    public Card.Value getCurrCard() {return currCard;}

    public void setCurrCard(Card.Value currCard) {this.currCard = currCard;}
}
