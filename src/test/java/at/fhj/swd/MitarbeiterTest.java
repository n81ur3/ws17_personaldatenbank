package at.fhj.swd;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.AfterClass;

@org.junit.FixMethodOrder( org.junit.runners.MethodSorters.NAME_ASCENDING)
public class MitarbeiterTest {
    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;
    static final String persistenceUnitName = "personaldatenbank";
    static final int id = 158;
    static final int salary = 10000;
    static final int salaryRaise = 10;
    static final String name = "John";
    @BeforeClass public static void setup() {
        factory = Persistence.createEntityManagerFactory( persistenceUnitName );
        assertNotNull (factory);
        manager = factory.createEntityManager();
        assertNotNull (manager);
        transaction = manager.getTransaction();
    }
    @AfterClass public static void teardown() {
        if (manager == null) return;
        manager.close();
        factory.close();
    }
    @Test public void create () {
        transaction.begin ();
        Mitarbeiter john = new Mitarbeiter (id, name, salary);
        assertNotNull (john);
        manager.persist (john);
        transaction.commit();
    }
    @Test public void modify () {
        Mitarbeiter john = manager.find (Mitarbeiter.class, id);
        assertNotNull (john);
        transaction.begin ();
        john.raiseSalary (salaryRaise);
        transaction.commit();
        teardown ();
        setup ();
        john = manager.find (Mitarbeiter.class, id);
        assertEquals (salary + salaryRaise, (int) john.getSalary());
    }
    @Test public void remove () {
        Mitarbeiter john = manager.find (Mitarbeiter.class, id);
        assertNotNull (john);
        transaction.begin ();
        manager.remove ( john );
        transaction.commit();
        john = manager.find(Mitarbeiter.class, id);
        assertNull (john);
    }
}