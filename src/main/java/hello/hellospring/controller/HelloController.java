package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        // 템플릿에 "data" : "hello!" 라는 데이터를 ViewResolver에게 전달
        model.addAttribute("data", "hello!");
        // ViewResolver가 /resources/templates 내에 존재하는 "hello"라는 html로 데이터와 함께 렌더링
        return "hello";
    }

    @GetMapping("hello-mvc")
    // required = false로 설정하면 name 값을 전달하지 않아도 됨 (기본값은 true)
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    //
    @GetMapping("hello-string")
    // @ResponseBody 사용시에는 ViewResolver가 템플릿을 찾는것이 아니라, HttpMessageConverter가 동작하여 HTTP body로 값을 전달
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        // String을 리턴할때는 HttpMessageConverter 하위의 StringHttpMessageConverter가 동작
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        // 객체를 전달하게되면 HttpMessageConverter 하위의 MappingJackson2HttpMessageConverter가 기본값으로 동작하여 JSON 객체로 전달
        return hello;
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