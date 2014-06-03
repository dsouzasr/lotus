package com.gmail.lifeofreilly.lotus;

import org.apache.log4j.Logger;

/**
 * A stub client that simulates incoming messages.
 */
class StubClient extends AbstractClient {

    private final static Logger log = Logger.getLogger(StubClient.class);

    /**
     * Sole constructor.
     *
     * @param trackedTerm the term to track on Twitter.
     * @param messageData the data structure for the Twitter data.
     */
    public StubClient(final String trackedTerm, final MessageData messageData) {
        super(trackedTerm, messageData);
    }

    @Override
    public void run() {
        String[] testData = new String[]{
                "RT @damonf1: 18 de mayo de 1985, 3ra Pole Position para @F1_AyrtonSenna  en #Lotus (Lotus-Renault) http://t.co/M3IKVSZ90m",
                "RT @wirewrapping: Lotus Necklace -  Moroccan - Silk Road - Yoga Jewelry - #Lotus #Intense",
                "Lotus berthelotii 'Parrot's Beak' for a container, ground cover or hanging basket. http://t.co/ssjpy8Ro5W",
                "RT @hlmalik: http://t.co/03qMN8l4yN #Lotus https://t.co/if5Y5PLMwW? #Yoga",
                "[Interviews] Jeremiah Jae Details Work With Flying Lotus; Showing... http://t.co/6eY4oR8gUf #BadTimes #GoodTimes",
                "About this weekend... The Lotus Summit was #Intense The beautiful #Lotus Leadership team drafted our 12É http://t.co/ZsK3ynGvQD",
                "Lotus notes isn't working. So what do i do now?",
                "@ArianaGrande you are like a lotus flower my little elf #Yoga",
                "My proudest moment of junior year was telling this dude at Rec Room that my name was Lotus with a straight face #Lotus #Yoga #Intense",
                "Rise up, lotus, rise, this is the beginning...  https://t.co/zdFKPVL4tF http://t.co/MupSTKq3Lh"};

        for (String str : testData) {
            log.debug("Received stub onStatus: " + str);
            StubClient.this.getMessageData().addMessage(str);

        }
    }

}
