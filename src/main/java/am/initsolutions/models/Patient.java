package am.initsolutions.models;

import am.initsolutions.models.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private int age;
    
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    private String address;
    private String phoneNumber;

    @ManyToMany
    @JoinTable(
            name = "patient_doctor",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id"))
    private List<Doctor> doctors;

    @OneToMany(mappedBy = "patient")
    private List<PatientHistory> histories;
    
    //TODO
}
