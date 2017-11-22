package ruffkat.hombucha.store;


import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.time.TimeSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:store-context.xml")
@Transactional(readOnly = false)
@Commit
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
public abstract class FunctionalTest {

    @Autowired
    protected TimeSource timeSource;

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
