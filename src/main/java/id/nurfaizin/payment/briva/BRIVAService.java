package id.nurfaizin.payment.briva;

import com.fasterxml.jackson.core.JsonProcessingException;
import id.nurfaizin.payment.briva.dto.BaseResponseBRIVA;
import id.nurfaizin.payment.briva.dto.VirtualAccountDTO;

public interface BRIVAService {

    BaseResponseBRIVA<VirtualAccountDTO> createVirtualAccount(VirtualAccountDTO request)  throws JsonProcessingException, InterruptedException;
}
