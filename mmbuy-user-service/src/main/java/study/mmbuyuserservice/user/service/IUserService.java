package study.mmbuyuserservice.user.service;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import study.mmbuyuserservice.user.entity.User;
import study.mmbuyuserservice.user.entity.UserElement;

import java.util.List;

public interface IUserService {

    public void add(User user);

    public void delete(int id);

    public void update(User user);

    public User get(int id);

    public List<User> queryAll();

    UserElement login(User user);

    void registerUser(User user) throws Exception;

    public User selectByEmail(String email);
}
