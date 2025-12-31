package sb_you_e8.sb_you_e8.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Demo4Condition {

    public boolean condition(){
        var a = SecurityContextHolder.getContext().getAuthentication();
        return true;    // write your conditions
    }
}
