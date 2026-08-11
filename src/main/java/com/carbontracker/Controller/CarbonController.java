package com.carbontracker.Controller;

import com.carbontracker.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.carbontracker.Service.CarbonService;
import java.util.List;

@RestController
@RequestMapping("/carbon")
@CrossOrigin(origins = "*")

public class CarbonController {

@Autowired
private CarbonService carbonService;

@PostMapping("/save")
public CarbonResponse saveCarbon(@RequestBody CarbonRequest carbonRequest) {

    return carbonService.saveCarbon(carbonRequest);
     }

    @GetMapping("/dashboard/{userId}")
    public DashboardResponse getDashboardData(@PathVariable Integer userId) {

        return carbonService.getDashboardData(userId);

    }
    @GetMapping("/history/{userId}")
    public List<PastEntryResponse> getPastEntries(@PathVariable Integer userId) {

        return carbonService.getPastEntries(userId);

    }
    @GetMapping("/report/{userId}")
    public ReportResponse getReport(

            @PathVariable Integer userId,

            @RequestParam(defaultValue = "Monthly") String reportType) {

        return carbonService.getReport(userId, reportType);

    }


}