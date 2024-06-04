package org.example.produser;

import org.example.broker.MessageBroker;
import org.example.model.Message;

import java.util.concurrent.TimeUnit;

public final class ProducerTask implements Runnable {

    private static final String MESSAGE_BE_PRODUCED = "Message '%s' is produced.\n";

    private static final int SECONDS_SLEEP = 3;

    private final MessageBroker messageBroker;

    private final MessageFactory messageFactory;

    public ProducerTask(final MessageBroker messageBroker) {
        this.messageBroker = messageBroker;
        this.messageFactory = new MessageFactory();
    }

    private static final class MessageFactory {
        private static final int INITIAL_NEXT_MESSAGE_INDEX = 1;

        private static final String TEMPLATE = "Message#%d";

        private int nextMessageIndex;

        public MessageFactory() {
            this.nextMessageIndex = INITIAL_NEXT_MESSAGE_INDEX;
        }

        public Message create() {
            return new Message(String.format(TEMPLATE, this.nextMessageIndex++));
        }

    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                final Message producedMessage = messageFactory.create();
                TimeUnit.SECONDS.sleep(SECONDS_SLEEP);
                this.messageBroker.produce(producedMessage);
                System.out.printf(MESSAGE_BE_PRODUCED, producedMessage);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
