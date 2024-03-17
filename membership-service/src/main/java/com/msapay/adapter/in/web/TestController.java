package com.msapay.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    @GetMapping(path="/test")
    void test(){
        System.out.println("Hello");
    }
    // 외부로부터의 http요청, 외부에서 내부로 들어오는 어뎁터 역할을 한다.
    //
}
