package ua.gov.mkip.craft.models;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "userMkip")
public class UserMkip extends User {

    private String position;
}
