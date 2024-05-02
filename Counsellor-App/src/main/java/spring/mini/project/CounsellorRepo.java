package spring.mini.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounsellorRepo extends JpaRepository<Counsellor, Integer> {
	    
	    public Counsellor findByEmailAndPwd(String email, String pwd);

	    public Counsellor findByEmail(String email);
}
