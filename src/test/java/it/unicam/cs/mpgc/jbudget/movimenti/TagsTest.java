package it.unicam.cs.mpgc.jbudget.movimenti;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagsTest {

    @Test
    void testCompareToWithGreaterPesoTag() {
        Tags tag1 = new Tags("Tag1", 10);
        Tags tag2 = new Tags("Tag2", 5);
        assertTrue(tag1.compareTo(tag2) > 0, "Tag1 should be greater than Tag2 based on pesoTag");
    }

    @Test
    void testCompareToWithEqualPesoTag() {
        Tags tag1 = new Tags("Tag1", 10);
        Tags tag2 = new Tags("Tag2", 10);
        assertEquals(0, tag1.compareTo(tag2), "Tags with the same pesoTag should be considered equal");
    }

    @Test
    void testCompareToWithLowerPesoTag() {
        Tags tag1 = new Tags("Tag1", 5);
        Tags tag2 = new Tags("Tag2", 10);
        assertTrue(tag1.compareTo(tag2) < 0, "Tag1 should be less than Tag2 based on pesoTag");
    }

    @Test
    void testCompareToWithNull() {
        Tags tag = new Tags("Tag", 5);
        assertThrows(NullPointerException.class, () -> tag.compareTo(null), "Comparing to null should throw NullPointerException");
    }
}