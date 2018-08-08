package com.co.almundo.callcenter.model;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.co.almundo.callcenter.controller.Dispatcher;
import com.co.almundo.callcenter.utilities.Util;

public class Agent implements Runnable {
		
	private static Logger logger = LoggerFactory.getLogger(Agent.class);

    private String name;
    private CallCenter callCenter;
    private Dispatcher dispatcher;
    private Iterator<Long> times;
    private String typeQueue;
    
    public Agent(String name, CallCenter callCenter, String typeQueue, Dispatcher dispatcher) {
        this.name = name;
        this.callCenter = callCenter;
        this.typeQueue = typeQueue;
        this.times = Util.asignRandomTimeToTask();
        this.dispatcher = dispatcher;
    }

    public void attendCall(Call call) throws InterruptedException {
        switch (typeQueue) {			
		case "operator":
			callCenter.addCountBusyOperator();
			break;
		case "supervisor":
			callCenter.addCountBusySupervisor();
			break;
		case "director":
			callCenter.addCountBusyDirector();
			break;
		}
    	long time = getTime();
        logger.info("{} attending call '{}' during {}ms", name, call.getName(), time);
        spendTime(time);
        logger.info("{} attended call '{}'", name, call.getName());
//        call.shaved();
        switch (typeQueue) {			
		case "operator":
			callCenter.removeCountBusyOperator();
			break;
		case "supervisor":
			callCenter.removeCountBusySupervisor();
			break;
		case "director":
			callCenter.removeCountBusyDirector();
			break;
		}
    }

    @Override
    public void run() {
        while (true) {
            try {
                //logger.info("{} awaiting call", name);
                Call call = this.dispatcher.next(typeQueue);
                attendCall(call);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    private long getTime() throws InterruptedException {
        return times.next();
    }

    private void spendTime(long time) throws InterruptedException {
        Thread.sleep(time);
    }

}
