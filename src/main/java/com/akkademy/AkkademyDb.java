package com.akkademy;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import com.akkademy.message.SetRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Li Haijie
 * @date 2019/7/19 9:53
 */
public class AkkademyDb extends AbstractActor {
    protected final LoggingAdapter log = Logging.getLogger(context().system(), this);
    protected final Map<String, Object> map = new HashMap<String, Object>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().
                match(SetRequest.class, message -> {
                    //log.info("Received Set request: {}", message);
                    map.put(message.getKey(), message.getValue());
                    System.out.println("class is " + SetRequest.class.getName());
                }).
                matchAny(o -> {
                    log.info("received unknown message: {}", o);
                    System.out.println("class is unknown");
                }).
                build();
    }
}
