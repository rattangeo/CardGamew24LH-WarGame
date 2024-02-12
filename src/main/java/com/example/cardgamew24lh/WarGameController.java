package com.example.cardgamew24lh;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WarGameController implements Initializable {

    @FXML
    private Label cardsInPileLabel;

    @FXML
    private Button playButton;

    @FXML
    private Label player1CardCountLabel;

    @FXML
    private ImageView player1ImageView;

    @FXML
    private Label player2CardCountLabel;

    @FXML
    private ImageView player2ImageView;

    private ArrayList<Card> player1Hand, player2Hand, centerPileOfCards;

    @FXML
    void playHand() {
        player1ImageView.setVisible(true);
        player2ImageView.setVisible(true);
        cardsInPileLabel.setVisible(true);

        if (player1Hand.size() == 0)
            declareWinner("player 2");
        else if (player2Hand.size()==0)
            declareWinner("player 1");
        else {
            //p1 removes top cards and adds to the pile
            Card p1Card = player1Hand.remove(0);
            player1ImageView.setImage(p1Card.getImage());
            centerPileOfCards.add(p1Card);

            //p2 removes top card and adds to the pile
            Card p2Card = player2Hand.remove(0);
            player2ImageView.setImage(p2Card.getImage());
            centerPileOfCards.add(p2Card);
            updateLabels();
        }


    }


    private void warHand(){
        if (player1Hand.size()<4)
            declareWinner("player 2");
        else if (player2Hand.size()<4)
            declareWinner("player 1");
        else {
            centerPileOfCards.add(player1Hand.remove(0));
            centerPileOfCards.add(player1Hand.remove(0));
            centerPileOfCards.add(player1Hand.remove(0));
            centerPileOfCards.add(player2Hand.remove(0));
            centerPileOfCards.add(player2Hand.remove(0));
            centerPileOfCards.add(player2Hand.remove(0));
        }
    }


    private void declareWinner(String winnerName){
        cardsInPileLabel.setText(winnerName + " Wins!!");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        player1ImageView.setVisible(false);
        player2ImageView.setVisible(false);
        cardsInPileLabel.setVisible(false);

        //initialize the ArrayList of Cards for Player 1 and 2
        player1Hand = new ArrayList<>();
        player2Hand = new ArrayList<>();
        centerPileOfCards = new ArrayList<>();

        //create deck of cards and shuffle the deck

        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();

        //deal all the cards
        while (deck.getNumOfCards()>=2){
            player1Hand.add(deck.dealTopCard());
            player2Hand.add(deck.dealTopCard());
        }



    }

    /**
     * This method will show how many cards are in each player's hands as well as the center pile
     */
    private void updateLabels(){
        player1CardCountLabel.setText(player1Hand.size() + " Cards");
        player2CardCountLabel.setText(player2Hand.size() + " Cards");
        cardsInPileLabel.setText(centerPileOfCards.size()+ " Cards");

    }
}
