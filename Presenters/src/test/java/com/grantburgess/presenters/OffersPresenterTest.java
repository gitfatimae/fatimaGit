package com.grantburgess.presenters;

import com.grantburgess.ports.presenters.TasksViewModel;
import com.grantburgess.ports.usescases.get.TaskResponse;
import com.grantburgess.ports.usescases.get.Tasks.TasksResponse;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;

public class OffersPresenterTest {
    private static final String DESCRIPTION = "description";
    private static final LocalDate START_DATE = LocalDate.of(2018, 01, 01);
    private static final LocalDate END_DATE = LocalDate.of(2018, 01, 31);
    private static final String CURRENCY = "GBP";
    private static final BigDecimal AMOUNT = BigDecimal.TEN;
    private static final TaskResponse.Status STATUS = TaskResponse.Status.ACTIVE;

    private void assertOfferViewModel(TasksViewModel viewModel, String id, String name, String description, String startDate, String endDate, String currency, String price, String status) {
        assertThat(viewModel.getOffers(),
                hasItem(both(hasProperty("id", equalTo(id)))
                        .and(hasProperty("name", equalTo(name)))
                        .and(hasProperty("description", equalTo(description)))
                        .and(hasProperty("duration", hasProperty("startDate", equalTo(startDate))))
                        .and(hasProperty("duration", hasProperty("endDate", equalTo(endDate))))
                        .and(hasProperty("currency", equalTo(currency)))
                        .and(hasProperty("price", equalTo(price)))
                        .and(hasProperty("status", equalTo(status)))
                )
        );
    }

    @Test
    public void can_present_offers() {
        // GIVEN
        TasksPresenter presenter = new TasksPresenter();
        UUID offerId1 = UUID.randomUUID();
        String offerName1 = "name-1";
        UUID offerId2 = UUID.randomUUID();
        String offerName2 = "name-2";
        TasksResponse responseModel = TasksResponse
                .builder()
                .addOffer(new TaskResponse(offerId1, offerName1, DESCRIPTION, START_DATE, END_DATE, CURRENCY, AMOUNT, STATUS))
                .addOffer(new TaskResponse(offerId2, offerName2, "", START_DATE.plusDays(1), END_DATE, CURRENCY, BigDecimal.ONE, TaskResponse.Status.EXPIRED))
                .build();
        // WHEN
        presenter.present(responseModel);

        // THEN
        TasksViewModel viewModel = presenter.getViewModel();
        assertThat(viewModel, is(not(nullValue())));
        assertThat(viewModel.getOffers(), hasSize(2));
        assertOfferViewModel(viewModel, offerId1.toString(), offerName1, DESCRIPTION, "2018-01-01", "2018-01-31", "GBP", "10.00", STATUS.name());
        assertOfferViewModel(viewModel, offerId2.toString(), offerName2, "", "2018-01-02", "2018-01-31", "GBP", "1.00", "EXPIRED");
    }
}