package com.akkademy.actor

import akka.actor.AbstractActor
import akka.actor.ActorRef
import akka.actor.Status
import akka.japi.pf.ReceiveBuilder

/**
 * @author Li Haijie
 * @date 2019/7/20 10:50
 */
open class PongActor : AbstractActor() {
    override fun createReceive(): AbstractActor.Receive {
        return ReceiveBuilder.create()
            .matchEquals("Ping", { message ->
                println("回应Ping消息")
                sender().tell("Pong", ActorRef.noSender())
            })
            .matchAny { s ->
                println("回应非Ping消息")
                sender().tell(Status.Failure(Exception("unknown message")), self())
            }.build()
    }
}
