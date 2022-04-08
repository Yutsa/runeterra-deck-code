package com.runeterrareporter.decks;

import com.runeterrareporter.cards.Card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestCardCopiesMarshaller {

  @Test
  void should_return_1_copy_of_card_01DE001_if_input_is_1_colon_01DE001() {
    // Given
    String input = "1:01DE001";
    List<CardCopies> expected = List.of(new CardCopies(1, Card.fromCode("01DE001")));
    // When
    List<CardCopies> result = CardCopiesMarshaller.unmarshall(input);
    // Then
    Assertions.assertThat(result).isEqualTo(expected);
  }

  @Test
  void should_return_1_copy_of_card_03FR002_if_input_is_1_colon_03FR002() {
    // Given
    String input = "1:03FR002";
    List<CardCopies> expected = List.of(new CardCopies(1, Card.fromCode("03FR002")));
    // When
    List<CardCopies> result = CardCopiesMarshaller.unmarshall(input);
    // Then
    Assertions.assertThat(result).isEqualTo(expected);
  }

  @Test
  void should_return_2_copies_of_card_03FR003_if_input_is_2_colon_03FR003() {
    // Given
    String input = "2:03FR003";
    List<CardCopies> expected = List.of(new CardCopies(2, Card.fromCode("03FR003")));
    // When
    List<CardCopies> result = CardCopiesMarshaller.unmarshall(input);
    // Then
    Assertions.assertThat(result).isEqualTo(expected);
  }

  @Test
  void should_return_3_copies_of_card_03FR004_if_input_is_3_colon_03FR004() {
    // Given
    String input = "3:03FR004";
    List<CardCopies> expected = List.of(new CardCopies(3, Card.fromCode("03FR004")));
    // When
    List<CardCopies> result = CardCopiesMarshaller.unmarshall(input);
    // Then
    Assertions.assertThat(result).isEqualTo(expected);
  }

  @Test
  void should_return_3_copies_of_card_03FR004_and_2_copies_of_04MT001_if_input_is_3_colon_03FR004_comma_2_colon_04MT001() {
    // Given
    String input = "3:03FR004,2:04MT001";
    List<CardCopies> expected = List.of(
      new CardCopies(3, Card.fromCode("03FR004")),
      new CardCopies(2, Card.fromCode("04MT001"))
    );
    // When
    List<CardCopies> result = CardCopiesMarshaller.unmarshall(input);
    // Then
    Assertions.assertThat(result).isEqualTo(expected);
  }

  @Test
  void should_return_3_copies_of_card_03FR004_and_2_copies_of_04MT001_and_1_copy_of_02SH001_if_input_is_3_colon_03FR004_comma_2_colon_04MT001_colon_1_comma_02SH001() {
    // Given
    String input = "3:03FR004,2:04MT001,1:02SH001";
    List<CardCopies> expected = List.of(
      new CardCopies(3, Card.fromCode("03FR004")),
      new CardCopies(2, Card.fromCode("04MT001")),
      new CardCopies(1, Card.fromCode("02SH001"))
    );
    // When
    List<CardCopies> result = CardCopiesMarshaller.unmarshall(input);
    // Then
    Assertions.assertThat(result).isEqualTo(expected);
  }

  @Test
  void should_return_1_colon_01DE001_if_input_is_1_copy_of_01DE001() {
    // Given
    String expected = "1:01DE001";
    List<CardCopies> input = List.of(new CardCopies(1, Card.fromCode("01DE001")));
    // When
    String result = CardCopiesMarshaller.marshall(input);
    // Then
    Assertions.assertThat(result).isEqualTo(expected);
  }

  @Test
  void should_return_2_colon_02DE001_if_input_is_2_copies_of_02DE001() {
    // Given
    String expected = "2:02DE001";
    List<CardCopies> input = List.of(new CardCopies(2, Card.fromCode("02DE001")));
    // When
    String result = CardCopiesMarshaller.marshall(input);
    // Then
    Assertions.assertThat(result).isEqualTo(expected);
  }

  @Test
  void should_return_2_colon_02DE001_comma_3_colon_04IO001_if_input_is_2_copies_of_02DE001_and_3_copies_of_04IO001() {
    // Given
    String expected = "2:02DE001,3:04IO001";
    List<CardCopies> input = List.of(
      new CardCopies(2, Card.fromCode("02DE001")),
      new CardCopies(3, Card.fromCode("04IO001"))
    );
    // When
    String result = CardCopiesMarshaller.marshall(input);
    // Then
    Assertions.assertThat(result).isEqualTo(expected);
  }

    @Test
  void should_return_2_colon_02DE001_comma_3_colon_04IO001_comma_1_colon_01MT001_if_input_is_2_copies_of_02DE001_and_3_copies_of_04IO001_and_1_copy_of_01MT001() {
    // Given
    String expected = "2:02DE001,3:04IO001,1:01MT001";
    List<CardCopies> input = List.of(
      new CardCopies(2, Card.fromCode("02DE001")),
      new CardCopies(3, Card.fromCode("04IO001")),
      new CardCopies(1, Card.fromCode("01MT001"))
    );
    // When
    String result = CardCopiesMarshaller.marshall(input);
    // Then
    Assertions.assertThat(result).isEqualTo(expected);
  }
}
