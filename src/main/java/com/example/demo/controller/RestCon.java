package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
@Controller
public class RestCon {
 
	@GetMapping("/newfile")
	public String con() {
		return "newfile";
	}
	@GetMapping("/homefile")
	public String conig() {
		return "homefile";
	}
	 // POST mapping for handling file upload
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("pdfFile") MultipartFile pdfFile, 
    		@RequestParam("input") String inputValue,
    		Model model, RedirectAttributes redirectAttributes) {
    	
    	
        if (pdfFile.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "No file selected.");
            return "redirect:/newfile"; 
        }
 
        try {
        	
        	// Define the parent directory where the temp folder will be created
            Path parentDir = Path.of("C:/Users/Mohamed Azeez/Downloads/demoPro/");
           
            // Get the file name and path to save the uploaded file
            String fileName = pdfFile.getOriginalFilename();
            Path uploadDir = Files.createTempDirectory(parentDir, "temp");
            System.out.println("Temporary directory created at: " + uploadDir);
            //Path uploadDir = Files.createTempDirectory("C:/Users/Mohamed Azeez/Downloads/demoPro/temp");; // Define the directory to save the uploaded files
            File uploadDirectory = new File(uploadDir.toFile(), fileName);
            if (!uploadDirectory.exists()) {
                uploadDirectory.mkdir(); // Create the upload directory if it doesn't exist
            }
 
            // Save the file
            File savedFile = new File(uploadDir + File.separator + fileName);
            pdfFile.transferTo(savedFile);
            System.out.println("File Uploaded");
            // Check PDF content (example: extract text)
            String pdfText = extractTextFromPDF(savedFile);
            System.out.println(pdfText);
            if (pdfText.contains(inputValue)) {
                model.addAttribute("message", "PDF contains "+inputValue);
            } else {
                model.addAttribute("message", "PDF does not contain "+inputValue);
            }
            if(savedFile.exists()) {
            	 savedFile.delete();
            }if(uploadDir.toFile().exists()) {
            	uploadDir.toFile().delete();
            }if(!pdfText.isEmpty()) {
            	pdfText = "";
            	System.out.println("Cleard");
            	System.out.println(pdfText);
            }
           
            
        } catch (IOException e) {
            model.addAttribute("message", "Failed to upload the file: " + e.getMessage());
        }
 
        return "homefile"; 
    }
 
    
    private String extractTextFromPDF(File file) throws IOException {
        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);  // Extract text from the PDF document
        }
    }


}