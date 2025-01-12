package com.stefanini.taskmanager.services.impl;

import com.stefanini.taskmanager.services.ApiDataService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ApiDataServiceImpl implements ApiDataService {
    public Map<String, String> getApiInformation() {
        Package pkg = this.getClass().getPackage();
        Map<String, String> values = new HashMap<>();
        values.put("version", pkg.getImplementationVersion());
        values.put("name", pkg.getImplementationTitle());
        return values;
    }
}
