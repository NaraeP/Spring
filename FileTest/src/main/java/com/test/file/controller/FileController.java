package com.test.file.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;
import com.test.file.model.FileDAO;
import com.test.file.model.PicDTO;
import com.test.file.model.PlaceDTO;

@Controller
public class FileController {

	@Autowired
	private FileDAO dao;
	
	@GetMapping(value = "/add.do")
	public String add(Model model) {

		return "add";
	}
	
	@PostMapping(value = "/addok.do")
	public String addok(Model model, String txt, MultipartFile attach, HttpServletRequest req) {

		//문자열, 첨부파일 넘어옴(txt, attach)
		//문자열: 기본 방식으로 받아와진다(매개변수로)
		//첨부파일: MultipartFile attach(매개변수 이름은 name과 동일해야한다.)
		
		//System.out.println(txt);
		System.out.println(attach.getName()); //<input type="file" name="attach">
		System.out.println(attach.getOriginalFilename()); //업로드 파일명
		System.out.println(attach.getContentType()); //MIME types
		System.out.println(attach.getSize()); //사이즈(Byte)
		System.out.println(attach.isEmpty()); //존재유무
		
		try {
			
			//두가지 문제점
			//1. 파일을 업로드 폴더에 이동하기(우리가 직접 해야 함)
			//2. 파일명 중복 방지(우리가 직접 해야 함)
			//	a. 숫자 붙이기
			//	b. 고유 접두어 붙이기
			//		- 시간_파일명(앞의 시간이 유일성 보장)
			//		- 난수_파일명
			//	c. UUID(Universally Unique ID)
			//		- 네트워크상에서 고유성이 보장되는 ID를 만들기 위한 표준
			//		- 시간 + 난수 조합
			
			System.out.println(System.nanoTime() + "_" + attach.getOriginalFilename());
			
			UUID uuid = UUID.randomUUID();
			System.out.println(uuid);
			
			//오늘은 UUID를 사용하여 파일명 중복 방지를 진행할 예정!!!
			
			String path = req.getRealPath("/resources/files");
			System.out.println(path);
			
			File file = new File(path + "\\" + uuid + "_" + attach.getOriginalFilename());
			
			//임시 폴더 > (이동) > files 폴더 :: 직접 이동시켜주는 방법
			attach.transferTo(file);
			
			model.addAttribute("txt", txt);
			model.addAttribute("filename", uuid + "_" + attach.getOriginalFilename()); //하드디스크에 저장한 이름
			model.addAttribute("orgfilename", attach.getOriginalFilename()); //사용자가 올린 원본 이름
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "addok";
	}
	
	@GetMapping(value = "/download.do", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent, String filename, HttpServletRequest req) {

		String path = req.getRealPath("/resources/files");
		Resource resource = new FileSystemResource(path + "\\" + filename);

		if (resource.exists() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		String resourceName = resource.getFilename();

		// remove UUID
		String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);

		HttpHeaders headers = new HttpHeaders();
		try {

			String downloadName = null;

			if (userAgent.contains("Trident")) {
				downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8").replaceAll("\\+", " ");
			} else if (userAgent.contains("Edge")) {
				downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8");
			} else {
				downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
			}


			headers.add("Content-Disposition", "attachment; filename=" + downloadName);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	//목록보기
	@GetMapping(value = "/multi/list.do")
	public String list(Model model) {

		List<PlaceDTO> list = dao.list();
		
		model.addAttribute("list", list);
		
		return "multi/list";
	}
	
	//상세보기
	@GetMapping(value = "/multi/view.do")
	public String view(Model model, String seq, HttpServletRequest req) { //글번호 받기
		
		PlaceDTO dto = dao.get(seq);
		
		//위치 정보가 있을 수도, 없을 수도 있다.
		PicDTO pdto = dto.getPicList().get(0); //여러 이미지를 올릴 경우 여러 이미지의 위치 정보 출력은 불가하니까 임의로 첫번째 사진의 위치 정보만 출력하도록 설정
		
		if (pdto != null) {
			
			//사진 파일 접근
			File file = new File(req.getRealPath("/resources/files/" + pdto.getFilename()));
			
try {
				
				Metadata metadata = ImageMetadataReader.readMetadata(file);
				
				GpsDirectory gps = metadata.getFirstDirectoryOfType(GpsDirectory.class);
				
				if (gps.containsTag(GpsDirectory.TAG_LATITUDE)
						&& gps.containsTag(GpsDirectory.TAG_LONGITUDE)) {
					
					double lat = gps.getGeoLocation().getLatitude();
					double lng = gps.getGeoLocation().getLongitude();
					
					//System.out.println(lat);
					//System.out.println(lng);
					
					model.addAttribute("lat", lat);
					model.addAttribute("lng", lng);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		model.addAttribute("dto", dto);

		return "multi/view";
	}
	
	//등록하기
	@GetMapping(value = "/multi/add.do")
	public String multiadd(Model model) {

		return "multi/add";
	}
	
	//등록하기(처리)
	@PostMapping(value = "/multi/addok.do")
	public String multiaddok(Model model, PlaceDTO dto, MultipartFile[] attach, HttpServletRequest req) { //첨부파일이 다수이므로, 배열로 받으면 된다.(굳이 따지자면 내부적으로 getParameter.values 역할), 파일 업로드 때문에 request 추가
		
		dto.setPicList(new ArrayList<PicDTO>()); //첨부 파일 배열
		
		for (MultipartFile file : attach) {
			
			try {
				
				UUID uuid = UUID.randomUUID();
				
				String filename = uuid + "_" + file.getOriginalFilename();
				
				file.transferTo(new File(req.getRealPath("/resources/files") + "\\" + filename));
				
				//첨부파일 1개 > PicDTO 1개
				PicDTO pdto = new PicDTO();
				pdto.setFilename(filename);
				
				dto.getPicList().add(pdto);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		System.out.println(req.getRealPath("/resources/files"));
		
		
		int result = dao.add(dto);
		
		if (result > 0) {
			return "redirect:/multi/list.do";
		} else {
			return "redirect:/multi/add.do";
		}

	}
	
	
	
}
