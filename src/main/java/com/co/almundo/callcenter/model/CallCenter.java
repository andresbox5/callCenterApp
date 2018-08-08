package com.co.almundo.callcenter.model;

import java.util.ArrayList;
import java.util.List;

import com.co.almundo.callcenter.controller.Dispatcher;

//@Component
public class CallCenter {
		
    private List<Agent> operatorsAgents;
    private List<Agent> directorsAgents;
    private List<Agent> supervisorsAgents;
    public int countBusyOperator;
    public int countBusySupervisor;
    public int countBusyDirector;

    public CallCenter(int numOperators, int numSupervisors, int numDirectors, Dispatcher dispatcher) {
        
     // Cantidad de operarios
        this.operatorsAgents = new ArrayList<>();
        for (int i = 0; i < numOperators; ++i) {
        	Agent agent = new Agent("Operators Agent " + (i + 1), this, "operator", dispatcher);
        	operatorsAgents.add(agent);
        }
        
     // Cantidad de directores
        this.directorsAgents = new ArrayList<>();
        for (int i = 0; i < numDirectors; ++i) {
        	Agent agent = new Agent("Directors Agent " + (i + 1), this, "director", dispatcher);
        	directorsAgents.add(agent);
        }
        
     // Cantidad de supervisores
        this.supervisorsAgents = new ArrayList<>();
        for (int i = 0; i < numSupervisors; ++i) {
        	Agent agent = new Agent("Supervisors Agent " + (i + 1), this, "supervisor", dispatcher);
        	supervisorsAgents.add(agent);
        }

    }

	public List<Agent> getOperatorsAgents() {
		return operatorsAgents;
	}

	public void setOperatorsAgents(List<Agent> operatorsAgents) {
		this.operatorsAgents = operatorsAgents;
	}

	public List<Agent> getDirectorsAgents() {
		return directorsAgents;
	}

	public void setDirectorsAgents(List<Agent> directorsAgents) {
		this.directorsAgents = directorsAgents;
	}

	public List<Agent> getSupervisorsAgents() {
		return supervisorsAgents;
	}

	public void setSupervisorsAgents(List<Agent> supervisorsAgents) {
		this.supervisorsAgents = supervisorsAgents;
	}

	public int getCountBusyOperator() {
		return countBusyOperator;
	}

	public void setCountBusyOperator(int countBusyOperator) {
		this.countBusyOperator = countBusyOperator;
	}

	public int getCountBusySupervisor() {
		return countBusySupervisor;
	}

	public void setCountBusySupervisor(int countBusySupervisor) {
		this.countBusySupervisor = countBusySupervisor;
	}

	public int getCountBusyDirector() {
		return countBusyDirector;
	}

	public void setCountBusyDirector(int countBusyDirector) {
		this.countBusyDirector = countBusyDirector;
	}

	public void addCountBusyOperator() {
		this.countBusyOperator++;
	}
	public void removeCountBusyOperator() {
		this.countBusyOperator--;
	}
	
	public void addCountBusySupervisor() {
		this.countBusySupervisor++;
	}
	public void removeCountBusySupervisor() {
		this.countBusySupervisor--;
	}
	
	public void addCountBusyDirector() {
		this.countBusyDirector++;
	}
	public void removeCountBusyDirector() {
		this.countBusyDirector--;
	}
}
