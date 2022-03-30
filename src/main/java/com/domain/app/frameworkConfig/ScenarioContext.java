package com.domain.app.frameworkConfig;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private Map<String, Object> scenarioContext;


    public ScenarioContext() {
        scenarioContext = new HashMap<>();
    }

    public Object getContext(String key) {
        return scenarioContext.get(key);
    }

    public void setContext(String key, Object value) {
        scenarioContext.put(key, value);
    }

    public void deleteContext(String key) {
        scenarioContext.remove(key);
    }

    public Boolean isContains(String key) {
        return scenarioContext.containsKey(key);
    }

    public void clearContext() {
        scenarioContext.clear();
    }

}
