package com.gmail.lifeofreilly.lotus;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Arrays;


/**
 * Unit Test: [test_UnitOfWork_StateUnderTest_ExpectedBehavior]
 */
public class MessageDataTest extends TestCase {

    private final MessageData messageData = new MessageData();

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public MessageDataTest(String testName)
    {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite(MessageDataTest.class);
    }

    public void setUp() {
        MessageProcessor messageProcessor = new MessageProcessor(messageData);
        StubClient stubClient = new StubClient(messageData, "Stub Term");

        Thread client = new Thread(stubClient);
        client.start();

        Thread processor = new Thread(messageProcessor);
        processor.start();

        //Sleep for one second to ensure all messages have been processed.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public void test_getTopHashtags_StubClient_TagsReturned()
    {
        assertEquals("{#Lotus=5, #Intense=3, #Yoga=3, #BadTimes=1, #GoodTimes=1}",
                messageData.getTopHashtags(5).toString());

    }

    public void test_getMessageCount_StubClient_TotalReturned()
    {
        assertEquals(10, messageData.getMessageCount());
    }

}
