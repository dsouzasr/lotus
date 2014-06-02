package com.gmail.lifeofreilly.lotus;

import org.apache.log4j.Logger;

import java.util.StringTokenizer;

/**
 * Extracts hashtags from messages.
 */
public class MessageProcessor implements Runnable {
    private final static Logger log = Logger.getLogger(MessageProcessor.class);
    private final MessageData messageData;

    /**
     * Constructs a MessageProcessor.
     *
     * @param messageData the MessageData.
     */
    public MessageProcessor(final MessageData messageData) {
        this.messageData = messageData;
    }

    @Override
    public void run() {
        while (true) {
            extractHashtagsFromMessage(messageData.takeMessageFromQueue());
        }
    }

    private void extractHashtagsFromMessage(final String message) {
        String deliminator = " \t\n\r\f,.:;?![]'"; //adds punctuation marks to default set
        StringTokenizer tokenizer = new StringTokenizer(message, deliminator);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (token.startsWith("#")) {
                messageData.addHashTag(token);
            }
        }
    }
}
