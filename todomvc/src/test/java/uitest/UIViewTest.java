package uitest;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UIViewTest extends BaseTest{


    @Test
    public void test() {
        Assert.assertEquals("Hello", "Hello1");
    }


}
