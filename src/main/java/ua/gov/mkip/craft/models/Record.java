package ua.gov.mkip.craft.models;

import lombok.Data;
import ua.gov.mkip.craft.models.enums.Role;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table (name = "record")
public class Record {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long recordId;

    private String recordName;

    private String recordDescription;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserCraftsMan userCraftsMan;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "record")
    private Set <Image> imageSet;

    public Record(){};

    public Record(Long recordId) {
        this.recordId = recordId;
    }
}
