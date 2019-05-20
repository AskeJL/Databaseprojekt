package database;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

public class DBControllerTest {
    
    DBController dbController;
    int authenticated = 0;
    
    public DBControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        dbController = new DBController();
    }
    
    @After
    public void tearDown() {
    }

    //Det antages at man har test-brugere i sin database. Disse bruges herunder
    /**
     * Test of authenticate method, of class DBController.
     */
    @org.junit.Test
    public void testAuthenticate() {
        authenticated = dbController.authenticate("sosu_test", "sosutest");
        Assert.assertEquals(authenticated,1);
        authenticated = dbController.authenticate("test1", "test1");
        Assert.assertEquals(authenticated,2);
        authenticated = dbController.authenticate("wrongUsername", "wrongPassword");
        Assert.assertNotSame(authenticated, 1);
        Assert.assertNotSame(authenticated, 2);
    }
}
   