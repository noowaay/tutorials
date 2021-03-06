package com.baeldung.spring.statemachine;

import com.baeldung.spring.statemachine.applicationreview.ApplicationReviewEvents;
import com.baeldung.spring.statemachine.applicationreview.ApplicationReviewStates;
import com.baeldung.spring.statemachine.config.SimpleEnumStateMachineConfiguration;
import com.baeldung.spring.statemachine.config.SimpleStateMachineConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SimpleEnumStateMachineConfiguration.class)
public class StateEnumMachineTest {

    @Resource
    private StateMachine stateMachine;

    @Before
    public void setUp() {
        stateMachine.start();
    }

    @Test
    public void whenStateMachineConfiguredWithEnums_thenStateMachineAcceptsEnumEvents() {
        assertTrue(stateMachine.sendEvent(ApplicationReviewEvents.APPROVE));
        assertEquals(ApplicationReviewStates.PRINCIPAL_REVIEW, stateMachine.getState().getId());
        assertTrue(stateMachine.sendEvent(ApplicationReviewEvents.REJECT));
        assertEquals(ApplicationReviewStates.REJECTED, stateMachine.getState().getId());
    }

    @After
    public void tearDown() {
        stateMachine.stop();
    }
}
