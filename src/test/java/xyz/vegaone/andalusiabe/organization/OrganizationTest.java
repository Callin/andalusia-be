package xyz.vegaone.andalusiabe.organization;

import org.dozer.Mapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.vegaone.andalusiabe.domain.OrganizationEntity;
import xyz.vegaone.andalusiabe.dto.Organization;
import xyz.vegaone.andalusiabe.repo.OrganizationRepo;
import xyz.vegaone.andalusiabe.service.OrganizationService;

import javax.persistence.EntityNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationTest {

    private final static Long ID = 1L;
    private final static String ORGANIZATION_NAME = "Organization name";
    private final static String ORGANIZATION_NEW_NAME = "Organization name two";
    private final static String ORGANIZATION_DESCRIPTION = "Organization description";

    @Autowired
    private Mapper mapper;

    @Autowired
    private OrganizationRepo organizationRepo;

    @Autowired
    private OrganizationService organizationService;

    @Test
    public void getOrganizationTest() {
        // given
        OrganizationEntity organizationEntity = organizationRepo.save(buildOrganizationEntity());

        // when
        Organization organization = organizationService.getOrganization(organizationEntity.getId());

        // then
        Assert.assertEquals("The id should have matched", organizationEntity.getId(), organization.getId());
        Assert.assertEquals("The name should have matched", organizationEntity.getName(), organization.getName());
        Assert.assertEquals("The description should have matched", organizationEntity.getDescription(), organization.getDescription());
    }

    @Test
    public void updateOrganizationTest() {
        // given
        OrganizationEntity organizationEntity = organizationRepo.save(buildOrganizationEntity());
        organizationEntity.setName(ORGANIZATION_NEW_NAME);

        // when
        Organization organization = organizationService.updateOrganization(mapper.map(organizationEntity, Organization.class));

        // then
        Assert.assertEquals("The id should have matched", organizationEntity.getId(), organization.getId());
        Assert.assertEquals("The name should have matched", organizationEntity.getName(), organization.getName());
        Assert.assertEquals("The description should have matched", organizationEntity.getDescription(), organization.getDescription());
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteOrganizationTest() {
        // given
        OrganizationEntity organizationEntity = organizationRepo.save(buildOrganizationEntity());

        // when
        organizationService.deleteOrganization(organizationEntity.getId());

        // then
        organizationService.getOrganization(organizationEntity.getId());
    }

    private Organization buildOrganization() {
        Organization organization = new Organization();
        organization.setId(ID);
        organization.setName(ORGANIZATION_NAME);
        organization.setDescription(ORGANIZATION_DESCRIPTION);

        return organization;
    }

    private OrganizationEntity buildOrganizationEntity() {
        OrganizationEntity organizationEntity = new OrganizationEntity();
        organizationEntity.setName(ORGANIZATION_NAME);
        organizationEntity.setDescription(ORGANIZATION_DESCRIPTION);

        return organizationEntity;
    }
}
