package com.grantburgess.application.endpoints.getoffers;

import com.grantburgess.application.endpoints.BaseEndpoint;
import com.grantburgess.ports.presenters.OffersOutputBoundary;
import com.grantburgess.ports.presenters.OffersViewModel;
import com.grantburgess.ports.usescases.get.offers.GetOfferInputBoundary;
import com.grantburgess.ports.usescases.get.offers.GetOffersRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/offers")
public class GetOffersEndpoint implements BaseEndpoint {
    private final GetOfferInputBoundary useCase;
    private final OffersOutputBoundary presenter;

    public GetOffersEndpoint(GetOfferInputBoundary useCase, OffersOutputBoundary presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    @GetMapping
    @ApiOperation(value = "Get offers", response = OffersViewModel.class)
    public ResponseEntity execute() {
        useCase.execute(new GetOffersRequest());

        return ResponseEntity.ok(presenter.getViewModel());
    }
}
