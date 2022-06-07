/**
 * 
 */
package mic.vn.client.controller;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mic.vn.client.exception.AppException;
import mic.vn.client.model.DemoHtMaCb;
import mic.vn.client.service.IDemoHtMaCbService;

/**
 * @author truongnh
 * @since 06/06/2022
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/htmacb")
public class DemoHtMaCbController {
	// logger
	public static Logger logger = LoggerFactory.getLogger(DemoHtMaCbController.class);

	@Autowired
	IDemoHtMaCbService demoHtMaCbService;

	@GetMapping("/")
	public ResponseEntity<Iterable<DemoHtMaCb>> getAllHtMaCb() {
		System.out.println("aaa");
		try {
			return new ResponseEntity<>(demoHtMaCbService.getAll(), HttpStatus.OK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(404, "SQLException");
			
		}
	}
	@GetMapping("/proc")
	public ResponseEntity<Iterable<DemoHtMaCb>> getAllHtMaCbProc() {
		System.out.println("aaa");
		try {
			return new ResponseEntity<>(demoHtMaCbService.getAllProc(), HttpStatus.OK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(404, "SQLException");
			
		}
	}


	/*
	 * @GetMapping("/{ma}") public ResponseEntity<DemoHtMaCb>
	 * getMaCbById(@PathVariable String ma) { Optional<DemoHtMaCb>
	 * demoHtMaCbOptional = demoHtMaCbService.findById(ma); return
	 * demoHtMaCbOptional.map(demoHtMaCb -> new ResponseEntity<>(demoHtMaCb,
	 * HttpStatus.OK)) .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	 * }
	 * 
	 * @PutMapping("/{ma}") public ResponseEntity<DemoHtMaCb>
	 * updateCategory(@PathVariable String ma, @RequestBody DemoHtMaCb demoHtMaCb) {
	 * Optional<DemoHtMaCb> demoHtMaCbOptional = demoHtMaCbService.findById(ma);
	 * return demoHtMaCbOptional.map(demoHtMaCb1 -> {
	 * demoHtMaCb.setMa(demoHtMaCb.getMa()); return new
	 * ResponseEntity<>(demoHtMaCbService.save(demoHtMaCb), HttpStatus.OK);
	 * }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); }
	 */
}
