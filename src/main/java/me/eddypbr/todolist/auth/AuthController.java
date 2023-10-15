package me.eddypbr.todolist.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import me.eddypbr.todolist.user.UserModel;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @GetMapping
  public ResponseEntity delete(HttpServletRequest request) {
    UserModel user = (UserModel) request.getAttribute("user");

    if (user == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
    }

    return ResponseEntity.status(HttpStatus.OK).body(user);
  }
}
