package kz.bi.service.util;

import kz.bi.dao.entity.incubator.*;
import kz.bi.service.dto.incubator.*;

import java.util.stream.Collectors;

public class IncubatorEntityToDtoConverter {

    public static IncubatorDto convertToDto(IncubatorEntity entity) {
        IncubatorDto dto = new IncubatorDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setFounded(entity.getFounded());
        
        if (entity.getManager() != null) {
            dto.setManagerId(entity.getManager().getId());
        }
        
        if (entity.getCountry() != null) {
            dto.setCountryId(entity.getCountry().getId());
        }

        if (entity.getIncubatorCharacteristics() != null) {
            dto.setIncubatorCharacteristics(convertCharacteristicsToDto(entity.getIncubatorCharacteristics()));
        }

        if (entity.getIncubatorInfrastructure() != null) {
            dto.setIncubatorInfrastructure(convertInfrastructureToDto(entity.getIncubatorInfrastructure()));
        }

        if (entity.getIncubatorSpace() != null) {
            dto.setIncubatorSpace(convertSpaceToDto(entity.getIncubatorSpace()));
        }

        if (entity.getIncubatorResidents() != null) {
            dto.setIncubatorResidents(convertResidentsToDto(entity.getIncubatorResidents()));
        }

        if (entity.getIncubatorServices() != null) {
            dto.setIncubatorServices(convertServiceToDto(entity.getIncubatorServices()));
        }

        if (entity.getIncubatorIncome() != null) {
            dto.setIncubatorIncome(convertIncomeToDto(entity.getIncubatorIncome()));
        }

        if (entity.getIncubatorInvestment() != null) {
            dto.setIncubatorInvestment(convertInvestmentToDto(entity.getIncubatorInvestment()));
        }

        if (entity.getIncubatorExpense() != null) {
            dto.setIncubatorExpense(convertExpenseToDto(entity.getIncubatorExpense()));
        }

        if (entity.getIncubatorProjects() != null && !entity.getIncubatorProjects().isEmpty()) {
            dto.setIncubatorProjects(entity.getIncubatorProjects().stream()
                    .map(IncubatorEntityToDtoConverter::convertProjectsToDto)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public static IncubatorCharacteristicsDto convertCharacteristicsToDto(IncubatorCharacteristicsEntity entity) {
        IncubatorCharacteristicsDto dto = new IncubatorCharacteristicsDto();
        dto.setAverageEmployeePerResident(entity.getAverageEmployeePerResident());
        dto.setShareAmount(entity.getShareAmount());
        dto.setTotalStaff(entity.getTotalStaff());
        dto.setExpertsAndConsultants(entity.getExpertsAndConsultants());
        dto.setManagers(entity.getManagers());
        dto.setMonitoringAndDataCollecting(entity.isMonitoringAndDataCollecting());
        dto.setRequirements(entity.getRequirements());
        return dto;
    }

    public static IncubatorInfrastructureDto convertInfrastructureToDto(IncubatorInfrastructureEntity entity) {
        IncubatorInfrastructureDto dto = new IncubatorInfrastructureDto();
        dto.setSectorsCovered(entity.getSectorsCovered());
        dto.setYearsInOperation(entity.getYearsInOperation());
        dto.setProgrammeDuration(entity.getProgrammeDuration());
        return dto;
    }

    public static IncubatorSpaceDto convertSpaceToDto(IncubatorSpaceEntity entity) {
        IncubatorSpaceDto dto = new IncubatorSpaceDto();
        dto.setOverallSpace(entity.getOverallSpace());
        dto.setAvgResidentSpace(entity.getAvgResidentSpace());
        dto.setCommunalSpace(entity.getCommunalSpace());
        dto.setAdminSpace(entity.getAdminSpace());
        dto.setCommunalSpaceRatio(entity.getCommunalSpaceRatio());
        return dto;
    }

    public static IncubatorResidentsDto convertResidentsToDto(IncubatorResidentsEntity entity) {
        IncubatorResidentsDto dto = new IncubatorResidentsDto();
        dto.setIncubatedCompanies(entity.getIncubatedCompanies());
        dto.setFailedCompanies(entity.getFailedCompanies());
        dto.setGraduatedCompanies(entity.getGraduatedCompanies());
        dto.setReceivedApplication(entity.getReceivedApplication());
        dto.setAcceptedApplication(entity.getAcceptedApplication());
        dto.setActiveAfter3Months(entity.getActiveAfter3Months());
        dto.setActiveAfter6Months(entity.getActiveAfter6Months());
        dto.setActiveAfter1Year(entity.getActiveAfter1Year());
        dto.setActiveAfter3Years(entity.getActiveAfter3Years());
        dto.setActiveAfter5Years(entity.getActiveAfter5Years());
        dto.setFailedAfter3Months(entity.getFailedAfter3Months());
        dto.setFailedAfter6Months(entity.getFailedAfter6Months());
        dto.setFailedAfter1Year(entity.getFailedAfter1Year());
        dto.setFailedAfter3Years(entity.getFailedAfter3Years());
        dto.setFailedAfter5Years(entity.getFailedAfter5Years());
        return dto;
    }

    public static IncubatorServiceDto convertServiceToDto(IncubatorServiceEntity entity) {
        IncubatorServiceDto dto = new IncubatorServiceDto();
        dto.setOfferedServices(entity.getOfferedServices());
        dto.setFreeServices(entity.getFreeServices());
        dto.setPaidServices(entity.getPaidServices());
        dto.setUsedServices(entity.getUsedServices());
        dto.setOfferedFacilities(entity.getOfferedFacilities());
        dto.setFreeFacilities(entity.getFreeFacilities());
        dto.setPaidFacilities(entity.getPaidFacilities());
        dto.setUsedFacilities(entity.getUsedFacilities());
        dto.setOfferedTrainings(entity.getOfferedTrainings());
        dto.setFreeTrainings(entity.getFreeTrainings());
        dto.setPaidTrainings(entity.getPaidTrainings());
        dto.setUsedTrainings(entity.getUsedTrainings());
        return dto;
    }

    public static IncubatorIncomeDto convertIncomeToDto(IncubatorIncomeEntity entity) {
        IncubatorIncomeDto dto = new IncubatorIncomeDto();
        dto.setInitialCapital(entity.getInitialCapital());
        dto.setPaidServicesIncome(entity.getPaidServicesIncome());
        dto.setPaidTrainingIncome(entity.getPaidTrainingIncome());
        dto.setPaidFacilitiesIncome(entity.getPaidFacilitiesIncome());
        dto.setDonors(entity.getDonors());
        dto.setState(entity.getState());
        dto.setLoans(entity.getLoans());
        return dto;
    }

    public static IncubatorInvestmentDto convertInvestmentToDto(IncubatorInvestmentEntity entity) {
        IncubatorInvestmentDto dto = new IncubatorInvestmentDto();
        dto.setSeed(entity.getSeed());
        dto.setState(entity.getState());
        dto.setPrivates(entity.getPrivates());
        dto.setCurrentYearInvestment(entity.getCurrentYearInvestment());
        dto.setCumulativeInvestment(entity.getCumulativeInvestment());
        return dto;
    }

    public static IncubatorExpenseDto convertExpenseToDto(IncubatorExpenseEntity entity) {
        IncubatorExpenseDto dto = new IncubatorExpenseDto();
        dto.setPayroll(entity.getPayroll());
        dto.setEquipment(entity.getEquipment());
        dto.setUtilities(entity.getUtilities());
        dto.setTax(entity.getTax());
        dto.setRents(entity.getRents());
        dto.setBankRepayments(entity.getBankRepayments());
        dto.setMaterial(entity.getMaterial());
        dto.setInsurance(entity.getInsurance());
        return dto;
    }

    public static IncubatorProjectsDto convertProjectsToDto(IncubatorProjectsEntity entity) {
        IncubatorProjectsDto dto = new IncubatorProjectsDto();
        dto.setYear(entity.getYear());
        dto.setProjectsCount(entity.getProjectsCount());
        dto.setFund(entity.getFund());
        return dto;
    }
}
