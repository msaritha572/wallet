package com.example.wallet.controller;

import com.example.wallet.service.EsftAccountStatusImpl;
import com.example.wallet.service.EsftAccountStatusService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eimpapi")
public class EsftController {
    String appName ="ESFT";

    @PutMapping("/changePassword")
    public void changePasswordEsft(){
        EsftAccountStatusService impl = new EsftAccountStatusImpl();
        try {
            if (appName.equals("ESFT")) {
                impl.updateAccountStatus("symo", "active");
            }
        }catch(Exception ex){
        logger.error("Exception while calling esft Accountstatus", ex);
        }

    }

}
