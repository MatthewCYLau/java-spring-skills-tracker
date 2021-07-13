package com.example.tracker.auth;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {

	Optional<User> selectUserByUsername(String username);

	List<User> selectAllUsers();

	int insertUser(UUID id, User user);

	int deleteUserById(UUID id);

}
