package it.unicam.cs.mpgc.jbudget.movimenti;

import it.unicam.cs.mpgc.jbudget.valori.Importo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovimentoTest {

    /**
     * Tests for the removeTag method in the Movimento class.
     * The removeTag method is responsible for removing a specified tag
     * from the tagList of a Movimento object. If the tag being removed is
     * the most important tag, the tagPiuImportante index is recalculated.
     */

    @Test
    void rimuoviTag_WhenTagExists_AndIsNotMostImportant() {
        Movimento movimento = new Movimento(new Importo(1000)) {
        };
        Tags tag1 = new Tags("Spesa", 5);
        Tags tag2 = new Tags("Lavoro", 10);

        movimento.aggiungiTag(tag1);
        movimento.aggiungiTag(tag2);

        movimento.rimuoviTag(tag1);

        assertFalse(movimento.getTagList().contains(tag1));
        assertTrue(movimento.getTagList().contains(tag2));
    }

    @Test
    void rimuoviTag_WhenTagExists_AndIsMostImportant_RecalculatesMostImportantTag() {
        Movimento movimento = new Movimento(new Importo(1000)) {
        };
        Tags tag1 = new Tags("Spesa", 5);
        Tags tag2 = new Tags("Lavoro", 10);

        movimento.aggiungiTag(tag1);
        movimento.aggiungiTag(tag2);

        movimento.rimuoviTag(tag2);

        assertFalse(movimento.getTagList().contains(tag2));
        assertEquals(tag1, movimento.getTagList().get(0));
    }

    @Test
    void rimuoviTag_WhenOnlyOneTagExists_EmptiesTagList() {
        Movimento movimento = new Movimento(new Importo(1000)) {
        };
        Tags tag = new Tags("Spesa", 5);

        movimento.aggiungiTag(tag);

        movimento.rimuoviTag(tag);

        assertTrue(movimento.getTagList().isEmpty());
    }

    @Test
    void rimuoviTag_WhenTagDoesNotExist_DoesNothing() {
        Movimento movimento = new Movimento(new Importo(1000)) {
        };
        Tags tag1 = new Tags("Spesa", 5);
        Tags tag2 = new Tags("Lavoro", 10);

        movimento.aggiungiTag(tag1);

        movimento.rimuoviTag(tag2);

        assertTrue(movimento.getTagList().contains(tag1));
        assertEquals(1, movimento.getTagList().size());
    }

    @Test
    void rimuoviTag_WhenEmptyTagList_DoesNothing() {
        Movimento movimento = new Movimento(new Importo(1000)) {
        };

        Tags tag = new Tags("Spesa", 5);

        movimento.rimuoviTag(tag);

        assertTrue(movimento.getTagList().isEmpty());
    }

    @Test
    void aggiungiTag_WhenTagDoesNotExist_AddsTagToList() {
        Movimento movimento = new Movimento(new Importo(2000)) {
        };
        Tags tag = new Tags("Viaggio", 8);

        movimento.aggiungiTag(tag);

        assertTrue(movimento.getTagList().contains(tag));
        assertEquals(1, movimento.getTagList().size());
    }

    @Test
    void aggiungiTag_WhenTagExists_DoesNotDuplicateTag() {
        Movimento movimento = new Movimento(new Importo(2000)) {
        };
        Tags tag = new Tags("Viaggio", 8);

        movimento.aggiungiTag(tag);
        movimento.aggiungiTag(tag);

        assertEquals(1, movimento.getTagList().size());
        assertTrue(movimento.getTagList().contains(tag));
    }

    @Test
    void aggiungiTag_WhenTagListWasEmpty_SetsNewTagAsMostImportant() {
        Movimento movimento = new Movimento(new Importo(2000)) {
        };
        Tags tag = new Tags("Viaggio", 8);

        movimento.aggiungiTag(tag);

        assertEquals(tag, movimento.getTagList().get(0));
    }

    @Test
    void aggiungiTag_WhenTagIsMoreImportant_UpdatesMostImportantTag() {
        Movimento movimento = new Movimento(new Importo(2000)) {
        };
        Tags lessImportantTag = new Tags("Viaggio", 5);
        Tags moreImportantTag = new Tags("Business", 10);

        movimento.aggiungiTag(lessImportantTag);
        movimento.aggiungiTag(moreImportantTag);

        assertEquals(moreImportantTag, movimento.getTagList().get(1));
    }
}