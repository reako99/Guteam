package project.spring.guteam.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.spring.guteam.domain.GameVO;
import project.spring.guteam.fileutil.GameImageUploadUtil;
import project.spring.guteam.fileutil.MediaUtil;
import project.spring.guteam.pageutil.PageCriteria;
import project.spring.guteam.pageutil.PageMaker;
import project.spring.guteam.service.GameService;

@Controller
@RequestMapping(value = "/game")
public class GameController {
	private static final Logger logger = LoggerFactory.getLogger(GameController.class);

	@Autowired
	private GameService gameService;

	@Resource(name = "uploadPath")
	private String uploadPath;

	@GetMapping("/list")
	public void list(Model model, Integer page, Integer numsPerPage, String keyword, String keywordCriteria, String orderBy, String interest, Principal principal) {
		logger.info("list 호출");
		logger.info("page = " + page + ", numsPerPage = " + numsPerPage);
		PageCriteria criteria = new PageCriteria();
		if (page != null) {
			criteria.setPage(page);
		}
		if (numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);		
		readListsAndSetModel(keyword, keywordCriteria, pageMaker, criteria, model, orderBy, interest, principal);
	}

	@GetMapping("/register")
	public void registerGET() {
	}

	@PostMapping("/register")
	public String registerPOST(GameVO vo, MultipartFile file, RedirectAttributes reAttr) {
		logger.info("register 호출 file = " + file);
		if (vo.getGameImageName().equals("basic.png") 
				&& file != null && !file.getOriginalFilename().equals("")) {
			try {
				logger.info(file.getOriginalFilename());
				vo.setGameImageName(
						GameImageUploadUtil.saveUploadedFile(uploadPath, gameService.getSeqNo()+"."+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1),
								file.getBytes()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String gameImageName = vo.getGameImageName();
		try {
			gameImageName = URLEncoder.encode(gameImageName, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		vo.setGameImageName(gameImageName);
		int result = gameService.create(vo);
		if (result == 1) {
			reAttr.addFlashAttribute("insert_result", "success");
			return "redirect:/game/list";
		} else {
			return "redirect:/game/register";
		}
	}

	@GetMapping("/detail")
	public void detail(Model model, int gameId, String prevListUrl, Principal principal) {
		Map<String, Object> args = gameService.read(gameId, principal);
		GameVO vo = (GameVO) args.get("vo");
		model.addAttribute("vo", vo);
		int rating = (int) args.get("rating");
		model.addAttribute("rating", rating);
		if (prevListUrl == null) {
			prevListUrl = "list";
		}
		model.addAttribute("prevListUrl", prevListUrl);
	}

	@GetMapping("/update")
	public void updateGET(Model model, int gameId, String prevListUrl, Principal principal) {
		Map<String, Object> args = gameService.read(gameId, principal);
		GameVO vo = (GameVO) args.get("vo");
		model.addAttribute("vo", vo);
		try {
			prevListUrl = URLEncoder.encode(prevListUrl, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			model.addAttribute("prevListUrl", prevListUrl);
		}
	}

	@PostMapping("/update")
	public String updatePOST(GameVO vo, RedirectAttributes reAttr, String prevListUrl, MultipartFile file, Principal principal) {

		logger.info("updatePOST() 호출");
		String beforeImageName = ((GameVO)gameService.read(vo.getGameId(), principal).get("vo")).getGameImageName();
		logger.info(vo + "");
		if (file != null && !file.getOriginalFilename().equals("")) {
			try {
				logger.info(file.getOriginalFilename());
				vo.setGameImageName(
						GameImageUploadUtil.saveUploadedFile(uploadPath, vo.getGameId()+"."+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1),
								file.getBytes()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String gameImageName = vo.getGameImageName();
		if (!beforeImageName.equals(gameImageName)) {
			try {
				gameImageName = URLEncoder.encode(gameImageName, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			vo.setGameImageName(gameImageName);
		}
		int result = gameService.update(vo);
		if (result == 1) {
			reAttr.addFlashAttribute("update_result", "success");
			return "redirect:/game/detail?gameId=" + vo.getGameId() + "&prevListUrl=" + prevListUrl;
		} else {
			return "redirect:/game/update?gameId=" + vo.getGameId() + "&prevListUrl=" + prevListUrl;
		}
	}

	@GetMapping("/display")
	public ResponseEntity<byte[]> display(String fileName) {
		logger.info("display() 호출");

		ResponseEntity<byte[]> entity = null;

		InputStream in = null;

		try {
			fileName = URLDecoder.decode(fileName, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (!(fileName.charAt(0) == '/')) {
			fileName = "/" + fileName;
		}

		String filePath = uploadPath + fileName;

		try {
			in = new FileInputStream(filePath);
			// 파일 확장자
			String extension = filePath.substring(filePath.lastIndexOf(".") + 1);
			logger.info(extension);

			// 응답 헤더(response header) 에 Content-Type 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaUtil.getMediaType(extension));
			// 데이터 전송
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), // 파일에서 읽은 데이터
					httpHeaders, // 응답 헤더
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	@GetMapping("/list-ajax/{memberId}")
	public ResponseEntity<Map<String, Object>> recentlyViewed(@PathVariable("memberId") String memberId){	
		logger.info("최근 조회한 게임 불러오기");
		Map<String, Object> args = gameService.recentlyViewedGames(memberId);
		return new ResponseEntity<Map<String, Object>>(args, HttpStatus.OK);
	}

	@PostMapping("/upload-ajax")
	public ResponseEntity<String> uploadAjaxPOST(MultipartFile[] files, String gameId) {
		logger.info("uploadAgaxPOST() 호출");
		// 파일 하나만 저장
		String result = ""; // result : 파일 경로 및 썸네일 이미지 이름
		
		for (int i = 0; i < files.length; i++) {
			try {
				String fileName = gameService.getSeqNo()+"";
				if(gameId!=null) {
					fileName=gameId;
				}
				logger.info(fileName);
				result = GameImageUploadUtil.saveUploadedFile(uploadPath, fileName+"."+files[i].getOriginalFilename().substring(files[i].getOriginalFilename().lastIndexOf(".")+1),
						files[i].getBytes());
				result = URLEncoder.encode(result, "utf-8");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	private void readListsAndSetModel(String keyword, String keywordCriteria, PageMaker pageMaker, PageCriteria criteria,
			Model model, String orderBy, String interest, Principal principal) {
		Map<String, Object> args = new HashMap<>();
		if(principal!=null&&interest!=null&&interest.equals("interest")) {
			pageMaker.setTotalCount(gameService.getTotalCountInterest(principal.getName()));
			if(pageMaker.getTotalCount()==0) {
				model.addAttribute("noData", "noData");
				pageMaker.setTotalCount(gameService.getTotalCount());
				paging(pageMaker, criteria);
				args = gameService.read(criteria);
			}else {
				paging(pageMaker, criteria);
				args = gameService.getInterestGames(principal.getName(),criteria);
			}
		}else if(orderBy!=null) {
			if (keyword == null || keyword.equals("")) {
				pageMaker.setTotalCount(gameService.getTotalCount());
				paging(pageMaker, criteria);
			}else if (keywordCriteria != null && keywordCriteria.equals("price")) {
				pageMaker.setTotalCount(gameService.getTotalCount(Integer.parseInt(keyword)));
				paging(pageMaker, criteria);
				model.addAttribute("keywordCriteria", keywordCriteria);
			}else {
				pageMaker.setTotalCount(gameService.getTotalCount(keyword));
				paging(pageMaker, criteria);
			}
			args = gameService.read(keyword, keywordCriteria, orderBy, criteria);
			
		} else if (keyword == null || keyword.equals("")) {
			pageMaker.setTotalCount(gameService.getTotalCount());
			paging(pageMaker, criteria);
			args = gameService.read(criteria);
			
		} else if (keywordCriteria != null && keywordCriteria.equals("price")) {
			pageMaker.setTotalCount(gameService.getTotalCount(Integer.parseInt(keyword)));
			paging(pageMaker, criteria);
			args = gameService.read(Integer.parseInt(keyword), criteria);
			
			model.addAttribute("keywordCriteria", keywordCriteria);
		} else {
			pageMaker.setTotalCount(gameService.getTotalCount(keyword));
			paging(pageMaker, criteria);
			args = gameService.read(keyword, criteria);
		
		}
		List<GameVO> gameVOList = (List<GameVO>) args.get("gameVOList");
		List<Integer> ratingList = (List<Integer>) args.get("ratingList");
		model.addAttribute("gameVOList", gameVOList);
		model.addAttribute("ratingList", ratingList);
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("keyword", keyword);
	}
	
	
	private void paging(PageMaker pageMaker, PageCriteria criteria) {
		pageMaker.setPageData();
		if (criteria.getPage() > pageMaker.getEndPageNo()) {
			criteria.setPage(pageMaker.getEndPageNo());
		} else if (criteria.getPage() <= 0) {
			criteria.setPage(1);
		}
		pageMaker.setPageData();
	}

}
