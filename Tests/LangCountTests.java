import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class LangCountTests {

    @Test
    public void canReturnCount () {
        LangCount lc = new LangCount("Java");
        assertEquals(1, lc.getCount());
    }

    @Test
    public void canReturnLanguage() {
        LangCount lc = new LangCount("Java");
        assertEquals("Java", lc.getLanguage());
    }

    @Test
    public void canIncrementCount() {
        LangCount lc = new LangCount("C");
        lc.incrementCount();
        assertEquals(2, lc.getCount());
    }

    @Test
    public void equalityCheck() {
        LangCount lc1 = new LangCount("Java");
        LangCount lc2 = new LangCount("Java");
        LangCount lc3 = new LangCount("C");

        assertEquals(lc1, lc2);
        assertNotEquals(lc1, lc3);
    }

    @Test
    public void comparisonCheck() {
        LangCount lc1 = new LangCount("Java");
        lc1.incrementCount();
        LangCount lc2 = new LangCount("Haskell");
        assertEquals(1, lc1.compareTo(lc2));
    }


}
