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
    private String name;
    @Column
    private String address;
    @Column
    private String phoneNumber;
    @Column
    private String openAt;
    @Column
    private String closeAt;
<<<<<<< HEAD

    @OneToMany(mappedBy = "hospital")
    private List<Department> departments;
    
    @OneToMany(mappedBy = "hospital")
    private List<Doctor> doctors;
=======

    private List<Department> departments;


>>>>>>> 4687108c2b80af8d52b9b89bf978c76cd074b1d0
}
