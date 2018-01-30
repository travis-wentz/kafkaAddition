package addition;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdditionController {

	@RequestMapping("/calculator/add")
	public String add(@RequestParam(value="value", defaultValue="0") int value) {
		return "A value of " + value + " was added.";
	}
}