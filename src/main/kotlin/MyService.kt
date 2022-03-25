package com.github.kantis.springmockk.repro

import org.springframework.stereotype.Service

@Service
class MyService {
    fun doSomething() {
        println("Hello")
    }
}
