package com.sagar.blockchain

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

@Configuration
class CommandLineAppStartupRunner : CommandLineRunner {

    private val logger = LoggerFactory.getLogger(CommandLineAppStartupRunner::class.java)

    override fun run(vararg args: String?) {
        logger.info("started bitcoin...")

        val transactionsOne = arrayListOf(
            "sagar nayak",
            "sagar kumar"
        )
        val blockOne = Block.generateBlock(transactionsOne, 0)

        val transactionsTwo = arrayListOf(
            "sagar nayak",
            "sagar kumar"
        )
        val blockTwo = Block.generateBlock(transactionsTwo, blockOne.blockHash)

        val transactionsThree = arrayListOf(
            "sagar nayak",
            "sagar kumar"
        )
        val blockThree = Block.generateBlock(transactionsThree, blockTwo.blockHash)

        val blockChain = arrayListOf(blockOne, blockTwo, blockThree)

        blockChain.forEach {
            logger.info(it.toString())
        }

        val digest: MessageDigest = MessageDigest.getInstance("SHA-256")
        val encodedhash: ByteArray = digest.digest(
            "sagar nayak".toByteArray()
        )

        logger.info("encrypted : $encodedhash")
    }
}