package com.co.almundo.callcenter.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.co.almundo.callcenter.model.Call;

public class UnitTest implements Runnable {

	private static Logger logger = LoggerFactory.getLogger(UnitTest.class);

    private AtomicLong counter;
    private Dispatcher dispatcher;
    private int quantityCalls;
    
    public UnitTest(Dispatcher dispatcher, int quantityCalls) {
        this.counter = new AtomicLong();
        this.dispatcher = dispatcher;
        this.quantityCalls = quantityCalls;
    }

    @Override
    public void run() {
    	long i = 1;
        while (i < this.quantityCalls) {
            try {
                i = counter.incrementAndGet();
                Call call = new Call("Call " + i, dispatcher, "call");
                logger.info("New call {}", call.getName());
                new Thread(call).start();
                Thread.sleep(100);  // Envia cada llamada cada segundo        
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
	
}
