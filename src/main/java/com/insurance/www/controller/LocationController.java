package com.insurance.www.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.insurance.www.service.LocationService;

@RestController
@RequestMapping("/locations")
@CrossOrigin(origins = "http://localhost:9505")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/states")
    public Set<String> states() {
        return locationService.getStates();
    }

    @GetMapping("/districts")
    public Set<String> districts(@RequestParam String state) {
        return locationService.getDistricts(state);
    }

    @GetMapping("/mandals")
    public Set<String> mandals(
        @RequestParam String state,
        @RequestParam String district
    ) {
        return locationService.getMandals(state, district);
    }

    @PostMapping("/code")
    public Map<String, String> codes(
        @RequestParam String state,
        @RequestParam String district,
        @RequestParam String mandal
    ) {
        return locationService.getCodes(state, district, mandal);
    }
}
