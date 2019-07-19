package com.akkademy;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.TestActorRef;
import com.akkademy.message.SetRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Li Haijie
 * @date 2019/7/19 10:23
 */
public class AkkademyDbTest {
    ActorSystem system = ActorSystem.create();

    @Test
    public void itShouldPlaceKeyValueFromSetMessageIntoMap(){
        TestActorRef<AkkademyDb> actorRef = TestActorRef.create(system, Props.create(AkkademyDb.class));
        actorRef.tell(new SetRequest("key", "value"), ActorRef.noSender());
        actorRef.tell(new SetRequest("key1", "value1"), ActorRef.noSender());
        AkkademyDb akkademyDb = actorRef.underlyingActor();
        assertEquals(akkademyDb.map.get("key"), "value");
        assertEquals(akkademyDb.map.get("key1"), "value1");
    }
}
