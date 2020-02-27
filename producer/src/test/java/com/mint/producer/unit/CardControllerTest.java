package com.mint.producer.unit;

import com.mint.mock.MockData;
import com.mint.producer.controller.CardController;
import com.mint.producer.dto.CardResponse;
import com.mint.producer.dto.CardStat;
import com.mint.producer.service.CardService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Created by Toyin on 2/28/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CardControllerTest {
    MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Mock
    private CardController cardController;

    @MockBean
    private CardService cardService;

    @Autowired
    private TestRestTemplate template;

    private final String validCard = "11111111";
    private final String invalidCard = "22222222";


    @Before
    public void setUp() throws Exception {
        when(cardService.validateCard(validCard)).thenReturn(MockData.mockValidCardResponse());
        when(cardService.validateCard(invalidCard)).thenReturn(MockData.mockInvalidCardResponse());
        when(cardService.getStats(anyInt(), anyInt())).thenReturn(MockData.mockCardStat());

        mockMvc = MockMvcBuilders
                .webAppContextSetup(context).build();
    }

    @Test
    public void validCard() {
        HttpEntity<Object> userRequest = getHttpEntity(
                null);
        ResponseEntity<CardResponse> response =
                template.exchange("/card-scheme/verify/" + validCard, HttpMethod.GET,
                        new HttpEntity<>(userRequest.getHeaders()), CardResponse.class);

        Assert.assertEquals(200,response.getStatusCode().value());
        Assert.assertTrue(response.getBody().isSuccess());
    }

    @Test
    public void invalidCard() {
        HttpEntity<Object> userRequest = getHttpEntity(
                null);
        ResponseEntity<CardResponse> response =
                template.exchange("/card-scheme/verify/" + invalidCard, HttpMethod.GET,
                        new HttpEntity<>(userRequest.getHeaders()), CardResponse.class);

        Assert.assertEquals(200,response.getStatusCode().value());
        Assert.assertFalse(response.getBody().isSuccess());
    }

    @Test
    public void getStats() {
        HttpEntity<Object> userRequest = getHttpEntity(
                null);
        ResponseEntity<CardStat> response =
                template.exchange("/card-scheme/stats?start=1&limit=2", HttpMethod.GET,
                        new HttpEntity<>(userRequest.getHeaders()), CardStat.class);

        Assert.assertEquals(200,response.getStatusCode().value());
        Assert.assertEquals(1, response.getBody().getStart());
        Assert.assertEquals(2, response.getBody().getSize());
    }

    private HttpEntity<Object> getHttpEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<Object>(body, headers);
    }
}
