package com.cartoffer.demo.testdata;

import com.cartoffer.demo.controller.ApplyOfferRequest;
import com.cartoffer.demo.controller.OfferRequest;

import java.util.List;

public final class TestDataFactory {

    private TestDataFactory() {}

    public static OfferRequest validFlatOfferRequest() {
        OfferRequest offer = new OfferRequest();
        offer.setRestaurant_id(2);
        offer.setOffer_type("FLATX");
        offer.setOffer_value(10);
        offer.setCustomer_segment(List.of("p2"));
        return offer;
    }

    public static ApplyOfferRequest applyFlatOfferRequest() {
        ApplyOfferRequest applyOffer = new ApplyOfferRequest();
        applyOffer.setRestaurant_id(2);
        applyOffer.setUser_id(2);
        applyOffer.setCart_value(100);
        return applyOffer;
    }

    public static OfferRequest validPercentOfferRequestForP1() {
        OfferRequest offer = new OfferRequest();
        offer.setRestaurant_id(1);
        offer.setOffer_type("PERCENTX");
        offer.setOffer_value(20);
        offer.setCustomer_segment(List.of("p1"));
        return offer;
    }

    public static ApplyOfferRequest applyFlatOfferRequestForP1() {
        ApplyOfferRequest applyOffer = new ApplyOfferRequest();
        applyOffer.setRestaurant_id(1);
        applyOffer.setUser_id(1);
        applyOffer.setCart_value(200);
        return applyOffer;
    }

    public static OfferRequest cartValue0OfferRequest() {
        OfferRequest offer = new OfferRequest();
        offer.setRestaurant_id(3);
        offer.setOffer_type("FLATX");
        offer.setOffer_value(10);
        offer.setCustomer_segment(List.of("p1"));
        return offer;
    }

    public static ApplyOfferRequest cartValue0ApplyOfferRequest() {
        ApplyOfferRequest applyOffer = new ApplyOfferRequest();
        applyOffer.setRestaurant_id(3);
        applyOffer.setUser_id(1);
        applyOffer.setCart_value(0);
        return applyOffer;
    }

    public static OfferRequest offerGreaterThanCartTotalOfferRequest() {
        OfferRequest offer = new OfferRequest();
        offer.setRestaurant_id(4);
        offer.setOffer_type("FLATX");
        offer.setOffer_value(300);
        offer.setCustomer_segment(List.of("p1"));
        return offer;
    }

    public static ApplyOfferRequest offerGreaterThanCartTotalApplyOfferRequest() {
        ApplyOfferRequest applyOffer = new ApplyOfferRequest();
        applyOffer.setRestaurant_id(4);
        applyOffer.setUser_id(1);
        applyOffer.setCart_value(200);
        return applyOffer;
    }

    public static OfferRequest invalidSegmentOfferRequest() {
        OfferRequest offer = new OfferRequest();
        offer.setRestaurant_id(1);
        offer.setOffer_type("PERCENTX");
        offer.setOffer_value(50);
        offer.setCustomer_segment(List.of("p4"));
        return offer;
    }

    public static ApplyOfferRequest invalidSegmentApplyOfferRequest() {
        ApplyOfferRequest applyOffer = new ApplyOfferRequest();
        applyOffer.setRestaurant_id(1);
        applyOffer.setUser_id(4);
        applyOffer.setCart_value(200);
        return applyOffer;
    }

    public static OfferRequest errorResponseOfferRequest() {
        OfferRequest offer = new OfferRequest();
        offer.setRestaurant_id(1);
        offer.setOffer_type("FLATX");
        offer.setOffer_value(50);
        offer.setCustomer_segment(List.of("p5"));
        return offer;
    }

    public static ApplyOfferRequest errorResponseApplyOfferRequest() {
        ApplyOfferRequest applyOffer = new ApplyOfferRequest();
        applyOffer.setRestaurant_id(1);
        applyOffer.setUser_id(6);
        applyOffer.setCart_value(100);
        return applyOffer;
    }

    public static OfferRequest differentRestaurantIdSameSegmentOfferRequest() {
        OfferRequest offer = new OfferRequest();
        offer.setRestaurant_id(10);
        offer.setOffer_type("FLATX");
        offer.setOffer_value(50);
        offer.setCustomer_segment(List.of("p2"));
        return offer;
    }

    public static ApplyOfferRequest differentRestaurantIdSameSegmentApplyOfferRequest() {
        ApplyOfferRequest applyOffer = new ApplyOfferRequest();
        applyOffer.setRestaurant_id(11);
        applyOffer.setUser_id(2);
        applyOffer.setCart_value(100);
        return applyOffer;
    }

    public static OfferRequest negativeOfferValueOfferRequest() {
        OfferRequest offer = new OfferRequest();
        offer.setRestaurant_id(3);
        offer.setOffer_type("FLATX");
        offer.setOffer_value(-50);
        offer.setCustomer_segment(List.of("p3"));
        return offer;
    }

    public static OfferRequest segmentOfferValueOfferRequest() {
        OfferRequest offer = new OfferRequest();
        offer.setRestaurant_id(3);
        offer.setOffer_type("FLATX");
        offer.setOffer_value(10);
        offer.setCustomer_segment(List.of(null));
        return offer;
    }
}
