package cc.jorgen.data.connection

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

/**
 * Purpose of this class ...
 *
 * Created by Jorgen Andersson (jorgen@kollektiva.se) on 2018-06-27.
 */
class MongoConnectionTest {

    @Before
    fun setupBeforeEachMethod() {
        MongoConnection.resetDatabase()
    }


    @Test
    fun mongoConnectionGetDatabase_name_hovno() {
        MongoConnection.setConnection("mongodb://localhost:27017/xyz")
        assertThat(MongoConnection.getDatabase().name).isEqualTo("xyz")
    }

    @Test
    fun mongoConnection_testTwoConnections_thereCanOnlyBeOneConnectionShouldThrowException() {
        val expectedExceptionMessage = "There is already a connection to a DB!"
        try {
            // Set first connection
            MongoConnection.setConnection("mongodb://localhost:27017/xyz")
            // Try to set another connection
            MongoConnection.setConnection("mongodb://localhost:27017/abc")
        } catch (e: RuntimeException) {
            assertThat(e.message).isEqualTo(expectedExceptionMessage)
        }
    }

}

