package io.quarkus.it.artemis.core.withdefault.changeurl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import io.smallrye.common.annotation.Identifier;

@Path("/artemis")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class ArtemisEndpoint {
    private final ArtemisProducerManager defaultProducer;
    private final ArtemisConsumerManager defaultConsumer;
    private final ArtemisProducerManager namedOneProducer;
    private final ArtemisConsumerManager namedOneConsumer;

    public ArtemisEndpoint(
            ArtemisProducerManager defaultProducer,
            ArtemisConsumerManager defaultConsumer,
            @Identifier("named-1") ArtemisProducerManager namedOneProducer,
            @Identifier("named-1") ArtemisConsumerManager namedOneConsumer) {
        this.defaultProducer = defaultProducer;
        this.defaultConsumer = defaultConsumer;
        this.namedOneProducer = namedOneProducer;
        this.namedOneConsumer = namedOneConsumer;
    }

    @POST
    public void defaultPost(String message) {
        defaultProducer.send(message);
    }

    @GET
    public String defaultGet() {
        return defaultConsumer.receive();
    }

    @POST
    @Path("named-1")
    public void namedOnePost(String message) {
        namedOneProducer.send(message);
    }

    @GET
    @Path("named-1")
    public String namedOneGet() {
        return namedOneConsumer.receive();
    }
}