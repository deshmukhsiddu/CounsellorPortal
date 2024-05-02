package spring.mini.project;

import org.springframework.stereotype.Service;

@Service
public interface CounsellorService {
    
    public boolean saveCounsellor(Counsellor counsellor);

    public Counsellor getCounsellor(String email,String pwd);
    
}

