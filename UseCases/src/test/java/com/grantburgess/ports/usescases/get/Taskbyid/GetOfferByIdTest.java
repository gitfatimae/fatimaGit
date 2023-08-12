package com.grantburgess.ports.usescases.get.Taskbyid;

import com.grantburgess.database.inmemory.InMemoryDatabase;
import com.grantburgess.entities.Task;
import com.grantburgess.ports.database.Database;
import com.grantburgess.ports.database.TaskGateway;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.get.TaskResponse;
import com.grantburgess.usecases.get.offerbyid.GetTaskById;
import com.grantburgess.usecases.testdoubles.ClockStub;
import com.grantburgess.usecases.testdoubles.OfferPresenterSpy;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;

public class GetOfferByIdTest {
    private static final String OFFER_NAME = "offer-1";
    private static final String OFFER_DESCRIPTION = "description";
    private static final LocalDate OFFER_START_DATE = LocalDate.of(2018, 01, 01);
    private static final LocalDate OFFER_END_DATE = LocalDate.of(2018, 01, 31);
    private static final BigDecimal OFFER_PRICE_AMOUNT = BigDecimal.TEN;
    private static final String OFFER_PRICE_CURRENCY = "GBP";

    private static final LocalDate CURRENT_DATE = LocalDate.of(2018, 01, 30);

    private Database database;
    private GetTaskById useCase;
    private OfferPresenterSpy presenterSpy;

    private void assertOffer(
            UUID id,
            LocalDate endDate,
            TaskResponse.Status status) {
        assertThat(presenterSpy.getResponseModel(),
                both(hasProperty("id", equalTo(id)))
                        .and(hasProperty("name", equalTo(GetOfferByIdTest.OFFER_NAME)))
                        .and(hasProperty("description", equalTo(GetOfferByIdTest.OFFER_DESCRIPTION)))
                        .and(hasProperty("startDate", equalTo(GetOfferByIdTest.OFFER_START_DATE)))
                        .and(hasProperty("endDate", equalTo(endDate)))
                        .and(hasProperty("currency", equalTo(GetOfferByIdTest.OFFER_PRICE_CURRENCY)))
                        .and(hasProperty("amount", is(closeTo(GetOfferByIdTest.OFFER_PRICE_AMOUNT, BigDecimal.ZERO))))
                        .and(hasProperty("status", equalTo(status)))
        );
        assertThat(presenterSpy.isOfferPresented(), is(true));
    }

    @Before
    public void setUp() {
        database = new InMemoryDatabase();
        Clock clock = new ClockStub(CURRENT_DATE);
        presenterSpy = new OfferPresenterSpy();
        useCase = new GetTaskById(presenterSpy, database.TaskGateway(), clock);
    }

    @Test(expected = TaskGateway.taskNotFoundException.class)
    public void cannot_get_offer_that_does_not_exist() {
        useCase.execute(GetTaskRequest.builder().id(UUID.randomUUID()).build());
    }

    @Test
    public void can_get_expired_offer_by_id() {
        // GIVEN
        LocalDate expiredEndDate = OFFER_END_DATE.minusDays(2);
        UUID offerId = database.TaskGateway().add(new Task(OFFER_NAME, OFFER_DESCRIPTION, OFFER_START_DATE, expiredEndDate));

        // WHEN
        useCase.execute(GetTaskRequest.builder().id(offerId).build());

        // THEN
        assertThat(presenterSpy.getResponseModel(), is(not(nullValue())));
        assertOffer(offerId, expiredEndDate, TaskResponse.Status.EXPIRED);
    }

    @Test(expected = TaskGateway.taskNotFoundException.class)
    public void cannot_get_cancelled_offer() {
        // GIVEN
        UUID offerId = database.TaskGateway().add(new Task(OFFER_NAME, OFFER_DESCRIPTION, OFFER_START_DATE, OFFER_END_DATE));
        Task offer = database.TaskGateway().getByIdExcludingCancelled(offerId);

        database.TaskGateway().update(offer);

        // WHEN
        useCase.execute(GetTaskRequest.builder().id(offerId).build());
    }

    @Test
    public void can_get_offer_by_id() {
        // GIVEN
        UUID offerId = database.TaskGateway().add(new Task(OFFER_NAME, OFFER_DESCRIPTION, OFFER_START_DATE, OFFER_END_DATE));

        // WHEN
        useCase.execute(GetTaskRequest.builder().id(offerId).build());

        // THEN
        assertThat(presenterSpy.getResponseModel(), is(not(nullValue())));
        assertOffer(offerId, OFFER_END_DATE, TaskResponse.Status.ACTIVE);
    }
}