package com.github.kantis.springmockk.repro

import com.ninjasquad.springmockk.MockkBean
import com.ninjasquad.springmockk.MockkClear
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.mockk.verify
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [MyController::class])
class ExampleTest(controller: MyController) : FunSpec() {

    // BEFORE -> Success
    // AFTER -> Failure on `no hello`
    @MockkBean(relaxed = true, clear = MockkClear.AFTER)
    lateinit var mockService: MyService

    override fun extensions() = listOf(SpringExtension)

    init {
        context("withData test") {
            withData(
                "a", "b"
            ) { arg ->
                controller.doSomething(arg)
                verify { mockService.doSomething(arg) }
            }
        }

        test("no hello") {
            controller.doSomething("c")
            verify { mockService.doSomething(withArg { it shouldBe "c" }) }
        }
    }
}
