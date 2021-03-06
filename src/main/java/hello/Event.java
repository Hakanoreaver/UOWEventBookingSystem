package hello;

import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@CrossOrigin(origins = "file:///C:/Users/hakan/Desktop/Database/complete/WebsiteText.html")
@Entity // This tells Hibernate to make a table out of this class
public class Event {
    @Id
    private String Name;

    private Integer Capacity;

    private Integer seatsLeft;

    private Date eventDate;

    private Double cost;

    private String location;

    private String promoCode;

	public String getName() {
		return Name;
	}

	public void setName(String eventName) {
		this.Name = eventName;
	}

	public Integer getCapacity() {
		return Capacity;
	}

	public void setCapacity(Integer capacity) {
		this.Capacity = capacity;
	}

	public Integer getseatsLeft() {
		return seatsLeft;
	}

	public void setseatsLeft(Integer seatsLeft) {
		this.seatsLeft = seatsLeft;
	}

	public Date getEventDate() {

		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Double getCost() {

		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getLocation() {

		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPromoCode() {

		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}


    
    
}
