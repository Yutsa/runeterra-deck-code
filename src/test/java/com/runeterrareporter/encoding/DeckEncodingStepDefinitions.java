package com.runeterrareporter.encoding;

import com.runeterrareporter.cards.Card;
import com.runeterrareporter.decks.CardCopies;
import com.runeterrareporter.decks.CardCopiesMarshaller;
import com.runeterrareporter.decks.Deck;

import org.assertj.core.api.Assertions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckEncodingStepDefinitions {

    private Deck deck;
    private SortedDeck sortedDeck;
    private VarInt varInt;
    private String deckCode;

    @Given("a deck")
    public void aDeck() {
        deck = new Deck();
    }

    @And("the deck contains the following {string}")
    public void theDeckContainsTheFollowing(String cards) {
        List<CardCopies> cardCopies = CardCopiesMarshaller.unmarshall(cards);
        deck.addCard(cardCopies);
    }

    @Then("the cards in the {int}ofs should be {string}")
    public void theCardsInTheOfsShouldBe(int copies, String cardCopiesList) {
        if (copies == 1) {
            assertThat(cardListToString(deck.oneOfs())).isEqualTo(cardCopiesList);
        } else if (copies == 2) {
            assertThat(cardListToString(deck.twoOfs())).isEqualTo(cardCopiesList);
        } else if (copies == 3) {
            assertThat(cardListToString(deck.threeOfs())).isEqualTo(cardCopiesList);
        }
    }

    @When("the deck is sorted")
    public void theDeckIsSorted() {
        sortedDeck = new DeckSorter().sort(deck);
    }

    @Then("the sorted deck should be {string}")
    public void theSortedDeckShouldBe(String sortedDeck) {
        Assertions.assertThat(this.sortedDeck.toString()).hasToString(sortedDeck);
    }

    @When("the deck is encoded to a varint")
    public void the_deck_is_encoded_to_a_varint() {
        varInt = new DeckVarintEncoder(new DeckSorter()).encode(deck);
    }

    @Then("the varint should contain the following {string}")
    public void the_varint_should_contain_the_following(String expectedVarint) {
        assertThat(varInt.toString()).isEqualTo(expectedVarint);
    }

    @When("the deck code is computed")
    public void theDeckCodeIsComputed() {
        deckCode = new DeckEncoder(new DeckVarintEncoder(new DeckSorter())).encode(deck);
    }

    @Then("the deck code is {string}")
    public void theDeckCodeIs(String expectedDeckCode) {
        assertThat(deckCode).isEqualTo(expectedDeckCode);
    }

    @Given("a deck code {string}")
    public void a_deck_code(String deckCode) {
        this.deckCode = deckCode;
    }
    
    @When("the code is decoded")
    public void the_code_is_decoded() {
        deck = new DeckEncoder(new DeckVarintEncoder(new DeckSorter())).decode(deckCode);
    }
    
    @Then("the resulting deck should contain the following {string}")
    public void the_resulting_deck_should_contain_the_following(String cards) {
        Arrays.stream(cards.split(","))
                .forEach(cardCode -> checkIfCardIsInDeck(cardCode, deck));
    }

    @Then("the deck code should be {string}")
    public void the_deck_code_should_be(String expectedDeckCode) {
        assertThat(deckCode).isEqualTo(expectedDeckCode);
    }

    private void checkIfCardIsInDeck(String cardCode, Deck deck) {
        CardCopies cardCopy = CardCopies.fromString(cardCode);
        assertThat(deck.getCards()).contains(cardCopy);
    }

    private String cardListToString(List<Card> cards) {
        return cards.stream()
                .map(Card::getCode)
                .collect(Collectors.joining(","));
    }
}
