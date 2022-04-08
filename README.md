# runeterra-deck-code

This project is a Java implementation to encode and decode Legends of Runeterra deck codes.

It is based on the [reference implementation](https://github.com/RiotGames/LoRDeckCodes) from Riot Games.

## Creating a Deck instance

To build an instance of Deck to encode, a builder is available. It has a pretty simple API with two ways to add cards to a deck : 

The first way is to add a card from its code :

```java
var deck = Deck.builder()
               .addCard("01DE001", 3)
               .addCard("02MT023", 1)
               .build();
```

The other way is to add a card directly from an instance of Card : 

```java
Card card = Card.fromCode("01DE001");
var deck2 = Deck.builder()
                .addCard(card, 3)
                .build();
```
