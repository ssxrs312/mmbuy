package study.mmbuyuserservice.user.dao;

import org.apache.ibatis.annotations.*;
import study.mmbuyuserservice.user.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("insert into t_user(id, uuid, email,password, nickname, mobile,create_time, update_time) " +
            "values (#{id},#{uuid},#{email},#{password}, #{nickname},#{mobile},#{createTime},#{updateTime}")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int add(User user);

    @Delete("delete from t_user where id = #{id}")
    public void delete(int id);

    @Update("update t_user set uuid = #{uuid,jdbcType=BIGINT},email = #{email,jdbcType=VARCHAR}," +
            "password = #{password,jdbcType=VARCHAR},nickname = #{nickname,jdbcType=VARCHAR}," +
            "mobile = #{mobile,jdbcType=VARCHAR},create_time = #{createTime,jdbcType=TIMESTAMP}," +
            "update_time = #{updateTime,jdbcType=TIMESTAMP}where id = #{id,jdbcType=BIGINT}")
    public void update(User user);

    @Select("select * from t_user where id =#{id}")
    public User get(int id);

    @Select("select * from t_user")
    public List<User> queryAll();

    @Select("select * from t_user where email = #{email}")
    public User selectByEmail(String email);






}
