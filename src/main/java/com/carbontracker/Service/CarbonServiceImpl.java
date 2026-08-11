package com.carbontracker.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carbontracker.DTO.CarbonRequest;
import com.carbontracker.DTO.CarbonResponse;
import com.carbontracker.Repository.CarbonRepository;
import com.carbontracker.Repository.UserRepository;
import com.carbontracker.Entity.Carbon;
import com.carbontracker.Entity.User;
import java.util.List;
import com.carbontracker.DTO.DashboardResponse;
import java.util.ArrayList;

import com.carbontracker.DTO.PastEntryResponse;
import java.time.LocalDate;
import com.carbontracker.DTO.ReportResponse;

@Service
public class CarbonServiceImpl implements CarbonService {
    @Autowired
    private CarbonRepository carbonRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CarbonResponse saveCarbon(CarbonRequest carbonRequest) {

        // User Find
        User user = userRepository.findById(carbonRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        // Carbon Calculation
        double totalEmission =
                (carbonRequest.getElectricity() * 0.4)
                        + (carbonRequest.getTravel() * 0.2)
                        + (carbonRequest.getWaste() * 1.5);
        // Status
        String status;

        if (totalEmission < 50) {
            status = "LOW";
        } else if (totalEmission < 100) {
            status = "MEDIUM";
        } else {
            status = "HIGH";
        }

        // Save Entity
        Carbon carbon = new Carbon();

        carbon.setUser(user);
        carbon.setElectricity(carbonRequest.getElectricity());
        carbon.setTravel(carbonRequest.getTravel());
        carbon.setWaste(carbonRequest.getWaste());
        carbon.setTotalEmission(totalEmission);
        carbon.setStatus(status);
        carbon.setCreatedAt(LocalDateTime.now());

        Carbon savedCarbon = carbonRepository.save(carbon);

        // Response
        CarbonResponse response = new CarbonResponse();

        response.setId(savedCarbon.getId());
        response.setTotalEmission(savedCarbon.getTotalEmission());
        response.setStatus(savedCarbon.getStatus());
        response.setMessage("Carbon Footprint Calculated Successfully.");

        return response;
    }

    @Override
    public DashboardResponse getDashboardData(Integer userId) {

        // Find User
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        // Get all carbon records of this user
        List<Carbon> records = carbonRepository.findByUser(user);

        double totalEmission = 0;
        double electricityEmission = 0;
        double travelEmission = 0;
        double wasteEmission = 0;

        for (Carbon record : records) {

            totalEmission += record.getTotalEmission();

            electricityEmission += record.getElectricity() * 0.4;

            travelEmission += record.getTravel() * 0.2;

            wasteEmission += record.getWaste() * 1.5;
        }

        int totalEntries = records.size();

        double averageEmission = totalEntries == 0
                ? 0
                : totalEmission / totalEntries;

        int treesRequired = (int) Math.ceil(totalEmission / 25);

        DashboardResponse response = new DashboardResponse();

        response.setTotalEmission(totalEmission);
        response.setTotalEntries(totalEntries);
        response.setAverageEmission(averageEmission);
        response.setTreesRequired(treesRequired);

        response.setElectricityEmission(electricityEmission);
        response.setTravelEmission(travelEmission);
        response.setWasteEmission(wasteEmission);

        return response;
    }
    @Override
    public List<PastEntryResponse> getPastEntries(Integer userId) {

        // Find User
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        // Get User Records
        List<Carbon> records = carbonRepository.findByUser(user);

        List<PastEntryResponse> responseList = new ArrayList<>();

        for (Carbon carbon : records) {

            PastEntryResponse response = new PastEntryResponse();

            response.setDate(carbon.getCreatedAt().toLocalDate().toString());

            response.setElectricity(carbon.getElectricity());

            response.setTravel(carbon.getTravel());

            response.setWaste(carbon.getWaste());

            response.setTotalEmission(carbon.getTotalEmission());

            response.setStatus(carbon.getStatus());

            responseList.add(response);
        }

        return responseList;
    }

    @Override
    public ReportResponse getReport(Integer userId, String reportType) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        List<Carbon> records = carbonRepository.findByUser(user);

        double totalEmission = 0;

        for (Carbon carbon : records) {
            totalEmission += carbon.getTotalEmission();
        }

        int totalEntries = records.size();

        double averageEmission =
                totalEntries == 0 ? 0 : totalEmission / totalEntries;

        int treesRequired =
                (int) Math.ceil(totalEmission / 25);

        ReportResponse response = new ReportResponse();

        response.setTotalEmission(totalEmission);
        response.setTotalEntries(totalEntries);
        response.setAverageEmission(averageEmission);
        response.setTreesRequired(treesRequired);
        response.setReportType(reportType);
        response.setGeneratedDate(LocalDate.now().toString());

        return response;
    }


    }

