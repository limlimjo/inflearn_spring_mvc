package hello.servlet.web.springmvc.v1;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//@ComponentScan
//@RequestMapping
// @Controller는 컴포넌트 스캔의 대상이 되고, RequestMappingHandlerMapping에서 쓰는 것 두 가지 역할을 함
@Controller
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
