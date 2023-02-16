import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
public class PdfToConsole extends test {
   public static void main(String args[]) throws IOException {
      //Loading an existing document
      File file = new File("G:\\project\\cs.pdf");
      PDDocument document = Loader.loadPDF(file);
      //Instantiate PDFTextStripper class
      PDFTextStripper pdfStripper = new PDFTextStripper();
      //Retrieving text from PDF document
      String strr = pdfStripper.getText(document);
      ///System.out.println(text);
      //test t=new test();
     // System.out.print(t.filter(text)); 
      String strNew=strr.replace("No of","");
      strr=strNew.replace("*********** End of statement *********","");
     String[]s1=strr.split("\\n");
  for(int y=0;y<s1.length;y++) {
	  if(s1[y].startsWith("College")) {
		  s1[y]="";
	  }
	  if(s1[y].startsWith("SCIENCE")) {
		  s1[y]="";
	  }
	  
	  if(s1[y].startsWith("SubCode")) {
		  s1[y]="";
	  }
  
	  if(s1[y].startsWith("Result")) {
		  s1[y]="";
	  }
	  if(s1[y].startsWith("Course")) {
		  s1[y]="";
	  }
	  if(s1[y].startsWith("NOTE")) {
		  s1[y]="";
	  }
	  if(s1[y].startsWith("UNIVERSITY")) {
		  s1[y]="";
	  }
	  
  }
	  StringJoiner joiner = new StringJoiner("");
      for(int i = 0; i < s1.length; i++) {
         joiner.add(s1[i]);
      }
      String str = joiner.toString();
      String[]dk=str.split("\\s");

	  	List<String> list1=new ArrayList<String>();  
	    	for(String lang:dk){  
	    	list1.add(lang);  
	    	} 
	    	
	    	list1.removeAll(Arrays.asList("", null));
	    	String st="Papers:";
	    	int u=0;
	    	int c=0;
	    	for( u=0;u<list1.size();u++) {
	    		try {
	    		 c=list1.indexOf(st);
	    		
	    		//System.out.println(c);
	    		list1.remove(c);
	    		list1.remove(c);
	    		
	    	}
	    		catch(Exception e) {
	    		}
	    	}
	    	st="Page:";
	    	for(u=0;u<list1.size();u++) {
	    		try {
	    			c=list1.indexOf(st);
		    		
		    	
		    		list1.remove(c);
		    		list1.remove(c);
	    			
	    		}
	    		catch(Exception e) {
	    			
	    		}
	    	}
	    	for(String kl:list1) {
    			System.out.println(kl);
    		  } 
    		
      
System.out.println("end");
    
   
       //Closing the document
      document.close();
   }
}
