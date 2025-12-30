package com.insurance.www.service;

import java.io.InputStream;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LocationService {

    private Map<String, Object> data;

    @SuppressWarnings("unchecked")
    public LocationService() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getClassLoader()
                .getResourceAsStream("data/locations.json");

            data = mapper.readValue(is, new TypeReference<>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to load locations.json", e);
        }
    }

    public Set<String> getStates() {
        return data.keySet();
    }

    @SuppressWarnings("unchecked")
    public Set<String> getDistricts(String state) {
        return ((Map<String, Object>) ((Map<String, Object>) data.get(state))
            .get("districts")).keySet();
    }

    @SuppressWarnings("unchecked")
    public Set<String> getMandals(String state, String district) {
        return ((Map<String, String>) ((Map<String, Object>)
            ((Map<String, Object>) ((Map<String, Object>) data.get(state))
            .get("districts")).get(district)).get("mandals")).keySet();
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> getCodes(String state, String district, String mandal) {
        Map<String, Object> stateObj = (Map<String, Object>) data.get(state);
        String stateCode = (String) stateObj.get("code");

        Map<String, Object> districtObj =
            (Map<String, Object>) ((Map<String, Object>) stateObj
            .get("districts")).get(district);

        String districtCode = (String) districtObj.get("code");
        String mandalCode =
            ((Map<String, String>) districtObj.get("mandals")).get(mandal);

        return Map.of(
            "stateCode", stateCode,
            "districtCode", districtCode,
            "mandalCode", mandalCode
        );
    }
}
