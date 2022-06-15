package io.github.basithnurfaizin.payment.briva;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.basithnurfaizin.payment.briva.dto.*;

import java.io.IOException;

public interface BRIVAService {

    /**
     * @param request is DTO for create virtual account
     * @return response of BaseResponse with data Virtual Account
     */
    BaseResponseBRIVA<VirtualAccountResponse> createVirtualAccount(VirtualAccountRequest request)  throws JsonProcessingException,
            InterruptedException;

    /**
     * @param institutionCode  is your institution code
     * @param accountNumber is virtual account number default 777777 for sandbox
     * @param customerCode is number unique customer
     * @return response virtual account
     */
    BaseResponseBRIVA<VirtualAccountResponse> getVirtualAccountInfo(String institutionCode, Long accountNumber, String customerCode) throws IOException, InterruptedException;

    /**
     * @param institutionCode  is your institution code
     * @param accountNumber is virtual account number default 777777 for sandbox
     * @param customerCode is number unique customer
     * @return String "Y" is paid and "N" is unpaid
     */
    String getStatusPayment(String institutionCode, Long accountNumber, String customerCode) throws IOException, InterruptedException;


    /**
     * @param request is DTO for update payment status
     * @return StatusResponse
     */
    BaseResponseBRIVA<StatusResponse> updateStatusPayment(StatusRequest request) throws IOException, InterruptedException;

    BaseResponseBRIVA<VirtualAccountResponse> updateVirtualAccount(VirtualAccountRequest request) throws IOException, InterruptedException;
}
