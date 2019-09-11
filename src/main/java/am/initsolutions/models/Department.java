package am.initsolutions.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(mappedBy = "department")
    private List<Doctor> doctors;
}
