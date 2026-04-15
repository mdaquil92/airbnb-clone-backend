package com.project.airBnbApp.dto;

import com.project.airBnbApp.entity.User;
import com.project.airBnbApp.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
public class SignUpRequestDto {
  private String email;
  private String password;
  private String name;

}
