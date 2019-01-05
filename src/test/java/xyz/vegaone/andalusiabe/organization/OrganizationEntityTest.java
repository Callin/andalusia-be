package xyz.vegaone.andalusiabe.organization;

import org.dozer.Mapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.vegaone.andalusiabe.domain.OrganizationEntity;
import xyz.vegaone.andalusiabe.repo.OrganizationRepo;
import xyz.vegaone.andalusiabe.service.OrganizationService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationEntityTest {

    private final static Long ID = 1L;
    private final static String ORGANIZATION_NAME_ONE = "OrganizationEntity name one";
    private final static String ORGANIZATION_NAME_TWO = "OrganizationEntity name two";
    private final static String ORGANIZATION_DESCRIPTION = "OrganizationEntity description";

    @Autowired
    private Mapper mapper;

    @Autowired
    private OrganizationRepo organizationRepo;

    @Autowired
    private OrganizationService organizationService;

    @After
    public void after() {
        organizationRepo.deleteAll();
    }

    @Test
    public void getOrganizationTest() {
        // given
        OrganizationEntity organizationEntity = organizationRepo.save(buildOrganizationEntity(ORGANIZATION_NAME_ONE));

        // when
        xyz.vegaone.andalusiabe.dto.Organization organization = organizationService.getOrganization(organizationEntity.getId());

        // then
        Assert.assertEquals("The id should have matched", organizationEntity.getId(), organization.getId());
        Assert.assertEquals("The name should have matched", organizationEntity.getName(), organization.getName());
        Assert.assertEquals("The description should have matched", organizationEntity.getDescription(), organization.getDescription());
    }

    @Test
    public void getAllOrganizationsTest() {
        // given
        organizationRepo.save(buildOrganizationEntity(ORGANIZATION_NAME_ONE));
        organizationRepo.save(buildOrganizationEntity(ORGANIZATION_NAME_TWO));

        // when
        List<xyz.vegaone.andalusiabe.dto.Organization> organizationList = organizationService.getAllOrganizations();

        // then
        Assert.assertEquals("There should have been 2 organizations", 2, organizationList.size());
    }

    @Test
    public void updateOrganizationTest() {
        // given
        OrganizationEntity organizationEntity = organizationRepo.save(buildOrganizationEntity(ORGANIZATION_NAME_ONE));
        organizationEntity.setName(ORGANIZATION_NAME_TWO);

        // when
        xyz.vegaone.andalusiabe.dto.Organization organization = organizationService.updateOrganization(mapper.map(organizationEntity, xyz.vegaone.andalusiabe.dto.Organization.class));

        // then
        Assert.assertEquals("The id should have matched", organizationEntity.getId(), organization.getId());
        Assert.assertEquals("The name should have matched", organizationEntity.getName(), organization.getName());
        Assert.assertEquals("The description should have matched", organizationEntity.getDescription(), organization.getDescription());
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteOrganizationTest() {
        // given
        OrganizationEntity organizationEntity = organizationRepo.save(buildOrganizationEntity(ORGANIZATION_NAME_ONE));

        // when
        organizationService.deleteOrganization(organizationEntity.getId());

        // then
        organizationService.getOrganization(organizationEntity.getId());
    }

    private xyz.vegaone.andalusiabe.dto.Organization buildOrganization() {
        xyz.vegaone.andalusiabe.dto.Organization organization = new xyz.vegaone.andalusiabe.dto.Organization();
        organization.setId(ID);
        organization.setName(ORGANIZATION_NAME_ONE);
        organization.setDescription(ORGANIZATION_DESCRIPTION);

        return organization;
    }

    private OrganizationEntity buildOrganizationEntity(String name) {
        OrganizationEntity organizationEntity = new OrganizationEntity();
        organizationEntity.setName(name);
        organizationEntity.setDescription(ORGANIZATION_DESCRIPTION);

        return organizationEntity;
    }
}
