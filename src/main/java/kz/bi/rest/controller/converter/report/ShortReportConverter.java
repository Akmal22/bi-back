package kz.bi.rest.controller.converter.report;

import kz.bi.rest.controller.converter.CountryConverter;
import kz.bi.rest.controller.dto.incubator.response.ManagerInfo;
import kz.bi.rest.controller.dto.report.*;
import kz.bi.service.dto.incubator.IncubatorIncomeDto;
import kz.bi.service.dto.incubator.IncubatorInvestmentDto;
import kz.bi.service.dto.incubator.IncubatorProjectsDto;
import kz.bi.service.dto.incubator.IncubatorResidentsDto;
import kz.bi.service.dto.report.ShortReportDto;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class ShortReportConverter {

    public static ShortReportResponse convertToResponse(ShortReportDto dto) {
        if (dto == null) {
            return new ShortReportResponse();
        }

        ShortReportResponse response = new ShortReportResponse();
        response.setCountry(CountryConverter.toCountry(dto.getCountry()));
        ManagerInfo manager = new ManagerInfo();
        manager.setId(dto.getManager().getId());
        manager.setUsername(dto.getManager().getUsername());
        manager.setEmail(dto.getManager().getEmail());
        manager.setFullName(dto.getManager().getFullName());
        response.setManagerInfo(manager);

        // Convert IncubatorProjectsDto to IncubatorProjects
        if (dto.getIncubatorProjects() != null && !dto.getIncubatorProjects().isEmpty()) {
            Set<IncubatorProjectsInfo> projects = dto.getIncubatorProjects().stream()
                    .map(ShortReportConverter::convertProjects)
                    .collect(Collectors.toSet());
            response.setIncubatorProjectInfos(projects);
        } else {
            response.setIncubatorProjectInfos(new HashSet<>());
        }

        // Convert IncubatorIncomeDto to IncubatorIncome (calculate total income)
        if (dto.getIncubatorIncome() != null && !dto.getIncubatorIncome().isEmpty()) {
            Set<IncubatorIncomeInfo> income = dto.getIncubatorIncome().stream()
                    .map(ShortReportConverter::convertIncome)
                    .collect(Collectors.toSet());
            response.setIncubatorIncomeInfo(income);
        } else {
            response.setIncubatorIncomeInfo(new HashSet<>());
        }

        // Convert IncubatorInvestmentDto to IncubatorFund
        if (dto.getIncubatorInvestment() != null && !dto.getIncubatorInvestment().isEmpty()) {
            List<IncubatorFundInfo> fund = dto.getIncubatorInvestment().stream()
                    .map(ShortReportConverter::convertFund)
                    .collect(Collectors.toList());
            response.setIncubatorFundInfo(fund);
        } else {
            response.setIncubatorFundInfo(new ArrayList<>());
        }

        // Convert IncubatorResidentsDto to IncubatorApplications
        if (dto.getIncubatorResidents() != null && !dto.getIncubatorResidents().isEmpty()) {
            List<IncubatorApplicationsInfo> applications = dto.getIncubatorResidents().stream()
                    .map(ShortReportConverter::convertApplications)
                    .collect(Collectors.toList());
            response.setApplications(applications);
        } else {
            response.setApplications(new ArrayList<>());
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
}
