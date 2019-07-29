package com.akkademy

import akka.actor.AbstractActor
import akka.event.Logging
import akka.event.LoggingAdapter
import akka.japi.pf.ReceiveBuilder
import com.akkademy.message.SetRequest

import java.util.HashMap

/**
 * @author Li Haijie
 * @date 2019/7/19 9:53
 */
open class AkkademyDb : AbstractActor() {

    var log = Logging.getLogger(context().system(), this)
    var map: MutableMap<String, Any> = HashMap()

    override fun createReceive(): AbstractActor.Receive {
        return ReceiveBuilder.create().match(SetRequest::class.java) { message ->
            //log.info("Received Set request: {}", message);
            map[message.key] = message.value
            println("class is " + SetRequest::class.java.name)
        }.matchAny { o ->
            log.info("received unknown message: {}", o)
            println("class is unknown")
        }.build()
    }
}
