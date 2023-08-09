package com.grantburgess.application.endpoints.getofferbyid;

import com.grantburgess.application.endpoints.BaseEndpoint;
import com.grantburgess.ports.presenters.OfferOutputBoundary;
import com.grantburgess.ports.presenters.OfferViewModel;
import com.grantburgess.ports.usescases.get.offerbyid.GetOfferByIdInputBoundary;
import com.grantburgess.ports.usescases.get.offerbyid.GetOfferRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/offers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GetOfferByIdEndpoint implements BaseEndpoint {
    private final GetOfferByIdInputBoundary useCase;
    private final OfferOutputBoundary presenter;

    public GetOfferByIdEndpoint(GetOfferByIdInputBoundary useCase, OfferOutputBoundary presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    @GetMapping("/{offerId}")
    @ApiOperation(value = "Get offer by ID", response = OfferViewModel.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity execute(@ApiParam(value = "ID of the offer that needs to be fetched", required = true) @PathVariable(value = "offerId") String offerId) {
        useCase.execute(GetOfferRequest.builder().id(UUID.fromString(offerId)).build());

        return ResponseEntity.ok(presenter.getViewModel());
    }
}
