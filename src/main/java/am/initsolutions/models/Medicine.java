package am.initsolutions.models;

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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="medicine")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
<<<<<<< HEAD

    @Column
    private String name;

    @ManyToMany(mappedBy = "medicines")
    private List<Recipe> recipes;
=======

>>>>>>> 4687108c2b80af8d52b9b89bf978c76cd074b1d0
}
