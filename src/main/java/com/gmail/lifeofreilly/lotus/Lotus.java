package com.gmail.lifeofreilly.lotus;

import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.Map;

/**
 * Identifies the top trending hashtags on Twitter for the supplied hashtag, term, or string.
 */
public class Lotus {

    private final static Logger log = Logger.getLogger(Lotus.class);
    private final MessageData messageData;
    private final TwitterClient twitterClient;

    /**
     * Constructs a client using the supplied keyword.
     *
     * @param trackedTerm the term to track on Twitter.
     */
    public Lotus(final String trackedTerm) {
        messageData = new MessageData();
        twitterClient = new TwitterClient(messageData, trackedTerm);
    }

    /**
     * Identifies the top trending hashtags on Twitter for the supplied hashtag, term, or string.
     * Usage: Lotus [keyword, hashtag, or string]
     *
     * @param args required argument. Specifies the keyword or hashtag to track on Twitter.
     */
    public static void main(String[] args) {
        if (args.length == 1) {
            Lotus lotus = new Lotus(args[0]);
            lotus.startTrackingTerm();
            lotus.startProcessingMessages();
            lotus.outputTop10Every30Seconds();
        } else {
            System.out.println("Invalid number of arguments. Usage: Lotus [keyword, hashtag, or string]");
            System.exit(-1);
        }
    }

    /**
     * Get the top 10 hashtags associated with the term being tracked.
     *
     * @return the top 10 hashtags.
     */
    public Map<String, Integer> getTopTenHashtags() {
        return messageData.getTopHashtags(10);
    }

    private void startTrackingTerm() {
        Thread client = new Thread(twitterClient);
        log.info("Starting Twitter client: " + twitterClient.toString() + ".");
        client.start();
    }

    private void startProcessingMessages() {
        MessageProcessor messageProcessor = new MessageProcessor(messageData);
        Thread processor = new Thread(messageProcessor);
        log.info("Starting message processor.");
        processor.start();
    }

    private void outputTop10Every30Seconds() {
        while (true) {
            System.out.println("Top 10 Related Hashtags for the term: " +
                    twitterClient.getTrackedTerm() + ", " +
                    getTopTenHashtags() +
                    ". Total Tweets Processed: " +
                    messageData.getMessageCount());
            try {
                Thread.sleep(30000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

        }
    }
}
