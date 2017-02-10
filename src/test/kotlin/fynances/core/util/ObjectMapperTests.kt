package fynances.core.util

import org.junit.Assert.assertEquals

data class SimpleObj(val field1: String?, val field2: Int?)

const val FIELD1 :String = "field1"
const val FIELD2 :String = "field2"
const val FIELD3 :String = "field3"

val field1 = listOf("one", "", null, "two")
val field2 = listOf(1, -4, 10, null)
val field3 = listOf("one", "two", "three", "four")

data class SkipFieldObj(val field1: String?, val field2: Int?, @ToMapSkip val field3: String?)

data class RenameFieldObj(val field1: String?, val field2: Int?, @ToMapName(FIELD3) val wrongFieldName: String?)

data class WrongRenameFieldObj(val field1: String?, @ToMapName("") val field2: Int?)

class ObjectMapperTests {
    @org.junit.Test fun testSimpleObjectToMap() {
        for (i in 0..field1.size-1) {
            val obj = SimpleObj(field1[i], field2[i])
            val map = mapOf(
                    FIELD1 to field1[i],
                    FIELD2 to field2[i]
            )
            doTest(map, obj)
        }
    }

    @org.junit.Test fun testSkipFieldObjectToMap() {
        for (i in 0..field1.size-1) {
            val obj = SkipFieldObj(field1[i], field2[i], field3[i])
            val map = mapOf(
                    FIELD1 to field1[i],
                    FIELD2 to field2[i]
            )
            doTest(map, obj)
        }
    }

    @org.junit.Test fun testRenameFieldObjectToMap() {
        for (i in 0..field1.size-1) {
            val obj = RenameFieldObj(field1[i], field2[i], field3[i])
            val map = mapOf(
                    FIELD1 to field1[i],
                    FIELD2 to field2[i],
                    FIELD3 to field3[i]
            )
            doTest(map, obj)
        }
    }

    @org.junit.Test fun testEmptyRenameFieldObjectToMap() {
        for (i in 0..field1.size-1) {
            val obj = WrongRenameFieldObj(field1[i], field2[i])
            val map = mapOf(
                    FIELD1 to field1[i],
                    FIELD2 to field2[i]
            )
            doTest(map, obj)
        }
    }

    private fun doTest(map: Map<String, Any?>, obj: Any) {
        val result = objectToMap(obj)
        assertEquals("Map size is incorrect", map.size, result.size)
        for ((key, value) in map) {
            assertEquals("Maps are not equal", value, result[key])
        }
    }
}
