package xyz.vegaone.andalusiabe.service;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import xyz.vegaone.andalusiabe.domain.UserEntity;
import xyz.vegaone.andalusiabe.dto.User;
import xyz.vegaone.andalusiabe.repo.UserRepo;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepo userRepo;

    private Mapper mapper;

    public UserService(UserRepo userRepo, Mapper mapper) {
        this.userRepo = userRepo;
        this.mapper = mapper;
    }

    public User getUser(Long id) {
        UserEntity userEntity = userRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapUserAndRemoveUserListFromOrganization(userEntity);
    }

    public List<User> getAllUsers() {
        List<UserEntity> userEntityList = userRepo.findAll();

        return userEntityList
                .stream()
                .map(this::mapUserAndRemoveUserListFromOrganization)
                .collect(Collectors.toList());
    }

    public List<User> getAllUsersByOrganizationId(Long id) {
        List<UserEntity> userEntityList = userRepo.findAllByOrganizationId(id);

        return userEntityList
                .stream()
                .map(this::mapUserAndRemoveUserListFromOrganization)
                .collect(Collectors.toList());
    }

    public User createUser(User User) {
        UserEntity userEntity = userRepo.save(mapper.map(User, UserEntity.class));
        return mapUserAndRemoveUserListFromOrganization(userEntity);
    }

    public User updateUser(User User) {
        UserEntity userEntity = userRepo.save(mapper.map(User, UserEntity.class));
        return mapUserAndRemoveUserListFromOrganization(userEntity);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    /**
     * Breaks circular reference of Organization that has a list of Users that have an Organization that has a list of
     * Users.
     *
     * @param userEntity the user that will have it's circular reference fixed
     * @return the organization
     */
    private User mapUserAndRemoveUserListFromOrganization(UserEntity userEntity) {
        User user = mapper.map(userEntity, User.class);
        if (user.getOrganization() != null) {
            user.getOrganization().setUsers(null);
        }
        return user;
    }
}
