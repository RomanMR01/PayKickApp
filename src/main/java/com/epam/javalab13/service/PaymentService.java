package com.epam.javalab13.service;

import java.util.HashMap;
import java.util.Map;

import com.liqpay.LiqPay;

public class PaymentService {


    private static final String PUBLIC_KEY = "PUBLIC_KEY";
    private static final String PRIVATE_KEY = "PRIVATE_KEY";
    private static final String serverUrl = "https://domenName/LiqpayCallbackServlet";


    /**
     *
     * @return String html form
     */
    public String refillAccount(String userId){
        // Creation of the HTML-form
        Map<String,String> params = new HashMap<>();
        params.put("action", "paydonate");
        params.put("version", "3");
        params.put("amount", "1");
        params.put("currency", "UAH");
        params.put("description", "description text");
        params.put("order_id", "order_id_1");
        params.put("customer", userId);
        params.put("server_url",serverUrl);
        params.put("sandbox", "1"); // enable the testing environment and card will NOT charged. If not set will be used property isCnbSandbox()
        LiqPay liqpay = new LiqPay(PUBLIC_KEY, PRIVATE_KEY);
        String htmlForm = liqpay.cnb_form(params);
//		System.out.println(html);
        return htmlForm;
    }
    /**
     *
     * @param amount
     * @param currency USD,UAH,EUR
     * @param receiverCard
     */
    public void p2pCredit(String amount,String currency, String receiverCard){
        HashMap<String,String> params = new HashMap<>();
        params.put("action", "p2pcredit");
        params.put("version", "3");
        params.put("amount", amount);
        params.put("currency", currency);
        params.put("description", "description text");
        params.put("order_id", "order_id_1");
        params.put("receiver_card", receiverCard);
//		params.put("receiver_last_name", "LastName");
//		params.put("receiver_first_name", "FirstName");
        params.put("sandbox", "1");
        LiqPay liqpay = new LiqPay(PUBLIC_KEY, PRIVATE_KEY);
        Map<String, Object> res=null;
        try {
            res = liqpay.api("request", params);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("status "+res.get("err_code"));
    }

    public void callback(String data, String signature) {
        // TODO Auto-generated method stub

    }
}
