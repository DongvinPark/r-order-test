package com.example.rordertest.controller;

import com.example.rordertest.redis.OrderCacheRepository;
import com.example.rordertest.service.TestService;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class OrderController {

    //메인페이지에서 뷰를 리턴해본다.
    @GetMapping("/")
    public String getRoot(Model model){
        return "order";
    }

    //사용자가 값을 입력해서 전송하면 서버에서 그것에 대한 작업을 처리하지만 프런트로 뭔가를 전달하지는 않는다.
    @PostMapping("/order.do")
    public void testOrderReceive (HttpServletRequest request) {
        System.out.println("프런트가 준 값 : " + request.getParameter("mobile_number"));
    }

    //사용자가 값을 입력해서 전송하면, 서버에서 작업을 처리한 후, 처리한 값이 반영된 화면을 보내준다.
    @GetMapping("/find.do")
    public String testGetOrderNumber(Model model, HttpServletRequest request){
        System.out.println("find.do 호출!");
        model.addAttribute("serverResult", request.getParameter("mobile_number_for_order_number"));
        return "order";
    }

}
