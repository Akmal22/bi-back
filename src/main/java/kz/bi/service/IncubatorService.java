package kz.bi.service;

import kz.bi.dao.entity.CountryEntity;
import kz.bi.dao.entity.incubator.*;
import kz.bi.dao.entity.user.Role;
import kz.bi.dao.entity.user.UserEntity;
import kz.bi.dao.repo.CountryRepository;
import kz.bi.dao.repo.UsersRepository;
import kz.bi.dao.repo.incubator.IncubatorRepository;
import kz.bi.service.converter.AddOrEditIncubatorConverter;
import kz.bi.service.converter.IncubatorInfoConverter;
import kz.bi.service.dto.incubator.*;
import kz.bi.service.dto.incubator.info.IncubatorInfoDto;
import kz.bi.service.dto.incubator.info.IncubatorsDto;
import kz.bi.service.exception.IncubatorNotAvailableException;
import kz.bi.service.exception.IncubatorNotFoundException;
import kz.bi.service.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class IncubatorService {
    private final IncubatorRepository incubatorRepository;
    private final CountryRepository countryRepository;
    private final UsersRepository usersRepository;


    public String addIncubator(AddOrEditIncubatorDto addOrEditIncubatorDto) {
        // Check if incubator with the same name already exists
        if (incubatorRepository.existsByName(addOrEditIncubatorDto.getName())) {
            throw new ValidationException("incubator.already.exists", "Incubator with name '" + addOrEditIncubatorDto.getName() + "' already exists");
        }

        CountryEntity country = countryRepository.findByCountryCode(addOrEditIncubatorDto.getCountryCode())
                .orElseThrow(() -> new ValidationException("country.not.exist", "Country does not exist"));

        UserEntity manager;
        if (addOrEditIncubatorDto.getManagerId() == null) {
            var currentAuthentication = SecurityContextHolder.getContext().getAuthentication();
            Assert.notNull(currentAuthentication, "Authentication is required");
            var user = (UserEntity) currentAuthentication.getPrincipal();
            if (user.getRole() == Role.ADMIN) {
                log.error("Admin has to specify manager for incubator");
                throw new ValidationException("manager.not.specified", "Manager not specified");
            }
            manager = usersRepository.findByUsername(currentAuthentication.getPrincipal().toString())
                    .orElseThrow(() -> new ValidationException("user.not.exist", "User does not exist"));
        } else {
            manager = usersRepository.findById(addOrEditIncubatorDto.getManagerId())
                    .orElseThrow(() -> new ValidationException("user.not.exist", "User does not exist"));
        }


        // Create main IncubatorEntity
        IncubatorEntity incubatorEntity = new IncubatorEntity();
        incubatorEntity.setName(addOrEditIncubatorDto.getName());
        incubatorEntity.setUuid(UUID.randomUUID().toString());
        incubatorEntity.setDescription(addOrEditIncubatorDto.getDescription());
        incubatorEntity.setManager(manager);
        incubatorEntity.setCountry(country);
        incubatorEntity.setFounded(addOrEditIncubatorDto.getFounded());

        // Convert and set related entities (will be saved via cascade)
        if (addOrEditIncubatorDto.getIncubatorCharacteristics() != null) {
            incubatorEntity.setIncubatorCharacteristics(
                    AddOrEditIncubatorConverter.convertToCharacteristicsEntity(addOrEditIncubatorDto.getIncubatorCharacteristics(), incubatorEntity));
        }

        if (addOrEditIncubatorDto.getIncubatorInfrastructure() != null) {
            incubatorEntity.setIncubatorInfrastructure(
                    AddOrEditIncubatorConverter.convertToInfrastructureEntity(addOrEditIncubatorDto.getIncubatorInfrastructure(), incubatorEntity));
        }

        if (addOrEditIncubatorDto.getIncubatorSpace() != null) {
            incubatorEntity.setIncubatorSpace(
                    AddOrEditIncubatorConverter.convertToSpaceEntity(addOrEditIncubatorDto.getIncubatorSpace(), incubatorEntity));
        }

        if (addOrEditIncubatorDto.getIncubatorResidents() != null && !addOrEditIncubatorDto.getIncubatorResidents().isEmpty()) {
            incubatorEntity.setIncubatorResidents(
                    AddOrEditIncubatorConverter.convertToResidentsEntitySet(addOrEditIncubatorDto.getIncubatorResidents(), incubatorEntity));
        }

        if (addOrEditIncubatorDto.getIncubatorServices() != null) {
            incubatorEntity.setIncubatorServices(
                    AddOrEditIncubatorConverter.convertToServiceEntity(addOrEditIncubatorDto.getIncubatorServices(), incubatorEntity));
        }

        if (addOrEditIncubatorDto.getIncubatorIncome() != null && !addOrEditIncubatorDto.getIncubatorIncome().isEmpty()) {
            incubatorEntity.setIncubatorIncome(
                    AddOrEditIncubatorConverter.convertToIncomeEntitySet(addOrEditIncubatorDto.getIncubatorIncome(), incubatorEntity));
        }

        if (addOrEditIncubatorDto.getIncubatorInvestment() != null && !addOrEditIncubatorDto.getIncubatorInvestment().isEmpty()) {
            incubatorEntity.setIncubatorInvestment(
                    AddOrEditIncubatorConverter.convertToInvestmentEntitySet(addOrEditIncubatorDto.getIncubatorInvestment(), incubatorEntity));
        }

        if (addOrEditIncubatorDto.getIncubatorExpense() != null) {
            incubatorEntity.setIncubatorExpense(
                    AddOrEditIncubatorConverter.convertToExpenseEntity(addOrEditIncubatorDto.getIncubatorExpense(), incubatorEntity));
        }

        if (addOrEditIncubatorDto.getIncubatorProjects() != null && !addOrEditIncubatorDto.getIncubatorProjects().isEmpty()) {
            Set<IncubatorProjectsEntity> projectsEntities = new HashSet<>();
            for (IncubatorProjectsDto projectDto : addOrEditIncubatorDto.getIncubatorProjects()) {
                projectsEntities.add(AddOrEditIncubatorConverter.convertToProjectsEntity(projectDto, incubatorEntity));
            }
            incubatorEntity.setIncubatorProjects(projectsEntities);
        }

        // Save only the parent entity - cascade will save all related entities
        incubatorRepository.save(incubatorEntity);

        return incubatorEntity.getUuid();
    }

    public String updateIncubator(AddOrEditIncubatorDto addOrEditIncubatorDto) {
        if (addOrEditIncubatorDto.getUuid() == null || addOrEditIncubatorDto.getUuid().isEmpty()) {
            throw new ValidationException("incubator.uuid.required", "Incubator UUID is required for update");
        }

        IncubatorEntity incubatorEntity = incubatorRepository.findByUuid(addOrEditIncubatorDto.getUuid())
                .orElseThrow(() -> new IncubatorNotFoundException());

        checkAccessToIncubator(incubatorEntity);

        // Update basic fields
        incubatorEntity.setDescription(addOrEditIncubatorDto.getDescription());
        incubatorEntity.setFounded(addOrEditIncubatorDto.getFounded());

        // Update related entities by editing existing ones instead of creating new
        if (addOrEditIncubatorDto.getIncubatorCharacteristics() != null) {
            IncubatorCharacteristicsEntity characteristics = incubatorEntity.getIncubatorCharacteristics();
            if (characteristics == null) {
                characteristics = AddOrEditIncubatorConverter.convertToCharacteristicsEntity(addOrEditIncubatorDto.getIncubatorCharacteristics(), incubatorEntity);
                incubatorEntity.setIncubatorCharacteristics(characteristics);
            } else {
                characteristics.setAverageEmployeePerResident(addOrEditIncubatorDto.getIncubatorCharacteristics().getAverageEmployeePerResident());
                characteristics.setShareAmount(addOrEditIncubatorDto.getIncubatorCharacteristics().getShareAmount());
                characteristics.setTotalStaff(addOrEditIncubatorDto.getIncubatorCharacteristics().getTotalStaff());
                characteristics.setExpertsAndConsultants(addOrEditIncubatorDto.getIncubatorCharacteristics().getExpertsAndConsultants());
                characteristics.setManagers(addOrEditIncubatorDto.getIncubatorCharacteristics().getManagers());
                characteristics.setMonitoringAndDataCollecting(addOrEditIncubatorDto.getIncubatorCharacteristics().isMonitoringAndDataCollecting());
                characteristics.setRequirements(addOrEditIncubatorDto.getIncubatorCharacteristics().getRequirements());
            }
        }

        if (addOrEditIncubatorDto.getIncubatorInfrastructure() != null) {
            IncubatorInfrastructureEntity infrastructure = incubatorEntity.getIncubatorInfrastructure();
            if (infrastructure == null) {
                infrastructure = AddOrEditIncubatorConverter.convertToInfrastructureEntity(addOrEditIncubatorDto.getIncubatorInfrastructure(), incubatorEntity);
                incubatorEntity.setIncubatorInfrastructure(infrastructure);
            } else {
                infrastructure.setSectorsCovered(addOrEditIncubatorDto.getIncubatorInfrastructure().getSectorsCovered());
                infrastructure.setYearsInOperation(addOrEditIncubatorDto.getIncubatorInfrastructure().getYearsInOperation());
                infrastructure.setProgrammeDuration(addOrEditIncubatorDto.getIncubatorInfrastructure().getProgrammeDuration());
            }
        }

        if (addOrEditIncubatorDto.getIncubatorSpace() != null) {
            IncubatorSpaceEntity space = incubatorEntity.getIncubatorSpace();
            if (space == null) {
                space = AddOrEditIncubatorConverter.convertToSpaceEntity(addOrEditIncubatorDto.getIncubatorSpace(), incubatorEntity);
                incubatorEntity.setIncubatorSpace(space);
            } else {
                space.setOverallSpace(addOrEditIncubatorDto.getIncubatorSpace().getOverallSpace());
                space.setAvgResidentSpace(addOrEditIncubatorDto.getIncubatorSpace().getAvgResidentSpace());
                space.setCommunalSpace(addOrEditIncubatorDto.getIncubatorSpace().getCommunalSpace());
                space.setAdminSpace(addOrEditIncubatorDto.getIncubatorSpace().getAdminSpace());
                space.setCommunalSpaceRatio(addOrEditIncubatorDto.getIncubatorSpace().getCommunalSpaceRatio());
            }
        }

        if (addOrEditIncubatorDto.getIncubatorResidents() != null) {
            if (addOrEditIncubatorDto.getIncubatorResidents().isEmpty()) {
                incubatorEntity.setIncubatorResidents(new HashSet<>());
            } else {
                Set<IncubatorResidentsEntity> existingResidents = incubatorEntity.getIncubatorResidents() != null
                        ? incubatorEntity.getIncubatorResidents()
                        : new HashSet<>();

                // Create a map of existing residents by year for quick lookup
                Map<Integer, IncubatorResidentsEntity> residentsByYear = new HashMap<>();
                for (IncubatorResidentsEntity existingResident : existingResidents) {
                    residentsByYear.put(existingResident.getYear(), existingResident);
                }

                // Update or create residents from DTO
                Set<IncubatorResidentsEntity> updatedResidents = new HashSet<>();
                for (IncubatorResidentsDto residentsDto : addOrEditIncubatorDto.getIncubatorResidents()) {
                    IncubatorResidentsEntity residentEntity = null;
                    if (residentsDto.getYear() != null && residentsByYear.containsKey(residentsDto.getYear())) {
                        // Update existing resident
                        residentEntity = residentsByYear.get(residentsDto.getYear());
                        residentEntity.setYear(residentsDto.getYear());
                        residentEntity.setIncubatedCompanies(residentsDto.getIncubatedCompanies());
                        residentEntity.setFailedCompanies(residentsDto.getFailedCompanies());
                        residentEntity.setGraduatedCompanies(residentsDto.getGraduatedCompanies());
                        residentEntity.setReceivedApplication(residentsDto.getReceivedApplication());
                        residentEntity.setAcceptedApplication(residentsDto.getAcceptedApplication());
                        residentEntity.setActiveAfter3Months(residentsDto.getActiveAfter3Months());
                        residentEntity.setActiveAfter6Months(residentsDto.getActiveAfter6Months());
                        residentEntity.setActiveAfter1Year(residentsDto.getActiveAfter1Year());
                        residentEntity.setActiveAfter3Years(residentsDto.getActiveAfter3Years());
                        residentEntity.setActiveAfter5Years(residentsDto.getActiveAfter5Years());
                        residentEntity.setFailedAfter3Months(residentsDto.getFailedAfter3Months());
                        residentEntity.setFailedAfter6Months(residentsDto.getFailedAfter6Months());
                        residentEntity.setFailedAfter1Year(residentsDto.getFailedAfter1Year());
                        residentEntity.setFailedAfter3Years(residentsDto.getFailedAfter3Years());
                        residentEntity.setFailedAfter5Years(residentsDto.getFailedAfter5Years());
                    } else {
                        // Create new resident
                        residentEntity = AddOrEditIncubatorConverter.convertToResidentsEntity(residentsDto, incubatorEntity);
                    }
                    updatedResidents.add(residentEntity);
                }

                incubatorEntity.setIncubatorResidents(updatedResidents);
            }
        }

        if (addOrEditIncubatorDto.getIncubatorServices() != null) {
            IncubatorServiceEntity services = incubatorEntity.getIncubatorServices();
            if (services == null) {
                services = AddOrEditIncubatorConverter.convertToServiceEntity(addOrEditIncubatorDto.getIncubatorServices(), incubatorEntity);
                incubatorEntity.setIncubatorServices(services);
            } else {
                services.setOfferedServices(addOrEditIncubatorDto.getIncubatorServices().getOfferedServices());
                services.setFreeServices(addOrEditIncubatorDto.getIncubatorServices().getFreeServices());
                services.setUsedServices(addOrEditIncubatorDto.getIncubatorServices().getUsedServices());
                services.setPaidServices(addOrEditIncubatorDto.getIncubatorServices().getPaidServices());
                services.setOfferedFacilities(addOrEditIncubatorDto.getIncubatorServices().getOfferedFacilities());
                services.setFreeFacilities(addOrEditIncubatorDto.getIncubatorServices().getFreeFacilities());
                services.setUsedFacilities(addOrEditIncubatorDto.getIncubatorServices().getUsedFacilities());
                services.setPaidFacilities(addOrEditIncubatorDto.getIncubatorServices().getPaidFacilities());
                services.setOfferedTrainings(addOrEditIncubatorDto.getIncubatorServices().getOfferedTrainings());
                services.setFreeTrainings(addOrEditIncubatorDto.getIncubatorServices().getFreeTrainings());
                services.setUsedTrainings(addOrEditIncubatorDto.getIncubatorServices().getUsedTrainings());
                services.setPaidTrainings(addOrEditIncubatorDto.getIncubatorServices().getPaidTrainings());
            }
        }

        if (addOrEditIncubatorDto.getIncubatorIncome() != null) {
            if (addOrEditIncubatorDto.getIncubatorIncome().isEmpty()) {
                incubatorEntity.setIncubatorIncome(new HashSet<>());
            } else {
                Set<IncubatorIncomeEntity> existingIncome = incubatorEntity.getIncubatorIncome() != null
                        ? incubatorEntity.getIncubatorIncome()
                        : new HashSet<>();

                // Create a map of existing income by year for quick lookup
                Map<Integer, IncubatorIncomeEntity> incomeByYear = new HashMap<>();
                for (IncubatorIncomeEntity existingIncomeEntity : existingIncome) {
                    incomeByYear.put(existingIncomeEntity.getYear(), existingIncomeEntity);
                }

                // Update or create income from DTO
                Set<IncubatorIncomeEntity> updatedIncome = new HashSet<>();
                for (IncubatorIncomeDto incomeDto : addOrEditIncubatorDto.getIncubatorIncome()) {
                    IncubatorIncomeEntity incomeEntity = null;
                    if (incomeDto.getYear() != null && incomeByYear.containsKey(incomeDto.getYear())) {
                        // Update existing income
                        incomeEntity = incomeByYear.get(incomeDto.getYear());
                        incomeEntity.setYear(incomeDto.getYear());
                        incomeEntity.setInitialCapital(incomeDto.getInitialCapital());
                        incomeEntity.setPaidServicesIncome(incomeDto.getPaidServicesIncome());
                        incomeEntity.setPaidTrainingIncome(incomeDto.getPaidTrainingIncome());
                        incomeEntity.setPaidFacilitiesIncome(incomeDto.getPaidFacilitiesIncome());
                        incomeEntity.setDonors(incomeDto.getDonors());
                        incomeEntity.setState(incomeDto.getState());
                        incomeEntity.setLoans(incomeDto.getLoans());
                    } else {
                        // Create new income
                        incomeEntity = AddOrEditIncubatorConverter.convertToIncomeEntity(incomeDto, incubatorEntity);
                    }
                    updatedIncome.add(incomeEntity);
                }

                incubatorEntity.setIncubatorIncome(updatedIncome);
            }
        }

        if (addOrEditIncubatorDto.getIncubatorInvestment() != null) {
            if (addOrEditIncubatorDto.getIncubatorInvestment().isEmpty()) {
                incubatorEntity.setIncubatorInvestment(new HashSet<>());
            } else {
                Set<IncubatorInvestmentEntity> existingInvestment = incubatorEntity.getIncubatorInvestment() != null
                        ? incubatorEntity.getIncubatorInvestment()
                        : new HashSet<>();

                // Create a map of existing investment by year for quick lookup
                Map<Integer, IncubatorInvestmentEntity> investmentByYear = new HashMap<>();
                for (IncubatorInvestmentEntity existingInvestmentEntity : existingInvestment) {
                    investmentByYear.put(existingInvestmentEntity.getYear(), existingInvestmentEntity);
                }

                // Update or create investment from DTO
                Set<IncubatorInvestmentEntity> updatedInvestment = new HashSet<>();
                for (IncubatorInvestmentDto investmentDto : addOrEditIncubatorDto.getIncubatorInvestment()) {
                    IncubatorInvestmentEntity investmentEntity = null;
                    if (investmentDto.getYear() != null && investmentByYear.containsKey(investmentDto.getYear())) {
                        // Update existing investment
                        investmentEntity = investmentByYear.get(investmentDto.getYear());
                        investmentEntity.setYear(investmentDto.getYear());
                        investmentEntity.setSeed(investmentDto.getSeed());
                        investmentEntity.setState(investmentDto.getState());
                        investmentEntity.setPrivates(investmentDto.getPrivates());
                        investmentEntity.setCurrentYearInvestment(investmentDto.getCurrentYearInvestment());
                        investmentEntity.setCumulativeInvestment(investmentDto.getCumulativeInvestment());
                    } else {
                        // Create new investment
                        investmentEntity = AddOrEditIncubatorConverter.convertToInvestmentEntity(investmentDto, incubatorEntity);
                    }
                    updatedInvestment.add(investmentEntity);
                }

                incubatorEntity.setIncubatorInvestment(updatedInvestment);
            }
        }

        if (addOrEditIncubatorDto.getIncubatorExpense() != null) {
            IncubatorExpenseEntity expense = incubatorEntity.getIncubatorExpense();
            if (expense == null) {
                expense = AddOrEditIncubatorConverter.convertToExpenseEntity(addOrEditIncubatorDto.getIncubatorExpense(), incubatorEntity);
                incubatorEntity.setIncubatorExpense(expense);
            } else {
                expense.setPayroll(addOrEditIncubatorDto.getIncubatorExpense().getPayroll());
                expense.setEquipment(addOrEditIncubatorDto.getIncubatorExpense().getEquipment());
                expense.setUtilities(addOrEditIncubatorDto.getIncubatorExpense().getUtilities());
                expense.setTax(addOrEditIncubatorDto.getIncubatorExpense().getTax());
                expense.setRents(addOrEditIncubatorDto.getIncubatorExpense().getRents());
                expense.setBankRepayments(addOrEditIncubatorDto.getIncubatorExpense().getBankRepayments());
                expense.setMaterial(addOrEditIncubatorDto.getIncubatorExpense().getMaterial());
                expense.setInsurance(addOrEditIncubatorDto.getIncubatorExpense().getInsurance());
            }
        }

        if (addOrEditIncubatorDto.getIncubatorProjects() != null) {
            if (addOrEditIncubatorDto.getIncubatorProjects().isEmpty()) {
                incubatorEntity.setIncubatorProjects(new HashSet<>());
            } else {
                Set<IncubatorProjectsEntity> existingProjects = incubatorEntity.getIncubatorProjects() != null
                        ? incubatorEntity.getIncubatorProjects()
                        : new HashSet<>();

                // Create a map of existing projects by year for quick lookup
                Map<Integer, IncubatorProjectsEntity> projectsByYear = new HashMap<>();
                for (IncubatorProjectsEntity existingProject : existingProjects) {
                    projectsByYear.put(existingProject.getYear(), existingProject);
                }

                // Update or create projects from DTO
                Set<IncubatorProjectsEntity> updatedProjects = new HashSet<>();
                for (IncubatorProjectsDto projectDto : addOrEditIncubatorDto.getIncubatorProjects()) {
                    IncubatorProjectsEntity projectEntity = projectsByYear.get(projectDto.getYear());
                    if (projectEntity == null) {
                        // Create new project
                        projectEntity = AddOrEditIncubatorConverter.convertToProjectsEntity(projectDto, incubatorEntity);
                    } else {
                        // Update existing project
                        projectEntity.setProjectsCount(projectDto.getProjectsCount());
                        projectEntity.setFund(projectDto.getFund());
                    }
                    updatedProjects.add(projectEntity);
                }

                incubatorEntity.setIncubatorProjects(updatedProjects);
            }
        }

        // Save the entity - cascade will handle related entities
        incubatorRepository.save(incubatorEntity);

        return incubatorEntity.getUuid();
    }

    public IncubatorsDto getIncubators(Pageable pageable) {
        // Get current authentication
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        Assert.notNull(authentication, "Authentication is required");

        Page<IncubatorEntity> incubators;

        // If user is MANAGER, filter incubators by manager
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority(Role.MANAGER.name()))) {
            // Find user by authentication username
            UserEntity user = (UserEntity) authentication.getPrincipal();
            UserEntity manager = usersRepository.findByUsername(user.getUsername())
                    .orElseThrow(() -> new ValidationException("user.not.exist", "User does not exist"));

            // Get incubators by manager
            List<IncubatorEntity> managerIncubators = incubatorRepository.findByManager(manager);

            // Manually paginate the results
            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), managerIncubators.size());
            List<IncubatorEntity> pagedContent = start < managerIncubators.size()
                    ? managerIncubators.subList(start, end)
                    : new ArrayList<>();

            incubators = new PageImpl<>(pagedContent, pageable, managerIncubators.size());
        } else {
            // For ADMIN and other roles, return all incubators
            incubators = incubatorRepository.findAll(pageable);
        }

        return new IncubatorsDto()
                .setIncubators(incubators.getContent().stream()
                        .map(IncubatorInfoConverter::convertToDtoSimple)
                        .collect(Collectors.toList()))
                .setPage(incubators.getNumber())
                .setSize(incubators.getSize())
                .setTotal(incubators.getTotalElements())
                .setTotalPages(incubators.getTotalPages())
                .setLast(incubators.isLast());
    }

    public IncubatorInfoDto getIncubator(String uuid) {
        var optionalIncubator = incubatorRepository.findByUuid(uuid);
        if (optionalIncubator.isEmpty()) {
            log.warn("Incubator with uuid {} not found", uuid);
            throw new IncubatorNotFoundException();
        }

        var incubator = optionalIncubator.get();
        checkAccessToIncubator(incubator);

        return IncubatorInfoConverter.convertToDto(incubator);
    }

    private void checkAccessToIncubator(IncubatorEntity incubator) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        Assert.notNull(authentication, "Authentication is required");
        UserEntity user = (UserEntity) authentication.getPrincipal();
        if (user.getRole() != Role.ADMIN && !incubator.getManager().getUsername().equals(user.getUsername())) {
            log.warn("User [{}] does not have access to incubator [{}]", authentication.getPrincipal(),
                    incubator.getName());
            throw new IncubatorNotAvailableException();
        }
    }
}
