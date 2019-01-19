package com.invillia.acme.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.AttributeValueUpdate;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;
import com.invilla.acme.dto.StoreDto;
import com.invillia.acme.models.Store;

/*
 * Here we would have a service just for each type of request on a production environment
 *
 */

@Service
public class StoreService implements IStoreService {

  private AmazonDynamoDB client =
      AmazonDynamoDBClientBuilder.standard().withRegion(Regions.SA_EAST_1).build();

  private DynamoDBMapper mapper = new DynamoDBMapper(client);
  
  private DynamoDB dynamoDB = new DynamoDB(client);
  
  private Table table = dynamoDB.getTable("store");

  @Override
  public Store createStore(Store store) {
    mapper.save(store);

    /*The reference for the object is updated, so the id field will be populated if the save is
    Successful*/
    return store;
  }

  @Override
  public Map<String, Object> getStoreById(String id) {
    return table.getItem("id", id).asMap();
  }

  @Override
  public Map<String, Object> updateStore(StoreDto dto, String id) {

    UpdateItemRequest updateItemRequest =
        new UpdateItemRequest()
            .withTableName("store")
            .addKeyEntry("id", new AttributeValue().withS(id))
            .addAttributeUpdatesEntry(
                "name",
                new AttributeValueUpdate().withValue(new AttributeValue().withS(dto.getName())))
            .addAttributeUpdatesEntry(
                "address",
                new AttributeValueUpdate().withValue(new AttributeValue().withS(dto.getAddress())));

    client.updateItem(updateItemRequest);

    return getStoreById(id);
  }
}
