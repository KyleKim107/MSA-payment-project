package com.myfastcampuspay.banking;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.myfastcampuspay.banking.adapter.in.web.RegisteredBankAccountRequest;
import com.myfastcampuspay.banking.domain.RegisteredBankAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.http.RequestEntity.post;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RegisterBankAccountControllerTest { //Test가 돌아가기 위해서는 gradle의 group에 Test하는 클래스의 패키지다 맞아야한다.
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testRegisterMembership() throws Exception{
        RegisteredBankAccountRequest request  = new RegisteredBankAccountRequest("1","1","1",true);
        mockMvc.perform(
            MockMvcRequestBuilders.post("/banking/account/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request))
        )
        .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
