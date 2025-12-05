package kz.bi.service;

import kz.bi.dao.entity.incubator.IncubatorEntity;
import kz.bi.dao.entity.incubator.IncubatorIncomeEntity;
import kz.bi.dao.entity.incubator.IncubatorResidentsEntity;
import kz.bi.dao.repo.incubator.IncubatorRepository;
import kz.bi.service.converter.CountryConverter;
import kz.bi.service.converter.IncubatorInfoConverter;
import kz.bi.service.converter.UserConverter;
import kz.bi.service.dto.incubator.IncubatorIncomeDto;
import kz.bi.service.dto.incubator.IncubatorInvestmentDto;
import kz.bi.service.dto.incubator.IncubatorProjectsDto;
import kz.bi.service.dto.incubator.IncubatorResidentsDto;
import kz.bi.service.dto.report.ApplicantKpiDataDto;
import kz.bi.service.dto.report.DemographicDataDto;
import kz.bi.service.dto.report.FullReportDto;
import kz.bi.service.dto.report.ShortReportDto;
import kz.bi.service.exception.IncubatorNotFoundException;
import kz.bi.service.utils.MathUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReportService {
    private final IncubatorRepository incubatorRepository;

    public ShortReportDto getShortReport(String incubatorUuid) {
        var incubator = validateAndGetIncubator(incubatorUuid);

        return new ShortReportDto()
                .setCountry(CountryConverter.toDto(incubator.getCountry()))
                .setManager(UserConverter.convertFromUserEntity(incubator.getManager()))
                .setIncubatorProjects(getIncubatorProjects(incubator))
                .setIncubatorIncome(getIncubatorIncome(incubator))
                .setIncubatorResidents(getIncubatorResidents(incubator))
                .setIncubatorInvestment(getIncubatorInvestment(incubator));
    }

    public FullReportDto getFullReport(String incubatorUuid) {
        var incubator = validateAndGetIncubator(incubatorUuid);

        return new FullReportDto()
                .setCountry(CountryConverter.toDto(incubator.getCountry()))
                .setManager(UserConverter.convertFromUserEntity(incubator.getManager()))
                .setIncubatorProjects(getIncubatorProjects(incubator))
                .setIncubatorIncome(getIncubatorIncome(incubator))
                .setIncubatorResidents(getIncubatorResidents(incubator))
                .setIncubatorInvestment(getIncubatorInvestment(incubator))
                .setDemographicData(getDemographicData(incubator))
                .setApplicantKpiData(getApplicantKpiData(incubator));
    }

    private ApplicantKpiDataDto getApplicantKpiData(IncubatorEntity incubator) {
        var applicantKpiDataDto = new ApplicantKpiDataDto();

        var residentsInfo = incubator.getIncubatorResidents();

        var overallApplications = residentsInfo.stream()
                .mapToLong(IncubatorResidentsEntity::getReceivedApplication)
                .sum();
        var acceptedApplications = residentsInfo.stream()
                .mapToLong(IncubatorResidentsEntity::getAcceptedApplication)
                .sum();
        applicantKpiDataDto.setAcceptanceRatio(MathUtils.getRatio(new BigDecimal(acceptedApplications),
                new BigDecimal(overallApplications), 2));

        var failedCompanies = residentsInfo.stream()
                .mapToLong(IncubatorResidentsEntity::getFailedCompanies)
                .sum();
        var incubatedCompanies = residentsInfo.stream()
                .mapToLong(IncubatorResidentsEntity::getIncubatedCompanies)
                .sum();
        applicantKpiDataDto.setCompaniesFailureIndex(MathUtils.getRatio(new BigDecimal(failedCompanies),
                new BigDecimal(incubatedCompanies), 2));

        var graduatedCompanies = residentsInfo.stream()
                .mapToLong(IncubatorResidentsEntity::getGraduatedCompanies)
                .sum();
        applicantKpiDataDto.setCompaniesGraduationIndex(MathUtils.getRatio(new BigDecimal(graduatedCompanies),
                new BigDecimal(incubatedCompanies), 2));

        var activeGraduatedCompanies = residentsInfo.stream()
                .mapToLong(IncubatorResidentsEntity::getActiveAfter5Years)
                .sum();
        applicantKpiDataDto.setGraduatedCompaniesSurvivalRate(MathUtils.getRatio(new BigDecimal(activeGraduatedCompanies),
                new BigDecimal(incubatedCompanies), 2));

        // Morality Rate after 1 Year
        var failedResidentsAfter1Year = new BigDecimal(residentsInfo.stream()
                .mapToLong(IncubatorResidentsEntity::getFailedAfter1Year)
                .sum());
        var activeResidentsAfter1Year = new BigDecimal(residentsInfo.stream()
                .mapToLong(IncubatorResidentsEntity::getActiveAfter1Year)
                .sum());
        var moralityRateAfter1Year = MathUtils.getRatio(failedResidentsAfter1Year, failedResidentsAfter1Year.add(activeResidentsAfter1Year), 2);
        applicantKpiDataDto.setMoralityRateAfter1Year(moralityRateAfter1Year);

        // Morality Rate after 3 Years
        var failedResidentsAfter3Years = new BigDecimal(residentsInfo.stream()
                .mapToLong(IncubatorResidentsEntity::getFailedAfter3Years)
                .sum());
        var activeResidentsAfter3Years = new BigDecimal(residentsInfo.stream()
                .mapToLong(IncubatorResidentsEntity::getActiveAfter3Years)
                .sum());
        var moralityRateAfter3Years = MathUtils.getRatio(failedResidentsAfter3Years, failedResidentsAfter3Years.add(activeResidentsAfter3Years), 2);
        applicantKpiDataDto.setMoralityRateAfter3Years(moralityRateAfter3Years);


        // Morality Rate after 5 Years
        var failedResidentsAfter5Years = new BigDecimal(residentsInfo.stream()
                .mapToLong(IncubatorResidentsEntity::getFailedAfter5Years)
                .sum());
        var activeResidentsAfter5Years = new BigDecimal(residentsInfo.stream()
                .mapToLong(IncubatorResidentsEntity::getActiveAfter5Years)
                .sum());
        var moralityRateAfter5Years = MathUtils.getRatio(failedResidentsAfter5Years, failedResidentsAfter5Years.add(activeResidentsAfter5Years), 2);
        applicantKpiDataDto.setMoralityRateAfter5Years(moralityRateAfter5Years);

        return applicantKpiDataDto;
    }

    private DemographicDataDto getDemographicData(IncubatorEntity incubator) {
        var demographicData = new DemographicDataDto();
        var incubatorService = incubator.getIncubatorServices();
        var incubatorIncomes = incubator.getIncubatorIncome();

        // Services data
        var servicesIncome = incubatorIncomes.stream()
                .map(IncubatorIncomeEntity::getPaidServicesIncome)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        var paidServicesCount = new BigDecimal(incubatorService.getPaidServices());
        var serviceAvgCost = servicesIncome
                .divide(paidServicesCount, 2, RoundingMode.HALF_UP);
        var overallServiceCostAvgServiceCostDiff = servicesIncome.subtract(serviceAvgCost);

        demographicData.setAvgServiceCost(serviceAvgCost);
        demographicData.setServiceCostVariance(MathUtils.sqrt(overallServiceCostAvgServiceCostDiff.multiply(overallServiceCostAvgServiceCostDiff)
                .divide(paidServicesCount, 2, RoundingMode.HALF_UP), 2));
        var usedServiceCount = new BigDecimal(incubatorService.getUsedServices());
        var offeredServicesCount = new BigDecimal(incubatorService.getOfferedServices());
        demographicData.setServiceAdoptionIndex(MathUtils.getRatio(usedServiceCount, offeredServicesCount, 2));
        demographicData.setServiceCostIndex(serviceAvgCost.
                multiply(usedServiceCount.divide(offeredServicesCount, 2, RoundingMode.HALF_UP))
                .setScale(2, RoundingMode.HALF_UP));

        // Facilities Data
        var facilitiesIncome = incubatorIncomes.stream()
                .map(IncubatorIncomeEntity::getPaidFacilitiesIncome)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        var paidFacilitiesCount = new BigDecimal(incubatorService.getPaidFacilities());
        var facilityAvgCost = facilitiesIncome.divide(paidFacilitiesCount, 2, RoundingMode.HALF_UP);
        var overallFacilityCostAvgFacilityCostDiff = facilitiesIncome.subtract(facilityAvgCost);
        demographicData.setAvgFacilityCost(facilityAvgCost);
        demographicData.setFacilityCostVariance(
                MathUtils.sqrt(overallFacilityCostAvgFacilityCostDiff.multiply(overallFacilityCostAvgFacilityCostDiff)
                        .divide(paidFacilitiesCount, 2, RoundingMode.HALF_UP), 2));
        var usedFacilitiesCount = new BigDecimal(incubatorService.getUsedFacilities());
        var offeredFacilitiesCount = new BigDecimal(incubatorService.getOfferedFacilities());
        demographicData.setFacilityAdoptionIndex(MathUtils.getRatio(usedFacilitiesCount, offeredFacilitiesCount, 2));
        demographicData.setFacilityCostIndex(facilityAvgCost.
                multiply(usedFacilitiesCount.divide(offeredFacilitiesCount, 2, RoundingMode.HALF_UP))
                .setScale(2, RoundingMode.HALF_UP));

        // Trainings data
        var trainingsIncome = incubatorIncomes.stream()
                .map(IncubatorIncomeEntity::getPaidTrainingIncome)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        var paidTrainingsCount = new BigDecimal(incubatorService.getPaidTrainings());
        var trainingAvgCost = trainingsIncome.divide(paidTrainingsCount, 2, RoundingMode.HALF_UP);
        var overallTrainingCostAvgTrainingCostDiff = trainingsIncome.subtract(trainingAvgCost);
        demographicData.setAvgTrainingCost(trainingAvgCost);
        demographicData.setTrainingCostVariance(
                MathUtils.sqrt(overallTrainingCostAvgTrainingCostDiff.multiply(overallTrainingCostAvgTrainingCostDiff)
                        .divide(paidTrainingsCount, 2, RoundingMode.HALF_UP), 2));
        var usedTrainingsCount = new BigDecimal(incubatorService.getUsedTrainings());
        var offeredTrainingsCount = new BigDecimal(incubatorService.getOfferedTrainings());
        demographicData.setTrainingAdoptionIndex(MathUtils.getRatio(usedTrainingsCount, offeredTrainingsCount, 2));
        demographicData.setTrainingCostIndex(trainingAvgCost.
                multiply(usedTrainingsCount.divide(offeredTrainingsCount, 2, RoundingMode.HALF_UP))
                .setScale(2, RoundingMode.HALF_UP));

        demographicData.setFreeToPaidServicesRatio(MathUtils.getRatio(new BigDecimal(incubatorService.getFreeServices()),
                new BigDecimal(incubatorService.getPaidServices()), 2));
        demographicData.setFreeToPaidFacilitiesRatio(MathUtils.getRatio(new BigDecimal(incubatorService.getFreeFacilities()),
                new BigDecimal(incubatorService.getPaidFacilities()), 2));
        demographicData.setFreeToPaidTrainingsRatio(MathUtils.getRatio(new BigDecimal(incubatorService.getFreeTrainings()),
                new BigDecimal(incubatorService.getPaidTrainings()), 2));

        return demographicData;
    }


    private IncubatorEntity validateAndGetIncubator(String incubatorUuid) {
        var optionalIncubator = incubatorRepository.findByUuid(incubatorUuid);
        if (optionalIncubator.isEmpty()) {
            log.error("incubator with uuid {} not found", incubatorUuid);
            throw new IncubatorNotFoundException();
        }

        return optionalIncubator.get();
    }

    private List<IncubatorProjectsDto> getIncubatorProjects(IncubatorEntity incubator) {
        return incubator.getIncubatorProjects().stream()
                .map(IncubatorInfoConverter::convertProjectsToDto)
                .sorted(Comparator.comparingInt(IncubatorProjectsDto::getYear))
                .collect(Collectors.toList());
    }

    private List<IncubatorIncomeDto> getIncubatorIncome(IncubatorEntity incubator) {
        return incubator.getIncubatorIncome().stream()
                .map(IncubatorInfoConverter::convertIncomeToDto)
                .sorted(Comparator.comparingInt(IncubatorIncomeDto::getYear))
                .collect(Collectors.toList());
    }

    private List<IncubatorResidentsDto> getIncubatorResidents(IncubatorEntity incubator) {
        return incubator.getIncubatorResidents().stream()
                .map(IncubatorInfoConverter::convertResidentsToDto)
                .sorted(Comparator.comparingInt(IncubatorResidentsDto::getYear))
                .collect(Collectors.toList());
    }

    private List<IncubatorInvestmentDto> getIncubatorInvestment(IncubatorEntity incubator) {
        return incubator.getIncubatorInvestment().stream()
                .map(IncubatorInfoConverter::convertInvestmentToDto)
                .sorted(Comparator.comparingInt(IncubatorInvestmentDto::getYear))
                .collect(Collectors.toList());
    }
}
