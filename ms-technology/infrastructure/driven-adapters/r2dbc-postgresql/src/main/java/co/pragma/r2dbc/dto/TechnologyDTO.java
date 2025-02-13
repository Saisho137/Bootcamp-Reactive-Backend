package co.pragma.r2dbc.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("technologies")
public class TechnologyDTO {
    @Id
    private Long id;

    private String name;
    private String description;
}
