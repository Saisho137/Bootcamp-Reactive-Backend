package co.com.bancolombia.r2dbc.provider;

import co.com.bancolombia.model.massivegeneration.MassiveGeneration;
import co.com.bancolombia.model.request.Request;
import co.com.bancolombia.r2dbc.model.MassiveDTO;
import co.com.bancolombia.r2dbc.model.RequestDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public class TestBuilders {

    public static MassiveDTO getMassiveDTO() {
        return MassiveDTO.builder().id(UUID.randomUUID())
                .checkEmail(false)
                .createdAt(LocalDateTime.now())
                .emailGeneral("@gmail")
                .quantity(2)
                .commerceDocumentNumber("12313123131")
                .commerceDocumentType("CC")
                .quantityApproved(1)
                .quantityPending(0)
                .quantityRejected(1)
                .createdAt(LocalDateTime.now()).build();
    }

    public static RequestDTO getRequest() {
        return RequestDTO.builder()
                .idRequest(UUID.randomUUID())
                .commerceName("k")
                .commerceAccountType("Cuenta Ahorros")
                .commerceAccountNumber("1321321312")
                .commerceDocumentNumber("12313123131")
                .commerceDocumentType("CC")
                .requestStatus("APPROVED")
                .validCommerce(true)
                .reasonReject("")
                .redebanQrImage("dwqeqweqeqeqeqw")
                .merchantId("2131231231")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .idMassive(UUID.randomUUID()).build();
    }

}
