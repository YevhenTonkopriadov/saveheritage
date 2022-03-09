package ua.gov.mkip.saveheritage.models;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.*;
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

    @Pattern(regexp = "^[A-Za-zА_Яа-я]+$")
    @Size(min=4, max = 20)
    private String recordName;

    @Pattern(regexp = "^[A-Za-zА_Яа-я]+$")
    @Size(max = 200)
    private String recordDescription;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "record_id")
    private List<Image> comments = new ArrayList<>();
}
