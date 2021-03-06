eventualj
====================

Eventual assertions, for Java.  
Making asynchronous assertions elegant.

What is eventualj
-----------------
eventualj is an extension for [hamcrest's](http://code.google.com/p/hamcrest/) *Matchers* that allows support for matching temporal values.
Often you will come across such scenarios when testing multi-threaded applications.

Imagine the scenario where your application consumes messages from queues asynchronously. You want to test that when you put a message on the inbound queue it gets consumed. Because the message is consumed asynchronous you can't write an immediate assertion. Wouldn't it be nice if you could just write:

<pre><code>whenIPutAMessageOnThe(inboundQueue);
assertThat(eventually(inboundQueue).isEmpty(), willBe(true));</code></pre>

How it works
------------
eventualj's **eventually** method returns a type safe proxy allowing you write a test asserting against the return value of one of its methods. Or more specifically, its eventual value.

Because the eventually proxy is type safe, you get all your usual IDE refactoring and autocompletion features for free.

Examples
--------

<pre><code>assertThat(eventually(ten()).getValue(), willBe(10)); // passes
assertThat(ten().getValue(), is(10)); // fails, ten()'s value hasn't been set yet
assertThat(eventually(messageQueue).isEmpty(), willBe(true).within(millis(100))); // passes eventually</code></pre>

For a more complete example see the **Queue** producer and consumer example test [here](http://github.com/oxlade39/eventualj/blob/master/src/test/java/org/doxla/eventualj/ExampleTest.java)

Maven projects
--------------
eventualj isn't currently released in a public maven repository. If you want it, stop by and add a plus 1 to the [issue](http://github.com/oxlade39/eventualj/issues#issue/3)