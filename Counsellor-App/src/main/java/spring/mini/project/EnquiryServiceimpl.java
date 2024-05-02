package spring.mini.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EnquiryServiceimpl implements EnquiryService {
	
	@Autowired
	private EnqiuryRepo enqrepo;
	
	@Autowired
	private CounsellorRepo counsellorrepo;

	@Override
	public Dashboard getDashboardinfo(Integer Counseelorid) {
	   Long totalcnt = enqrepo.getEnquiries(Counseelorid);
	   Long opencnt = enqrepo.getEnquiries(Counseelorid, "new");
	   Long lostcnt =  enqrepo.getEnquiries(Counseelorid,"lost");
	   Long enrollcnt=enqrepo.getEnquiries(Counseelorid,"enrolled");
	   
	   Dashboard d=new Dashboard();
	   d.setTotalenq(totalcnt);
	   d.setEnrollenq(enrollcnt);
	   d.setOpenenq(opencnt);
	   d.setLostenq(enrollcnt);
	   
		return d;
	}

	@Override
	public boolean AddEnquiry(Enquiry enquiry, Integer counsellorid) {
		Counsellor counsellor = counsellorrepo.findById(counsellorid).orElseThrow();
		enquiry.setCounsellor(counsellor);//for fk
		Enquiry saveenq = enqrepo.save(enquiry);
		return saveenq.getStuid()!=null;
	}

	@Override
	public List<Enquiry> getEnquires(Enquiry enquiry, Integer counsellorid) {
		//Counsellor counsellor = counsellorrepo.findById(counsellorid).orElseThrow();
		
		Counsellor counsellor=new Counsellor();
		counsellor.setCounsellorid(counsellorid);
		
		Enquiry searchCriteria=new Enquiry();
		searchCriteria.setCounsellor(counsellor);
		
		if(null!=enquiry.getCourse() && !"".equals(enquiry.getCourse())) {
			searchCriteria.setCourse(enquiry.getCourse());
			
		}

		if(null!=enquiry.getMode() && !"".equals(enquiry.getMode())) {
			searchCriteria.setCourse(enquiry.getMode());
			
		}

		if(null!=enquiry.getStatus() && !"".equals(enquiry.getStatus())) {
			searchCriteria.setStatus(enquiry.getStatus());
			
		}
		Example<Enquiry>of=Example.of(searchCriteria);//based on filter to write dynamic query
		return enqrepo.findAll(of);
	}

	
	@Override
	public Enquiry getEnquiry(Integer enqid) {
		return enqrepo.findById(enqid).orElseThrow();
	
	}

}
