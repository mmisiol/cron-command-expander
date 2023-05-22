package org.mmi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PartExpanderTest {

    @Test
    void expand() {
        PartExpander partExpander = new PartExpander();
        assertEquals("0", partExpander.expandPart("0", 0, 59));
        assertEquals("10 13 16 19", partExpander.expandPart("10-20/3", 0, 59));
        assertEquals("55 56 57 58 59", partExpander.expandPart("55-90", 0, 59));
        assertEquals("0 10 13 16 19 55 56 57 58 59", partExpander.expandPart("0,10-20/3,55-90", 0, 59));
        assertEquals("2 3 4 5 6", partExpander.expandPart("*", 2, 6));
        assertEquals("1 18 35", partExpander.expandPart("*/17", 1, 43));
        assertEquals("1 2 3 4 5 6 7", partExpander.expandPart("0-8", 1, 7));
        assertEquals("1 3 5 7", partExpander.expandPart("0-8/2", 1, 7));
    }
}