package cc.jorgen.common.serialize

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Purpose of this file is wrapping Jackson ObjectMapper with serialisation of dates
 *
 * Created by Jorgen Andersson (jorgen@kollektiva.se) on 2018-06-27.
 */
class SerializeUtil {
    /**
     * Static implementation
     */
    companion object {
        private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

        /**
         * Object to JSON string
         * Example: SerializeUtil.objectToString(MyDBO(id=0, name="Santa", phone="+15553332222"))
         * output: {"id":0,"name":"Santa","phone":"+15553332222"}
         *
         * @param item
         * @return object as JSON string
         */
        fun <T> objectToString(item: T): String {
            return getSerializer()
                    .writeValueAsString(item)
        }

        /**
         * JSON string
         * Example: SerializeUtil.stringToObject({"id":0,"name":"Santa","phone":"+15553332222"}, MyDBO::class.java)
         * output: MyDBO(id=0, name="Santa", phone="+15553332222")
         *
         * @param value string value to make object from
         * @param clazz object Java class
         * @return serialized object
         */
        fun <T> stringToObject(value: String, clazz: Class<T>): T {
            return getDeserializer()
                    .readValue(value, clazz)
        }

        /**
         * List of objects to string
         * @param items
         * @return list of objects as JSON string
         */
        fun <T> objectListToString(items: List<T>): String {
            return getSerializer()
                    .writeValueAsString(items)
        }

        /**
         * String to list of objects
         * Example: SerializeUtil.stringToObjectList([{"id":0,"name":"Santa","phone":"+15553332222"}], MyDBO::class.java)
         * @param value
         * @param clazz object Java class
         * @return list of serialized objects
         */
        fun <T> stringToObjectList(value: String, clazz: Class<T>): List<T> {
            val objectMapper = getDeserializer()
            return objectMapper.readValue(value, objectMapper.typeFactory.constructCollectionType(List::class.java, clazz))
        }

        /**
         * Create and get a mapper for serialization
         * LocalDateTime: LocalDateTime.now() => "2018-04-28T11:59:22.012Z"
         *
         * @return Jackson ObjectMapper
         */
        private fun getSerializer(): ObjectMapper {
            val javaTimeModule = JavaTimeModule()
            val localDateTimeSerializer = LocalDateTimeSerializer(dateTimeFormatter)
            javaTimeModule.addSerializer(LocalDateTime::class.java, localDateTimeSerializer)

            return jacksonObjectMapper()
                    .registerModule(javaTimeModule)
        }

        /**
         * Create and get mapper for deserialization
         *
         * @return Jackson ObjectMapper
         */
        private fun getDeserializer(): ObjectMapper {
            val javaTimeModule = JavaTimeModule()
            val localDateTimeDeserializer = LocalDateTimeDeserializer(dateTimeFormatter)
            javaTimeModule.addDeserializer(LocalDateTime::class.java, localDateTimeDeserializer)

            return jacksonObjectMapper()
                    .registerModule(javaTimeModule)
        }
    }
}