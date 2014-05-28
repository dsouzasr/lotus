package com.gmail.lifeofreilly.lotus;

import org.apache.log4j.Logger;

import java.util.*;

import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.TreeMultiset;

/**
 * A message queue and the hashtags extracted.
 */
public class MessageData {
    private final static Logger log = Logger.getLogger(MessageData.class);
    private long messageCount;
    private final Multiset<String> hashTags = TreeMultiset.create();
    private final Queue<String> messageQueue = new LinkedList<String>();

    /**
     * Add a message to the queue to be processed.
     *
     * @param message the message.
     */
    public void addMessage(final String message) {
        messageQueue.add(message);
        messageCount++;
        log.debug("Current Queue size: " + messageQueue.size());
    }

    /**
     * Get the number of messages submitted for processing.
     *
     * @return the number of messages.
     */
    public long getMessageCount() {
        return messageCount;
    }

    /**
     * Returns true if the message queue is empty.
     *
     * @return is queue empty.
     */
    public boolean messageQueueIsEmpty() {
        return messageQueue.isEmpty();
    }

    /**
     * Returns and removes a message from the queue.
     *
     * @return the message.
     */
    public String removeMessageFromQueue() {
        return messageQueue.remove();
    }

    /**
     * Adds a hashtag to the collection.
     *
     * @param hashtag the hashtag.
     */
    public void addHashTag(final String hashtag) {
        hashTags.add(hashtag);
    }

    /**
     * Get the top hashtags.
     *
     * @return the top hashtags and occurrence of each.
     */
    public Map<String, Integer> getTopHashtags(int maxNumberOfHashTags) {
        Set<String> sortedSet = Multisets.copyHighestCountFirst(hashTags).elementSet();
        Iterator<String> iterator = sortedSet.iterator();
        Map<String, Integer> topTerms = new LinkedHashMap<String, Integer>();

        for (int i = 0; i < maxNumberOfHashTags; i++) {
            if (iterator.hasNext()) {
                String term = iterator.next();
                topTerms.put(term, hashTags.count(term));
            } else {
                break;
            }
        }

        return topTerms;
    }

}
