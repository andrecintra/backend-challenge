package com.invillia.acme.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.invilla.acme.dto.StoreDto;
import com.invillia.acme.models.Store;
import com.invillia.acme.services.IStoreService;

public class StoreControllerTest {

  MockMvc mockMvc;

  @Mock IStoreService service;

  @InjectMocks StoreController controller;
  
  private StoreDto dto;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    
    dto = new StoreDto();
    dto.setAddress("test Address");
    dto.setName("test name");
  }

  @Test
  public void createStoreSuccessTest() throws Exception {

    when(service.createStore(any())).thenReturn(new Store());

    mockMvc
        .perform(
            post("/store").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(dto)))
        .andExpect(status().isCreated());

    Mockito.verify(service, times(1)).createStore(any());
  }

  @Test
  public void getStoreByIdSuccessTest() throws Exception {

    when(service.getStoreById(any())).thenReturn(new HashMap<>());

    mockMvc
        .perform(get("/store/{id}", "123").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    Mockito.verify(service, times(1)).getStoreById(any());
  }

  @Test
  public void updateStoreSuccessTest() throws Exception {

    when(service.updateStore(any(), any())).thenReturn(new HashMap<>());

    mockMvc
        .perform(
            patch("/store/{id}", "123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(dto)))
        .andExpect(status().isOk());

    Mockito.verify(service, times(1)).updateStore(any(), any());
  }
}
