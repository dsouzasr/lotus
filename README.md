Lotus
=======

Identifies the current top trending hashtags for the supplied hashtag, term, or string, using the Twitter Streaming API.

If using or modifying this application please adhere to: Automation rules and best practices
https://support.twitter.com/articles/76915-automation-rules-and-best-practices

Prerequisites:
-------------------------

* JDK 1.6 or higher
* Maven
* For additional dependency information refer to pom.xml
* A twitter developer account and application

Build Instructions:
-------------------------

Building an Executable Jar:
> mvn package

The executable jar can be found at: ./target/TweetBot-1.0-SNAPSHOT.jar

Usage:
-------------------------

Modify twitter4j.properties and specify the information for your twitter application.

Running as an executable jar:

> java -jar ./target/lotus-1.0-SNAPSHOT-jar-with-dependencies.jar football

Log information will be output to ./logs/error.log

Example Output:
-------------------------

Top 10 Related Hashtags for the term: football, {#Football=266, #MissionMillion=184, #LFC=112, #Indonesia=95, #football=76, #SUPEREAGLES=75, #worldcup=71, #Brazil=58, #FootBall=52, #FiFa=49}. Total Tweets Processed: 4783

Test Execution:
-------------------------

Execute all unit tests:
> mvn test

Current Unit Test Code Coverage:
AbstractClient (100% methods, 100% lines covered)
Lotus (0% methods, 0% lines covered)
MessageData (100% methods, 100% lines covered)
MessageProcessor (100% methods, 86% lines covered)
TwitterClient (0% methods, 0% lines covered)
Note: A StubClient is available for testing. You should not make multiple connections to the Twitter streaming api over a short period of time.

Limitations:
-------------------------

* The application is memory bound as the hashtags are stored in memory in a TreeMultiset - http://docs.guava-libraries.googlecode.com/git/javadoc/com/google/common/collect/TreeMultiset.html
* When connecting to the Twitter streaming api you can only have one standing connection. Do not make excessive connection attempts or your account will be rate limited or suspended.

License
-------------------------
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.