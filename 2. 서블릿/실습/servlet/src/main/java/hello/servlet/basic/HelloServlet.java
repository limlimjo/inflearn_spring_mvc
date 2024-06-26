package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// 톰캣 서버는 내부에 서블릿 컨테이너 기능을 가지고 있음
// 서블릿 컨테이너를 통해서 서블릿을 생성함
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    // Ctrl + O로 자동생성
    // 서블릿이 호출되면 아래 서비스 메서드가 호출됨
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service"); // soutm
        System.out.println("request = " + request); // soutv
        System.out.println("response = " + response); // soutv

        String username = request.getParameter("username"); //option + command + v (변수 자동완성)
        System.out.println("username = " + username);

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        // http body에 메시지가 들어감
        response.getWriter().write("hello " + username);
    }
}
