package com.github.kantis.springmockk.repro

import org.springframework.stereotype.Service

@Service
class MyService {
    fun doSomething(arg: String) {
        println("Hello $arg")
    }
}
