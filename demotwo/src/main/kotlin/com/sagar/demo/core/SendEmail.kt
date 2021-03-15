package com.sagar.demo.core

import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class SendEmail {

    @Async
    fun sendEmail() {
        (1..90).forEach {
            (1..90).forEach {
                Thread.sleep(500)
                print("running ...")
            }
        }
        print("DONE ...")
    }
}