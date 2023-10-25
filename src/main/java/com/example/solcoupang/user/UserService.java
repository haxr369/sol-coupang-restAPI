package com.example.solcoupang.user;

import com.example.solcoupang.common.BaseException;
import com.example.solcoupang.common.BaseResponseStatus;
import com.example.solcoupang.user.model.GetUserRes;
import com.example.solcoupang.user.model.PatchUserReq;
import com.example.solcoupang.user.model.PostUserReq;
import com.example.solcoupang.user.model.PostUserRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    public PostUserRes createUser(PostUserReq postUserReq) throws BaseException {
        if (this.checkEmail(postUserReq.getEmail()) == 1) {
            throw new BaseException(BaseResponseStatus.POST_USERS_EXISTS_EMAIL);
        } else {
            try {
                int userId = this.userDao.createUser(postUserReq);
                return new PostUserRes(userId);
            } catch (Exception var3) {
                throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
            }
        }
    }

    public void modifyUserName(PatchUserReq patchUserReq) throws BaseException {
        try {
            int result = this.userDao.modifyUserName(patchUserReq);
            if (result == 0) {
                throw new BaseException(BaseResponseStatus.MODIFY_FAIL_USERNAME);
            }
        } catch (Exception var3) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public void deleteUser(int userId) throws BaseException {
        try {
            int result = this.userDao.deleteUser(userId);
            if (result == 0) {
                throw new BaseException(BaseResponseStatus.DELETE_FAIL_USERNAME);
            }
        } catch (Exception var3) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public List<GetUserRes> getUsers() throws BaseException {
        try {
            List<GetUserRes> getUserRes = this.userDao.getUsers();
            return getUserRes;
        } catch (Exception var2) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public List<GetUserRes> getUsersByEmail(String email) throws BaseException {
        try {
            List<GetUserRes> getUsersRes = this.userDao.getUsersByEmail(email);
            return getUsersRes;
        } catch (Exception var3) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public GetUserRes getUser(int userId) throws BaseException {
        try {
            GetUserRes getUserRes = this.userDao.getUser(userId);
            return getUserRes;
        } catch (Exception var3) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public int checkEmail(String email) throws BaseException {
        try {
            return userDao.checkEmail(email);
        } catch (Exception var3) {
            throw new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }

    public UserService(final UserDao userDao) {
        this.userDao = userDao;
    }
}
