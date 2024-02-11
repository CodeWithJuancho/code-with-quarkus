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
        @QueryParam("brandId") Long productId,
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

    /**
     * TODO
     * 0. PASAR AL PROYECTO A SPRING
     * 1. Terminar los dos endpoints de Queries.
     *  1.1. Todo a partir de los prices -> prices?brandId=x&productId=x&date=x
     *  1.2. El valor de la fecha que se envia tiene que ser en "epoc" segundos pasados desde 1970
     *  1.3. Crear servicios y la lógica de adquisición de datos
     *  - Lógica de búsqueda entre fechas -- TODO QUERY IN SQL
     *  - Si dos productos coinciden en fecha se coge el de mayor prioridad -- TODO LOGIC IN JAVA
     *    - Si la prioridad es igual se coge el de mayor valor monetario
     * 2. Explicar el por qué de las decisiones, por ejemplo la de guardar el dinero en integer (dividir y multiplicar por 100).
     *  - @see for Instant https://stackoverflow.com/questions/32437550/whats-the-difference-between-instant-and-localdatetime
     * 3. Crear los TESTs de Integracion sobre la BBDD H2 con los casos expuestos.
     * 4. Crear un script de POSTMAN que pruebe todos los casos en el RESOURCE ENDPOINT. -- todo meeeeeeh
     */
}
