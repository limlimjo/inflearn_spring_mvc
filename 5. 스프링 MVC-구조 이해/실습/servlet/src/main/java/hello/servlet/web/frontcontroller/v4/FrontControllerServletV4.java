package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    // 서블릿이 생성될 때 여기에 값을 넣어놓게 됨
    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV4.service");

        // 1. 요청 URI 추출해서 requsetURI에 담아줌
        String requestURI = request.getRequestURI();

        // 2. controllerMap에서 키값(requestURI)으로 조회해와 controller 변수에 담아줌
        ControllerV4 controller = controllerMap.get(requestURI);

        // controller가 null이면 상태코드 404 던져줌
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // V4는 기본적인 구조는 V3와 같으나
        // 컨트롤러가 ModelView를 반환하지 않고 ViewName만 반환함
        // 3. HttpServletRequest에서 파라미터 정보를 꺼내서 Map으로 변환
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();
        String viewName = controller.process(paramMap, model);

        // 4. 논리 뷰 이름 -> 물리 뷰 경로로 변경
        MyView view = viewResolver(viewName);

        // 5. 뷰 객체를 통해서 HTML 화면 렌더링
        view.render(model, request, response);
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