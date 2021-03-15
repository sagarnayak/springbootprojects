package com.sagar.demo.core

import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class SendEmail {

    @Async
    fun sendEmail() {
        (1..2).forEach {
            (1..2).forEach {
                Thread.sleep(1000)
                print("running ...")
            }
        }
        print("DONE ...")
    }
}