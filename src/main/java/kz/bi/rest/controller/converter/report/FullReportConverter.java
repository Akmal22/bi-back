package kz.bi.rest.controller.converter.report;

import kz.bi.rest.controller.dto.incubator.response.ManagerInfo;
import kz.bi.rest.controller.dto.report.*;
import kz.bi.service.dto.incubator.IncubatorIncomeDto;
import kz.bi.service.dto.incubator.IncubatorInvestmentDto;
import kz.bi.service.dto.incubator.IncubatorProjectsDto;
import kz.bi.service.dto.incubator.IncubatorResidentsDto;
import kz.bi.service.dto.report.ApplicantKpiDataDto;
import kz.bi.service.dto.report.DemographicDataDto;
import kz.bi.service.dto.report.FullReportDto;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static kz.bi.rest.controller.converter.CountryConverter.toCountry;

@UtilityClass
public class FullReportConverter {

    public static FullReportResponse convertToResponse(FullReportDto dto) {
        if (dto == null) {
            return new FullReportResponse();
        }

        FullReportResponse response = new FullReportResponse();

        response.setCountry(toCountry(dto.getCountry()));
        ManagerInfo manager = new ManagerInfo();
        manager.setId(dto.getManager().getId());
        manager.setUsername(dto.getManager().getUsername());
        manager.setEmail(dto.getManager().getEmail());
        manager.setFullName(dto.getManager().getFullName());
        response.setManagerInfo(manager);

        // Convert IncubatorProjectsDto to IncubatorProjectsInfo
        if (dto.getIncubatorProjects() != null && !dto.getIncubatorProjects().isEmpty()) {
            List<IncubatorProjectsInfo> projects = dto.getIncubatorProjects().stream()
                    .map(FullReportConverter::convertProjects)
                    .collect(Collectors.toList());
            response.setIncubatorProjectInfos(projects);
        } else {
            response.setIncubatorProjectInfos(new ArrayList<>());
        }

        // Convert IncubatorIncomeDto to IncubatorIncomeInfo (calculate total income)
        if (dto.getIncubatorIncome() != null && !dto.getIncubatorIncome().isEmpty()) {
            List<IncubatorIncomeInfo> income = dto.getIncubatorIncome().stream()
                    .map(FullReportConverter::convertIncome)
                    .collect(Collectors.toList());
            response.setIncubatorIncomeInfo(income);
        } else {
            response.setIncubatorIncomeInfo(new ArrayList<>());
        }

        // Convert IncubatorInvestmentDto to IncubatorFundInfo
        if (dto.getIncubatorInvestment() != null && !dto.getIncubatorInvestment().isEmpty()) {
            List<IncubatorFundInfo> fund = dto.getIncubatorInvestment().stream()
                    .map(FullReportConverter::convertFund)
                    .collect(Collectors.toList());
            response.setIncubatorFundInfo(fund);
        } else {
            response.setIncubatorFundInfo(new ArrayList<>());
        }

        // Convert IncubatorResidentsDto to IncubatorApplicationsInfo
        if (dto.getIncubatorResidents() != null && !dto.getIncubatorResidents().isEmpty()) {
            List<IncubatorApplicationsInfo> applications = dto.getIncubatorResidents().stream()
                    .map(FullReportConverter::convertApplications)
                    .collect(Collectors.toList());
            response.setApplications(applications);
        } else {
            response.setApplications(new ArrayList<>());
        }

        // Convert DemographicDataDto to DemographicData
        if (dto.getDemographicData() != null) {
            response.setDemographicData(convertDemographicData(dto.getDemographicData()));
        }

        // Convert ApplicantKpiDataDto to ApplicantKpiData
        if (dto.getApplicantKpiData() != null) {
            response.setApplicantKpiData(convertApplicantKpiData(dto.getApplicantKpiData()));
        }

        return response;
    }

    private static IncubatorProjectsInfo convertProjects(IncubatorProjectsDto dto) {
        if (dto == null) {
            return null;
        }
        return new IncubatorProjectsInfo()
                .setYear(dto.getYear())
                .setProjectsCount(dto.getProjectsCount());
    }

    private static IncubatorIncomeInfo convertIncome(IncubatorIncomeDto dto) {
        if (dto == null) {
            return null;
        }
        // Calculate total income by summing all income fields
        BigDecimal totalIncome = BigDecimal.ZERO;
        if (dto.getInitialCapital() != null) {
            totalIncome = totalIncome.add(dto.getInitialCapital());
        }
        if (dto.getPaidServicesIncome() != null) {
            totalIncome = totalIncome.add(dto.getPaidServicesIncome());
        }
        if (dto.getPaidTrainingIncome() != null) {
            totalIncome = totalIncome.add(dto.getPaidTrainingIncome());
        }
        if (dto.getPaidFacilitiesIncome() != null) {
            totalIncome = totalIncome.add(dto.getPaidFacilitiesIncome());
        }
        if (dto.getDonors() != null) {
            totalIncome = totalIncome.add(dto.getDonors());
        }
        if (dto.getState() != null) {
            totalIncome = totalIncome.add(dto.getState());
        }
        if (dto.getLoans() != null) {
            totalIncome = totalIncome.add(dto.getLoans());
        }

        return new IncubatorIncomeInfo()
                .setYear(dto.getYear())
                .setIncome(totalIncome);
    }

    private static IncubatorFundInfo convertFund(IncubatorInvestmentDto dto) {
        if (dto == null) {
            return null;
        }
        return new IncubatorFundInfo()
                .setYear(dto.getYear())
                .setAmount(dto.getCumulativeInvestment());
    }

    private static IncubatorApplicationsInfo convertApplications(IncubatorResidentsDto dto) {
        if (dto == null) {
            return null;
        }
        return new IncubatorApplicationsInfo()
                .setYear(dto.getYear())
                .setApplicationCount((int) dto.getReceivedApplication());
    }

    private static DemographicData convertDemographicData(DemographicDataDto dto) {
        if (dto == null) {
            return null;
        }
        return new DemographicData()
                .setAvgServiceCost(dto.getAvgServiceCost())
                .setServiceCostVariance(dto.getServiceCostVariance())
                .setServiceAdoptionIndex(dto.getServiceAdoptionIndex())
                .setServiceCostIndex(dto.getServiceCostIndex())
                .setAvgFacilityCost(dto.getAvgFacilityCost())
                .setFacilityCostVariance(dto.getFacilityCostVariance())
                .setFacilityAdoptionIndex(dto.getFacilityAdoptionIndex())
                .setFacilityCostIndex(dto.getFacilityCostIndex())
                .setAvgTrainingCost(dto.getAvgTrainingCost())
                .setTrainingCostVariance(dto.getTrainingCostVariance())
                .setTrainingAdoptionIndex(dto.getTrainingAdoptionIndex())
                .setTrainingCostIndex(dto.getTrainingCostIndex())
                .setFreeToPaidServicesRatio(dto.getFreeToPaidServicesRatio())
                .setFreeToPaidFacilitiesRatio(dto.getFreeToPaidFacilitiesRatio())
                .setFreeToPaidTrainingsRatio(dto.getFreeToPaidTrainingsRatio());
    }

    private static ApplicantKpiData convertApplicantKpiData(ApplicantKpiDataDto dto) {
        if (dto == null) {
            return null;
        }
        return new ApplicantKpiData()
                .setAcceptanceRatio(dto.getAcceptanceRatio())
                .setCompaniesFailureIndex(dto.getCompaniesFailureIndex())
                .setCompaniesGraduationIndex(dto.getCompaniesGraduationIndex())
                .setGraduatedCompaniesSurvivalRate(dto.getGraduatedCompaniesSurvivalRate())
                .setMoralityRateAfter1Year(dto.getMoralityRateAfter1Year())
                .setMoralityRateAfter3Years(dto.getMoralityRateAfter3Years())
                .setMoralityRateAfter5Years(dto.getMoralityRateAfter5Years());
    }
}
