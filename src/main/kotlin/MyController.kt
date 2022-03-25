package com.github.kantis.springmockk.repro

import org.springframework.web.bind.annotation.RestController

@RestController
class MyController(val service: MyService) {
    fun doSomething(arg: String) {
        service.doSomething(arg)
    }
}
