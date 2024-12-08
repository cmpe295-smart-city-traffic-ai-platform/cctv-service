package org.example.cctvsmarttraffic.Controller;

import org.example.cctvsmarttraffic.Model.CctvData;
import org.example.cctvsmarttraffic.Service.CctvDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/cctv")
public class CctvDataController {

    private final CctvDataService cctvDataService;

    @Autowired
    public CctvDataController(CctvDataService cctvDataService) {
        this.cctvDataService = cctvDataService;
    }

    @GetMapping("/fetch-data")
    public List<CctvData> fetchData(@RequestParam String county) throws IOException {
        System.out.println("Request received for county: " + county); // Debug log
        List<CctvData> result = cctvDataService.fetchAndStoreCctvData(county);
        System.out.println("Data returned to client: " + result); // Debug log
        return result;
    }
}

