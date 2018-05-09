package founder.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/welcome")
public class HomeController {
	@RequestMapping(value="/{mess}")
       public String home(@PathVariable String mess,Map model){
    	  List<String> arry=new ArrayList();
 	      arry.add("ฤ๚บรฃบ");
 	      arry.add("list");
 	      arry.add("spring parm");
 	      arry.add(mess);
 	      model.put("list", arry);
    	  return "home";
       }
}
