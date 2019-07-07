package study.mmbuyuserservice.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import study.mmbuyuserservice.common.constants.Constants;
import study.mmbuyuserservice.common.exception.MamaBuyException;
import study.mmbuyuserservice.user.dao.UserMapper;
import study.mmbuyuserservice.user.entity.User;
import study.mmbuyuserservice.user.entity.UserElement;
import study.mmbuyuserservice.user.service.IUserService;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private CuratorFramework zkClient;

    @Override
    public void add(User user) {
        userMapper.add(user);
    }

    @Override
    public void delete(int id) {
        userMapper.delete(id);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public User get(int id) {
        User user = userMapper.get(id);
        return user;
    }

    @Override
    public List<User> queryAll() {
        List<User> users = userMapper.queryAll();
        return users;
    }

    @Override
    public UserElement login(User user) {
        UserElement ue = null;
        User userExist = userMapper.selectByEmail(user.getEmail());
        if(userExist != null){
            //对密码与数据库密码进行校验
            boolean result = passwordEncoder.matches(user.getPassword(),userExist.getPassword());
            if(!result){
                throw new MamaBuyException("密码错误");
            }else{
                //校验全部通过，登陆通过
                ue = new UserElement();
                ue.setEmail(userExist.getEmail());
                ue.setUserId(userExist.getId());
                ue.setNickname(userExist.getNickname());
                ue.setUuid(userExist.getUuid());
            }
        }else {
            throw new MamaBuyException("用户不存在");
        }
        return ue;
    }

    @Override
    public void registerUser(User user) throws Exception {
        InterProcessLock lock = null;
        try{
            lock = new InterProcessMutex(zkClient, Constants.USER_REGISTER_DISTRIBUTE_LOCK_PATH);
            boolean retry = true;
            do{
                if (lock.acquire(3000, TimeUnit.MILLISECONDS)){
                    log.info(user.getEmail()+Thread.currentThread().getName()+"获取锁");
                    //查询重复用户
                    User repeatedUser = userMapper.selectByEmail(user.getEmail());
                    if(repeatedUser!=null){
                        throw  new MamaBuyException("用户邮箱重复");
                    }
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    user.setNickname("码码购用户"+user.getEmail());
//                    userMapper.insertSelective(user);
                    userMapper.add(user);
                    //跳出循环
                    retry = false;
                }
                //可以适当休息一会...也可以设置重复次数，不要无限循环
            }while (retry);
        }catch (Exception e){
            log.error("用户注册异常",e);
            throw  e;
        }finally {
            if(lock != null){
                try {
                    lock.release();
                    log.info(user.getEmail()+Thread.currentThread().getName()+"释放锁");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public User selectByEmail(String email) {
        User user = userMapper.selectByEmail(email);
        return user;
    }
}
