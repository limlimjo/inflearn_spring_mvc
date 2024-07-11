package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    // 서블릿이 생성될 때 여기에 값을 넣어놓게 됨
    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");

        // 1. 요청 URI 추출해서 requsetURI에 담아줌
        String requestURI = request.getRequestURI();

        // 2. controllerMap에서 키값(requestURI)으로 조회해와 controller 변수에 담아줌
        ControllerV3 controller = controllerMap.get(requestURI);

        // controller가 null이면 상태코드 404 던져줌
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 앞서 V1, V2에 비해 V3에서는 frontController의 역할이 커짐
        // But, 구현 Controller는 간결해짐
        // 3. HttpServletRequest에서 파라미터 정보를 꺼내서 Map으로 변환
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        // 4. 논리 뷰 이름 -> 물리 뷰 경로로 변경
        String viewName = mv.getViewName(); //논리이름 new-form
        MyView view = viewResolver(viewName);

        // 5. 뷰 객체를 통해서 HTML 화면 렌더링
        view.render(mv.getModel(), request, response);
    }

    // 메소드 뽑아내는 단축키: option + command + M
    // HttpServletRequest에서 파라미터 정보를 꺼내서 Map으로 변환해주는 메소드
    private Map<String, String> createParamMap(HttpServletRequest request) {
        // paramMap
        Map<String, String> paramMap = new HashMap<>();
        // parameterName 다 조회하여 paramMap에 넣어줌
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

    // 컨트롤러가 반환한 논리 뷰 이름을 실제 물리 뷰 경로로 변경해주는 메소드
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}