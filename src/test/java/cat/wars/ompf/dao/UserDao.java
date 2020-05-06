package cat.wars.ompf.dao;

import cat.wars.ompf.model.User;

import java.util.List;

/**
 * @program: oh-my-pf
 * @description:
 * @author: Wars
 * @created: 2020-05-06 01:17
 */
public interface UserDao {

  List<User> findAll();

  User findByCondition(User user);
}
