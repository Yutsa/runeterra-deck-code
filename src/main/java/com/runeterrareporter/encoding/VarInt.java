package com.runeterrareporter.encoding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class VarInt {

    private static final int ALL_BUT_MSB = 0x7f;
    private static final int JUST_MSB = 0x80;

    private final List<Integer> internalVarInt;

    public VarInt() {
        internalVarInt = new ArrayList<>();
    }

    public VarInt(List<Integer> varint) {
        this.internalVarInt = varint;
    }

    public void add(int number) {
        List<Integer> result = new ArrayList<>();
        if (number < 128) {
            result.add(number);
        } else {
            int count = 1;
            for (; number > 255; number -= 128) {
                count++;
            }
            result.addAll(List.of(number, count));
        }

        internalVarInt.addAll(result);
    }

    public int pop() {
        int result = 0;
        int shift = 0;
        int popped = 0;

        for (int i = 0; i < internalVarInt.size(); i++) {
            popped++;
            int current = internalVarInt.get(i) & ALL_BUT_MSB;
            result |= current << shift;

            if ((internalVarInt.get(i) & JUST_MSB) != JUST_MSB) {
                internalVarInt.subList(0, popped).clear();
                return result;
            }

            shift += 7;
        }

        throw new IllegalArgumentException("Byte array did not contain valid VarInts");
    }

    public List<Integer> getValues() {
        return internalVarInt;
    }

    @Override
    public String toString() {
        return internalVarInt.stream()
                             .map(Objects::toString)
                             .collect(Collectors.joining(","));
    }
}
