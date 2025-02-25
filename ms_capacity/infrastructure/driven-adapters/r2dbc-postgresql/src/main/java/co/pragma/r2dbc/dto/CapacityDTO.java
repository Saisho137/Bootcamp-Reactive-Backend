package co.pragma.r2dbc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("ms_capacity.capacities")
public class CapacityDTO {
    @Id
    private Long id;
    private String name;
    private String description;
    private int technologyCount;
}
