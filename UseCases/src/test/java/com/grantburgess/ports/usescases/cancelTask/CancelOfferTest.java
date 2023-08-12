package com.grantburgess.ports.usescases.cancelTask;

import com.grantburgess.database.inmemory.InMemoryDatabase;
import com.grantburgess.entities.Task;
import com.grantburgess.ports.database.Database;
import com.grantburgess.ports.database.TaskGateway;
import com.grantburgess.usecases.cancelTask.CancelTask;
import com.grantburgess.usecases.testdoubles.ClockStub;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class CancelOfferTest {
    private static final LocalDate OFFER_START_DATE = LocalDate.of(2018, 1, 1);
    private static final LocalDate OFFER_END_DATE = LocalDate.of(2018, 1, 31);
    private static final LocalDate CURRENT_DATE = OFFER_START_DATE.plusDays(14);

    private Task offer;
    private Database database;
    private CancelTask useCase;

    @Before
    public void setUp() {
        database = new InMemoryDatabase();
        useCase = new CancelTask(database.TaskGateway(), new ClockStub(CURRENT_DATE));
        offer = new Task(
                "name",
                "description",
                OFFER_START_DATE,
                OFFER_END_DATE

        );
    }

    @Test(expected = TaskGateway.taskNotFoundException.class)
    public void cannot_cancel_offer_that_does_not_exist() {
        useCase.execute(new CancelTaskRequest(UUID.randomUUID()));
    }

    @Test(expected = TaskGateway.taskNotFoundException.class)
    public void cannot_cancel_offer_that_is_already_cancelled() {
        UUID offerId = database.TaskGateway().add(offer);
        Task offer2 = database.TaskGateway().getByIdExcludingCancelled(offerId);

        database.TaskGateway().update(offer2);

        useCase.execute(new CancelTaskRequest(UUID.randomUUID()));
    }

    @Test(expected = TaskGateway.CannotCanceltaskThatHasExpiredException.class)
    public void cannot_cancel_offer_after_it_has_expired() {
        // GIVEN
        UUID offerId = database.TaskGateway().add(offer);
        LocalDate currentClockDate = OFFER_END_DATE.plusDays(1);
        useCase = new CancelTask(database.TaskGateway(), new ClockStub(currentClockDate));

        // WHEN
        useCase.execute(new CancelTaskRequest(offerId));
    }

    @Test
    public void can_cancel_offer() {
        // GIVEN
        UUID offerId = database.TaskGateway().add(offer);

        // WHEN
        useCase.execute(new CancelTaskRequest(offerId));

        // THEN
        Task cancelledOffer = database.TaskGateway().getByIdExcludingCancelled(offerId);
        assertThat(cancelledOffer, is(nullValue()));
    }
}