package ua.gov.mkip.craft.models;

import lombok.Data;
import ua.gov.mkip.craft.models.enums.Region;
import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table (name = "userCraftsMan")
public class UserCraftsMan extends User {

    @CollectionTable(name = "user_region", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Region region;

    private String historicalEthnographicDistrict;

    private String  tradition;

    private String  type;

    private String address;

    private String  organization;

    private String  document;

    private String fileNameCraftsManImage;

    @Lob
    private byte[] craftsManImage;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userCraftsMan")
    private Set <Record> recordsSet;
}
