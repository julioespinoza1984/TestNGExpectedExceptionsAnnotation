package edu.jespinoza.testng;

import edu.jespinoza.testng.impl.CalculatorImpl;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestNGExpectedExceptionsExample {
    private static final Logger logger = Logger.getLogger(TestNGExpectedExceptionsExample.class);

    private Calculator calculator;

    @BeforeClass
    public void setup() {
        logger.info("setup()");
        calculator = CalculatorImpl.getInstance();
    }

    @AfterClass
    public void tearDown() {
        logger.info("tearDown()");
        calculator = null;
    }

    @Test
    public void testAdd() {
        logger.info("testAdd()");
        Assert.assertEquals(calculator.add(3, 4), 7);
    }

    @Test
    public void testDivide() {
        logger.info("testDivide()");
        Assert.assertEquals(calculator.divide(16, 4), 4);
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void testDivideByZero() {
        logger.info("testDivideByZero() - Entrando");
        double d = calculator.divide(16, 0); // deberia lanzar la excepción esperada
        
        // Las siguientes sentencias NO se ejecutarán debido
        // a la excepción lanzada
        logger.info("testDivideByZero() - Después de lanzar la excepción");
        Assert.assertEquals(d, 12345.0);
    }
}
