package lab;

import org.junit.Test;

import static org.junit.Assert.*;

public class TermTest {

    Term term1 = new Term(9,45);
    Term term2 = new Term(10,15);
    Term term3 = new Term(8, 0);
    Term term4 = new Term(17, 50);
    Term term5 = new Term(0, 0);

    @Test
    public void toStringTest() {
        assertEquals("9:45 [90]", term1.toString());
        assertEquals("10:15 [90]", term2.toString());
        assertEquals("8:00 [90]", term3.toString());
        assertEquals("17:50 [90]", term4.toString());
        assertEquals("00:00 [90]", term5.toString());
    }

    @Test
    public void earlierThanTest() {
        assertTrue(term1.earlierThan(term2));
        assertFalse(term2.earlierThan(term1));
    }

    @Test
    public void laterThanTest() {
        assertFalse(term1.laterThan(term2));
        assertTrue(term2.laterThan(term1));
    }

    @Test
    public void endTermTest() {
        Term term11 = new Term(9, 45);
        term11.setDuration(30);
        assertEquals(term11, term1.endTerm(term2));

        Term term21 = new Term(10, 15);
        term21.setDuration(0);
        assertEquals(term21, term2.endTerm(term1));
    }

    @Test
    public void endTerm1Test() {
        Term term11 = new Term(11, 15);
        term11.setDuration(term1.getDuration());
        assertEquals(term11, term1.endTerm());

        Term term21 = new Term(11, 45);
        term21.setDuration(term2.getDuration());
        assertEquals(term21, term2.endTerm());
    }
}