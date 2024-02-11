package com.vass;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneOffset;

import static com.vass.domain.ProviderEnum.ZARA;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;

@QuarkusTest
class PriceResourceTest {

    private final Long brandId = 1L;
    private final Long productId = 1L;
    private final String zara = ZARA.name();

    @Test
    void testPricesEndpoint() {
        given()
            .when().get("/api/prices")
            .then()
            .statusCode(200)
            .body("size()", is(4),
                "price", containsInAnyOrder(3550, 2545, 3050, 3895),
                "brand.provider", containsInAnyOrder(zara, zara, zara, zara),
                "product.name", containsInAnyOrder("camiseta blanca", "camiseta blanca", "camiseta blanca", "camiseta blanca"),
                "priceList", containsInAnyOrder(1, 2, 3, 4),
                "priority", containsInAnyOrder(0, 1, 1, 1));
    }

    @Test
    void test_Not_Found() {
        String dateEpoch = String.valueOf(LocalDate.parse("2020-06-13").atTime(23, 0).toInstant(ZoneOffset.UTC).toEpochMilli());
        given().contentType(ContentType.JSON)
            .params("brandId", brandId,
                "productId", productId,
                "dateEpoch", dateEpoch)
            .when().get("/api/prices/filter")
            .then()
            .statusCode(404);
    }

    @Test
    void test_1() {
        String dateEpoch = String.valueOf(LocalDate.parse("2020-06-14").atTime(10, 0).toInstant(ZoneOffset.UTC).toEpochMilli());
        given().contentType(ContentType.JSON)
            .params("brandId", brandId,
                "productId", productId,
                "dateEpoch", dateEpoch)
            .when().get("/api/prices/filter")
            .then()
            .statusCode(200)
            .body("size()", is(9),
                "price", is(3550),
                "brand.provider", is(zara),
                "product.name", is("camiseta blanca"),
                "priceList", is(1),
                "priority", is(0));
    }

    @Test
    void test_2() {
        String dateEpoch = String.valueOf(LocalDate.parse("2020-06-14").atTime(16, 0).toInstant(ZoneOffset.UTC).toEpochMilli());
        given().contentType(ContentType.JSON)
            .params("brandId", brandId,
                "productId", productId,
                "dateEpoch", dateEpoch)
            .when().get("/api/prices/filter")
            .then()
            .statusCode(200)
            .body("size()", is(9),
                "price", is(2545),
                "brand.provider", is(zara),
                "product.name", is("camiseta blanca"),
                "priceList", is(2),
                "priority", is(1));
    }

    @Test
    void test_3() {
        String dateEpoch = String.valueOf(LocalDate.parse("2020-06-14").atTime(21, 0).toInstant(ZoneOffset.UTC).toEpochMilli());
        given().contentType(ContentType.JSON)
            .params("brandId", brandId,
                "productId", productId,
                "dateEpoch", dateEpoch)
            .when().get("/api/prices/filter")
            .then()
            .statusCode(200)
            .body("size()", is(9),
                "price", is(3550),
                "brand.provider", is(zara),
                "product.name", is("camiseta blanca"),
                "priceList", is(1),
                "priority", is(0));
    }

    @Test
    void test_4() {
        String dateEpoch = String.valueOf(LocalDate.parse("2020-06-15").atTime(10, 0).toInstant(ZoneOffset.UTC).toEpochMilli());
        given().contentType(ContentType.JSON)
            .params("brandId", brandId,
                "productId", productId,
                "dateEpoch", dateEpoch)
            .when().get("/api/prices/filter")
            .then()
            .statusCode(200)
            .body("size()", is(9),
                "price", is(3050),
                "brand.provider", is(zara),
                "product.name", is("camiseta blanca"),
                "priceList", is(3),
                "priority", is(1));
    }

    @Test
    void test_5() {
        String dateEpoch = String.valueOf(LocalDate.parse("2020-06-16").atTime(21, 0).toInstant(ZoneOffset.UTC).toEpochMilli());
        given().contentType(ContentType.JSON)
            .params("brandId", brandId,
                "productId", productId,
                "dateEpoch", dateEpoch)
            .when().get("/api/prices/filter")
            .then()
            .statusCode(200)
            .body("size()", is(9),
                "price", is(3895),
                "brand.provider", is(zara),
                "product.name", is("camiseta blanca"),
                "priceList", is(4),
                "priority", is(1));
    }

}