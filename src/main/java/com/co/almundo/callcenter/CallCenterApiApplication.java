package com.co.almundo.callcenter;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import com.co.almundo.callcenter.controller.Dispatcher;
import com.co.almundo.callcenter.controller.UnitTest;
import com.co.almundo.callcenter.model.Agent;
import com.co.almundo.callcenter.model.CallCenter;
import com.co.almundo.callcenter.model.DispatcherAgent;

@SpringBootApplication
@EnableAsync
public class CallCenterApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CallCenterApiApplication.class, args);
		CallCenterApiApplication callCenter = new CallCenterApiApplication();
		// Condiciones de entrada (capacidad de llamadas a encolar y equipo de trabajo
		// del call center)
		int capacityCalls = 20;
		int numOperators = 5;
		int numSupervisors = 3;
		int numDirectors = 2;
		int quantityCalls = 20;
		callCenter.executeCallCenter(capacityCalls, numOperators, numSupervisors, numDirectors, quantityCalls);
	}
	
	public String executeCallCenter(int capacityCalls, int numOperators, int numSupervisors, int numDirectors, int quantityCalls) 
	{
		
		// se define la capacidad de la cola de llamadas y se crean 3 colas correspondientes a operadores, supervisores y directores
		Dispatcher dispatcher = new Dispatcher(capacityCalls, numOperators, numSupervisors, numDirectors);
		
		// Se crean los diferentes hilos que representan los operadores, supervisores y directores que atienden las llamadas,
		// adicionalmente se le asigna el objeto dispatcher encargado de la asignación de las llamadas
		CallCenter callCenter = new CallCenter(numOperators, numSupervisors, numDirectors, dispatcher);
		dispatcher.setCallCenter(callCenter);

		// Se ejecutan todos los agentes (Hilos que van a atender las llamadas) 
        List<DispatcherAgent> dispatcherAgents = dispatcher.getDispatcherAgent(); // Responsable del direccionamiento de las llamadas
        List<Agent> operatorsAgents = callCenter.getOperatorsAgents();
        List<Agent> supervisorsAgents = callCenter.getSupervisorsAgents();
        List<Agent> directorsAgents = callCenter.getDirectorsAgents();
        ExecutorService executorService = Executors.newFixedThreadPool((2*(numOperators + numDirectors + numSupervisors)) + 1);
        dispatcherAgents.forEach(executorService::submit); // Encargados de asignar las llamadas
        operatorsAgents.forEach(executorService::submit); // Empleados operadores que atienden las llamadas
        supervisorsAgents.forEach(executorService::submit); // Empleados supervisores que atienden las llamadas
        directorsAgents.forEach(executorService::submit); // Empleados directores que atienden las llamadas
        
        // Se ejecuta la prueba con n llamadas concurrentes
        UnitTest unitTest = new UnitTest(dispatcher, quantityCalls);
        executorService.submit(unitTest); // Encargado de lanzar las llamadas ejemplo
        return "Llamadas atendidas correctamente";
	}
}
