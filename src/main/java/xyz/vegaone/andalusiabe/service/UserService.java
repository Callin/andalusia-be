package xyz.vegaone.andalusiabe.service;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import xyz.vegaone.andalusiabe.domain.UserEntity;
import xyz.vegaone.andalusiabe.dto.User;
import xyz.vegaone.andalusiabe.repo.UserRepo;
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
        UserEntity UserEntity = userRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapper.map(UserEntity, User.class);
    }

    public List<User> getAllUsers() {
        List<UserEntity> UserEntityList = userRepo.findAll();

        return UserEntityList
                .stream()
                .map(UserEntity -> mapper.map(UserEntity, User.class))
                .collect(Collectors.toList());
    }

    public User createUser(User User) {
        UserEntity UserEntity = userRepo.save(mapper.map(User, UserEntity.class));
        return mapper.map(UserEntity, User.class);
    }

    public User updateUser(User User) {
        UserEntity UserEntity = userRepo.save(mapper.map(User, UserEntity.class));
        return mapper.map(UserEntity, User.class);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
