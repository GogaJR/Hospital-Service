package am.initsolutions.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hospital")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String executive;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String phoneNumber;

    @Column
    private String site;
<<<<<<< HEAD
=======
    @Column
    private String executive;
>>>>>>> 943f8ce757bbbb25dbc51fd3e82566abfdeb9ca2

    @OneToMany(mappedBy = "hospital")
    private List<Department> departments;
    
    @OneToMany(mappedBy = "hospital")
    private List<Doctor> doctors;
}
