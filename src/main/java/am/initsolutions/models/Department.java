package am.initsolutions.models;

<<<<<<< HEAD
import javax.persistence.*;
import java.util.List;
=======
>>>>>>> 4687108c2b80af8d52b9b89bf978c76cd074b1d0
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
=======

>>>>>>> 4687108c2b80af8d52b9b89bf978c76cd074b1d0
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
<<<<<<< HEAD

    @ManyToOne
    @JoinColumn(name = "hospital_id")
=======

>>>>>>> 4687108c2b80af8d52b9b89bf978c76cd074b1d0
    private Hospital hospital;

    @OneToMany(mappedBy = "department")
    private List<Doctor> doctors;
}
