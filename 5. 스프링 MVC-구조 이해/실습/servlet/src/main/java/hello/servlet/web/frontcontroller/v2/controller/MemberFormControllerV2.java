package hello.servlet.web.frontcontroller.v2.controller;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // v1에서처럼 dispatcher.forward()를 직접 생성해서 호출하지 않아도 됨
        // 단순히 MyView 객체를 생성하고 거기에 뷰 이름만 넣고 반환하면 됨
        return new MyView("/WEB-INF/views/new-form.jsp");
    }
}