package kz.bi.service;

import kz.bi.config.security.UserAuthentication;
import kz.bi.dao.entity.CountryEntity;
import kz.bi.dao.entity.incubator.IncubatorEntity;
import kz.bi.dao.entity.incubator.IncubatorProjectsEntity;
import kz.bi.dao.entity.user.Role;
import kz.bi.dao.entity.user.UserEntity;
import kz.bi.dao.repo.CountryRepository;
import kz.bi.dao.repo.UsersRepository;
import kz.bi.dao.repo.incubator.IncubatorRepository;
import kz.bi.service.dto.incubator.IncubatorDto;
import kz.bi.service.dto.incubator.IncubatorProjectsDto;
import kz.bi.service.exception.IncubatorNotAvailableException;
import kz.bi.service.exception.IncubatorNotFoundException;
import kz.bi.service.exception.ValidationException;
import kz.bi.service.util.IncubatorDtoToEntityConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static kz.bi.service.util.IncubatorEntityToDtoConverter.convertToDto;

@Slf4j
@RequiredArgsConstructor
@Service
public class IncubatorService {
    private final IncubatorRepository incubatorRepository;
    private final CountryRepository countryRepository;
    private final UsersRepository usersRepository;


    public void addIncubator(IncubatorDto incubatorDto) {
        // Check if incubator with the same name already exists
        if (incubatorRepository.existsByName(incubatorDto.getName())) {
            throw new ValidationException("incubator.already.exists", "Incubator with name '" + incubatorDto.getName() + "' already exists");
        }

        // Find manager and country entities
        UserEntity manager = usersRepository.findById(incubatorDto.getManagerId())
                .orElseThrow(() -> new ValidationException("user.not.exist", "User does not exist"));

        CountryEntity country = countryRepository.findById(incubatorDto.getCountryId())
                .orElseThrow(() -> new ValidationException("country.not.exist", "Country does not exist"));

        // Create main IncubatorEntity
        IncubatorEntity incubatorEntity = new IncubatorEntity();
        incubatorEntity.setName(incubatorDto.getName());
        incubatorEntity.setUuid(UUID.randomUUID().toString());
        incubatorEntity.setDescription(incubatorDto.getDescription());
        incubatorEntity.setManager(manager);
        incubatorEntity.setCountry(country);
        incubatorEntity.setFounded(incubatorDto.getFounded());

        // Convert and set related entities (will be saved via cascade)
        if (incubatorDto.getIncubatorCharacteristics() != null) {
            incubatorEntity.setIncubatorCharacteristics(
                    IncubatorDtoToEntityConverter.convertToCharacteristicsEntity(incubatorDto.getIncubatorCharacteristics(), incubatorEntity));
        }

        if (incubatorDto.getIncubatorInfrastructure() != null) {
            incubatorEntity.setIncubatorInfrastructure(
                    IncubatorDtoToEntityConverter.convertToInfrastructureEntity(incubatorDto.getIncubatorInfrastructure(), incubatorEntity));
        }

        if (incubatorDto.getIncubatorSpace() != null) {
            incubatorEntity.setIncubatorSpace(
                    IncubatorDtoToEntityConverter.convertToSpaceEntity(incubatorDto.getIncubatorSpace(), incubatorEntity));
        }

        if (incubatorDto.getIncubatorResidents() != null) {
            incubatorEntity.setIncubatorResidents(
                    IncubatorDtoToEntityConverter.convertToResidentsEntity(incubatorDto.getIncubatorResidents(), incubatorEntity));
        }

        if (incubatorDto.getIncubatorServices() != null) {
            incubatorEntity.setIncubatorServices(
                    IncubatorDtoToEntityConverter.convertToServiceEntity(incubatorDto.getIncubatorServices(), incubatorEntity));
        }

        if (incubatorDto.getIncubatorIncome() != null) {
            incubatorEntity.setIncubatorIncome(
                    IncubatorDtoToEntityConverter.convertToIncomeEntity(incubatorDto.getIncubatorIncome(), incubatorEntity));
        }

        if (incubatorDto.getIncubatorInvestment() != null) {
            incubatorEntity.setIncubatorInvestment(
                    IncubatorDtoToEntityConverter.convertToInvestmentEntity(incubatorDto.getIncubatorInvestment(), incubatorEntity));
        }

        if (incubatorDto.getIncubatorExpense() != null) {
            incubatorEntity.setIncubatorExpense(
                    IncubatorDtoToEntityConverter.convertToExpenseEntity(incubatorDto.getIncubatorExpense(), incubatorEntity));
        }

        if (incubatorDto.getIncubatorProjects() != null && !incubatorDto.getIncubatorProjects().isEmpty()) {
            List<IncubatorProjectsEntity> projectsEntities = new ArrayList<>();
            for (IncubatorProjectsDto projectDto : incubatorDto.getIncubatorProjects()) {
                projectsEntities.add(IncubatorDtoToEntityConverter.convertToProjectsEntity(projectDto, incubatorEntity));
            }
            incubatorEntity.setIncubatorProjects(projectsEntities);
        }

        // Save only the parent entity - cascade will save all related entities
        incubatorRepository.save(incubatorEntity);
    }

    public IncubatorDto findByUuid(String uuid) {
        var optionalIncubator = incubatorRepository.findByUuid(uuid);
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        Assert.notNull(authentication, "Authentication is required");
        var userAuthentication = (UserAuthentication) authentication.getPrincipal();

        if (optionalIncubator.isEmpty()) {
            log.warn("Incubator with uuid {} not found", uuid);
            throw new IncubatorNotFoundException();
        }

        var incubator = optionalIncubator.get();
        if (userAuthentication.getRole() != Role.ADMIN && incubator.getManager().getId() != userAuthentication.getUserId()) {
            log.warn("User [{}] does not have access to incubator [{}]", userAuthentication.getPrincipal(),
                    incubator.getName());
            throw new IncubatorNotAvailableException();
        }

        return convertToDto(incubator);
    }
}
