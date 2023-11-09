package project.spring.guteam.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.spring.guteam.domain.BoardAndReplyVO;
import project.spring.guteam.domain.BoardCommentVO;
import project.spring.guteam.domain.MemberVO;
import project.spring.guteam.pageutil.PageCriteria;
import project.spring.guteam.pageutil.PageMaker;
import project.spring.guteam.persistence.BoardCommentDAO;
import project.spring.guteam.persistence.GameBoardDAO;
import project.spring.guteam.persistence.MemberDAO;
import project.spring.guteam.persistence.ReplyDAO;

@Service //@Component
public class BoardCommentServiceImple implements BoardCommentService {
	private static final Logger logger= LoggerFactory.getLogger(BoardCommentServiceImple.class);
	
	@Autowired
	private ReplyDAO replyDAO;
	
	@Autowired
	private BoardCommentDAO boardCommentDAO;
	
	@Autowired
	private GameBoardDAO gameBoardDAO;
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Transactional(value= "transactionManager")
	@Override
	public int create(BoardCommentVO vo) throws Exception{
		boardCommentDAO.insert(vo);
		logger.info("comment create()실행");
		gameBoardDAO.updateCommentCnt(vo.getGameBoardId(), 1);
		logger.info("boardComment update실행");
		return 1;
	}	

	@Transactional(value = "transactionManager")
	@Override
	public Map<String, Object> read(int gameBoardId,PageCriteria criteria) {
		logger.info("Comment read() 실행");
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(boardCommentDAO.getTotalCount(gameBoardId));
		pageMaker.setPageData();
		List<BoardCommentVO> list = boardCommentDAO.select(gameBoardId,criteria);
		List<String> nicknameList = new ArrayList<String>();
		List<String> memberImageNameList = new ArrayList<String>();
		for(int i =0; i<  list.size();i++) {
			String memberId = list.get(i).getMemberId();
			MemberVO memberVO = memberDAO.select(memberId);
			String nickname = memberVO.getNickname();
			String memberImageName = memberVO.getMemberImageName();
			nicknameList.add(nickname);
			memberImageNameList.add(memberImageName);
		}
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("list", list);
		args.put("nicknameList", nicknameList);
		args.put("memberImageNameList",memberImageNameList);
		args.put("pageMaker", pageMaker);
		return args;
	}

	@Override
	public int update(int commentId, String CommentContent) {
		logger.info("comment update() 실행");
		return boardCommentDAO.update(CommentContent, commentId);
	}

	@Override
	public int delete(int commentId, int gameBoardId) {
		logger.info("comment delete 실행");
		return boardCommentDAO.delete(commentId);
	}


	@Override
	public int getBoardId(int commentId) {
		logger.info("service getBoardId 실행");
		return boardCommentDAO.getBoardId(commentId);
	}

	@Override
	public int updateReplyCnt(int commentId, int amount) {
		logger.info("updateReplyCnt 실행");
		return boardCommentDAO.updateReplyCnt(commentId, amount);
	}

	@Override
	public Map<String, Object> getAllCommentsAndReplies(String memberId,PageCriteria criteria){
		List<BoardAndReplyVO> list = boardCommentDAO.select(memberId,criteria);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(boardCommentDAO.getTotalCount(memberId));
		pageMaker.setPageData();
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("pageMaker", pageMaker);
		args.put("list", list);
		return args;
	}
	
	

}
