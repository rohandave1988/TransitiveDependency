package com.dependencyanalysis;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Unit tests for Transitive Dependency Finder.
 */

public class DependencyCalculatorTest {
	@Test
	public void shouldResolveDependenciesIfOnlyOneDirectDependencySpecified() throws CyclicDependencyException {
		DependencyCalculator dependencyCalculator = new DependencyCalculator();
		assertEquals(true, dependencyCalculator.checkDependency("A B C"));
	}

	@Test
	public void shouldResolveDependenciesMultipleDependencySpecified() throws CyclicDependencyException {
		DependencyCalculator dependencyCalculator = new DependencyCalculator();
		assertEquals(true, dependencyCalculator.checkDependency("A B C"));
		assertEquals(true, dependencyCalculator.checkDependency("A B C"));
	}

	@Test
	public void testExpectedException() {
		DependencyCalculator dependencyCalculator = new DependencyCalculator();
		try {
			assertEquals(true, dependencyCalculator.checkDependency("A B C"));
		} catch (CyclicDependencyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assertions.assertThrows(CyclicDependencyException.class, () -> {
			dependencyCalculator.checkDependency("B A");
		});

	}
}
