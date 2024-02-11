import com.vass.domain.*;
import com.vass.repository.BrandRepository;
import com.vass.repository.PriceRepository;
import com.vass.repository.ProductRepository;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Arrays;

@ApplicationScoped
public class VassChallengeApplication {

    @Inject
    PriceRepository priceRepository;

    @Inject
    BrandRepository brandRepository;

    @Inject
    ProductRepository productRepository;

    @Transactional
    public void onStart(@Observes StartupEvent ev) {
        if (brandRepository.count() == 0) {
            Brand zara = new Brand(ProviderEnum.ZARA);
            Brand springfield = new Brand(ProviderEnum.SPRINGFIELD);
            Brand pullAndBear = new Brand(ProviderEnum.PULL_AND_BEAR);
            brandRepository.saveAll(Arrays.asList(zara, springfield, pullAndBear));

            if (productRepository.count() == 0) {
                Product zaraProduct1 = new Product("camiseta blanca", zara);
                Product zaraProduct2 = new Product("camiseta negra", zara);
                Product zaraProduct3 = new Product("camiseta roja", zara);
                Product zaraProduct4 = new Product("camiseta verde", zara);
                Product springfield1 = new Product("falda blanca", springfield);
                Product springfield2 = new Product("falda negra", springfield);
                Product pullAndBear1 = new Product("gorra roja", pullAndBear);

                productRepository.saveAll(Arrays.asList(zaraProduct1, zaraProduct2, zaraProduct3, zaraProduct4, springfield1, springfield2, pullAndBear1));
                // only reindex if we imported some content
                if (priceRepository.count() == 0) {
                    Instant price1StartDate = LocalDate.parse("2020-06-14").atTime(0,0).toInstant(ZoneOffset.UTC);
                    Instant price1EndDate = LocalDate.parse("2020-12-31").atTime(23,59,59).toInstant(ZoneOffset.UTC);
                    Instant price2StartDate = LocalDate.parse("2020-06-14").atTime(15, 0).toInstant(ZoneOffset.UTC);
                    Instant price2EndDate = LocalDate.parse("2020-06-14").atTime(18, 30).toInstant(ZoneOffset.UTC);
                    Instant price3StartDate = LocalDate.parse("2020-06-15").atTime(0,0).toInstant(ZoneOffset.UTC);
                    Instant price3EndDate = LocalDate.parse("2020-06-15").atTime(11, 0).toInstant(ZoneOffset.UTC);
                    Instant price4StartDate = LocalDate.parse("2020-06-15").atTime(16, 0).toInstant(ZoneOffset.UTC);
                    Instant price4EndDate =LocalDate.parse("2020-12-31").atTime(23,59,59).toInstant(ZoneOffset.UTC);

                    Price price1 = new Price(3550, 1, 0, CurrencyEnum.EUR, price1StartDate, price1EndDate, zaraProduct1, zara);
                    Price price2 = new Price(2545, 2, 1, CurrencyEnum.EUR, price2StartDate, price2EndDate, zaraProduct1, zara);
                    Price price3 = new Price(3050, 3, 1, CurrencyEnum.EUR, price3StartDate, price3EndDate, zaraProduct1, zara);
                    Price price4 = new Price(3895, 4, 1, CurrencyEnum.EUR, price4StartDate, price4EndDate, zaraProduct1, zara);

                    priceRepository.saveAll(Arrays.asList(price1,price2,price3,price4));
                }
            }

        }

    }

}
