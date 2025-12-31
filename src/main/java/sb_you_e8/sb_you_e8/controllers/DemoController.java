package sb_you_e8.sb_you_e8.controllers;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sb_you_e8.sb_you_e8.security.Demo4Condition;

import java.util.List;

@RestController
public class DemoController {

    @GetMapping("/demo")
    @PreAuthorize("hasAuthority('read')")    // hasAuthority(), hasAnyAuthority(), hasRole(), hasAnyRole()[
    public String demo(){
        return "Demo !!";
    }

    @GetMapping("/demo2")
    @PreAuthorize("hasAnyAuthority('write','read')")
    public String demo2(){
        return "Demo @@2";
    }

    @GetMapping("/demo3/{smth}")
//    @PreAuthorize(
//            """
//                    (#something == authentication.name) or
//                    hasAnyAuthority('write','read')
//                    """
//    ) // authentication from SecurityContext

    // another way
    @PreAuthorize("@Demo4Condition.condition()")
    public String demo(@PathVariable("smth") String something){

        var a = SecurityContextHolder.getContext().getAuthentication(); // returns authentication object

        return "demo 3";
    }

    // PostAuthorize mainly used when we want to restrict the access to some related value.
    // never use @PostAuthorize with methods that change data

    //PreFilter => works with either array or collection

    @GetMapping("/demo6")
    @PreFilter("filterObject.contains('a')")
    public String demo6(@RequestBody List<String> values){
        System.out.println("Values : " + values);
        return "demo 6";
    }

    //PostFilet => return type must be either a Collection or an array

//    @GetMapping("/demo7")
//    @PostFilter("filterObject.contains('a')")
//    public List<String> demo6(){
//        return List.of("adcd","derf","qaaz","wrt"); // List.of creates and immutable collection -> doubt
//    }

    // To create this create a arrayList manually and add the items into the list and return the list.
}
