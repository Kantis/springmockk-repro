package com.github.kantis.springmockk.repro

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import io.mockk.verify
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [MyController::class])
class ExampleTest(controller: MyController) : FunSpec() {

    @MockkBean(relaxed = true)
    lateinit var mockService: MyService

    override fun extensions() = listOf(SpringExtension)

    init {
        test("hello") {
            controller.doSomething()
            verify { mockService.doSomething() }
        }

        test("no hello") {
            verify(exactly = 0) { mockService.doSomething() }
        }
    }
}
