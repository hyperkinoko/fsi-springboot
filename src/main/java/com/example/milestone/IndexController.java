package com.example.milestone;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("")
    public String getIndex() {
        return "index";
    }

    @GetMapping("hello")
    public String getHello(Model model) {
        int randam = new Random().nextInt(3);
        String[] omikuji = {"大吉", "中吉", "小吉"};
        model.addAttribute("name", "田中");
        model.addAttribute("omikuji", omikuji[randam]);
        return "hello";
    }
    
}
