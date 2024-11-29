<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 
<style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            padding: 50px;
        }
        .upload-container {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 30px;
            max-width: 400px;
            margin: auto;
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .file-input {
            margin: 20px 0;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            width: 100%;
        }
        .submit-btn {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            text-align: center;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
        }
        .submit-btn:hover {
            background-color: #45a049;
        }
    </style>
    
    
</head>
<body>
    <div class="upload-container">
        <h2>Upload Your PDF</h2>
        
   <!-- Display message if available -->
<% String message = (String) request.getAttribute("message"); %>
<% if (message != null) { %>
    <div style="color: green;">
        <%= message %>
    </div>
<% } %>
    
        <form action="/upload" method="POST" enctype="multipart/form-data">
        
        <input type="text" name="input" class="input" accept="String" required><br>
        
            <input type="file" name="pdfFile" class="file-input" accept=".pdf" required>
            <button type="submit" class="submit-btn">Upload PDF</button>
            
            
            
            
        </form>
        
         
        
    </div>
</body>
</html>