package domain;

import com.sun.deploy.security.CredentialInfo;
import domain.users.Citizen;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.UUID;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.postgresql.core.Oid;

public class ControllerTest {

    Controller controller;
    Citizen citizen;

    public ControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        controller = new Controller();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void setCurrentCitizenTest() {
        citizen = new Citizen("test1", "test1", "12345", Date.valueOf("2019-05-10"), UUID.fromString("50586125-bfc9-4e3d-920c-95fa7bc433e5"), new Schedule());
        controller.setCurrentCitizen(citizen.getId(), citizen.getUsername());
        Assert.assertEquals(citizen.getUsername(), controller.getCurrentCitizen().getUsername());
        Assert.assertEquals(citizen.getBirthday(), controller.getCurrentCitizen().getBirthday());
        Assert.assertEquals(citizen.getId(), controller.getCurrentCitizen().getId());
        Assert.assertEquals(citizen.getName(), controller.getCurrentCitizen().getName());
    }
}
