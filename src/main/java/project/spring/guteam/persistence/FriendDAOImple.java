package project.spring.guteam.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.guteam.domain.FriendVO;

@Repository // @Component
public class FriendDAOImple implements FriendDAO {
	private static final Logger logger = LoggerFactory.getLogger(FriendDAOImple.class);
	
	public static final String NAMESPACE = "project.spring.guteam.FriendMapper";
	
	@Autowired
	public SqlSession sqlSession;
	
	@Override
	public int insert(FriendVO vo) {
		logger.info("insert() 호출 vo ? " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<FriendVO> select(String memberId) {
		logger.info("select() 호출 memId ? " + memberId);
		return sqlSession.selectList(NAMESPACE + ".select_by_member_id", memberId);
	}

	@Override
	public int delete(String friendId) {
		logger.info("delete() 호출 friendId ? " + friendId);
		return sqlSession.delete(friendId);
	}

}
