package com.mint.producer.unit;

import com.mint.mock.MockData;
import com.mint.producer.dto.CardResponse;
import com.mint.producer.dto.CardStat;
import com.mint.producer.dto.ServiceResponse;
import com.mint.producer.model.Card;
import com.mint.producer.repository.CardRepository;
import com.mint.producer.repository.CardRequestRepository;
import com.mint.producer.service.BinService;
import com.mint.producer.service.CardService;
import com.mint.producer.service.MessageSource;
import com.mint.producer.service.impl.CardServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by Toyin on 2/28/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CardServiceTest {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private CardRequestRepository cardRequestRepository;
    @MockBean
    private BinService binService;
    @MockBean
    private MessageSource messageSource;

    private CardService cardService;

    private final String validCard = "11111111";
    private final String invalidCard = "22222222";



    @Before
    public void setUp() throws Exception {
        cardRepository.deleteAll();
        cardRequestRepository.deleteAll();

        ServiceResponse serviceResponse = MockData.mockServiceResponse();
        when(binService.validateCard(validCard)).thenReturn(ResponseEntity.ok(serviceResponse));
        when(binService.validateCard(invalidCard)).thenReturn(ResponseEntity.notFound().build());

        cardService = new CardServiceImpl(binService, cardRepository,
                cardRequestRepository, messageSource);
    }

    @Test
    public void validCard() {
        CardResponse response = cardService.validateCard(validCard);
        assertTrue(response.isSuccess());
        Card card = cardRepository.findByCardDigit(validCard);
        assertNotNull(card);
        assertEquals(validCard, card.getCardDigit());
    }

    @Test
    public void invalidCard() {
        CardResponse response = cardService.validateCard(invalidCard);
        assertFalse(response.isSuccess());
    }

    @Test
    public void cardStats() {
        cardService.validateCard(validCard);
        cardService.validateCard(validCard);
        cardService.validateCard(validCard);
        CardStat cardStat = cardService.getStats(1, 2);
        assertEquals(cardStat.getSize(), 2);
    }
}
