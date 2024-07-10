package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    // 서블릿이 생성될 때 여기에 값을 넣어놓게 됨
    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV2.service");

        // 요청 URI 추출해서 requsetURI에 담아줌
        String requestURI = request.getRequestURI();

        // controllerMap에서 키값(requestURI)으로 조회해와 controller 변수에 담아줌
        ControllerV2 controller = controllerMap.get(requestURI);

        // controller가 null이면 상태코드 404 던져줌
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // controller가 null이 아니면 아래 메소드 처리
        // 만약 JSP 뿐만 아니라 다른 것까지 렌더링해야된다고 하면 MyView도 인터페이스로 설계하면 더 나은 설계가 될 수 있음
        MyView view = controller.process(request, response);
        view.render(request, response);
    }
}