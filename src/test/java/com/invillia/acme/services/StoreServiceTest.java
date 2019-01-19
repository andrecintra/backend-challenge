package com.invillia.acme.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.UpdateItemResult;
import com.invilla.acme.dto.StoreDto;
import com.invillia.acme.models.Store;

import static org.mockito.Mockito.when;

import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class StoreServiceTest {

  @Mock private DynamoDBMapper mapper;
  @Mock private Table table;
  @Mock private AmazonDynamoDB client;

  @InjectMocks private StoreService service = new StoreService();

  @Before
  public void setUp() {

    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void createStoreSuccessTest() {
    doNothing().when(mapper).save(any());
    
    service.createStore(new Store());
    
    verify(mapper, times(1)).save(any());
  }
  
  @Test
  public void getStoreSuccessTest() {
      when(table.getItem(any(), any())).thenReturn(new Item());
      
      Map<String, Object> returnMap = service.getStoreById("123");
      
      verify(table, times(1)).getItem(any(), any());
      assertNotNull(returnMap);
  }
  
  @Test
  public void updateStoreSuccess() {
      when(table.getItem(any(), any())).thenReturn(new Item());
      when(client.updateItem(any())).thenReturn(new UpdateItemResult());
      
      Map<String, Object> returnMap = service.updateStore(new StoreDto(), "123");
      
      verify(table, times(1)).getItem(any(), any());
      verify(client, times(1)).updateItem(any());
      assertNotNull(returnMap);
  }
}
