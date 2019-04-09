package com.dental.project.controller;

import com.dental.project.model.company.CompanyType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.dental.project.payload.Company.CompanyTypeCreateRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        objectMapper  = new ObjectMapper();

    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    @WithUserDetails("app_tester")
    public void getCompanyTypeById() throws Exception{

        this.mockMvc.perform(get("/api/company/type/{id}",1))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 1,'name': 'CUSTOMER', 'description': 'Companies that are Customers'}"));

        //fail("not implemented");
    }

    @Test
    @WithMockUser(username = "username", authorities={"ROLE_USER"})
    public void createCompanyTypeWithUserRoleUser() throws Exception{

        CompanyTypeCreateRequest companyTypeCreateRequest = new CompanyTypeCreateRequest();
        companyTypeCreateRequest.setDescription("TEST DESCRIPTION");
        companyTypeCreateRequest.setName("TEST NAME");

        CompanyType ct = new CompanyType();
        ct.setId(6);
        ct.setName(companyTypeCreateRequest.getName());
        ct.setDescription(companyTypeCreateRequest.getDescription());

        String jsonString = objectMapper.writeValueAsString(companyTypeCreateRequest);

        this.mockMvc.perform(post("/api/company/type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(status().isForbidden());

        //fail("not implemented");
    }


    @Test
    @WithMockUser(username = "username", authorities={"ROLE_USER"})
    public void deleteCompanyTypeWithUserRoleUser() throws Exception{
        this.mockMvc.perform(delete("/api/company/type/{id}",11))
                .andExpect(status().isForbidden());

    }

    @Test
    @WithMockUser(username = "username", authorities={"ROLE_ADMIN"})
    public void deleteCompanyTypeWithUserRoleAdmin() throws Exception{
        this.mockMvc.perform(delete("/api/company/type/{id}",11))
                .andExpect(status().isOk());

        //fail("not implemented");
    }

}