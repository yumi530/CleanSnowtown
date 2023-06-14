package com.project.smartclean.sms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.smartclean.sms.dto.SmsResponseDto;
import com.project.smartclean.sms.model.Request;
import com.project.smartclean.sms.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestClientException;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Controller
@RequiredArgsConstructor
public class SmsController {

    private final SmsService smsService;

//    @PostMapping("/user/sms")
//    public ResponseEntity<SmsResponse> test(@RequestBody Request request) throws NoSuchAlgorithmException, URISyntaxException, UnsupportedEncodingException, InvalidKeyException, JsonProcessingException {
//        SmsResponse data = smsService.sendSms(request.getRecipientPhoneNumber(), request.getContent());
//        return ResponseEntity.ok().body(data);
//    }

    @GetMapping("/admin/send.do")
    public String getSmsPage() {
        return "admin/pickup/sendSms";
    }

    @PostMapping("/admin/sms/send.do")
    public String sendSms(Request request, Model model) throws JsonProcessingException, RestClientException, URISyntaxException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        SmsResponseDto response = smsService.sendSms(request.getRecipientPhoneNumber(), request.getContent());
        model.addAttribute("response", response);
        return "admin/pickup/smsResult";
    }
}
