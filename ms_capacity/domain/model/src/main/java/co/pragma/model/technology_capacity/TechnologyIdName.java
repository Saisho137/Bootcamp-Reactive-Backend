package co.pragma.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TechnologyIdName {
    private Long id;
    private String name;
}
