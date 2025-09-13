/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public Optional<User> getUserById(Long id) {
    return userRepository.findById(id);
  }

  public Optional<User> getUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public User createUser(User user) {
    return userRepository.save(user);
  }

  public User updateUser(Long id, User updatedUser) {
    return userRepository.findById(id).map(existingUser -> {
      if (updatedUser.getEmail() != null) {
        existingUser.setEmail(updatedUser.getEmail());
      }
      if (updatedUser.getPassword() != null) {
        existingUser.setPassword(updatedUser.getPassword());
      }
      if (updatedUser.getFullName() != null) {
        existingUser.setFullName(updatedUser.getFullName());
      }
      existingUser.setUpdatedAt(LocalDateTime.now());
      return userRepository.save(existingUser);
    }).orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }
}
