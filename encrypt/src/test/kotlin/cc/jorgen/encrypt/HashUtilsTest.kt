package cc.jorgen.encrypt

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

/**
 * Created by Jorgen Andersson on 2018-06-26.
 */
class HashUtilsTest {

    private val stringToHash = "An input string"

    @Test
    fun `SHA-1 Test`() {
        assertThat(HashUtils.SHA1(stringToHash)).isEqualTo("E81A96C87E28B6C611983B75DD469D688634A0C1")
    }

    @Test
    fun `SHA-256 Test`() {
        assertThat(HashUtils.SHA256(stringToHash)).isEqualTo("1AA3F807565479B5E2B99789E928271E63BFF4A074410737AE9550DBBE091A52")
    }

    @Test
    fun `SHA-512 Test`() {
        assertThat(HashUtils.SHA512(stringToHash)).isEqualTo("9866DEB1EE3582428936D59339E0B0C19C4FA05691D425C3BDAB2AAEC900E9F8E74A46D2168B96F268960841157634B897305AF11027FF089D71D3C2083AB5E6")
    }
}