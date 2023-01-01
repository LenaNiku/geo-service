package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    String IP = "172.4.5.6";

    @Test
    void byIp_test() {
        Location expected = new Location("Moscow", Country.RUSSIA, null, 0);
        GeoService geoService = new GeoServiceImpl();
        Location result = geoService.byIp(IP);
        assertEquals(expected.getCountry(), result.getCountry());
    }
}