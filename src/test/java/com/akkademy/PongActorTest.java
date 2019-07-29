package com.akkademy;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import com.akkademy.actor.PongActor;
import org.junit.Test;
import scala.concurrent.Future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * @author Li Haijie
 * @date 2019/7/29 17:58
 */
public class PongActorTest {
    ActorSystem system = ActorSystem.create();
    ActorRef actorRef =
            system.actorOf(Props.create(PongActor.class), "BruceWillis");


    @Test
    public void shouldReplyToPingWithPong() throws Exception {
        Future sFuture = Patterns.ask(actorRef, "Ping", 1000);
        final CompletionStage<String> cs = scala.compat.java8.FutureConverters.toJava(sFuture);
        final CompletableFuture<String> jFuture = (CompletableFuture<String>) cs;
        assertEquals("Pong", jFuture.get(1000, TimeUnit.MILLISECONDS));
    }
}
