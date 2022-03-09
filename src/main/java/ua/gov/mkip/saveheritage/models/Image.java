package ua.gov.mkip.saveheritage.models;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@Table (name = "images")
public class Image {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long imageId;

    @Pattern(regexp = "^[A-Za-zА_Яа-я]+$")
    @Size(min=4, max = 20)
    private String descriptionImage;

    @Lob
    private byte[] image;
}
