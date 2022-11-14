import kotlin.reflect.full.callSuspend

interface TestRunner {
    fun <T> runTest(steps: T, test: () -> Unit)
}

class TestRunnerImpl : TestRunner {
    override fun <T> runTest(steps: T, test: () -> Unit) {
        val beforeMethods = steps!!::class.members.distinctBy{it.name.contains ("before")}
        println(beforeMethods)

        test()

    }
}



class Steps {
    fun beforeClass() {
        println("Before Class")
    }

    fun afterTest() {
        println("After Test")
    }

    fun afterClass() {
        println("After Class")
    }

    fun beforeTest() {
        println("Before Test")
    }
}

fun test(){
    println("test")
}

fun main() {
    TestRunnerImpl().runTest(steps = Steps(), test =  { test() })
}