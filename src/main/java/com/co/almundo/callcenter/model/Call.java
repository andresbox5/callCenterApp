package com.co.almundo.callcenter.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.co.almundo.callcenter.controller.Dispatcher;

public class Call implements Runnable {
	
	private static Logger logger = LoggerFactory.getLogger(Call.class);

    private String name;
    private Dispatcher dispatcher;
    private String typeQueue;

    public Call(String name, Dispatcher dispatcher, String typeQueue) {
        this.name = name;
        this.dispatcher = dispatcher;
        this.typeQueue = typeQueue;
        
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try {
            boolean entered = dispatcher.enter(this, typeQueue);
            if (!entered) {
                logger.info("{} not queue, callCenter at full capacity", name);
            } else {
                //logger.info("{} queue", name);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}
