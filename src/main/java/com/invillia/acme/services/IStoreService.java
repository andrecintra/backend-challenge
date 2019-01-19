package com.invillia.acme.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.invilla.acme.dto.StoreDto;
import com.invillia.acme.models.Store;

@Service
public interface IStoreService {
    
    Store createStore(Store store);
    
    Map<String, Object> getStoreById(String id);
    
    Map<String, Object> updateStore(StoreDto dto, String id);
}
