//implementation 'com.itextpdf:itextpdf:5.5.13'

package com.example.test;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_PDF_REQUEST = 1;

    private Button selectPdfButton;
    private TextView pdfTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectPdfButton = findViewById(R.id.select_pdf_button);
        pdfTextView = findViewById(R.id.pdf_text_view);

        selectPdfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent, "Select PDF"), PICK_PDF_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();

            new PdfTextExtractorTask().execute(uri);
        }
    }

    private class PdfTextExtractorTask extends AsyncTask<Uri, Void, String> {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected String doInBackground(Uri... uris) {
            try {
                InputStream inputStream = getContentResolver().openInputStream(uris[0]);
                PdfReader pdfReader = new PdfReader(inputStream);
                StringBuilder textBuilder = new StringBuilder();
                int numberOfPages = pdfReader.getNumberOfPages();

                for (int i = 1; i <= numberOfPages; i++) {
                    String text = PdfTextExtractor.getTextFromPage(pdfReader, i).trim();
                    textBuilder.append(text);
                    textBuilder.append(" ");
                }
                String strr=textBuilder.toString();
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

                if(s1[y].startsWith("No")) {

                    s1[y]="";
                }

                if(s1[y].startsWith("Page:")) {

                    s1[y]="";
                }
                   




                }
             

                List<String> list1=new ArrayList<String>();
          
                for(int i = 0; i < s1.length; i++){
                   list1.add(s1[i]) ;
                }

                list1.removeAll(Arrays.asList("", null));
              
                pdfReader.close();
                return list1.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String text) {
            if (text != null) {
                pdfTextView.setText(text);
            }
        }
    }
}


