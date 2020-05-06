package cat.wars.ompf.session.defaults;

import cat.wars.ompf.dao.UserDao;
import cat.wars.ompf.io.Resource;
import cat.wars.ompf.model.User;
import cat.wars.ompf.session.SQLSession;
import cat.wars.ompf.session.SQLSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.util.List;

class DefaultSQLSessionTest {

  @Test
  void selectAll() {
    SQLSession sqlSession =
        new SQLSessionFactoryBuilder()
            .build(Resource.getResourceAsStream("SQLMapConfig.yml"))
            .openSession();

    User user = new User(1, "Wars");
    List<Object> findList = sqlSession.selectAll("cat.wars.ompf.dao.UserMapper", "findList", user);
    System.out.println(findList);
  }

  @Test
  void selectOne() {
    SQLSession sqlSession =
        new SQLSessionFactoryBuilder()
            .build(Resource.getResourceAsStream("SQLMapConfig.yml"))
            .openSession();

    User user = new User(1, "Wars");
    Object result = sqlSession.selectOne("cat.wars.ompf.dao.UserMapper", "findList", user);
    System.out.println(result);
  }

  @Test
  void mapper(){
    SQLSession sqlSession =
            new SQLSessionFactoryBuilder()
                    .build(Resource.getResourceAsStream("SQLMapConfig.yml"))
                    .openSession();

    List<User> result = sqlSession.getMapper(UserDao.class).findAll();
    System.out.println(result);
  }
}
