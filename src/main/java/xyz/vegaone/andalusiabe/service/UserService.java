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
        return mapper.map(userEntity, User.class);
    }

    public List<User> getAllUsers() {
        List<UserEntity> userEntityList = userRepo.findAll();

        return userEntityList
                .stream()
                .map(userEntity -> mapper.map(userEntity, User.class))
                .collect(Collectors.toList());
    }

    public List<User> getAllUsersByOrganizationId(Long id) {
        List<UserEntity> userEntityList = userRepo.findAllByOrganizationId(id);

        return userEntityList
                .stream()
                .map(userEntity -> mapper.map(userEntity, User.class))
                .collect(Collectors.toList());
    }

    public User createUser(User User) {
        UserEntity userEntity = userRepo.save(mapper.map(User, UserEntity.class));
        return mapper.map(userEntity, User.class);
    }

    public User updateUser(User User) {
        UserEntity userEntity = userRepo.save(mapper.map(User, UserEntity.class));
        return mapper.map(userEntity, User.class);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

}
