package com.example.solcoupang.user;

import com.example.solcoupang.common.exceptions.BaseException;
import com.example.solcoupang.common.BaseResponse;
import com.example.solcoupang.common.BaseResponseStatus;
import com.example.solcoupang.user.model.*;
import com.example.solcoupang.utils.ValidationRegex;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/app/users"})
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseBody
    @PostMapping({""})
    public BaseResponse<PostUserRes> createUser(@RequestBody PostUserReq postUserReq) {
        if (postUserReq.getEmail() == null) { // 유저 정보 중 이메일 없는 경우
            return new BaseResponse(BaseResponseStatus.POST_USERS_EMPTY_EMAIL);
        } else if (!ValidationRegex.isRegexEmail(postUserReq.getEmail())) { // 이메일 형식이 맞지 않음
            return new BaseResponse(BaseResponseStatus.POST_USERS_INVALID_EMAIL);
        } else {
            try {
                PostUserRes postUserRes = userService.createUser(postUserReq);
                return new BaseResponse(postUserRes);
            } catch (BaseException var3) {
                return new BaseResponse(var3.getStatus());
            }
        }
    }

    @ResponseBody
    @GetMapping({""})
    public BaseResponse<List<GetUserRes>> getUsers(@RequestParam(required = false) String Email) {
        try {
            List getUsersRes;
            if (Email == null) {
                getUsersRes = this.userService.getUsers();
                return new BaseResponse(getUsersRes);
            } else {
                getUsersRes = this.userService.getUsersByEmail(Email);
                return new BaseResponse(getUsersRes);
            }
        } catch (BaseException var3) {
            return new BaseResponse(var3.getStatus());
        }
    }

    @ResponseBody
    @GetMapping({"/{userId}"})
    public BaseResponse<GetUserRes> getUser(@PathVariable("userId") int userId) {
        try {
            GetUserRes getUserRes = this.userService.getUser(userId);
            return new BaseResponse(getUserRes);
        } catch (BaseException var3) {
            return new BaseResponse(var3.getStatus());
        }
    }

    @ResponseBody
    @PatchMapping({"/{userId}"})
    public BaseResponse<String> modifyUserName(@PathVariable("userId") int userId, @RequestBody User user) {
        try {
            PatchUserReq patchUserReq = new PatchUserReq(userId, user.getName());
            this.userService.modifyUserName(patchUserReq);
            String result = "";
            return new BaseResponse(result);
        } catch (BaseException var5) {
            return new BaseResponse(var5.getStatus());
        }
    }

    @ResponseBody
    @DeleteMapping({"/{userId}"})
    public BaseResponse<String> deleteUser(@PathVariable("userId") int userId) {
        try {
            this.userService.deleteUser(userId);
            String result = "";
            return new BaseResponse(result);
        } catch (BaseException var3) {
            return new BaseResponse(var3.getStatus());
        }
    }

}
