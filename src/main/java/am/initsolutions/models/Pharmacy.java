package am.initsolutions.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="pharmacy")
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
<<<<<<< HEAD
    private int phoneNumber;
    @Column
    private String address;
    @Column
    private String openAt;
    @Column
    private String closeAt;
=======

>>>>>>> 4687108c2b80af8d52b9b89bf978c76cd074b1d0

}
