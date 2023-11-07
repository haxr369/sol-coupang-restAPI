package com.example.solcoupang.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.example.solcoupang.common.BaseException;
import com.example.solcoupang.common.BaseResponseStatus;
import com.example.solcoupang.user.model.GetUserRes;
import com.example.solcoupang.user.model.PatchUserReq;
import com.example.solcoupang.user.model.PostUserReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor // jdbcTemplate 의존성 추가
@Slf4j
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public List<GetUserRes> getUsers() {
        String getUsersQuery = "select * from USER";
        return this.jdbcTemplate.query(getUsersQuery, (rs, rowNum) -> {
            return new GetUserRes(rs.getInt("id"), rs.getString("email"), rs.getString("password"), rs.getString("name"));
        });
    }

    public List<GetUserRes> getUsersByEmail(String email) {
        String getUsersByEmailQuery = "select * from USER where email =?";
        return this.jdbcTemplate.query(getUsersByEmailQuery, (rs, rowNum) -> {
            return new GetUserRes(rs.getInt("id"), rs.getString("email"), rs.getString("password"), rs.getString("name"));
        }, new Object[]{email});
    }

    public GetUserRes getUser(int userId) {
        String getUserQuery = "select * from USER where id = ?";
        return (GetUserRes)this.jdbcTemplate.queryForObject(getUserQuery, (rs, rowNum) -> {
            return new GetUserRes(rs.getInt("id"), rs.getString("email"), rs.getString("password"), rs.getString("name"));
        }, new Object[]{userId});
    }

    public int createUser(PostUserReq postUserReq) {
        String createUserQuery = "insert into USER (name, email, password) VALUES (?,?,?)";
        log.info(postUserReq.getName()+" "+ postUserReq.getEmail()+" "+ postUserReq.getPassword());
        Object[] createUserParams = new Object[]{postUserReq.getName(), postUserReq.getEmail(), postUserReq.getPassword()};
        jdbcTemplate.update(createUserQuery, createUserParams);
        String lastInsertIdQuery = "select last_insert_id()";
        return (Integer)jdbcTemplate.queryForObject(lastInsertIdQuery, Integer.TYPE);
    }

    public Integer checkEmail(String email) {
//        log.info(email+"아니왜 안보니얃로ㅕㅏㅗㄷ재롣쟈ㅐㅕ롣ㅈㄹㅈㄷ뢔도");
        String checkEmailQuery = "select exists(select email from USER where email = ?)";
        try{
            return this.jdbcTemplate.queryForObject(checkEmailQuery, itemRowMapper(), email);
        }
        catch (RuntimeException e){
            throw new BaseException(BaseResponseStatus.NULLPOINTER_EXCEPTION);
        }
    }
    private RowMapper<Integer> itemRowMapper() {
        return ((rs, rowNum) -> {
            log.info(String.valueOf(rs.getInt(1)));
            return rs.getInt(1);
        });
    }

    public int modifyUserName(PatchUserReq patchUserReq) {
        String modifyUserNameQuery = "update USER set name = ? where id = ? ";
        Object[] modifyUserNameParams = new Object[]{patchUserReq.getName(), patchUserReq.getId()};
        return this.jdbcTemplate.update(modifyUserNameQuery, modifyUserNameParams);
    }

    public int deleteUser(int userId) {
        String deleteUserNameQuery = "update USER set status = ? where id = ? ";
        Object[] deleteUserNameParams = new Object[]{"INACTIVE", userId};
        return this.jdbcTemplate.update(deleteUserNameQuery, deleteUserNameParams);
    }
}
