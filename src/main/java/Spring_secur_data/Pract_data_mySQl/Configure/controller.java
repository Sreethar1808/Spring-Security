package Spring_secur_data.Pract_data_mySQl.Configure;



import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/control")
public class controller {

    @Autowired
    private UserInitializer userInitializer;

    @Autowired
    private CustomuserDetails customuserDetails;

    @PostMapping("/register")
    public User addRegister(@RequestBody User user){
        return userInitializer.addUsers(user);
    }

    @GetMapping("/getStudent")
    @PreAuthorize("hasRole('admin')")
    public String add() {
        return "welcome";
    }


    @GetMapping("/csrf")
    public CsrfToken gettoken(HttpServletRequest request)
    {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping("/role")
    @PreAuthorize("principal.username == 'sree'")
    public String getrole()
    {
        return "Student";
    }


    @PostMapping("/login")
    public String getlogin(@RequestBody User user)
    {
        return userInitializer.verify(user);
    }
}

