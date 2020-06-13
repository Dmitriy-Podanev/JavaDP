package Test;

import com.company.DataProvider;
import com.company.DataServise;
import com.company.ObjectContext;
import com.company.WebProvider;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class TestTest {
    @Test
    public void testObjectContext() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ObjectContext objectContext = new ObjectContext("com.company");
    }

    @Test
    public void testFailObjectContext() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            ObjectContext objectContext = new ObjectContext("null");
            ;
        });
    }

    @Test
    public void testGetComponent() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ObjectContext objectContext = new ObjectContext("com.company");
        DataServise dataServise = objectContext.GetComponent(DataServise.class);
        Assert.assertNotNull(dataServise);
        DataProvider dataProvider = objectContext.GetComponent(DataProvider.class);
        WebProvider webProvider = objectContext.GetComponent(WebProvider.class);


    }

    @Test
    public void testFailGetComponent() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ObjectContext objectContext = new ObjectContext("com.company");

        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            DataServise dataServise = objectContext.GetComponent(null);
        });
    }
    @Test
    public void testDataServiceProcessData() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ObjectContext objectContext = new ObjectContext("com.company");
        objectContext.Init();
        DataServise dataServise = objectContext.GetComponent(DataServise.class);
        Assert.assertEquals("SAMPLE DATA TEST",dataServise.ProcessData("TEST"));
    }


}