package org.doxla.eventualj;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.doxla.eventualj.Eventually.eventually;
import static org.doxla.eventualj.EventuallyMatchers.willBe;
import static org.doxla.eventualj.EventuallyMatchers.willReturn;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class EventuallyTest {

    @Test
    public void withoutEventualyWeGetNull() throws Exception {
        assertThat(ten().getValue(), nullValue());
    }

    @Test
    public void withEventuallyICanAssertWhatTheValueWillEventualReturn() throws Exception {
        InThread inThread = eventually(ten());
        assertThat(inThread.getValue(), willReturn(10));
    }

    @Test
    public void withEventuallyICanAssertWhatTheValueWillEventualBe() throws Exception {
        assertThat(eventually(ten()).getValue(), willBe(10));
    }

    public static InThread ten() {
        return willBe10Eventually();
    }

    public static InThread willBe10Eventually() {
        InThread inThread = new InThread();
        new Thread(inThread).start();
        return inThread;
    }

    public static class InThread implements Runnable {
        private Integer value;

        public void run() {
            value = sleepFor200MillisThenReturnTen();
        }

        public Integer getValue() {
            return value;
        }

        private int sleepFor200MillisThenReturnTen() {
            try {
                TimeUnit.MILLISECONDS.sleep(200L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            return 10;
        }
    }

}
