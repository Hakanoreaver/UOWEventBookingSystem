package hello;

import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
@CrossOrigin(origins = "file:///C:/Users/hakan/Desktop/Database/complete/WebsiteText.html")
@Entity // This tells Hibernate to make a table out of this class
public class Booking {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name ="ID")
    private Integer id;
    @Column(name="EventName")
    private String EventName;
    @Column(name="SeatNumber")
    private Integer SeatNumber;
    @Column(name="Email")
    private String email;


    public Integer getId() {
        return id;
    }

    public void setId(Integer Id) {
        this.id = id;
    }

    public String getName() {
        return EventName;
    }

    public void setName(String eventName) {
        this.EventName = eventName;
    }

    public Integer getSeatNumber() {
        return SeatNumber;
    }

    public void setSeatNo(Integer SeatNumber) {
        this.SeatNumber =SeatNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
