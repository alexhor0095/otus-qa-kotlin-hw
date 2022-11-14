package `hw01-testrunner`

interface TestRunner {
    fun <T> runTest(steps: T, test: () -> Unit)
}

class TestRunnerImpl : TestRunner {
    override fun <T> runTest(steps: T, test: () -> Unit) {
        val beforeMethods = steps!!::class.members.filter { it.name.contains("before") }

        for (i in 0..beforeMethods.size - 1) {
            beforeMethods.get(i).call(steps)
        }

        test()

        val afterMethods = steps!!::class.members.filter { it.name.contains("after") }

        for (i in 0..afterMethods.size - 1) {
            afterMethods.get(i).call(steps)
        }
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

    fun before() {
        println("Before")
    }
}

fun test() {
    println("test")
}

fun main() {
    TestRunnerImpl().runTest(steps = Steps(), test = { test() })
}