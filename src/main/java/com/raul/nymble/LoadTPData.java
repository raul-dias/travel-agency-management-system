package com.raul.nymble;

import com.raul.nymble.model.*;
import com.raul.nymble.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadTPData {
    private static final Logger log = LoggerFactory.getLogger(LoadTPData.class);

    @Bean
    CommandLineRunner initDatabase(TravelPackageRepository TPrepo, DestinationRepository desRepo, ItineraryRepository itrRepo, PassengerRepository passRepo, PackageEnrollmentRepository packRepo) {
        return args -> {
            log.info("Preloading " + TPrepo.save(new TravelPackage("Lovely Goa", 10)));
            log.info("Preloading " + TPrepo.save(new TravelPackage("Wonderful Goa", 20)));
            log.info("Preloading " + TPrepo.save(new TravelPackage("Unique Goa", 30)));
            log.info("-------------------------Adding destinations----------------------------");
            log.info("Destinations" + desRepo.save(new Destination("Panjim")));
            log.info("Destinations" + desRepo.save(new Destination("Margao")));
            log.info("Destinations" + desRepo.save(new Destination("Ponda")));
            log.info("Destinations" + desRepo.save(new Destination("Mapusa")));
            log.info("Destinations" + desRepo.save(new Destination("Vasco")));
            log.info("-------------------------Adding itineraires----------------------------");
            log.info("Itineraires" + itrRepo.save(new Itinerary(1L, 1L)));
            log.info("Itineraires" + itrRepo.save(new Itinerary(1L, 2L)));
            log.info("Itineraires" + itrRepo.save(new Itinerary(1L, 3L)));

            log.info("Itineraires" + itrRepo.save(new Itinerary(2L, 1L)));
            log.info("Itineraires" + itrRepo.save(new Itinerary(2L, 4L)));
            log.info("Itineraires" + itrRepo.save(new Itinerary(2L, 5L)));
            log.info("-------------------------Adding Passengers----------------------------");
            log.info("Passengers" + passRepo.save(new Passenger("Raul",123, 1, 999L)));
            log.info("Passengers" + passRepo.save(new Passenger("Raul",987, 2, 999L)));
            log.info("Passengers" + passRepo.save(new Passenger("Raul",159, 3, 999L)));
            log.info("-------------------------Adding Enrollments----------------------------");
            log.info("Enrollments" + packRepo.save(new PackageEnrollment(1L, 1L)));
            log.info("Enrollments" + packRepo.save(new PackageEnrollment(1L, 2L)));
            log.info("Enrollments" + packRepo.save(new PackageEnrollment(2L, 1L)));
            log.info("Enrollments" + packRepo.save(new PackageEnrollment(2L, 2L)));
            log.info("Enrollments" + packRepo.save(new PackageEnrollment(2L, 3L)));
            log.info("Enrollments" + packRepo.save(new PackageEnrollment(3L, 2L)));
            log.info("Enrollments" + packRepo.save(new PackageEnrollment(1L, 3L)));

        };
    }
}
