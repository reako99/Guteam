package project.spring.guteam.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.spring.guteam.domain.FriendRequestVO;
import project.spring.guteam.domain.FriendVO;
import project.spring.guteam.domain.MemberVO;
import project.spring.guteam.fileutil.MediaUtil;
import project.spring.guteam.service.FriendRequestService;
import project.spring.guteam.service.FriendService;
import project.spring.guteam.service.FriendServiceImple;
import project.spring.guteam.service.MemberService;

@Controller // @Component
@RequestMapping(value = "/friend")
public class FriendController {
	private static final Logger logger = LoggerFactory.getLogger(FriendController.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private FriendRequestService friendRequestService;
	
	@Autowired
	private FriendService friendService;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
//	// 친구 목록
//    @GetMapping("/list")
//    public void listGET(Model model, Principal principal) {
//    	logger.info("(friend) listGET() 호출");
//    	MemberVO vo = new MemberVO();
//		String memberId = principal.getName();
//		logger.info(memberId);
//		vo = memberService.read(memberId);
//		model.addAttribute("vo", vo);
//		logger.info(vo.toString());
//    }
    
	// 친구 목록
    @GetMapping("/list")
    public void listGET(Model model, Principal principal) {
    	logger.info("(friend) listGET() 호출");
    	List<String> sendMemberList;
    	List<String> receiveMemberList;
    	List<MemberVO> sendList = new ArrayList<MemberVO>();
    	List<MemberVO> receiveList = new ArrayList<MemberVO>();
    	
    	sendMemberList = friendRequestService.readTo(principal.getName());
    	for(int i=0; i<sendMemberList.size(); i++) {
    		MemberVO vo = memberService.read(sendMemberList.get(i));
    		sendList.add(vo);
    	}
    	
    	receiveMemberList = friendRequestService.readFrom(principal.getName());
    	for(int i=0; i<receiveMemberList.size(); i++) {
    		MemberVO vo = memberService.read(receiveMemberList.get(i));
    		receiveList.add(vo);
    	}
    	model.addAttribute("vo", memberService.read(principal.getName()));
    	model.addAttribute("sendList", sendList);
    	model.addAttribute("receiveList", receiveList);
    }
    
    // 친구 추가 요청
    @PostMapping("/addFriend")
    public String addFriend (FriendRequestVO vo, RedirectAttributes reAttr) {
    	logger.info("addFriend() vo ? " + vo.toString());
    	int result = memberService.read(vo.getReceiveMemberId(), "check");
    	logger.info("멤버 중 요청 아이디 있나? 1이면 있음 = " + result);
 		if(result == 1) {
 			result = friendRequestService.read(vo.getReceiveMemberId());
 			logger.info("친추 중복인가? 1이면 중복 = " + result);
 			if(result != 1) {
 				result = friendRequestService.create(vo);
 	 			logger.info("친구 요청 성공");
 	 			reAttr.addFlashAttribute("alert", "success");
 	 			return "redirect:/friend/list";
 	 		} else {
 	 			logger.info("친구 신청 중복? y");
 	 			reAttr.addFlashAttribute("alert", "dupl");
 	 			return "redirect:/friend/list";
 	 		}
 		} else {
 			logger.info("없는 아이디 입니다.");
	 		reAttr.addFlashAttribute("alert", "fail");
	 		return "redirect:/friend/list";
 			
 		}
 	} //end addFirend()
    
	
	// 받은 요청 수락
    @PostMapping
    public void acceptPOST(FriendVO vo) {
    	logger.info("acceptPOST() vo ? " + vo.toString());
    	int result = friendService.create(vo);
    	logger.info("");
    	if(result == 1) {
    		
    	}
    }
    
    
    
    
    
    // 미리보기
 	@GetMapping("/display")
     public ResponseEntity<byte[]> display(String fileName) {
         logger.info("display() 호출 fileName = " + fileName);
         
         ResponseEntity<byte[]> entity = null;
         InputStream in = null;
         
         String filepath = uploadPath + fileName;
         logger.info("filepath 경로 = " + filepath);
         
         try {
             in = new FileInputStream(filepath); // InputStream에 넣어서
             
             // 파일 확장자
             String extension = 
                     filepath.substring(filepath.lastIndexOf(".") + 1);
             logger.info(extension);
             
             // 응답 헤더(response header)에 Content-type 설정
             HttpHeaders httpHeaders = new HttpHeaders();
             httpHeaders.setContentType(MediaUtil.getMediaType(extension)); // 까지가 확장자 인식
             // 데이터 전송
             entity = new ResponseEntity<byte[]>( // ResponseEntity JSON에서 데이터 보낼때 씀
                     IOUtils.toByteArray(in), // 파일에서 읽은 데이터
                     httpHeaders, // 응답 헤더
                     HttpStatus.OK
                     );
             
         } catch (Exception e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
         
         return entity;
     } //end display()
	
	
} //end friendController
