package database;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;

public class DBControllerTest {

    private DBController dbController;
    private int authenticated = 0;

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
        assertEquals(1, authenticated);
        authenticated = dbController.authenticate("test1", "test1");
        assertEquals(2, authenticated);
        authenticated = dbController.authenticate("wrongUsername", "wrongPassword");
        Assert.assertNotSame(authenticated, 1);
        Assert.assertNotSame(authenticated, 2);
    }
}
