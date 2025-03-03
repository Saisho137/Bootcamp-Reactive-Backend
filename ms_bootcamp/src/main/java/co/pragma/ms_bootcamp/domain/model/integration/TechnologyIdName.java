package co.pragma.ms_bootcamp.domain.model.integration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyIdName {
    private Long id;
    private String name;
}
