package BikeRental;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Bike_table")
public class Bike {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String status;

    @PostPersist
    public void onPostPersist(){

        System.out.println("###onPostPersist###");
        BikeRegistered bikeRegistered = new BikeRegistered();
        BeanUtils.copyProperties(this, bikeRegistered);
        bikeRegistered.publishAfterCommit();

    }

    @PostUpdate
    public void onPostUpdate(){


        System.out.println("###onPostUpdate###");
        BikeUpdated bikeUpdated = new BikeUpdated();
        // bikeUpdated.setStatus("occupied");
        BeanUtils.copyProperties(this, bikeUpdated);

        



        bikeUpdated.publishAfterCommit();




    }

    @PreRemove
    public void onPreRemove(){
        BikeDeleted bikeDeleted = new BikeDeleted();
        BeanUtils.copyProperties(this, bikeDeleted);
        bikeDeleted.publishAfterCommit();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
