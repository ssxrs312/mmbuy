package study.mmbuyuserservice.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.mmbuyuserservice.common.constants.Constants;
import study.mmbuyuserservice.common.resp.ApiResult;
import study.mmbuyuserservice.user.entity.User;
import study.mmbuyuserservice.user.entity.UserElement;
import study.mmbuyuserservice.user.service.IUserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("login")
    public ApiResult login(@RequestBody @Valid User user, HttpSession session){
        ApiResult<UserElement> result = new ApiResult<>(Constants.RESP_STATUS_OK,"登录成功");

        UserElement ue= userService.login(user);
        if(ue != null){
            if(session.getAttribute(Constants.REQUEST_USER_SESSION) == null){
                session.setAttribute(Constants.REQUEST_USER_SESSION,ue);
            }
            result.setData(ue);
        }

        return result;
    }

    /**
     *
     * @Description  用户注册
     */
    @RequestMapping("/register")
    public ApiResult register (@RequestBody @Valid User user) throws Exception {

        userService.registerUser(user);

        return new ApiResult(Constants.RESP_STATUS_OK,"注册成功");

    }

}
