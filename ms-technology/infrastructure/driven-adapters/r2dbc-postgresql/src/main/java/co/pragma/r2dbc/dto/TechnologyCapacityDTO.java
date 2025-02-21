package co.pragma.r2dbc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("ms_technology.capacity_technology")
public class TechnologyCapacityDTO {
    @Id
    private Long id;
    @Column("technology_id")
    private Long technologyId;
    @Column("capacity_id")
    private Long capacityId;
}
