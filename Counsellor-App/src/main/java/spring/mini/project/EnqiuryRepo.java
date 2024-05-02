package spring.mini.project;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EnqiuryRepo extends JpaRepository<Enquiry,Integer> {

	    @Query(value = "select count(*) from Enquiry where counsellor_id = :id", nativeQuery = true)
	    
	    public Long getEnquiries(Integer id);

	    @Query(value = "select count(*) from Enquiry where counsellor_id = :id and status = :status", nativeQuery = true)
	    
	    public Long getEnquiries(Integer id,String status);
	    
	}


