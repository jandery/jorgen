package cc.jorgen

import com.eclipsesource.v8.NodeJS
import com.eclipsesource.v8.V8
import org.assertj.core.api.Assertions
import org.junit.Test
import java.io.File

/**
 * Purpose of this file is ...
 */
class EngineTest {

    private val fileLocation = """${System.getProperty("user.dir")}/src/main/resources/www/js/classes/Formatter.js"""


    private fun getFormatterFileAsString(): String {
        return File(fileLocation)
                .readText(Charsets.UTF_8)
                // export/import not yet supported
                .replace("export class", "class")
    }


    @Test
    fun inlineClass_callClassFunction_7() {
        val runtime = V8.createV8Runtime()

        // use strict required for engine to read es6
        val result: Int = runtime.executeIntegerScript("""
            "use strict";
            class Tada {
                constructor(first, second) { this.first = first; this.second = second; }
                add() { return this.first + this.second; }
            }
            new Tada(3,4).add()
        """.trimIndent())

        runtime.release()
        Assertions.assertThat(result).isEqualTo(7)
    }

    @Test
    fun readFormatterFile_formatMoneyFloorValue_12() {
        val runtime = V8.createV8Runtime()
        val str = """
            "use strict";
            ${getFormatterFileAsString()}
            Formatter.formatMoneyAsInt(1299)
        """.trimIndent()

        val result: String = runtime.executeStringScript(str)

        runtime.release()
        Assertions.assertThat(result).isEqualTo("12")
    }

    @Test
    fun readFormatterFile_formatWithThousandSeparator_1000() {
        val runtime = V8.createV8Runtime()
        val str = """
            "use strict";
            ${getFormatterFileAsString()}
            Formatter.formatMoneyAsInt(100000)
        """.trimIndent()

        val result: String = runtime.executeStringScript(str)
        runtime.release()
        Assertions.assertThat(result).isEqualTo("1 000")
    }

    @Test
    fun import_V8_notWorking() {
        // undefined:2: SyntaxError: Unexpected token import
        val runtime = V8.createV8Runtime()
        val str = """
            "use strict";
            import {Formatter} from "$fileLocation";
            Formatter.formatMoneyAsInt(100000)
        """.trimIndent()

        val result: String = runtime.executeStringScript(str)
        runtime.release()
        Assertions.assertThat(result).isEqualTo("1 000")
    }

    @Test
    fun import_viaNode_notWorking() {
        // module:2: SyntaxError: Unexpected token import
        val node = NodeJS.createNodeJS()

        val str = """
            "use strict";
            import {Formatter} from "$fileLocation";
            Formatter.formatMoneyAsInt(100000)
        """.trimIndent()

        val result: String = node.runtime.executeStringScript(str, "module", 0)
        node.runtime.release()
        node.release()
        Assertions.assertThat(result).isEqualTo("1 000")
    }

    @Test
    fun require_fromNode_notWorking() {
        // SyntaxError: Unexpected token export
        val node = NodeJS.createNodeJS()
        node.require(File(fileLocation))

        val str = """
            "use strict";
            Formatter.formatMoneyAsInt(100000)
        """.trimIndent()

        val result: String = node.runtime.executeStringScript(str)
        node.runtime.release()
        node.release()
        Assertions.assertThat(result).isEqualTo("1 000")
    }

    @Test
    fun require_fromJS_notWorking() {
        // ReferenceError: require is not defined
        val node = NodeJS.createNodeJS()

        val str = """
            "use strict";
            var Formatter = require("$fileLocation").default;
            Formatter.formatMoneyAsInt(100000)
        """.trimIndent()

        val result: String = node.runtime.executeStringScript(str)
        node.runtime.release()
        node.release()
        Assertions.assertThat(result).isEqualTo("1 000")
    }
}