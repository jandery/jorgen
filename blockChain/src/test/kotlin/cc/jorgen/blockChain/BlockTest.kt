package cc.jorgen.blockChain

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

/**
 * Purpose of this class ...
 *
 * Created by Jorgen Andersson (jorgen@kollektiva.se) on 2018-06-27.
 */
class BlockTest {

    data class Doc(val title: String, val description: String)

    @Test
    fun blockOfTypeString_size_3() {
        val block = Block(
                previousHash = "",
                data = listOf("one", "two", "three"),
                nonce = 9,
                hash = "")
        assertThat(block.data.size).isEqualTo(3)
    }

    @Test
    fun blockOfTypeDoc_size_2() {
        val docBlock = Block(
                previousHash = "",
                data = listOf(Doc("title1", "decription 1"), Doc("title 2", "description 2")),
                nonce = 0,
                hash = "")
        assertThat(docBlock.data.size).isEqualTo(2)
    }
}