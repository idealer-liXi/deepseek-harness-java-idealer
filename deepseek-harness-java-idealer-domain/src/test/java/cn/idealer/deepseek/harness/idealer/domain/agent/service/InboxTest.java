package cn.idealer.deepseek.harness.idealer.domain.agent.service;

import cn.idealer.deepseek.harness.idealer.domain.agent.model.InboxTarget;
import cn.idealer.deepseek.harness.idealer.types.model.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InboxTest {
    @Test
    void claimsOnlyTheSelectedBoundary() {
        Inbox inbox = new Inbox();
        inbox.append(InboxTarget.NEXT_TURN, Message.user("first turn"));
        inbox.append(InboxTarget.NEXT_STEP, Message.user("steering"));

        assertEquals(1, inbox.claim(InboxTarget.NEXT_TURN).size());
        assertTrue(inbox.hasPending());
        assertEquals(1, inbox.claim(InboxTarget.NEXT_STEP).size());
        assertFalse(inbox.hasPending());
    }
}

