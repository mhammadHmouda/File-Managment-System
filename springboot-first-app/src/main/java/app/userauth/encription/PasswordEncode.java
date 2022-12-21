package app.userauth.encription;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncode {

    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
