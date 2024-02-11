package com.vass.api;

import com.vass.domain.Price;
import com.vass.repository.PriceRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Path("/api/prices")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PriceResource {

    @Inject
    PriceRepository priceRepository;

    @GET
    public List<Price> prices() {
        return priceRepository.findAll();
    }

    @GET
    @Path("/{id}")
    public Response priceById(@PathParam("id") Long id) {
        Optional<Price> optionalPrice = priceRepository.findById(id);
        if (optionalPrice.isEmpty()) {
            return null;
        }
        return Response.ok(optionalPrice.get()).build();
    }

    @GET
    @Path("/filter")
    public Response getPriceQuery(
        @QueryParam("brandId") Long brandId,
        @QueryParam("productId") Long productId,
        @QueryParam("dateEpoch") String dateEpoch
    ) {
        Instant date = Instant.ofEpochMilli(Long.parseLong(dateEpoch));
        Optional<Price> optionalPrice = priceRepository.getPrice(brandId, productId, date)
            .stream()
            .max(Comparator.comparing(Price::getPriority))
            .stream()
            .max(Comparator.comparing(Price::getPrice));

        if (optionalPrice.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(optionalPrice.get()).build();
    }
}
