package spring.mini.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {

	@Autowired
	private EnquiryService enqservice;

	@GetMapping("/enquiry")
	public String addEnquiry(Enquiry enq, Model model) {
		model.addAttribute("enq", new Enquiry());
		return "addEnq";

	}

	@PostMapping("/enquiry")
	public String saveEnquiry(Enquiry enq, HttpServletRequest req, Model model) {

		HttpSession session = req.getSession(false);
		Integer cid = (Integer) session.getAttribute("cid");

		boolean status = enqservice.AddEnquiry(enq, cid);
		if (status) {
			model.addAttribute("smsg", "Enquiry Saved");

		} else {
			model.addAttribute("emsg", "Enquiry not Saved");
		}
		model.addAttribute("enq", new Enquiry());
		return "addEnq";

	}

	@GetMapping("/enquiries")
	public String getEnquires(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		Integer cid = (Integer) session.getAttribute("cid");
		List<Enquiry> list = enqservice.getEnquires(new Enquiry(), cid);
		model.addAttribute("enqs", list);
		model.addAttribute("enq", new Enquiry());
		return "viewEnquiries";
	}

	@PostMapping("/filter-enqs")
	public String filterEnqs(@ModelAttribute("enq") Enquiry enq, HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		Integer cid = (Integer) session.getAttribute("cid");
		List<Enquiry> list = enqservice.getEnquires(enq, cid);
		model.addAttribute("enqs", list);
		return "viewEnquiries";
 
	}

	@PostMapping("/edit")
	public String editEnquiry(@RequestParam("enqid") Integer enqid, Model model) {
		Enquiry enquiry = enqservice.getEnquiry(enqid);
		model.addAttribute("enq", enquiry);
		return "addEnq";
	}

}
