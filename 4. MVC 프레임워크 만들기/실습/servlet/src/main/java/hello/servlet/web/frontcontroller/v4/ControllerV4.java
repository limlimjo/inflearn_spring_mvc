package hello.servlet.web.frontcontroller.v4;

import java.util.Map;

// V4의 핵심: 기본적인 구조는 V3와 같으며,
// 대신에 컨트롤러가 ModelView를 반환하지 않고, ViewName을 반환함
public interface ControllerV4 {

    /**
     * @param paramMap
     * @param model
     * @return viewName
     */
    String process(Map<String, String> paramMap, Map<String, Object> model);
}
