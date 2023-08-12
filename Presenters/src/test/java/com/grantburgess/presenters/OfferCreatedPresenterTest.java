package com.grantburgess.presenters;

import com.grantburgess.ports.presenters.TaskCreatedViewModel;
import com.grantburgess.ports.usescases.addTask.NewTaskResponse;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class OfferCreatedPresenterTest {
    @Test
    public void can_present_created_offer() {
        TaskCreatedPresenter presenter = new TaskCreatedPresenter();
        UUID id = UUID.randomUUID();
        // WHEN
        presenter.present(new NewTaskResponse(id));
        // THEN
        TaskCreatedViewModel viewModel = presenter.getViewModel();
        assertThat(viewModel, is(not(nullValue())));
        assertThat(id.toString(), equalTo(viewModel.getId()));
    }
}