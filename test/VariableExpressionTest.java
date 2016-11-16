import Model.Expression.*;
import DataTypes.*;
import Exception.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by vladc on 31.10.2016.
 */
public class VariableExpressionTest {
    private VariableExpression e1;
    private VariableExpression e2;
    private MyIDictionary<String, Integer> symTab;
    @Before
    public void setUp() throws Exception {
        symTab = new MyDictionary<>();
        e1 = new VariableExpression("a");
        e2 = new VariableExpression("A");
        symTab.put("a", 42);
    }

    @After
    public void tearDown() throws Exception {
        symTab.remove("a");
    }

    @Test
    public void evaluate() throws Exception {
        assert(e1.evaluate(symTab) == 42);
        try {
            e2.evaluate(symTab);
            assert(false);
        }
        catch (UndeclaredVariableException e) {
            assert(true);
        }
        symTab.put("A", 9);
        assert(e2.evaluate(symTab) == 9);
        symTab.remove("A");
        try {
            e2.evaluate(symTab);
            assert(false);
        }
        catch (UndeclaredVariableException e) {
            assert(true);
        }
    }

}