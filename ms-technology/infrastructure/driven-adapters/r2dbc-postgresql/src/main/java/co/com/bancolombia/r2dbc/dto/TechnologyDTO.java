package co.com.bancolombia.r2dbc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("schepago.request")
@Builder
public class RequestDTO {
    @Id
    UUID idRequest;
    String commerceName;
    String requestStatus;
    Boolean validCommerce;
    String reasonReject;
    String redebanQrImage;
    String merchantId;
    String commerceAccountType;
    String commerceAccountNumber;
    String commerceDocumentNumber;
    String commerceDocumentType;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    @Column("id_request_massive")
    UUID idMassive;

}
