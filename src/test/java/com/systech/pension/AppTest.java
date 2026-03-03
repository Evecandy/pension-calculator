package com.systech.pension;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testEmployeeContribution() {
        assertEquals(3000.0, App.calculateEmployeeContribution(50000), 0.01);
    }

    @Test
    public void testEmployerContribution() {
        assertEquals(3000.0, App.calculateEmployerContribution(50000), 0.01);
    }

    @Test
    public void testTotalContribution() {
        assertEquals(6000.0, App.calculateTotalContribution(50000), 0.01);
    }
}