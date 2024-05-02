package spring.mini.project;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface EnquiryService {
	
	public Dashboard getDashboardinfo(Integer Counseelorid);
	
	public boolean AddEnquiry(Enquiry enquiry,Integer counsellorid);
	
	public List<Enquiry> getEnquires(Enquiry enquiry,Integer counsellorid);
	
	public Enquiry getEnquiry(Integer enqid);
	

}
