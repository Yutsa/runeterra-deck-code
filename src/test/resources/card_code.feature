Feature: Computing the card code of a card

  Scenario Outline: The code of the release set is in the code of the card
    Given a card
    And the card being from the "<releaseSet>" release set
    When the code of the card is computed
    Then the first two characters from the code should be "<releaseSetCode>"
    Examples:
      | releaseSet             | releaseSetCode |
      | Foundation             | 01             |
      | Rising Tides           | 02             |
      | Call of the Mountain   | 03             |
      | Empire of the Ascended | 04             |

  Scenario Outline: The code of the region is in the code of the card
    Given a card
    And the card being from the "<region>" region
    When the code of the card is computed
    Then the third and fourth characters from the code should be "<regionCode>"
    Examples:
      | region          | regionCode |
      | Demacia         | DE         |
      | Freljord        | FR         |
      | Ionia           | IO         |
      | Noxus           | NX         |
      | Piltover & Zaun | PZ         |
      | Shadow Isles    | SI         |
      | Bilgewater      | BW         |
      | Mount Targon    | MT         |
      | Shurima         | SH         |


  Scenario Outline: The number of the card is in the code
    Given a card
    And the card having the number "<cardNumber>"
    When the code of the card is computed
    Then the fifth to last character from the code should be the "<cardNumber>"
    Examples:
      | cardNumber |
      | 123        |
      | 456        |
      | 111        |
      | 001        |


  Scenario Outline: Testing the code of the card
    Given a card
    And the card being from the "<releaseSet>" release set
    And the card being from the "<region>" region
    And the card having the number "<cardNumber>"
    When the code of the card is computed
    Then the code should be "<cardcode>"
    Examples:
      | releaseSet             | region          | cardNumber | cardcode |
      | Foundation             | Demacia         | 123        | 01DE123  |
      | Rising Tides           | Freljord        | 001        | 02FR001  |
      | Call of the Mountain   | Ionia           | 002        | 03IO002  |
      | Empire of the Ascended | Noxus           | 999        | 04NX999  |
      | Foundation             | Demacia         | 756        | 01DE756  |
      | Rising Tides           | Piltover & Zaun | 45         | 02PZ045  |
      | Call of the Mountain   | Shadow Isles    | 1          | 03SI001  |
      | Empire of the Ascended | Bilgewater      | 85         | 04BW085  |
      | Foundation             | Mount Targon    | 54         | 01MT054  |
      | Rising Tides           | Shurima         | 7          | 02SH007  |

  Scenario Outline: Creating a card from its code
    Given the card code "<cardCode>"
    When a card is created from the card code
    Then the created card should be of release se "<releaseSet>", region "<region>" with the number "<cardNumber>"
    Examples:
      | releaseSet             | region          | cardNumber | cardCode |
      | Foundation             | Demacia         | 123        | 01DE123  |
      | Rising Tides           | Freljord        | 001        | 02FR001  |
      | Call of the Mountain   | Ionia           | 002        | 03IO002  |
      | Empire of the Ascended | Noxus           | 999        | 04NX999  |
      | Foundation             | Demacia         | 756        | 01DE756  |
      | Rising Tides           | Piltover & Zaun | 45         | 02PZ045  |
      | Call of the Mountain   | Shadow Isles    | 1          | 03SI001  |
      | Empire of the Ascended | Bilgewater      | 85         | 04BW085  |
      | Foundation             | Mount Targon    | 54         | 01MT054  |
      | Rising Tides           | Shurima         | 7          | 02SH007  |