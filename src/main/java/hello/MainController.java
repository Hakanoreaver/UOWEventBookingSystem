package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import hello.User;
import hello.UserRepository;
import hello.Event;
import hello.EventRepository;
import hello.BookingRepository;
import hello.Booking;

@CrossOrigin(origins = "*")
@Controller    // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private BookingRepository bookingRepository;

	@GetMapping(path="/event/add")
	public @ResponseBody String addNewEvent (@RequestParam String eventName
			, @RequestParam Integer capacity,@RequestParam String date, @RequestParam Double price, @RequestParam String location,@RequestParam String promoCode) {
	if (eventRepository.findByEventName(eventName) == null) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Event n = new Event();
		n.setName(eventName);
		n.setCapacity(capacity);
		n.setseatsLeft(capacity);
		java.sql.Date expiry = new java.sql.Date(Long.parseLong(date));
		n.setEventDate(expiry);
		n.setCost(price);
		n.setLocation(location);
n.setPromoCode(promoCode);
		eventRepository.save(n);
		return "Event Created";
}
else return "Event Name already in use";
	}

	@GetMapping(path="/booking/add")
	public @ResponseBody String addNewBooking (@RequestParam String eventName, @RequestParam String Email, @RequestParam Integer noOfTickets) {
		for(int Z = 0; Z < noOfTickets; Z++) {
		Integer a = eventRepository.findCapacityByEventName(eventName);
		Integer c = eventRepository.findSeatsLeftByEventName(eventName);
		Booking b = new Booking();
		b.setName(eventName);
		b.setSeatNo(a - c);
		b.setEmail(Email);
		Integer i = eventRepository.findSeatsLeftByEventName(eventName);
		if (i > 0) {
			bookingRepository.save(b);
			eventRepository.setSeatsLeft(eventName, i-1);
		}
		else return "booking failed";
		}
	return "booking/s created";
	}

	@GetMapping(path="/booking/search")
	public @ResponseBody
	List<Booking> searchBookings (@RequestParam String Email) {

		return bookingRepository.findByEmail(Email);
	}

	@GetMapping(path="/booking/all")
	public @ResponseBody
	Iterable<Booking> searchAllBookings () {
		return bookingRepository.findAll();
	}

	@GetMapping(path="/event/byLocation")
	public @ResponseBody
	Iterable<Event> searchEventByLocation (@RequestParam String Location) {
		Iterable<Event> events = eventRepository.findAll();
		ArrayList<Event> eventss = new ArrayList<>();
		for (Event e : events) {
String i = e.getLocation();
			if (Location == i) {
				eventss.add(e);
			}
		}

		return eventss;
	}

	@GetMapping(path="/event/search")
	public@ResponseBody Optional<Event> findEvent(@RequestParam String eventName) {
		return  eventRepository.findById(eventName);
}

@GetMapping(path="/event/all")
	public@ResponseBody Iterable<Event> listEvents() {
		return  eventRepository.findAll();
}


	

	

	@GetMapping(path="/event/byDate")
	public @ResponseBody Iterable<Event> findEventForMonth(@RequestParam int month, @RequestParam int year) {
		Iterable<Event> events = eventRepository.findAll();
		ArrayList<Event> eventss = new ArrayList<>();
		for (Event e : events) {
			System.out.println("Searching Events");
			Date temp = e.getEventDate();
			Calendar cal = Calendar.getInstance();
			cal.setTime(temp);

			if (cal.get(Calendar.MONTH) == month && cal.get(Calendar.YEAR) == year) {
				eventss.add(e);
			}
		}

		return eventss;
	}

	@GetMapping(path="/user/add") // Map ONLY GET Requests
	public @ResponseBody String addNewUser (@RequestParam String name
			, @RequestParam String email, @RequestParam String password, @RequestParam String admin) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		if(userRepository.emailCheck(email) == null) {
			User n = new User();
			n.setName(name);
			n.setEmail(email);
			n.setAdmin(admin);
			n.setPassword((password));
			userRepository.save(n);
			return "User Created";
		}
		else return "Email already in use";
	}

	@GetMapping(path="/user/edit") // Map ONLY GET Requests
	public @ResponseBody String editUser (@RequestParam String name
			, @RequestParam String email, @RequestParam String password, @RequestParam String admin) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
			User n = new User();
			n.setName(name);
			n.setEmail(email);
			n.setAdmin(admin);
			n.setPassword((password));
			userRepository.save(n);
			return "User Created";
	}

	@GetMapping(path="user/logIn")
	public @ResponseBody User logInUser(@RequestParam String password, @RequestParam String email) {
		String p = userRepository.findPasswordByEmail(email);
		if (p.equals(password)) {
			return userRepository.findUser(email);
		}
		else return null;
	}
	
	@GetMapping(path="booking/delete")
	public @ResponseBody String deleteUser(@RequestParam int id) {
	eventRepository.setSeatsLeft(bookingRepository.getEventById(id),eventRepository.findSeatsLeftByEventName(bookingRepository.getEventById(id))+1);
	bookingRepository.delete(bookingRepository.findById(id));
	return "booking deleted";
	}

	@GetMapping(path="/user/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
}
