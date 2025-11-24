package kz.bi.rest.controller.util;

import kz.bi.rest.controller.dto.incubator.response.*;
import kz.bi.service.dto.incubator.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class IncubatorDtoConverter {

    public static Incubator toIncubator(IncubatorDto dto) {
        if (dto == null) {
            return null;
        }
        Incubator incubator = new Incubator();
        incubator.setName(dto.getName());
        incubator.setDescription(dto.getDescription());
        incubator.setManagerId(dto.getManagerId());
        incubator.setCountryId(dto.getCountryId());
        incubator.setFounded(dto.getFounded());
        incubator.setIncubatorCharacteristics(toIncubatorCharacteristics(dto.getIncubatorCharacteristics()));
        incubator.setIncubatorInfrastructure(toIncubatorInfrastructure(dto.getIncubatorInfrastructure()));
        incubator.setIncubatorSpace(toIncubatorSpace(dto.getIncubatorSpace()));
        incubator.setIncubatorResidents(toIncubatorResidents(dto.getIncubatorResidents()));
        incubator.setIncubatorServices(toIncubatorService(dto.getIncubatorServices()));
        incubator.setIncubatorIncome(toIncubatorIncome(dto.getIncubatorIncome()));
        incubator.setIncubatorInvestment(toIncubatorInvestment(dto.getIncubatorInvestment()));
        incubator.setIncubatorExpense(toIncubatorExpense(dto.getIncubatorExpense()));
        incubator.setIncubatorProjects(toIncubatorProjectsList(dto.getIncubatorProjects()));
        return incubator;
    }

    private static IncubatorCharacteristics toIncubatorCharacteristics(IncubatorCharacteristicsDto dto) {
        if (dto == null) {
            return null;
        }
        IncubatorCharacteristics characteristics = new IncubatorCharacteristics();
        characteristics.setAverageEmployeePerResident(dto.getAverageEmployeePerResident() != null ? dto.getAverageEmployeePerResident().name() : null);
        characteristics.setShareAmount(dto.getShareAmount() != null ? dto.getShareAmount().name() : null);
        characteristics.setTotalStaff(dto.getTotalStaff());
        characteristics.setExpertsAndConsultants(dto.getExpertsAndConsultants());
        characteristics.setManagers(dto.getManagers());
        characteristics.setMonitoringAndDataCollecting(dto.isMonitoringAndDataCollecting());
        characteristics.setRequirements(dto.getRequirements());
        return characteristics;
    }

    private static IncubatorInfrastructure toIncubatorInfrastructure(IncubatorInfrastructureDto dto) {
        if (dto == null) {
            return null;
        }
        IncubatorInfrastructure infrastructure = new IncubatorInfrastructure();
        infrastructure.setSectorsCovered(dto.getSectorsCovered());
        infrastructure.setYearsInOperation(dto.getYearsInOperation());
        infrastructure.setProgrammeDuration(dto.getProgrammeDuration());
        return infrastructure;
    }

    private static IncubatorSpace toIncubatorSpace(IncubatorSpaceDto dto) {
        if (dto == null) {
            return null;
        }
        IncubatorSpace space = new IncubatorSpace();
        space.setOverallSpace(dto.getOverallSpace());
        space.setAvgResidentSpace(dto.getAvgResidentSpace());
        space.setCommunalSpace(dto.getCommunalSpace());
        space.setAdminSpace(dto.getAdminSpace());
        space.setCommunalSpaceRatio(dto.getCommunalSpaceRatio());
        return space;
    }

    private static IncubatorResidents toIncubatorResidents(IncubatorResidentsDto dto) {
        if (dto == null) {
            return null;
        }
        IncubatorResidents residents = new IncubatorResidents();
        residents.setIncubatedCompanies(BigDecimal.valueOf(dto.getIncubatedCompanies()));
        residents.setFailedCompanies(BigDecimal.valueOf(dto.getFailedCompanies()));
        residents.setGraduatedCompanies(BigDecimal.valueOf(dto.getGraduatedCompanies()));
        residents.setReceivedApplication(BigDecimal.valueOf(dto.getReceivedApplication()));
        residents.setAcceptedApplication(BigDecimal.valueOf(dto.getAcceptedApplication()));
        residents.setActiveAfter3Months(BigDecimal.valueOf(dto.getActiveAfter3Months()));
        residents.setActiveAfter6Months(BigDecimal.valueOf(dto.getActiveAfter6Months()));
        residents.setActiveAfter1Year(BigDecimal.valueOf(dto.getActiveAfter1Year()));
        residents.setActiveAfter3Years(BigDecimal.valueOf(dto.getActiveAfter3Years()));
        residents.setActiveAfter5Years(BigDecimal.valueOf(dto.getActiveAfter5Years()));
        residents.setFailedAfter3Months(BigDecimal.valueOf(dto.getFailedAfter3Months()));
        residents.setFailedAfter6Months(BigDecimal.valueOf(dto.getFailedAfter6Months()));
        residents.setFailedAfter1Year(BigDecimal.valueOf(dto.getFailedAfter1Year()));
        residents.setFailedAfter3Years(BigDecimal.valueOf(dto.getFailedAfter3Years()));
        residents.setFailedAfter5Years(BigDecimal.valueOf(dto.getFailedAfter5Years()));
        return residents;
    }

    private static IncubatorService toIncubatorService(IncubatorServiceDto dto) {
        if (dto == null) {
            return null;
        }
        IncubatorService service = new IncubatorService();
        service.setOfferedServices(dto.getOfferedServices());
        service.setFreeServices(dto.getFreeServices());
        service.setPaidServices(dto.getPaidServices());
        service.setUsedServices(dto.getUsedServices());
        service.setOfferedFacilities(dto.getOfferedFacilities());
        service.setFreeFacilities(dto.getFreeFacilities());
        service.setPaidFacilities(dto.getPaidFacilities());
        service.setUsedFacilities(dto.getUsedFacilities());
        service.setOfferedTrainings(dto.getOfferedTrainings());
        service.setFreeTrainings(dto.getFreeTrainings());
        service.setPaidTrainings(dto.getPaidTrainings());
        service.setUsedTrainings(dto.getUsedTrainings());
        return service;
    }

    private static IncubatorIncome toIncubatorIncome(IncubatorIncomeDto dto) {
        if (dto == null) {
            return null;
        }
        IncubatorIncome income = new IncubatorIncome();
        income.setInitialCapital(dto.getInitialCapital());
        income.setPaidServicesIncome(dto.getPaidServicesIncome());
        income.setPaidTrainingIncome(dto.getPaidTrainingIncome());
        income.setPaidFacilitiesIncome(dto.getPaidFacilitiesIncome());
        income.setDonors(dto.getDonors());
        income.setState(dto.getState());
        income.setLoans(dto.getLoans());
        return income;
    }

    private static IncubatorInvestment toIncubatorInvestment(IncubatorInvestmentDto dto) {
        if (dto == null) {
            return null;
        }
        IncubatorInvestment investment = new IncubatorInvestment();
        investment.setSeed(dto.getSeed());
        investment.setState(dto.getState());
        investment.setPrivates(dto.getPrivates());
        investment.setCurrentYearInvestment(dto.getCurrentYearInvestment());
        investment.setCumulativeInvestment(dto.getCumulativeInvestment());
        return investment;
    }

    private static IncubatorExpense toIncubatorExpense(IncubatorExpenseDto dto) {
        if (dto == null) {
            return null;
        }
        IncubatorExpense expense = new IncubatorExpense();
        expense.setPayroll(dto.getPayroll());
        expense.setEquipment(dto.getEquipment());
        expense.setUtilities(dto.getUtilities());
        expense.setTax(dto.getTax());
        expense.setRents(dto.getRents());
        expense.setBankRepayments(dto.getBankRepayments());
        expense.setMaterial(dto.getMaterial());
        expense.setInsurance(dto.getInsurance());
        return expense;
    }

    private static List<IncubatorProjects> toIncubatorProjectsList(List<IncubatorProjectsDto> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(IncubatorDtoConverter::toIncubatorProjects)
                .collect(Collectors.toList());
    }

    private static IncubatorProjects toIncubatorProjects(IncubatorProjectsDto dto) {
        if (dto == null) {
            return null;
        }
        IncubatorProjects projects = new IncubatorProjects();
        projects.setYear(dto.getYear());
        projects.setProjectsCount(dto.getProjectsCount());
        projects.setFund(dto.getFund());
        return projects;
    }
}
