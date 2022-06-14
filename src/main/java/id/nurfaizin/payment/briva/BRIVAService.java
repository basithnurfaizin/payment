package id.nurfaizin.payment.briva;

import com.fasterxml.jackson.core.JsonProcessingException;
import id.nurfaizin.payment.briva.dto.BaseResponseBRIVA;
import id.nurfaizin.payment.briva.dto.VirtualAccountRequest;
import id.nurfaizin.payment.briva.dto.VirtualAccountResponse;

import java.io.IOException;

public interface BRIVAService {

    BaseResponseBRIVA<VirtualAccountResponse> createVirtualAccount(VirtualAccountRequest request)  throws JsonProcessingException,
            InterruptedException;

    BaseResponseBRIVA<VirtualAccountResponse> getVirtualAccountInfo(String institutionCode, Long accountNumber, String customerCode) throws IOException, InterruptedException;
}
