package xyz.vegaone.andalusiabe.service;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import xyz.vegaone.andalusiabe.domain.OrganizationEntity;
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

        return mapOrganizationAndRemoveOrganizationFromUser(organizationEntity);
    }

    public List<Organization> getAllOrganizations() {
        List<OrganizationEntity> organizationEntityList = organizationRepo.findAll();
        return organizationEntityList
                .stream()
                .map(this::mapOrganizationAndRemoveOrganizationFromUser)
                .collect(Collectors.toList());
    }


    public Organization createOrganization(Organization organization) {
        OrganizationEntity organizationEntity =
                organizationRepo.save(mapper.map(organization, OrganizationEntity.class));
        return mapOrganizationAndRemoveOrganizationFromUser(organizationEntity);
    }

    public Organization updateOrganization(Organization organization) {
        OrganizationEntity organizationEntity =
                organizationRepo.save(mapper.map(organization, OrganizationEntity.class));
        return mapOrganizationAndRemoveOrganizationFromUser(organizationEntity);
    }

    public void deleteOrganization(Long id) {
        organizationRepo.deleteById(id);
    }

    /**
     * Breaks circular reference of Organization that has a list of Users that have an Organization that has a list of
     * USers.
     *
     * @param organizationEntity the organization that will have it's circular reference fixed
     * @return the organization
     */
    private Organization mapOrganizationAndRemoveOrganizationFromUser(OrganizationEntity organizationEntity) {
        Organization organization = mapper.map(organizationEntity, Organization.class);
        if (organization.getUserList() != null) {
            organization.getUserList().forEach(user -> user.setOrganization(null));
        }
        return organization;
    }
}
