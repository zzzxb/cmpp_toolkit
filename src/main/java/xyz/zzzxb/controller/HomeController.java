package xyz.zzzxb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * zzzxb
 * 2023/10/8
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("welcome", "Welcome to CMPP ToolKit");
        mav.setViewName("index");
        return mav;
    }
}
