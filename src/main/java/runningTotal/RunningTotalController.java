package runningTotal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RunningTotalController {
	
	@RequestMapping("/calculator/total")
	public String runningTotal() throws InterruptedException {
		return ("Current total is: " + ConsumeOutput.consumeOutput());
	}
}
