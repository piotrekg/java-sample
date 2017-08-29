package pl.gielerak.domain.date.model;

import org.junit.Assert;
import org.junit.Test;
import pl.gielerak.domain.date.model.DateFormat;

import static org.junit.Assert.*;

public class DateFormatTest {
    @Test
    public void format() throws Exception {
        Assert.assertEquals(DateFormat.ISO8601.format(), new String("yyyy-MM-dd'T'HH:mm:ss'Z'"));
        assertEquals(DateFormat.JSON.format(), new String("yyyy-MM-dd'T'HH:mm:ss"));
    }

}