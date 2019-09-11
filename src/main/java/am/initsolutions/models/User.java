package am.initsolutions.models;

<<<<<<< HEAD
import am.initsolutions.models.enums.UserType;
=======
>>>>>>> 4687108c2b80af8d52b9b89bf978c76cd074b1d0
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> 4687108c2b80af8d52b9b89bf978c76cd074b1d0

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
<<<<<<< HEAD

    @Enumerated(value = EnumType.STRING)
    private UserType userType;

    @Column
    private String email;

    @Column
    private String hashPassword;

    @OneToMany(mappedBy = "user")
    private List<Doctor> doctors;

    @OneToMany(mappedBy = "user")
    private List<Patient> patients;
=======

>>>>>>> 4687108c2b80af8d52b9b89bf978c76cd074b1d0
}
