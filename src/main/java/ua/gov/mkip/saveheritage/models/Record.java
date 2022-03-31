package ua.gov.mkip.saveheritage.models;

import lombok.Data;
import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table (name = "record")
public class Record {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long recordId;

    @ManyToOne(fetch=FetchType.LAZY)
    private User user;
    private String recordName;
    private String recordDescription;

}
