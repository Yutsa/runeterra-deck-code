Feature: VarInt encoder/decoder

  Scenario Outline: The VarInt translators transforms integers to variable lenght integer (Big Endian)
    Given the integer <integer>
    When the varint for this integer is computed
    Then the result is "<listOfBits>"
    Examples:
      | integer | listOfBits |
      | 1       | 1          |
      | 127     | 127        |
      | 128     | 1281       |
      | 129     | 1291       |
      | 130     | 1301       |
      | 254     | 2541       |
      | 255     | 2551       |
      | 256     | 1282       |
      | 257     | 1292       |
      | 383     | 2552       |
      | 384     | 1283       |
      | 511     | 2553       |
      | 512     | 1284       |
      | 654     | 1425       |
      | 785     | 1456       |
      | 999     | 2317       |

  Scenario Outline: The VarInt translators can get the next encoded value from the VarInt with one integer in it
    Given the integer <integer>
    When the integer is added to the varint
    And the next value is popped from the varint
    Then the result is <integer>
    Examples:
      | integer |
      | 1       |
      | 127     |
      | 128     |
      | 129     |
      | 130     |
      | 254     |
      | 255     |
      | 256     |
      | 257     |
      | 383     |
      | 384     |
      | 511     |
      | 512     |
      | 654     |
      | 785     |
      | 999     |

  Scenario Outline: The VarInt translators can get the next two encoded values in a VarInt with 2 values in it
    Given the integers <integer> and <integer2>
    When the two integers are added to the varint
    And the two values are popped
    Then the popped integer are the one that were encoded
    Examples:
      | integer | integer2 |
      | 1       | 2        |
      | 127     | 3        |
      | 128     | 4        |
      | 129     | 254      |
      | 130     | 211      |
      | 254     | 122      |
      | 255     | 25       |
      | 256     | 29       |
      | 257     | 122      |
      | 383     | 296      |
      | 384     | 95       |
      | 511     | 1        |
      | 512     | 9865     |
      | 654     | 1354     |
      | 785     | 2974     |
      | 999     | 15683    |