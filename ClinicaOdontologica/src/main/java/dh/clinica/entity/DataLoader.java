package dh.clinica.entity;

import dh.clinica.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    private final IUserRepository userRepository;

    @Autowired
    public DataLoader(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("password");
        String hashedPassword2 = passwordEncoder.encode("password2");

        userRepository.deleteAll();

        //Cuando levante la aplicación, se van a generar estos dos usuarios
        userRepository.save(new AppUser("Diego", "diego", "diego@gmail.com", hashedPassword, AppUserRoles.ADMIN));
        userRepository.save(new AppUser("Paula", "paula", "paula@gmail.com", hashedPassword2, AppUserRoles.USER));
    }
}
