package microArchAddition;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TotalController {
	
	@RequestMapping("/calculator/total")
	public Total total() {
		return new Total();
	}
}
