package com.example.wallet;

import com.example.wallet.model.Wallet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
@SpringBootTest
@AutoConfigureMockMvc
public class WalletControllerTest{

    @Autowired
    private MockMvc mockMvc;

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

   // @Test
    public void createWallet() throws Exception{
        System.out.println("hello");
        Wallet wallet=new Wallet();
        wallet.setAmount(new BigDecimal(50));
        String uri = "/api/v1/wallet";
        String inputJson = mapToJson(wallet);
        System.out.println(mockMvc);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        System.out.println( mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getWallets() throws Exception{

        Wallet wallet=new Wallet();
        wallet.setAmount(new BigDecimal(50));
        String uri = "/api/v1/wallet";
        String inputJson = mapToJson(wallet);
        System.out.println(mockMvc);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);

        System.out.println("hello");
         uri = "/api/v1/wallets";
         mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
         status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        Wallet[] getWallet = mapFromJson(content,Wallet[].class);
        assertTrue(getWallet.length == 1);
    }
}
