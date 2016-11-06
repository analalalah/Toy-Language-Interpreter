package Tests;

import DataTypes.*;
import Model.Expression.*;
import Exception.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by vladc on 31.10.2016.
 */
public class ArithmeticExpressionTest {
    private MyIDictionary<String, Integer>  symTab;
    private ConstantExpression              ce;
    private VariableExpression              ve;
    private ArithmeticExpression            ae;

    @Before
    public void setUp() throws Exception {
        symTab = new MyDictionary<>();
        ce = new ConstantExpression(40);
        ve = new VariableExpression("n");
        symTab.put("n", 2);
        symTab.put("A", -1);
    }

    @After
    public void tearDown() throws Exception {
        symTab.remove("n");
        symTab.remove("A");
    }

    @Test
    public void evaluate() throws Exception {
        ae = new ArithmeticExpression(ve, ce, '+');
        assert(ae.evaluate(symTab) == 42);
        ae = new ArithmeticExpression(ce, ve, '-');
        assert(ae.evaluate(symTab) == 38);
        ae = new ArithmeticExpression(new ConstantExpression(21), ve, '*');
        assert(ae.evaluate(symTab) == 42);
        ae = new ArithmeticExpression(new VariableExpression("A"), new ConstantExpression(0), '/');
        try {
            ae.evaluate(symTab);
            assert(false);
        }
        catch (DivisionByZeroException e) {
            assert(true);
        }
    }

}