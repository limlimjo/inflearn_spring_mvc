package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloData {
    // command + N: getter/setter 생성
    private String username;
    private int age;
}