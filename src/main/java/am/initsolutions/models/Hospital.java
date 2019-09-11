package am.initsolutions.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hospital")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String openAt;
    private String closeAt;
    private List<Department> departments;

    public Hospital() {
    }
}
