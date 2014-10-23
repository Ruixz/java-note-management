package com.ray.jnm.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ray.jnm.util.FileUpload;

public class FileValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		FileUpload file = (FileUpload) target;
		
		if (file.getFile().getSize() == 0) {
			errors.rejectValue("file", "uploadForm.selectFile",
					"Please select a file!");
		}
	}
}
