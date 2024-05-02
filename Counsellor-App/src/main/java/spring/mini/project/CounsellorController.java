package spring.mini.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {
	
	@Autowired
	private CounsellorService counsellorservice;
	
	@Autowired
	private EnquiryService enqservice;
	
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest req,Model model) {
		HttpSession session = req.getSession(false);
		session.invalidate();//to remove session
		return "redirect:/";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("counsellor",new Counsellor());
		return "registerView";
	}
	
	@PostMapping("/register")
	public String handleRegister(Counsellor c,Model model) {
		boolean status = counsellorservice.saveCounsellor(c);
		if(status) {
			model.addAttribute("smsg","Counsellor Saved");
    	}else {
			model.addAttribute("emsg","Failed To save");
		}
		return "registerView";
	}
	
	@GetMapping("/")
	public String login(Model model) {
		//form binding obj..
	model.addAttribute("counsellor",new Counsellor());
		return "index";
	}
	
	@PostMapping("/")
	public String handleLogin(Counsellor counsellor, HttpServletRequest req, Model model) {
	Counsellor c = counsellorservice.getCounsellor(counsellor.getEmail(),counsellor.getPwd());
	
	if(c==null) {
		model.addAttribute("errormsg","Invalid Credentials");
		return "index";
	}else {
		//session is use to know which counsellor login 
		HttpSession session = req.getSession(true);
		session.setAttribute("cid", c.getCounsellorid());
	
	    return "redirect:dashboard";
	
	}
	}
	 
	@GetMapping("/dashboard")
	public String buildDashboard(HttpServletRequest req,Model model) {
		HttpSession session=req.getSession(false);
		Integer cid=(Integer)session.getAttribute("cid");
		Dashboard dbinfo = enqservice.getDashboardinfo(cid);
		model.addAttribute("dashboard",dbinfo);
	    return "dashboard";
	
	}
}
