package com.carbontracker.Service;

import com.carbontracker.DTO.*;

import java.util.List;


public interface CarbonService {

    CarbonResponse saveCarbon(CarbonRequest carbonRequest);

    DashboardResponse getDashboardData(Integer userId);

    List<PastEntryResponse> getPastEntries(Integer userId);

    ReportResponse getReport(Integer userId, String reportType);


}
