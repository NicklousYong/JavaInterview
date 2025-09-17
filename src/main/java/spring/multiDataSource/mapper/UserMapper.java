package spring.multiDataSource.mapper;


import org.apache.ibatis.annotations.*;
import spring.multiDataSource.dao.User;

import java.util.List;

@Mapper
public interface UserMapper {

    User getUserById(@Param("id") Long id);

    int insertUser(User user);

    int updateUser(User user);

    int deleteUserById(@Param("id") Long id);

    List<User> getUserByCondition(User user);

    int batchInsert(@Param("list") List<User> users);
}