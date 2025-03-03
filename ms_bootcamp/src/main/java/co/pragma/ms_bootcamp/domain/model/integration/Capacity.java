package co.pragma.ms_bootcamp.domain.model.integration;

public class Capacity {
    private Long id;
    private String name;
    private String description;
    private int technologyCount;

    public Capacity(Long id, String name, String description, int technologyCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.technologyCount = technologyCount;
    }

    public Capacity() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getTechnologyCount() {
        return technologyCount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTechnologyCount(int technologyCount) {
        this.technologyCount = technologyCount;
    }

    public static CapacityBuilder builder() {
        return new CapacityBuilder();
    }

    public static class CapacityBuilder {
        private Long id;
        private String name;
        private String description;
        private int technologyCount;

        public CapacityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CapacityBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CapacityBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CapacityBuilder technologyCount(int technologyCount) {
            this.technologyCount = technologyCount;
            return this;
        }

        public Capacity build() {
            return new Capacity(id, name, description, technologyCount);
        }
    }
}
