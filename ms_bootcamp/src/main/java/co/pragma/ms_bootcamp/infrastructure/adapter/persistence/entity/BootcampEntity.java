package co.pragma.ms_bootcamp.infrastructure.adapter.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ms_bootcamp")
public class BootcampEntity {
    @Id
    private String id;
    private String name;
    private String description;
    private Integer capacitiesCount;
    private List<Long> capacitiesIds;
}
