package runningTotal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RunningTotalController {
	
	@RequestMapping("/calculator/total")
	public ConsumeOutput runningTotal() {
		return new ConsumeOutput();
	}
	
	//TODO: consumer for topic: output
}
