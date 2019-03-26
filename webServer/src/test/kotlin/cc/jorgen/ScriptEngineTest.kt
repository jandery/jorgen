package cc.jorgen

import org.junit.Test
import javax.script.Invocable
import javax.script.ScriptContext
import javax.script.ScriptEngineManager

/**
 * Purpose of this file is ...
 */
class ScriptEngineTest {

    @Test
    fun firstTest() {

        val engine = ScriptEngineManager(null).getEngineByName("V8")
        val context: ScriptContext = engine.context

        val obj = engine.eval("""
            "use strict";
            class Tada {
                constructor(first, second) { this.first = first; this.second = second; }
                add() { return this.first + this.second; }
            }
        """.trimIndent())

        val invokeFunction = (engine as Invocable).invokeFunction("Tada", 7, 3)
        println(invokeFunction)
    }
}