package fynances.core

import org.junit.Assert
import org.junit.Test

/**
 * @author Andrey Yevseyenko
 */

class ExampleTests {
    @Test fun simple() {
        val example = Example()
        Assert.assertEquals("test", example.data)
    }
}