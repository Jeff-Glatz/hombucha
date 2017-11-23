package ruffkat.hombucha.store;

import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import ruffkat.hombucha.runtime.StoreWiring;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Clock;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StoreWiring.class)
@Commit
@Transactional(readOnly = false)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
public abstract class IntegrationTest {

    @Autowired
    protected Clock clock;

    @PersistenceContext(name = "hombucha")
    protected EntityManager entityManager;

    @Autowired
    protected PlatformTransactionManager transactionManager;

    @After
    public void tearDown()
            throws Exception {
        entityManager.flush();
    }
}
