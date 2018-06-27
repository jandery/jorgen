package cc.jorgen.common.serialize

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/**
 * Created by Jorgen Andersson (jorgen@kollektiva.se) on 2018-06-27.
 */
class SerializeUtilTest {
    data class SomeObject(val title: String, val description: String, val created: LocalDateTime)

    private val SINGLE_SESSION_OBJECT = SomeObject(
            "D",
            "I",
            LocalDateTime.parse("2018-05-09 11:58:27", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("UTC"))))

    private val MULTIPLE_SESSION_OBJECTS = listOf(
            SomeObject(
                    "D1",
                    "I1",
                    LocalDateTime.parse("2018-05-09 10:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("UTC")))),
            SINGLE_SESSION_OBJECT,
            SomeObject(
                    "D2",
                    "I2",
                    LocalDateTime.parse("2018-05-09 14:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("UTC"))))
    )

    private val SINGLE_SESSION_STRING = """{"title":"D","description":"I","created":"2018-05-09T11:58:27.000Z"}"""

    private val MULTIPLE_SESSIONS_STRING = """[{"title":"D1","description":"I1","created":"2018-05-09T10:00:00.000Z"},{"title":"D","description":"I","created":"2018-05-09T11:58:27.000Z"},{"title":"D2","description":"I2","created":"2018-05-09T14:00:00.000Z"}]"""

    @Test
    fun objectToString_searilize_singleString() {
        val str = SerializeUtil.objectToString(SINGLE_SESSION_OBJECT)
        assertThat(str).isEqualTo(SINGLE_SESSION_STRING)
    }

    @Test
    fun stringToObject_deserailize_singleObject() {
        val obj = SerializeUtil.stringToObject(SINGLE_SESSION_STRING, SomeObject::class.java)
        assertThat(obj.title).isEqualTo("D")
    }

    @Test
    fun objectListToString_serialize_mutipleString() {
        val str = SerializeUtil.objectListToString(MULTIPLE_SESSION_OBJECTS)
        assertThat(str).isEqualTo(MULTIPLE_SESSIONS_STRING)
    }

    @Test
    fun stringToObjects_deserialize_multipleObjects() {
        val objs = SerializeUtil.stringToObjectList(MULTIPLE_SESSIONS_STRING, SomeObject::class.java)
        assertThat(objs).isEqualTo(MULTIPLE_SESSION_OBJECTS)
    }
}