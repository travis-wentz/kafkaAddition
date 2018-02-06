package producer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * This method functions as an endpoint to PUT a value. 
 * It creates a kafka producer and adds the given value to the current kafka topic.
 */
@RestController
@RequestMapping("/calculator/add")
public class AdditionController {
	private final static String TOPIC = "input";

	/**
	 * Gets the int value given in the URL.
	 * Calls runProducer() and tells you if it succeeded.
	 * @param int value
	 * @return final status of runProducer()
	 * @throws Exception
	 */
    @RequestMapping(method = RequestMethod.PUT)
	public String add(@RequestParam(value = "value", defaultValue = "0") String value) throws Exception {
		Produce producer = new Produce() {{
			sendMessage("input", value);
		}};
		return ("A value of " + value + " was added to " + TOPIC + " topic.");
	}
}
