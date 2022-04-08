package com.runeterrareporter.cards;

import org.assertj.core.api.SoftAssertions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class CardStepDefinitions {

  private String code;
  private CardBuilder cardBuilder;
  private Card card;

  @When("the code of the card is computed")
  public void i_compute_the_code_from_the_card() {
    code = cardBuilder.build().getCode();
  }

  @Then("the third and fourth characters from the code should be {string}")
  public void the_third_and_fourth_caracters_from_the_code_should_be(String regionCode) {
    assertThat(code.substring(2, 4)).isEqualTo(regionCode);
  }

  @Then("the first two characters from the code should be {string}")
  public void theFirstTwoCharactersFromTheCodeShouldBe(String releaseSetCode) {
    assertThat(code).startsWith(releaseSetCode);
  }

  @Then("the fifth to last character from the code should be the {string}")
  public void theFifthToLastCharacterFromTheCodeShouldBeThe(String cardNumber) {
    assertThat(code.substring(4)).isEqualTo(cardNumber);
  }

  @Given("a card")
  public void aCard() {
    cardBuilder = CardBuilder.createCard();
  }

  @And("the card being from the {string} release set")
  public void theCardBeingFromTheReleaseSet(String releaseSet) {
    cardBuilder.withReleaseSet(ReleaseSetParser.parse(releaseSet));
  }

  @And("the card being from the {string} region")
  public void theCardBeingFromTheRegion(String region) {
    cardBuilder.withRegion(RegionParser.parse(region));
  }

  @And("the card having the number {string}")
  public void theCardHavingTheNumber(String cardNumber) {
    cardBuilder.withCardNumber(Integer.parseInt(cardNumber));
  }

  @Then("the code should be {string}")
  public void theCodeShouldBe(String cardCode) {
    assertThat(code).isEqualTo(cardCode);
  }

  @Given("the card code {string}")
  public void theCardCode(String cardCode) {
    code = cardCode;
  }

  @When("a card is created from the card code")
  public void aCardIsCreatedFromTheCardCode() {
    card = Card.fromCode(code);
  }

  @Then("the created card should be of release se {string}, region {string} with the number {string}")
  public void theCreatedCardShouldBeOfReleaseSeRegionWithTheNumber(String releaseSet, String region, String cardNumber) {
    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(card.getRegion()).isEqualTo(RegionParser.parse(region));
      softly.assertThat(card.getReleaseSet()).isEqualTo(ReleaseSetParser.parse(releaseSet));
      softly.assertThat(card.getCardNumber()).isEqualTo(Integer.parseInt(cardNumber));
    });
  }
}
