package kz.bi.service.converter;

import kz.bi.dao.entity.incubator.*;
import kz.bi.service.dto.incubator.*;
import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@UtilityClass
public class AddOrEditIncubatorConverter {

    public static IncubatorCharacteristicsEntity convertToCharacteristicsEntity(
            IncubatorCharacteristicsDto dto, IncubatorEntity incubatorEntity) {
        IncubatorCharacteristicsEntity entity = new IncubatorCharacteristicsEntity();
        entity.setIncubator(incubatorEntity);
        entity.setAverageEmployeePerResident(dto.getAverageEmployeePerResident());
        entity.setShareAmount(dto.getShareAmount());
        entity.setTotalStaff(dto.getTotalStaff());
        entity.setExpertsAndConsultants(dto.getExpertsAndConsultants());
        entity.setManagers(dto.getManagers());
        entity.setMonitoringAndDataCollecting(dto.isMonitoringAndDataCollecting());
        entity.setRequirements(dto.getRequirements());
        return entity;
    }

    public static IncubatorInfrastructureEntity convertToInfrastructureEntity(
            IncubatorInfrastructureDto dto, IncubatorEntity incubatorEntity) {
        IncubatorInfrastructureEntity entity = new IncubatorInfrastructureEntity();
        entity.setIncubator(incubatorEntity);
        entity.setSectorsCovered(dto.getSectorsCovered());
        entity.setYearsInOperation(dto.getYearsInOperation());
        entity.setProgrammeDuration(dto.getProgrammeDuration());
        return entity;
    }

    public static IncubatorSpaceEntity convertToSpaceEntity(
            IncubatorSpaceDto dto, IncubatorEntity incubatorEntity) {
        IncubatorSpaceEntity entity = new IncubatorSpaceEntity();
        entity.setIncubator(incubatorEntity);
        entity.setOverallSpace(dto.getOverallSpace());
        entity.setAvgResidentSpace(dto.getAvgResidentSpace());
        entity.setCommunalSpace(dto.getCommunalSpace());
        entity.setAdminSpace(dto.getAdminSpace());
        entity.setCommunalSpaceRatio(dto.getCommunalSpaceRatio());
        return entity;
    }

    public static IncubatorResidentsEntity convertToResidentsEntity(
            IncubatorResidentsDto dto, IncubatorEntity incubatorEntity) {
        IncubatorResidentsEntity entity = new IncubatorResidentsEntity();
        entity.setIncubator(incubatorEntity);
        entity.setYear(dto.getYear());
        entity.setIncubatedCompanies(dto.getIncubatedCompanies());
        entity.setFailedCompanies(dto.getFailedCompanies());
        entity.setGraduatedCompanies(dto.getGraduatedCompanies());
        entity.setReceivedApplication(dto.getReceivedApplication());
        entity.setAcceptedApplication(dto.getAcceptedApplication());
        entity.setActiveAfter3Months(dto.getActiveAfter3Months());
        entity.setActiveAfter6Months(dto.getActiveAfter6Months());
        entity.setActiveAfter1Year(dto.getActiveAfter1Year());
        entity.setActiveAfter3Years(dto.getActiveAfter3Years());
        entity.setActiveAfter5Years(dto.getActiveAfter5Years());
        entity.setFailedAfter3Months(dto.getFailedAfter3Months());
        entity.setFailedAfter6Months(dto.getFailedAfter6Months());
        entity.setFailedAfter1Year(dto.getFailedAfter1Year());
        entity.setFailedAfter3Years(dto.getFailedAfter3Years());
        entity.setFailedAfter5Years(dto.getFailedAfter5Years());
        return entity;
    }

    public static IncubatorServiceEntity convertToServiceEntity(
            IncubatorServiceDto dto, IncubatorEntity incubatorEntity) {
        IncubatorServiceEntity entity = new IncubatorServiceEntity();
        entity.setIncubator(incubatorEntity);
        entity.setOfferedServices(dto.getOfferedServices());
        entity.setFreeServices(dto.getFreeServices());
        entity.setUsedServices(dto.getUsedServices());
        entity.setPaidServices(dto.getPaidServices());
        entity.setOfferedFacilities(dto.getOfferedFacilities());
        entity.setFreeFacilities(dto.getFreeFacilities());
        entity.setUsedFacilities(dto.getUsedFacilities());
        entity.setPaidFacilities(dto.getPaidFacilities());
        entity.setOfferedTrainings(dto.getOfferedTrainings());
        entity.setFreeTrainings(dto.getFreeTrainings());
        entity.setUsedTrainings(dto.getUsedTrainings());
        entity.setPaidTrainings(dto.getPaidTrainings());
        return entity;
    }

    public static IncubatorIncomeEntity convertToIncomeEntity(
            IncubatorIncomeDto dto, IncubatorEntity incubatorEntity) {
        IncubatorIncomeEntity entity = new IncubatorIncomeEntity();
        entity.setIncubator(incubatorEntity);
        entity.setYear(dto.getYear());
        entity.setInitialCapital(dto.getInitialCapital());
        entity.setPaidServicesIncome(dto.getPaidServicesIncome());
        entity.setPaidTrainingIncome(dto.getPaidTrainingIncome());
        entity.setPaidFacilitiesIncome(dto.getPaidFacilitiesIncome());
        entity.setDonors(dto.getDonors());
        entity.setState(dto.getState());
        entity.setLoans(dto.getLoans());
        return entity;
    }

    public static IncubatorInvestmentEntity convertToInvestmentEntity(
            IncubatorInvestmentDto dto, IncubatorEntity incubatorEntity) {
        IncubatorInvestmentEntity entity = new IncubatorInvestmentEntity();
        entity.setIncubator(incubatorEntity);
        entity.setYear(dto.getYear());
        entity.setSeed(dto.getSeed());
        entity.setState(dto.getState());
        entity.setPrivates(dto.getPrivates());
        entity.setCurrentYearInvestment(dto.getCurrentYearInvestment());
        entity.setCumulativeInvestment(dto.getCumulativeInvestment());
        return entity;
    }

    public static IncubatorExpenseEntity convertToExpenseEntity(
            IncubatorExpenseDto dto, IncubatorEntity incubatorEntity) {
        IncubatorExpenseEntity entity = new IncubatorExpenseEntity();
        entity.setIncubator(incubatorEntity);
        entity.setPayroll(dto.getPayroll());
        entity.setEquipment(dto.getEquipment());
        entity.setUtilities(dto.getUtilities());
        entity.setTax(dto.getTax());
        entity.setRents(dto.getRents());
        entity.setBankRepayments(dto.getBankRepayments());
        entity.setMaterial(dto.getMaterial());
        entity.setInsurance(dto.getInsurance());
        return entity;
    }

    public static IncubatorProjectsEntity convertToProjectsEntity(
            IncubatorProjectsDto dto, IncubatorEntity incubatorEntity) {
        IncubatorProjectsEntity entity = new IncubatorProjectsEntity();
        entity.setIncubator(incubatorEntity);
        entity.setYear(dto.getYear());
        entity.setProjectsCount(dto.getProjectsCount());
        entity.setFund(dto.getFund());
        return entity;
    }

    public static Set<IncubatorResidentsEntity> convertToResidentsEntitySet(
            List<IncubatorResidentsDto> dtos, IncubatorEntity incubatorEntity) {
        if (dtos == null || dtos.isEmpty()) {
            return new HashSet<>();
        }
        Set<IncubatorResidentsEntity> entities = new HashSet<>();
        for (IncubatorResidentsDto dto : dtos) {
            entities.add(convertToResidentsEntity(dto, incubatorEntity));
        }
        return entities;
    }

    public static Set<IncubatorIncomeEntity> convertToIncomeEntitySet(
            List<IncubatorIncomeDto> dtos, IncubatorEntity incubatorEntity) {
        if (dtos == null || dtos.isEmpty()) {
            return new HashSet<>();
        }
        Set<IncubatorIncomeEntity> entities = new HashSet<>();
        for (IncubatorIncomeDto dto : dtos) {
            entities.add(convertToIncomeEntity(dto, incubatorEntity));
        }
        return entities;
    }

    public static Set<IncubatorInvestmentEntity> convertToInvestmentEntitySet(
            List<IncubatorInvestmentDto> dtos, IncubatorEntity incubatorEntity) {
        if (dtos == null || dtos.isEmpty()) {
            return new HashSet<>();
        }
        Set<IncubatorInvestmentEntity> entities = new HashSet<>();
        for (IncubatorInvestmentDto dto : dtos) {
            entities.add(convertToInvestmentEntity(dto, incubatorEntity));
        }
        return entities;
    }
}
