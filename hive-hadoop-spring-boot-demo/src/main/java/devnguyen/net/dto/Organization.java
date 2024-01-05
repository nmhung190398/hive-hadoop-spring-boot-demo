package devnguyen.net.dto;

import lombok.Data;

@Data
public class Organization {
    private String id;
    private String name;
    private String website;
    private String country;
    private String description;
    private String founded;
    private String industry;
    private Integer numberOfEmployees;
}
