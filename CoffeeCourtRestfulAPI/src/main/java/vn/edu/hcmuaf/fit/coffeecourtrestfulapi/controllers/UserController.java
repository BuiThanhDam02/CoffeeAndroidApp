package vn.edu.hcmuaf.fit.coffeecourtrestfulapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.Role;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.RoleName;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.models.User;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.RoleRepository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.repositories.UserRepository;
import vn.edu.hcmuaf.fit.coffeecourtrestfulapi.services.UserService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;




@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserService userService;

    @GetMapping("/all")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findOneById(id);
    }

    @GetMapping("/paged")
    public List<User> getAllUserPaged(Pageable pageable) {
        return userRepository.findAll(pageable).getContent();
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updateUser) {
        Optional<User> existingUser  = Optional.ofNullable(userRepository.findOneById(userId));

        if(existingUser.isPresent()) {
            User user = existingUser.get();

            user.setName(Optional.ofNullable(updateUser.getName()).orElse(user.getName()));
            user.setEmail(Optional.ofNullable(updateUser.getEmail()).orElse(user.getEmail()));
            user.setAddress(Optional.ofNullable(updateUser.getAddress()).orElse(user.getAddress()));
            user.setPhone(Optional.ofNullable(updateUser.getPhone()).orElse(user.getPhone()));

            userRepository.save(user);

            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<Void> forgotPassword(@RequestParam String email) {
        User user = userRepository.findByEmail(email);
        System.out.println("user: " + user);
        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String newPassword = generateRandomPassword();

        String content = "Cập lại mật khẩu: " + "\t" + newPassword;
        sendMail(user.getEmail(), "Forgot Email", content);
        user.setPassword(userService.hashPassword(newPassword));
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);

    }


    private String generateRandomPassword(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();

        StringBuilder newPassword = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            newPassword.append(characters.charAt(random.nextInt(characters.length())));
        }

        return newPassword.toString();
    }

    private boolean sendMail(String to, String subject, String text) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("thaha8788@gmail.com", "vcfkhctyluiorzpw");
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setHeader("Content-Type", "text/plain; charset=UTF-8");
            message.setFrom(new InternetAddress("thaha8788@gmail.com"));
            message.setReplyTo(InternetAddress.parse("thaha8788@gmail.com", false));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
