package za.ac.cput.service.impl;

/* CityServiceImplTest.java
Implementation test class for CityServiceImpl.java
Author: Michael Daniel Johnson 221094040
Date: 19 August 2023
*/

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.City;
import za.ac.cput.domain.Country;
import za.ac.cput.factory.CityFactory;
import za.ac.cput.factory.CountryFactory;
import za.ac.cput.repository.CityRepository;
import za.ac.cput.repository.CountryRepository;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class CityServiceImplTest {
    private static final Country country = CountryFactory.createCountry("United States of America");
    @Autowired
    private CityServiceImpl cityService;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;
    private static final City city = CityFactory.createCity("Africa",country);

    @Order(1)
    @Test
    void a_create() {
        countryRepository.save(country);
        City created = cityService.create(city);
        assertEquals(city.getCityID(),created.getCityID());
        System.out.println("Create: "+created);
    }

    @Order(2)
    @Test
    void b_read() {
        City read = cityService.read(city.getCityID());
        assertNotNull(read);
        System.out.println("Read: "+read);
    }

    @Order(3)
    @Test
    void c_update() {

        City updateCity = new City.Builder().copy(city)
                .setCityName("Texas")
                .build();

        cityRepository.save(updateCity);

        assertNotNull(updateCity);
        System.out.println("Updated: "+updateCity);
    }

    @Order(5)
    @Test
    @Disabled
    void e_delete() {
        boolean success = cityService.delete(city.getCityID());
        assertTrue(success);
        System.out.println("Deleted: " +success);
    }

    @Order(4)
    @Test
    void d_getAll() {
        System.out.println("Show All:");
        System.out.println(cityService.getAll());
    }
}