package co.pragma.ms_bootcamp.domain.model;

import java.util.List;

public class Bootcamp {
    private Long id;
    private String name;
    private String description;
    private List<Long> capacitiesIds;

    public Bootcamp(Long id, String name, String description, List<Long> capacitiesIds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.capacitiesIds = capacitiesIds;
    }
    public Bootcamp() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getCapacitiesIds() {
        return capacitiesIds;
    }

    public void setCapacitiesIds(List<Long> capacitiesIds) {
        this.capacitiesIds = capacitiesIds;
    }

    public static BootcampBuilder builder() {
        return new BootcampBuilder();
    }

    public static class BootcampBuilder {
        private Long id;
        private String name;
        private String description;
        private List<Long> capacitiesIds;

        public BootcampBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public BootcampBuilder name(String name) {
            this.name = name;
            return this;
        }

        public BootcampBuilder description(String description) {
            this.description = description;
            return this;
        }

        public BootcampBuilder capacitiesIds(List<Long> capacitiesIds) {
            this.capacitiesIds = capacitiesIds;
            return this;
        }

        public Bootcamp build() {
            return new Bootcamp(id, name, description, capacitiesIds);
        }
    }
}
