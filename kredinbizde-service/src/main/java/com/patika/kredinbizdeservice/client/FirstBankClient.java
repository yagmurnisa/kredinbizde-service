package com.patika.kredinbizdeservice.client;

import com.patika.kredinbizdeservice.client.dto.request.FirstBankCardAppReq;
import com.patika.kredinbizdeservice.client.dto.request.FirstBankLoanAppReq;
import com.patika.kredinbizdeservice.client.dto.response.ApplicationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "firstbank-service", url = "localhost:8081")
public interface FirstBankClient {

    @PostMapping("api/firstbank/v1/applications/loans")
    ApplicationResponse createLoanApplication(@RequestBody FirstBankLoanAppReq request);
    @PostMapping("api/firstbank/v1/applications/cards")
    ApplicationResponse createCardApplication(@RequestBody FirstBankCardAppReq request);
}
