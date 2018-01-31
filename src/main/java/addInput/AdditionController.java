package addInput;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * This method functions as an endpoint to PUT a value. 
 * It creates a kafka producer and adds the given value to the current kafka topic.
 */
@RestController
public class AdditionController {
	private final static String TOPIC = "input";

	/**
	 * Gets the int value given in the URL.
	 * Calls runProducer() and tells you if it succeeded.
	 * @param int value
	 * @return final status of runProducer()
	 * @throws Exception
	 */
	@RequestMapping("/calculator/add")
	public String add(@RequestParam(value = "value", defaultValue = "0") int value) throws Exception {
		if(ProduceInput.runProducer(value)) { return ("A value of " + value + " was added to " + TOPIC + " topic."); }
		return "Something went wrong. No value was added.";
	}
}
