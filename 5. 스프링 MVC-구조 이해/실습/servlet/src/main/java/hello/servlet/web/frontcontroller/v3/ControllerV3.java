package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

// V3의 핵심: 서블릿 종속성 제거, 뷰 이름 중복 제거
public interface ControllerV3 {

    // HttpServeltRequest, HttpServletResponse를 사용하지 않음으로써 서블릿에 종속적이지 않게 됨
    ModelView process(Map<String, String> paramMap);
}
