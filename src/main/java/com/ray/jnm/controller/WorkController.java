package com.ray.jnm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ray.jnm.entity.Note;
import com.ray.jnm.entity.Project;
import com.ray.jnm.entity.User;
import com.ray.jnm.entity.WorkGroup;
import com.ray.jnm.service.ProjectService;
import com.ray.jnm.service.UserService;
import com.ray.jnm.service.WorkGroupService;
import com.ray.jnm.util.FileUpload;
import com.ray.jnm.validator.FileValidator;

@Controller
public class WorkController {
	
	private static final int BUFFER_SIZE = 4096;

	@Autowired
	private UserService userService;

	@Autowired
	private WorkGroupService workGroupService;
	
	@Autowired
	private ProjectService projectService;

	@Autowired
	private FileValidator fileValidator;
	
	@RequestMapping("/group-work")
	public String groupWork(Model model, Principal principal,
			@ModelAttribute("uploadedFile") FileUpload file,
			BindingResult result) {
		String name = principal.getName();
		User user = userService.findOneWithNotes(name);
		if(user.getWorkGroup()==null){
			return "group-blank";
		}
		WorkGroup workGroup = workGroupService.findOneWithProjects(user.getWorkGroup());
		model.addAttribute("user", user);
		model.addAttribute("group", workGroup);
		return "group-work";
	}

	@RequestMapping(value="/group/{id}", method=RequestMethod.POST)
	public String fileUploaded(
			@ModelAttribute("uploadedFile") FileUpload uploadedFile,
			BindingResult result, @PathVariable int id, Principal principal) {
		InputStream inputStream = null;
		OutputStream outputStream = null;

		MultipartFile file = uploadedFile.getFile();
		fileValidator.validate(uploadedFile, result);

		String newName = FileUpload.rename(file.getOriginalFilename());
		
		String saveFileName = "D:/uploads/" + newName;
		
		if (result.hasErrors()) {
			return "redirect:/group-work.html";
		}

		try {
			inputStream = file.getInputStream();

			File newFile = new File(saveFileName);
			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			outputStream = new FileOutputStream(newFile);
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String userName = principal.getName();
		User user = userService.findOne(userName);
		WorkGroup workGroup = workGroupService.findOne(id);
		projectService.save(newName, user, workGroup);

		return "redirect:/group-work.html";
	}
	
	@RequestMapping(value="/download/{name}.html",method = RequestMethod.GET)
	public String download(@PathVariable String name,HttpServletRequest request,
            HttpServletResponse response, Principal principal) throws IOException{
		Project project = projectService.findOne(name);
		String userName = principal.getName();
		User user = userService.findOne(userName);
		if(user.getWorkGroup().getId()!=project.getWorkGroup().getId()){
			return "deny";
		}
		
		ServletContext context = request.getServletContext();
        System.out.println(name);
        String fullPath = "D:/uploads/"+name;
        
        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);
         
        // get MIME type of the file
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);
 
        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
 
        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);
 
        // get output stream of the response
        OutputStream outStream = response.getOutputStream();
 
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
 
        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
 
        inputStream.close();
        outStream.close();
        
        return "download-sucess";
	}
	
	@RequestMapping("/group-work/remove/{id}")
	public String removeProject(@PathVariable int id, Principal principal) {
		Project project = projectService.findOne(id);
		
		String userName = principal.getName();
		User user = userService.findOne(userName);
		if(project.getWorkGroup().getId()!=user.getWorkGroup().getId()){
			
			return "deny";
		}
		projectService.delete(project);
		return "redirect:/group-work.html";
	}
}
