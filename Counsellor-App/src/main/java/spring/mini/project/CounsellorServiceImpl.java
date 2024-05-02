package spring.mini.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class CounsellorServiceImpl implements CounsellorService {
    
    @Autowired
    private CounsellorRepo counsellorRepo;

    @Override
    public boolean saveCounsellor(Counsellor counsellor) {
        Counsellor findByEmail = counsellorRepo.findByEmail(counsellor.getEmail());
        if (findByEmail != null) {
            return false;
        } else {
            Counsellor savedCounsellor = counsellorRepo.save(counsellor);
            return savedCounsellor.getCounsellorid() != null;
        }
    }

    @Override
    public Counsellor getCounsellor(String email, String pwd) {
        return counsellorRepo.findByEmailAndPwd(email, pwd);
    }
}







	

