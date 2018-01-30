package runningTotal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RunningTotalController {
	
	@RequestMapping("/calculator/total")
	public RunningTotal runningTotal() {
		return new RunningTotal();
	}
}
