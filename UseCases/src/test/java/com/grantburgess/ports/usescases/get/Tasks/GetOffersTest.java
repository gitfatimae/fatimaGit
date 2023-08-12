package com.grantburgess.ports.usescases.get.Tasks;

import com.grantburgess.database.inmemory.InMemoryDatabase;
import com.grantburgess.entities.Task;
import com.grantburgess.ports.database.Database;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.get.TaskResponse;
import com.grantburgess.usecases.get.offers.GetTasks;
import com.grantburgess.usecases.testdoubles.ClockStub;
import com.grantburgess.usecases.testdoubles.OffersPresenterSpy;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;

public class GetOffersTest {
    private static final String OFFER_DESCRIPTION = "description";
    private static final LocalDate OFFER_START_DATE = LocalDate.of(2018, 01, 01);
    private static final LocalDate OFFER_END_DATE = LocalDate.of(2018, 01, 31);
    private static final BigDecimal OFFER_PRICE_AMOUNT = BigDecimal.TEN;
    private static final String OFFER_PRICE_CURRENCY = "GBP";
    private static final TaskResponse.Status OFFER_STATUS = TaskResponse.Status.ACTIVE;
    private static final LocalDate CURRENT_DATE = LocalDate.of(2018, 01, 30);

    private Database database;
    private GetTasks useCase;
    private OffersPresenterSpy presenterSpy;

    private void assertOffer(
            UUID id,
            String name,
            LocalDate endDate,
            TaskResponse.Status status) {
        assertThat(presenterSpy.getResponseModel().getTasks(),
                hasItem(both(hasProperty("id", equalTo(id)))
                        .and(hasProperty("name", equalTo(name)))
                        .and(hasProperty("description", equalTo(GetOffersTest.OFFER_DESCRIPTION)))
                        .and(hasProperty("startDate", equalTo(GetOffersTest.OFFER_START_DATE)))
                        .and(hasProperty("endDate", equalTo(endDate)))
                        .and(hasProperty("currency", equalTo(GetOffersTest.OFFER_PRICE_CURRENCY)))
                        .and(hasProperty("amount", is(closeTo(GetOffersTest.OFFER_PRICE_AMOUNT, BigDecimal.ZERO))))
                        .and(hasProperty("status", equalTo(status)))
                )
        );
    }
    private void assertOfferPresented(int offerSize) {
        assertThat(presenterSpy.getResponseModel(), is(not(nullValue())));
        assertThat(presenterSpy.getResponseModel().getTasks(), hasSize(offerSize));
        assertThat(presenterSpy.isOffersPresented(), is(true));
    }

    private UUID addOffer(String name, LocalDate endDate) {
        return database.TaskGateway().add(new Task(name, GetOffersTest.OFFER_DESCRIPTION, GetOffersTest.OFFER_START_DATE, endDate));
    }

    @Before
    public void setUp() {
        database = new InMemoryDatabase();
        Clock clock = new ClockStub(CURRENT_DATE);
        presenterSpy = new OffersPresenterSpy();
        useCase = new GetTasks(presenterSpy, database.TaskGateway(), clock);
    }

    @Test
    public void no_offers_returns_empty_response_list() {
        // WHEN
        useCase.execute(new GetTaskRequest());
        // THEN
        assertOfferPresented(0);
    }

    @Test
    public void can_get_offers() {
        // GIVEN
        UUID offer1Id = addOffer("offer-1", OFFER_END_DATE);
        UUID offer2Id = addOffer("offer-2", OFFER_END_DATE);
        // WHEN
        useCase.execute(new GetTaskRequest());
        // THEN
        assertOfferPresented(2);
        assertOffer(offer1Id, "offer-1", OFFER_END_DATE, OFFER_STATUS);
        assertOffer(offer2Id, "offer-2", OFFER_END_DATE, OFFER_STATUS);
    }

    @Test
    public void expired_offers_have_the_status_expired() {
        // GIVEN
        LocalDate expiredOfferEndDate = OFFER_END_DATE.minusDays(2);
        UUID offer1Id = addOffer("offer-1", OFFER_END_DATE);
        UUID offer2Id = addOffer("offer-2", expiredOfferEndDate);
        // WHEN
        useCase.execute(new GetTaskRequest());
        // THEN
        assertOfferPresented(2);
        assertOffer(offer1Id, "offer-1", OFFER_END_DATE, OFFER_STATUS);
        assertOffer(offer2Id, "offer-2", expiredOfferEndDate, TaskResponse.Status.EXPIRED);
    }

    @Test
    public void cancelled_offers_do_not_appear_in_list() {
        // GIVEN
        UUID offer1Id = addOffer("offer-1", OFFER_END_DATE);
        UUID offer2Id = addOffer("offer-2", OFFER_END_DATE);
        Task offer2 = database.TaskGateway().getByIdExcludingCancelled(offer2Id);
        database.TaskGateway().update(offer2);
        // WHEN
        useCase.execute(new GetTaskRequest());
        // THEN
        assertOfferPresented(1);
        assertOffer(offer1Id, "offer-1", OFFER_END_DATE, OFFER_STATUS);
    }
}