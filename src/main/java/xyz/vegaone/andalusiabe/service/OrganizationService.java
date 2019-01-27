package xyz.vegaone.andalusiabe.service;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import xyz.vegaone.andalusiabe.domain.OrganizationEntity;
import xyz.vegaone.andalusiabe.domain.UserEntity;
import xyz.vegaone.andalusiabe.dto.Organization;
import xyz.vegaone.andalusiabe.repo.OrganizationRepo;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationService {

    private OrganizationRepo organizationRepo;

    private Mapper mapper;

    public OrganizationService(OrganizationRepo organizationRepo, Mapper mapper) {
        this.organizationRepo = organizationRepo;
        this.mapper = mapper;
    }

    public Organization getOrganization(Long id) {
        OrganizationEntity organizationEntity = organizationRepo.findById(id).orElseThrow(EntityNotFoundException::new);

        return mapper.map(organizationEntity, Organization.class);
    }

    public List<Organization> findAllByUsersIsContaining(Long userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);

        return organizationRepo.findAllByUsersIsContaining(userEntity)
                .stream()
                .map(organizationEntity -> mapper.map(organizationEntity, Organization.class))
                .collect(Collectors.toList());
    }

    public List<Organization> getAllOrganizations() {
        List<OrganizationEntity> organizationEntityEntityList = organizationRepo.findAll();
        return organizationEntityEntityList
                .stream()
                .map(organizationEntity -> mapper.map(organizationEntity, Organization.class))
                .collect(Collectors.toList());
    }


    public Organization createOrganization(Organization organization) {
        OrganizationEntity organizationEntity =
                organizationRepo.save(mapper.map(organization, OrganizationEntity.class));
        return mapper.map(organizationEntity, Organization.class);
    }

    public Organization updateOrganization(Organization organization) {
        OrganizationEntity organizationEntity =
                organizationRepo.save(mapper.map(organization, OrganizationEntity.class));
        return mapper.map(organizationEntity, Organization.class);
    }

    public void deleteOrganization(Long id) {
        organizationRepo.deleteById(id);
    }
}
