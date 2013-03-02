package org.apache.flume.channel.kafka.impl;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.Transaction;
import org.apache.flume.channel.kafka.ConfigurationConstants;
import org.apache.flume.channel.kafka.KafkaChannel;
import org.apache.flume.channel.kafka.KafkaChannelProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: jcreasy
 * Date: 3/1/13
 * Time: 4:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class KafkaChannelProviderImpl implements KafkaChannelProvider {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaChannel.class);

    @Override
    public void initialize(Context context) {
        LOG.debug("initialize channel");
        LOG.debug(context.toString());
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void close() {
        LOG.debug("closing channel");
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void produceEvent(String channelName, Event event) {
        String message = String.format("writing to %s: %s", channelName, event.toString());
        LOG.debug(message);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Event consumeEvent(String channelName) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Transaction getTransaction() {
        LOG.debug("get transaction on Kafka Channel");
        return null;
    }
}
