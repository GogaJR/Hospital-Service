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

    @OneToMany(mappedBy = "hospital", orphanRemoval = true)
    private List<Department> departments;
    
    @OneToMany(mappedBy = "hospital", orphanRemoval = true)
    private List<Doctor> doctors;
}
