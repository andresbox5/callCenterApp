package com.co.almundo.callcenter.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.co.almundo.callcenter.controller.Dispatcher;

public class DispatcherAgent implements Runnable {
		
	private static Logger logger = LoggerFactory.getLogger(DispatcherAgent.class);

    private String name;
    private Dispatcher dispatcher;
    

    public DispatcherAgent(String name, Dispatcher dispatcher) {
        this.name = name;
        this.dispatcher = dispatcher;

    }

    @Override
    public void run() {
        while (true) {
            try {
            	//logger.info("{} awaiting call", name);
            	this.dispatcher.dispatchCall(this.name);
            	Thread.sleep(100);  // Revisa cada llamada cada segundo              
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

}
