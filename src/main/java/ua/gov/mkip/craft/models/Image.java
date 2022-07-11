package ua.gov.mkip.craft.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long imageId;

    private String imageName;

    private String originalImageName;

    private String descriptionImage;

    @Lob
    private byte[] image;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "record_id")
    private Record record;
}
