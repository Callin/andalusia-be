package xyz.vegaone.andalusiabe.user;

import org.dozer.Mapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.vegaone.andalusiabe.domain.UserEntity;
import xyz.vegaone.andalusiabe.dto.User;
import xyz.vegaone.andalusiabe.repo.UserRepo;
import xyz.vegaone.andalusiabe.service.UserService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    private final static Long ID = 1L;
    private final static String USER_NAME_ONE = "User name one";
    private final static String USER_NAME_TWO = "User name two";
    private final static String EMAIL = "user@mail.com";

    @Autowired
    private Mapper mapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @After
    public void after() {
        userRepo.deleteAll();
    }

    @Test
    public void getUserTest() {
        // given
        UserEntity userEntity = userRepo.save(buildUserEntity(USER_NAME_ONE));

        // when
        User user = userService.getUser(userEntity.getId());

        // then
        Assert.assertEquals("The id should have matched", userEntity.getId(), user.getId());
        Assert.assertEquals("The name should have matched", userEntity.getName(), user.getName());
        Assert.assertEquals("The email should have matched", userEntity.getEmail(), user.getEmail());
    }

    @Test
    public void getAllUsersTest() {
        // given
        userRepo.save(buildUserEntity(USER_NAME_ONE));
        userRepo.save(buildUserEntity(USER_NAME_TWO));

        // when
        List<User> userList = userService.getAllUsers();

        // then
        Assert.assertEquals("There should have been 2 users", 2, userList.size());
    }

    @Test
    public void updateUserTest() {
        // given
        UserEntity userEntity = userRepo.save(buildUserEntity(USER_NAME_ONE));
        userEntity.setName(USER_NAME_TWO);

        // when
        User user = userService.updateUser(mapper.map(userEntity, User.class));

        // then
        Assert.assertEquals("The id should have matched", userEntity.getId(), user.getId());
        Assert.assertEquals("The name should have matched", userEntity.getName(), user.getName());
        Assert.assertEquals("The email should have matched", userEntity.getEmail(), user.getEmail());
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteUserTest() {
        // given
        UserEntity userEntity = userRepo.save(buildUserEntity(USER_NAME_ONE));

        // when
        userService.deleteUser(userEntity.getId());

        // then
        userService.getUser(userEntity.getId());
    }

    private User buildUser() {
        User user = new User();
        user.setId(ID);
        user.setName(USER_NAME_ONE);
        user.setEmail(EMAIL);

        return user;
    }

    private UserEntity buildUserEntity(String name) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);
        userEntity.setEmail(EMAIL);

        return userEntity;
    }
}
