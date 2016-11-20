import DataTypes.*;
import Model.Expression.*;

/**
 * Created by vladc on 31.10.2016.
 */
public class ConstantExpressionTest {
    private ConstantExpression              e1;
    private ConstantExpression              e2;
    private ConstantExpression              e3;
    private MyIDictionary<String, Integer>  symTab;
    private MyIHeap                          heap;

    @org.junit.Before
    public void setUp() throws Exception {
        e1 = new ConstantExpression(1);
        e2 = new ConstantExpression(42);
        e3 = new ConstantExpression(-7);
        symTab = new MyDictionary<>();
        heap = new MyHeap();
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void evaluate() throws Exception {
        assert(e1.evaluate(symTab, heap) == 1);
        assert(e2.evaluate(symTab, heap) == 42);
        assert(e3.evaluate(symTab, heap) == -7);
    }
}
