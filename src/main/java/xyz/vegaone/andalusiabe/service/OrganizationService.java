package xyz.vegaone.andalusiabe.service;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import xyz.vegaone.andalusiabe.domain.OrganizationEntity;
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

    public xyz.vegaone.andalusiabe.dto.Organization getOrganization(Long id) {
        OrganizationEntity organizationEntity = organizationRepo.findById(id).orElseThrow(EntityNotFoundException::new);

        return mapOrganizationAndRemoveOrganizationFromUser(organizationEntity);
    }

    public List<xyz.vegaone.andalusiabe.dto.Organization> getAllOrganizations() {
        List<OrganizationEntity> organizationEntityEntityList = organizationRepo.findAll();
        return organizationEntityEntityList
                .stream()
                .map(this::mapOrganizationAndRemoveOrganizationFromUser)
                .collect(Collectors.toList());
    }


    public xyz.vegaone.andalusiabe.dto.Organization createOrganization(xyz.vegaone.andalusiabe.dto.Organization organization) {
        OrganizationEntity organizationEntity =
                organizationRepo.save(mapper.map(organization, OrganizationEntity.class));
        return mapOrganizationAndRemoveOrganizationFromUser(organizationEntity);
    }

    public xyz.vegaone.andalusiabe.dto.Organization updateOrganization(xyz.vegaone.andalusiabe.dto.Organization organization) {
        OrganizationEntity organizationEntity =
                organizationRepo.save(mapper.map(organization, OrganizationEntity.class));
        return mapOrganizationAndRemoveOrganizationFromUser(organizationEntity);
    }

    public void deleteOrganization(Long id) {
        organizationRepo.deleteById(id);
    }

    /**
     * Breaks circular reference of OrganizationEntity that has a list of Users that have an OrganizationEntity that has a list of
     * USers.
     *
     * @param organizationEntity the organizationEntity that will have it's circular reference fixed
     * @return the organizationEntity
     */
    private xyz.vegaone.andalusiabe.dto.Organization mapOrganizationAndRemoveOrganizationFromUser(OrganizationEntity organizationEntity) {
        xyz.vegaone.andalusiabe.dto.Organization organization = mapper.map(organizationEntity, xyz.vegaone.andalusiabe.dto.Organization.class);
        if (organization.getUsers() != null) {
            organization.getUsers().forEach(user -> user.setOrganization(null));
        }
        return organization;
    }
}
