package com.ian.spring_practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") // HTTP GET 요청이 들어올 때, 해당 요청을 처리하는 메서드와 매핑
    public String hello(Model model) { // 컨트롤러와 뷰 사이에서 데이터를 전달하는 객체
        model.addAttribute("data", "Hello!"); // 모델에 데이터를 저장 후 뷰로 전달, 모델은 Map 인터페이스를 확장하기 때문에 키-값 형식으로 값을 저장
        return "hello"; // ViewResolver가 view 파일 반환 (반환되는 view 파일의 경로는 suffix와 prefix로 지정)
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model) { // URL을 통해 HTTP에서 요청하는 쿼리 파라미터를 받아옴
        model.addAttribute("name", name);
        return "hello-mvc";
    }

    @GetMapping("hello-string")
    @ResponseBody // 메서드의 반환값을 HTTP body에 직접 반환하면서 응답, ViewResolver가 view 파일을 반환하는 일반 메서드와 달리 HttpMessageConverter가 동작
    public String helloString(@RequestParam(value = "name") String name) {
        return "hello" + name; // 메서드의 반환값을 그대로 반환했기 때문에 반환 결과는 view 파일이 아닌 문자열이 그대로 나옴, 반환 타입이 String이기 때문에 ViewResolver가 아닌 StringConverter가 동작 -> StringHttpMessageConverter
    }

    /* API 방식
    * 컨트롤러에서 내부 클래스를 정의하여 응답 데이터를 JSON 형식으로 반환
    * 주로 RESTful API를 구현할 때 사용
    * */
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam(value = "name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 반환값이 메모리 주소가 아닌 JSON인 이유는 Spring이 자동으로 직렬화(객체를 JSON으로 변환)해주기 때문, 반환 타입이 객체이기 때문에 ViewResolver가 아닌 JSONConverter가 동작 -> MappingJackson2HttpMessageConverter
    }
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

}
