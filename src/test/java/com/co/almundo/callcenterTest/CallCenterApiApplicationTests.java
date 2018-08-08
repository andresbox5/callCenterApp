package com.co.almundo.callcenterTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.co.almundo.callcenter.CallCenterApiApplication;

@RunWith(MockitoJUnitRunner.class)
public class CallCenterApiApplicationTests {
	
	@InjectMocks
	private CallCenterApiApplication callCenterApiApplicationTests;

	/**
	 * La presente prueba simula un call center compuesto por 10 empleados (5 Operarios, 3 Supervisores y 2 Directores), 
	 * con una capacidad de recibir 20 llamadas al mismo tiempo y atender 10 de estas de manera concurrente
	 */
	@Test
	public void executeCallCenter1Test() {
		int capacityCalls = 20;
		int numOperators = 5;
		int numSupervisors = 3;
		int numDirectors = 2;
		int quantityCalls = 20;
		
		String response = callCenterApiApplicationTests.executeCallCenter(capacityCalls, numOperators, numSupervisors, numDirectors, quantityCalls);
		assertThat(response).isEqualTo(new String("Llamadas atendidas correctamente"));
		assertThat(response).isNotNull();
	}

	/**
	 * La presente prueba simula un call center compuesto por 10 empleados (5 Operarios, 3 Supervisores y 2 Directores), 
	 * con una capacidad de recibir 50 llamadas al mismo tiempo y atender 10 de estas de manera concurrente
	 */
	@Test
	public void executeCallCenter2Test() {
		int capacityCalls = 20;
		int numOperators = 5;
		int numSupervisors = 3;
		int numDirectors = 2;
		int quantityCalls = 50;
		
		String response = callCenterApiApplicationTests.executeCallCenter(capacityCalls, numOperators, numSupervisors, numDirectors, quantityCalls);
		assertThat(response).isEqualTo(new String("Llamadas atendidas correctamente"));
		assertThat(response).isNotNull();
	}

}
