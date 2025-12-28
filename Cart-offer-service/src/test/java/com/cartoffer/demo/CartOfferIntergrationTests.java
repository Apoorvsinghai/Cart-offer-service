package com.cartoffer.demo;

import com.cartoffer.demo.controller.ApiResponse;
import com.cartoffer.demo.controller.ApplyOfferRequest;
import com.cartoffer.demo.controller.ApplyOfferResponse;
import com.cartoffer.demo.controller.OfferRequest;
import com.cartoffer.demo.testdata.TestDataFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CartOfferIntergrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    //FLAT 10 off for a user in restaurant 2 under p2 segment
    @Test
    void shouldApplyFlatAmountOfferForP2User() {
        ApplyOfferRequest request = TestDataFactory.applyFlatOfferRequest();
        OfferRequest offerRequest = TestDataFactory.validFlatOfferRequest();

        ResponseEntity<ApiResponse> offerStatus =
                restTemplate.postForEntity(
                        "/api/v1/offer",
                        offerRequest,
                        ApiResponse.class
                );

        assertThat(offerStatus.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(offerStatus.getBody().getResponse_msg()).isEqualTo("success");

        ResponseEntity<ApplyOfferResponse> response =
                restTemplate.postForEntity(
                        "/api/v1/cart/apply_offer",
                        request,
                        ApplyOfferResponse.class
                );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertNotNull(response.getBody());
        assertThat(response.getBody().getCart_value()).isEqualTo(90);
    }

    //PERCENT 20 off for a user in restaurant 1 under p1 segment
    @Test
    void shouldApplyPercentAmountOfferForP1User() {
        ApplyOfferRequest request = TestDataFactory.applyFlatOfferRequestForP1();
        OfferRequest offerRequest = TestDataFactory.validPercentOfferRequestForP1();

        ResponseEntity<ApiResponse> offerStatus =
                restTemplate.postForEntity(
                        "/api/v1/offer",
                        offerRequest,
                        ApiResponse.class
                );

        assertThat(offerStatus.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(offerStatus.getBody().getResponse_msg()).isEqualTo("success");

        ResponseEntity<ApplyOfferResponse> response =
                restTemplate.postForEntity(
                        "/api/v1/cart/apply_offer",
                        request,
                        ApplyOfferResponse.class
                );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertNotNull(response.getBody());
        assertThat(response.getBody().getCart_value()).isEqualTo(160);
    }

    //Cart value is 0 and no discount should be applied
    @Test
    void shouldNotReduceCartValueLessThan0() {
        ApplyOfferRequest request = TestDataFactory.cartValue0ApplyOfferRequest();
        OfferRequest offerRequest = TestDataFactory.cartValue0OfferRequest();

        ResponseEntity<ApiResponse> offerStatus =
                restTemplate.postForEntity(
                        "/api/v1/offer",
                        offerRequest,
                        ApiResponse.class
                );

        assertThat(offerStatus.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(offerStatus.getBody().getResponse_msg()).isEqualTo("success");

        ResponseEntity<ApplyOfferResponse> response =
                restTemplate.postForEntity(
                        "/api/v1/cart/apply_offer",
                        request,
                        ApplyOfferResponse.class
                );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertNotNull(response.getBody());
        assertThat(response.getBody().getCart_value()).isEqualTo(0);
    }

    //offer if greater than the cart value then cart total after discount should be 0
    @Test
    void shouldNotReduceCartTotalLessThan0AfterOfferCalculation() {
        ApplyOfferRequest request = TestDataFactory.offerGreaterThanCartTotalApplyOfferRequest();
        OfferRequest offerRequest = TestDataFactory.offerGreaterThanCartTotalOfferRequest();

        ResponseEntity<ApiResponse> offerStatus =
                restTemplate.postForEntity(
                        "/api/v1/offer",
                        offerRequest,
                        ApiResponse.class
                );

        assertThat(offerStatus.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(offerStatus.getBody().getResponse_msg()).isEqualTo("success");

        ResponseEntity<ApplyOfferResponse> response =
                restTemplate.postForEntity(
                        "/api/v1/cart/apply_offer",
                        request,
                        ApplyOfferResponse.class
                );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertNotNull(response.getBody());
        assertThat(response.getBody().getCart_value()).isEqualTo(0);
    }

    //User belongs to incorrect segment should not be eligible for any offers and cart value should remain unchanged
    @Test
    void shouldNotApplyOfferForInvalidSegment() {
        ApplyOfferRequest request = TestDataFactory.invalidSegmentApplyOfferRequest();
        OfferRequest offerRequest = TestDataFactory.invalidSegmentOfferRequest();

        ResponseEntity<ApiResponse> offerStatus =
                restTemplate.postForEntity(
                        "/api/v1/offer",
                        offerRequest,
                        ApiResponse.class
                );

        assertThat(offerStatus.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(offerStatus.getBody().getResponse_msg()).isEqualTo("success");

        ResponseEntity<ApplyOfferResponse> response =
                restTemplate.postForEntity(
                        "/api/v1/cart/apply_offer",
                        request,
                        ApplyOfferResponse.class
                );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertNotNull(response.getBody());
        assertThat(response.getBody().getCart_value()).isEqualTo(200);
    }

    //Error response from User segment API should be handled gracefully, and the cart value should remain same
    @Test
    void shouldHandleErrorResponseGracefully() {
        ApplyOfferRequest request = TestDataFactory.errorResponseApplyOfferRequest();
        OfferRequest offerRequest = TestDataFactory.errorResponseOfferRequest();

        ResponseEntity<ApiResponse> offerStatus =
                restTemplate.postForEntity(
                        "/api/v1/offer",
                        offerRequest,
                        ApiResponse.class
                );

        assertThat(offerStatus.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(offerStatus.getBody().getResponse_msg()).isEqualTo("success");

        ResponseEntity<ApplyOfferResponse> response =
                restTemplate.postForEntity(
                        "/api/v1/cart/apply_offer",
                        request,
                        ApplyOfferResponse.class
                );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.valueOf(200));
        Assertions.assertNotNull(response.getBody());
        assertThat(response.getBody().getCart_value()).isEqualTo(100);
    }

    //Offer should not be applicable to users with different restaurant id under same segment
    @Test
    void shouldNotApplyOfferInCaseRestaurantIdIsDifferent() {
        ApplyOfferRequest request = TestDataFactory.differentRestaurantIdSameSegmentApplyOfferRequest();
        OfferRequest offerRequest = TestDataFactory.differentRestaurantIdSameSegmentOfferRequest();

        ResponseEntity<ApiResponse> offerStatus =
                restTemplate.postForEntity(
                        "/api/v1/offer",
                        offerRequest,
                        ApiResponse.class
                );

        assertThat(offerStatus.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(offerStatus.getBody().getResponse_msg()).isEqualTo("success");

        ResponseEntity<ApplyOfferResponse> response =
                restTemplate.postForEntity(
                        "/api/v1/cart/apply_offer",
                        request,
                        ApplyOfferResponse.class
                );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertNotNull(response.getBody());
        assertThat(response.getBody().getCart_value()).isEqualTo(100);
    }

    //Negative values in offer shouldn't be allowed
    @Test
    void shouldNotAcceptNegativeOfferValue() {
        OfferRequest offerRequest = TestDataFactory.negativeOfferValueOfferRequest();

        ResponseEntity<ApiResponse> offerStatus =
                restTemplate.postForEntity(
                        "/api/v1/offer",
                        offerRequest,
                        ApiResponse.class
                );

        assertThat(offerStatus.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(offerStatus.getBody().getResponse_msg()).isEqualTo("INVALID_OFFER_VALUE");
    }
}