package kz.bi.service.util;

import kz.bi.dao.entity.incubator.AverageEmployeePerResident;
import kz.bi.dao.entity.incubator.ShareAmount;
import kz.bi.rest.controller.dto.incubator.request.*;
import kz.bi.service.dto.incubator.*;

import java.util.ArrayList;
import java.util.List;

public class RequestToDtoConverter {

    public static IncubatorDto convertToDto(IncubatorRequest request) {
        IncubatorDto dto = new IncubatorDto();
        dto.setName(request.getName());
        dto.setDescription(request.getDescription());
        dto.setManagerId(request.getManagerId());
        dto.setCountryId(request.getCountryId());
        dto.setFounded(request.getFounded());

        if (request.getIncubatorCharacteristics() != null) {
            dto.setIncubatorCharacteristics(convertToCharacteristicsDto(request.getIncubatorCharacteristics()));
        }

        if (request.getIncubatorInfrastructure() != null) {
            dto.setIncubatorInfrastructure(convertToInfrastructureDto(request.getIncubatorInfrastructure()));
        }

        if (request.getIncubatorSpace() != null) {
            dto.setIncubatorSpace(convertToSpaceDto(request.getIncubatorSpace()));
        }

        if (request.getIncubatorResidents() != null) {
            dto.setIncubatorResidents(convertToResidentsDto(request.getIncubatorResidents()));
        }

        if (request.getIncubatorServices() != null) {
            dto.setIncubatorServices(convertToServiceDto(request.getIncubatorServices()));
        }

        if (request.getIncubatorIncome() != null) {
            dto.setIncubatorIncome(convertToIncomeDto(request.getIncubatorIncome()));
        }

        if (request.getIncubatorInvestment() != null) {
            dto.setIncubatorInvestment(convertToInvestmentDto(request.getIncubatorInvestment()));
        }

        if (request.getIncubatorExpense() != null) {
            dto.setIncubatorExpense(convertToExpenseDto(request.getIncubatorExpense()));
        }

        if (request.getIncubatorProjects() != null && !request.getIncubatorProjects().isEmpty()) {
            List<IncubatorProjectsDto> projectsDtos = new ArrayList<>();
            for (IncubatorProjects projectRequest : request.getIncubatorProjects()) {
                projectsDtos.add(convertToProjectsDto(projectRequest));
            }
            dto.setIncubatorProjects(projectsDtos);
        }

        return dto;
    }

    private static IncubatorCharacteristicsDto convertToCharacteristicsDto(IncubatorCharacteristics request) {
        IncubatorCharacteristicsDto dto = new IncubatorCharacteristicsDto();
        dto.setAverageEmployeePerResident(
                AverageEmployeePerResident.valueOf(request.getAverageEmployeePerResident()));
        dto.setShareAmount(ShareAmount.valueOf(request.getShareAmount()));
        dto.setTotalStaff(request.getTotalStaff());
        dto.setExpertsAndConsultants(request.getExpertsAndConsultants());
        dto.setManagers(request.getManagers());
        dto.setMonitoringAndDataCollecting(
                request.getMonitoringAndDataCollecting() != null && request.getMonitoringAndDataCollecting());
        dto.setRequirements(request.getRequirements());
        return dto;
    }

    private static IncubatorInfrastructureDto convertToInfrastructureDto(IncubatorInfrastructure request) {
        IncubatorInfrastructureDto dto = new IncubatorInfrastructureDto();
        dto.setSectorsCovered(request.getSectorsCovered());
        dto.setYearsInOperation(request.getYearsInOperation());
        dto.setProgrammeDuration(request.getProgrammeDuration());
        return dto;
    }

    private static IncubatorSpaceDto convertToSpaceDto(IncubatorSpace request) {
        IncubatorSpaceDto dto = new IncubatorSpaceDto();
        dto.setOverallSpace(request.getOverallSpace());
        dto.setAvgResidentSpace(request.getAvgResidentSpace());
        dto.setCommunalSpace(request.getCommunalSpace());
        dto.setAdminSpace(request.getAdminSpace());
        dto.setCommunalSpaceRatio(request.getCommunalSpaceRatio());
        return dto;
    }

    private static IncubatorResidentsDto convertToResidentsDto(IncubatorResidents request) {
        IncubatorResidentsDto dto = new IncubatorResidentsDto();
        dto.setIncubatedCompanies(request.getIncubatedCompanies().longValue());
        dto.setFailedCompanies(request.getFailedCompanies().longValue());
        dto.setGraduatedCompanies(request.getGraduatedCompanies().longValue());
        dto.setReceivedApplication(request.getReceivedApplication().longValue());
        dto.setAcceptedApplication(request.getAcceptedApplication().longValue());
        dto.setActiveAfter3Months(request.getActiveAfter3Months().longValue());
        dto.setActiveAfter6Months(request.getActiveAfter6Months().longValue());
        dto.setActiveAfter1Year(request.getActiveAfter1Year().longValue());
        dto.setActiveAfter3Years(request.getActiveAfter3Years().longValue());
        dto.setActiveAfter5Years(request.getActiveAfter5Years().longValue());
        dto.setFailedAfter3Months(request.getFailedAfter3Months().longValue());
        dto.setFailedAfter6Months(request.getFailedAfter6Months().longValue());
        dto.setFailedAfter1Year(request.getFailedAfter1Year().longValue());
        dto.setFailedAfter3Years(request.getFailedAfter3Years().longValue());
        dto.setFailedAfter5Years(request.getFailedAfter5Years().longValue());
        return dto;
    }

    private static IncubatorServiceDto convertToServiceDto(IncubatorService request) {
        IncubatorServiceDto dto = new IncubatorServiceDto();
        dto.setOfferedServices(request.getOfferedServices());
        dto.setFreeServices(request.getFreeServices());
        dto.setUsedServices(request.getUsedServices());
        dto.setPaidServices(request.getPaidServices());
        dto.setOfferedFacilities(request.getOfferedFacilities());
        dto.setFreeFacilities(request.getFreeFacilities());
        dto.setUsedFacilities(request.getUsedFacilities());
        dto.setPaidFacilities(request.getPaidFacilities());
        dto.setOfferedTrainings(request.getOfferedTrainings());
        dto.setFreeTrainings(request.getFreeTrainings());
        dto.setUsedTrainings(request.getUsedTrainings());
        dto.setPaidTrainings(request.getPaidTrainings());
        return dto;
    }

    private static IncubatorIncomeDto convertToIncomeDto(IncubatorIncome request) {
        IncubatorIncomeDto dto = new IncubatorIncomeDto();
        dto.setInitialCapital(request.getInitialCapital());
        dto.setPaidServicesIncome(request.getPaidServicesIncome());
        dto.setPaidTrainingIncome(request.getPaidTrainingIncome());
        dto.setPaidFacilitiesIncome(request.getPaidFacilitiesIncome());
        dto.setDonors(request.getDonors());
        dto.setState(request.getState());
        dto.setLoans(request.getLoans());
        return dto;
    }

    private static IncubatorInvestmentDto convertToInvestmentDto(IncubatorInvestment request) {
        IncubatorInvestmentDto dto = new IncubatorInvestmentDto();
        dto.setSeed(request.getSeed());
        dto.setState(request.getState());
        dto.setPrivates(request.getPrivates());
        dto.setCurrentYearInvestment(request.getCurrentYearInvestment());
        dto.setCumulativeInvestment(request.getCumulativeInvestment());
        return dto;
    }

    private static IncubatorExpenseDto convertToExpenseDto(IncubatorExpense request) {
        IncubatorExpenseDto dto = new IncubatorExpenseDto();
        dto.setPayroll(request.getPayroll());
        dto.setEquipment(request.getEquipment());
        dto.setUtilities(request.getUtilities());
        dto.setTax(request.getTax());
        dto.setRents(request.getRents());
        dto.setBankRepayments(request.getBankRepayments());
        dto.setMaterial(request.getMaterial());
        dto.setInsurance(request.getInsurance());
        return dto;
    }

    private static IncubatorProjectsDto convertToProjectsDto(IncubatorProjects request) {
        IncubatorProjectsDto dto = new IncubatorProjectsDto();
        dto.setYear(request.getYear());
        dto.setProjectsCount(request.getProjectsCount());
        dto.setFund(request.getFund());
        return dto;
    }
}
