package com.co.almundo.callcenter.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import com.co.almundo.callcenter.model.Call;
import com.co.almundo.callcenter.model.CallCenter;
import com.co.almundo.callcenter.model.DispatcherAgent;

//@Controller
public class Dispatcher {
	
	private static Logger logger = LoggerFactory.getLogger(Dispatcher.class);

//    @Autowired
    private CallCenter callCenter;

    // Definición de colas
    private ArrayBlockingQueue<Call> callsQueue;
    private ArrayBlockingQueue<Call> operatorsQueue;
    private ArrayBlockingQueue<Call> directorsQueue;
    private ArrayBlockingQueue<Call> supervisorsQueue;
    private List<DispatcherAgent> dispatcherAgents;
    
    public Dispatcher(int capacityCalls, int numOperators, int numSupervisors, int numDirectors) {
        
     // Cantidad de llamadas y agentes dispatchers
    	this.callsQueue = new ArrayBlockingQueue<Call>(capacityCalls);
        this.dispatcherAgents = new ArrayList<>();
        int numDispatcherAgent = 1;
        for (int i = 0; i < numDispatcherAgent; ++i) {
        	DispatcherAgent agent = new DispatcherAgent("Dispatcher Agent " + (i + 1), this);
        	dispatcherAgents.add(agent);
        }
        
     // Cantidad de colas call operarios
    	this.operatorsQueue = new ArrayBlockingQueue<Call>(capacityCalls);
        
     // Cantidad de colas call directores
    	this.directorsQueue = new ArrayBlockingQueue<Call>(capacityCalls);
        
     // Cantidad de colas call supervisores
    	this.supervisorsQueue = new ArrayBlockingQueue<Call>(capacityCalls);

    }

    @Async
    public void dispatchCall(String name) throws InterruptedException {
        
        if(callCenter.countBusyOperator < callCenter.getOperatorsAgents().size()) {
        	Call call = this.next("call");
        	Call callOperator = new Call(call.getName() , this, "operator");
            //logger.info("New callOperator {}-{}-{}", call.getName(),callCenter.countBusyOperator,callCenter.getAgents("operator").size());
            new Thread(callOperator).start();
        } else if(callCenter.countBusySupervisor < callCenter.getSupervisorsAgents().size()) {
        	Call call = this.next("call");
        	Call callSupervisor = new Call(call.getName() , this, "supervisor");
//            logger.info("New callSupervisor {}", call.getName());
            new Thread(callSupervisor).start();
        } else if(callCenter.countBusyDirector < callCenter.getDirectorsAgents().size()) {
        	Call call = this.next("call");
        	Call callDirector = new Call(call.getName() , this, "director");
//            logger.info("New callDirector {}", call.getName());
            new Thread(callDirector).start();
        } else {
        	if(!this.callsQueue.isEmpty())
	        	// No se libera de la cola hasta que no este disponible un agente
	        	logger.info("{} awaiting to employee", name);
        }
        
    }
    
    public boolean enter(Call call, String typeQueue) throws InterruptedException {
    	boolean entered = false;
    	switch (typeQueue) {
		case "call":
			entered = this.callsQueue.offer(call, 1, TimeUnit.SECONDS);
			break;
		case "operator":
			entered = this.operatorsQueue.offer(call, 1, TimeUnit.SECONDS);
			break;
		case "supervisor":
			entered = this.supervisorsQueue.offer(call, 1, TimeUnit.SECONDS);
			break;
		case "director":
			entered = this.directorsQueue.offer(call, 1, TimeUnit.SECONDS);
			break;
		default:
			break;
		}    	
        
        if (!entered) {
            return false;
        }
        
        return true;
    }

    public Call next(String typeQueue) throws InterruptedException {
    	
    	switch (typeQueue) {
		case "call":
			return this.callsQueue.take();			
		case "operator":
			return this.operatorsQueue.take();
		case "supervisor":
			return this.supervisorsQueue.take();			
		case "director":
			return this.directorsQueue.take();			
		default:
			return null;
		}
    	
    }
    
    public List<DispatcherAgent> getDispatcherAgent() {
		return this.dispatcherAgents;
	}

    public void setCallCenter(CallCenter callCenter) {
    	this.callCenter = callCenter;
    }
}