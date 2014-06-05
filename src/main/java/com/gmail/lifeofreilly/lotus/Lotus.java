package com.gmail.lifeofreilly.lotus;

import org.apache.log4j.Logger;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Identifies the top trending hashtags on Twitter for the supplied hashtag, term, or string.
 */
public class Lotus {
    private final static Logger log = Logger.getLogger(Lotus.class);
    private final MessageData messageData;
    private final TwitterClient twitterClient;
    private final ExecutorService pool = Executors.newFixedThreadPool(2);

    /**
     * Constructs a client using the supplied keyword.
     *
     * @param trackedTerm the term to track on Twitter.
     */
    public Lotus(final String trackedTerm) {
        messageData = new MessageData();
        twitterClient = new TwitterClient(trackedTerm, messageData);
    }

    /**
     * Identifies the top trending hashtags on Twitter for the supplied hashtag, term, or string.
     * Usage: Lotus [keyword]
     *
     * @param args required argument. Specifies the keyword or hashtag to track on Twitter.
     */
    public static void main(String[] args) {
        if (args.length == 1 & validCredentialsSupplied()) {
            Lotus lotus = new Lotus(args[0]);
            lotus.startTrackingTerm();
            lotus.startProcessingMessages();
            lotus.outputTopTenEveryThirtySeconds();
        } else {
            if (args.length != 1) {
                System.out.println("Invalid number of arguments. Usage: Lotus [keyword]");
            }
            System.exit(-1);
        }
    }

    private static boolean validCredentialsSupplied() {
        try {
            Twitter twitter = TwitterFactory.getSingleton();
            twitter.verifyCredentials();
            return true;
        } catch (TwitterException ex) {
            System.out.println("Please supply a valid twitter4j.properties file in your working directory. " + ex.getMessage());
            return false;
        }
    }

    private void startTrackingTerm() {
        log.info("Starting Twitter client: " + twitterClient.toString() + ".");
        pool.execute(twitterClient);
    }

    private void startProcessingMessages() {
        log.info("Starting message processor.");
        pool.execute(new MessageProcessor(messageData));
    }

    private void outputTopTenEveryThirtySeconds() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                messageData.printTopTenHashTags();
            }
        }, 0, 30000);
    }
}
