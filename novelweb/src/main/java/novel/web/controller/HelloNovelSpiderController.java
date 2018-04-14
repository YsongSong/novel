package novel.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Yang on 2018/4/14 0014.
 */

@Controller
public class HelloNovelSpiderController {

    @RequestMapping(value = "hello.do", method = RequestMethod.GET)
    @ResponseBody
    public A hello() {
        A a = new A();
        a.setMessage("Hello Novel!");
        return a;
    }
}

class A {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}