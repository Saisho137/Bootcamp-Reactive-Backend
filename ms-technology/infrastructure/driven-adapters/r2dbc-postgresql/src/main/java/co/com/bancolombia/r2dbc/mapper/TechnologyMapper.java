package co.com.bancolombia.r2dbc.mapper;

import co.com.bancolombia.model.massivegeneration.MassiveGeneration;
import co.com.bancolombia.model.request.Request;
import co.com.bancolombia.r2dbc.model.MassiveDTO;
import co.com.bancolombia.r2dbc.model.RequestDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class GenericMapper {

    public MassiveGeneration getMassiveGeneration(MassiveDTO massiveDTO) {
        return MassiveGeneration.builder()
                .id(massiveDTO.getId())
                .commerceDocumentNumber(massiveDTO.getCommerceDocumentNumber())
                .commerceDocumentType(massiveDTO.getCommerceDocumentType())
                .emailGeneral(massiveDTO.getEmailGeneral())
                .checkEmail(massiveDTO.getCheckEmail())
                .quantity(massiveDTO.getQuantity())
                .quantityApproved(massiveDTO.getQuantityApproved())
                .quantityRejected(massiveDTO.getQuantityRejected())
                .quantityPending(massiveDTO.getQuantityPending())
                .createdAt(massiveDTO.getCreatedAt())
                .updatedAt(massiveDTO.getUpdatedAt()).build();
    }
}
