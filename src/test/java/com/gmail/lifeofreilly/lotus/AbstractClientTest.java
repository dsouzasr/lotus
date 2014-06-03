package com.gmail.lifeofreilly.lotus;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit Test: [test_UnitOfWork_StateUnderTest_ExpectedBehavior]
 */
public class AbstractClientTest extends TestCase {

    private StubClient stubClient;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AbstractClientTest(String testName)
    {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite(AbstractClientTest.class);
    }

    public void setUp() {
        MessageData messageData = new MessageData();
        stubClient = new StubClient("Stub Term", messageData);
    }

    public void test_getTrackedTerm_NoArgs_TrackedTermReturned()
    {
        assertEquals("Stub Term", stubClient.getTrackedTerm());
    }

    public void test_toString_NoArgs_ClientInfoReturned()
    {
        assertEquals("AbstractClient{trackedTerm='Stub Term', class=class com.gmail.lifeofreilly.lotus.StubClient}", stubClient.toString());
    }
}
