import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;

public class MessageSenderImplTest {

    String IP = "192.144.12.12";

    GeoService geoService = Mockito.mock(GeoServiceImpl.class);

    LocalizationService localizationService = Mockito.spy(LocalizationServiceImpl.class);

    MessageSender messageSender;

    @BeforeEach
    void beforeEach(){
        messageSender = new MessageSenderImpl(geoService, localizationService);
    }

    @Test
    public void test_russian_ip(){

        given(geoService.byIp(IP)).willReturn(new Location("MOSCOW", Country.RUSSIA, null, 0));

        //Mockito.verify(geoService, times(1)).byIp(IP);

        Map<String, String> headers = new HashMap<String, String>(1);
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, IP);

        String actual = messageSender.send(headers);

        assertEquals("Добро пожаловать", actual);
    }

    @Test
    public void test_usa_ip(){

        given(geoService.byIp(IP)).willReturn(new Location("ORLANDO", Country.USA, null, 0));

        Map<String, String> headers = new HashMap<String, String>(1);
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, IP);

        String actual = messageSender.send(headers);

        assertEquals("Welcome", actual);
    }

}
