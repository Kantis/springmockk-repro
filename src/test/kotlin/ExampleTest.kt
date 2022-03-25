package com.github.kantis.springmockk.repro

import com.ninjasquad.springmockk.MockkBean
import com.ninjasquad.springmockk.MockkClear
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.extensions.spring.SpringExtension
import io.mockk.coVerify
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [MyController::class])
class ExampleTest(controller: MyController) : FunSpec() {

    // BEFORE -> Success
    // AFTER -> Failure on `no hello`
    @MockkBean(relaxed = true, clear = MockkClear.AFTER)
    lateinit var mockService: MyService

    override fun extensions() = listOf(SpringExtension)

    init {
        context("maybe hello") {
            withData(
                row("a"), row("b")
            ) {
                controller.doSomething()
                coVerify { mockService.doSomething() }
            }
        }

        test("no hello") {
            coVerify(exactly = 0) { mockService.doSomething() }
        }
    }
}