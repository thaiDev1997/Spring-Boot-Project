package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ResponseController {

    @RequestMapping(value = "/response", method = {RequestMethod.GET, RequestMethod.PUT})
    public Map<String, Object> response(@RequestParam(value = "code_id") Integer codeId, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", new Date().getTime());
        response.put("path", request.getRequestURL());
        response.put("code", codeId);
        response.put("status", HttpStatus.valueOf(codeId));
        switch (codeId) {
            case 200:
                response.put("message", "Success/成功");
                break;
            case 401:
                response.put("message", "Authorization Required /認証エラー");
                break;
            case 402:
                response.put("message", "Failed to send SMS due the Overlimit/送信上限でエラー。営業かサポートまで。");
                break;
            case 414:
                response.put("message", "URL is longer than 8190bytes /リクエストしている URL が長過ぎる（GET リクエストの場合）");
                break;
            case 550:
                response.put("message", "Failure/失敗");
                break;
            case 555:
                response.put("message", "Your IP address has been blocked/IP アドレスは不正な値としてシステムへ 登録されている（401 を連続して 5 回繰り 返すと不正とみなされる）");
                break;
            case 560:
                response.put("message", "Mobile number is invalid /mobilenumber の値が不正");
                break;
            case 562:
                response.put("message", "Start date is invalid/sms 送信時間が無効");
                break;
            case 568:
                response.put("message", "Au SMS title is invalid /autitle の値が不正");
                break;
            case 569:
                response.put("message", "Softbank SMS title is invalid/Softbanktitle の値が不正");
                break;
            case 570:
                response.put("message", "Sms text id is invalid /smstextid の値が不正");
                break;
            case 571:
                response.put("message", "Sending attempts is invalid/Sending attempts の値が不正");
                break;
            case 572:
                response.put("message", "Resending interval is invalid./Resending の値が不正");
                break;
            case 573:
                response.put("message", "Status is invalid/Status の値が不正");
                break;
            case 574:
                response.put("message", "SMS ID is invalid/SMS ID の値が不正");
                break;
            case 575:
                response.put("message", "Docomo is invalid/Docomo の値が不正");
                break;
            case 576:
                response.put("message", "au is invalid /Au の値が不正");
                break;
            case 577:
                response.put("message", "Soft Bank is invalid /Soft Bank の値が不正");
                break;
            case 579:
                response.put("message", "Gateway is invalid/Gateway の値が不正");
                break;
            case 580:
                response.put("message", "Sms title is invalid/smstitle の値が不正");
                break;
            case 585:
                response.put("message", "Sms text is invalid/smstext の値が不正\n");
                break;
            case 590:
                response.put("message", "Original URL is invalid/Original URL の値が不正\n");
                break;
            case 598:
                response.put("message", "Docomo SMS title is invalid/Docomotitle の値が不正");
                break;
            case 599:
                response.put("message", "Resending is disabled/Resending の値が不正");
                break;
            case 601:
                response.put("message", "SMS title function is disabled/SMS 送信元選択機能が OFF になっております。メディア 4u までご連絡ください。");
                break;
            case 605:
                response.put("message", "Invalid type/type で選択した値が無い");
                break;
            case 606:
                response.put("message", "This API is disabled/他人判定利用が無効。");
                break;
            case 608:
                response.put("message", "Invalid Registration date/registrationdate の値が無効。※24 カ月 前まで。");
                break;
            case 610:
                response.put("message", "HLR is disabled /HLR の機能が無効です。");
                break;
            case 616:
                response.put("message", "Memo is disabled/メモ API 機能が無効。メディア 4u へ依頼。");
                break;
            default:
                response.put("message", "Not Found/見つかりません");
            }
        return response;
    }
}
