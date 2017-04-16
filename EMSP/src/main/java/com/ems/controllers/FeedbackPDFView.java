/**
 *
 */
package com.ems.controllers;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.util.IOUtils;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.util.Map;
import java.util.Properties;

import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import static jdk.nashorn.internal.objects.Global.print;

/**
 * @author Christopher Dsouza
 */
public class FeedbackPDFView {
    private AWSCredentials createAwsCredentials() throws IOException {
        Properties properties = new Properties();
        InputStream input;
        properties.load(getClass().getClassLoader().getResourceAsStream("aws.properties"));
        AWSCredentials credentials = new BasicAWSCredentials(properties.getProperty("accessKey"), properties.getProperty("secretKey"));
        return credentials;
    }

	Document document = new Document(PageSize.LETTER);
	public void buildPdfDocument(HttpServletRequest request) throws IOException {
		ByteArrayOutputStream outfile = new ByteArrayOutputStream(4096);

		PdfWriter writer;
		try {
			writer = PdfWriter.getInstance(document, outfile);
			writer.setPdfVersion(PdfWriter.PDF_VERSION_1_7);
			document.open();

			String empID = String.valueOf(request.getAttribute("empID"));
			String firstName = (String) request.getAttribute("firstName");
			String lastName = (String) request.getAttribute("lastName");
			String emailAddress = (String) request.getAttribute("emailAddress");
			String designation = (String) request.getAttribute("designation");
			String managerFirstName = (String) request.getAttribute("managerFirstName");
			String managerLastName = (String) request.getAttribute("managerLastName");
			String managerID = String.valueOf(request.getAttribute("managerID"));
			String managerEmailAddress = (String) request.getAttribute("managerEmailAddress");
			String valueCreator = (String) request.getAttribute("valueCreator");
			String peopleDeveloper = (String) request.getAttribute("peopleDeveloper");
			String businessOperator = (String) request.getAttribute("businessOperator");
			String technicalSkills = (String) request.getAttribute("technicalSkills");
			String taskCompletionSkills = (String) request.getAttribute("taskCompletionSkills");
			String uninformedLease = (String) request.getAttribute("uninformedLease");
			String communicationSkills = (String) request.getAttribute("communicationSkills");
			String efficientAreas = (String) request.getAttribute("efficientAreas");
			String improvementAreas = (String) request.getAttribute("improvementAreas");


			Font font_helvetica_14_normal_black = new Font(Font.HELVETICA, 16, Font.NORMAL, Color.BLACK);
			Font font_courier_14_italic_black = new Font(Font.COURIER, 16, Font.ITALIC, Color.BLACK);
			Font font_times_18_bold_black = new Font(Font.TIMES_ROMAN, 18, Font.BOLD, Color.BLACK);

			Paragraph prg1 = new Paragraph("Performance Feedback", font_times_18_bold_black);
			Phrase phr1 = new Phrase("Personal Details", font_helvetica_14_normal_black);
			Paragraph prg2 = new Paragraph(" ", font_times_18_bold_black);

			Chunk c1 = new Chunk("EMPLOYEE ID:          ", font_courier_14_italic_black);
			Chunk c2 = new Chunk(empID, font_courier_14_italic_black);

			Chunk c35 = new Chunk("EMPLOYEE NAME:          ", font_courier_14_italic_black);
			Chunk c4 = new Chunk(firstName, font_courier_14_italic_black);
			Chunk c5 = new Chunk(lastName, font_courier_14_italic_black);


			Chunk c6 = new Chunk("EMPLOYEE EMAIL:          ", font_courier_14_italic_black);
			Chunk c7 = new Chunk(emailAddress, font_courier_14_italic_black);


			Chunk c8 = new Chunk("DESIGNATION:          ", font_courier_14_italic_black);
			Chunk c9 = new Chunk(designation, font_courier_14_italic_black);

			Phrase phr8 = new Phrase("Manager Details", font_helvetica_14_normal_black);

			Chunk c10 = new Chunk("MANAGER ID:          ", font_courier_14_italic_black);
			Chunk c11 = new Chunk(managerID, font_courier_14_italic_black);


			Chunk c12 = new Chunk("MANAGER NAME:          ", font_courier_14_italic_black);
			Chunk c13 = new Chunk(managerFirstName, font_courier_14_italic_black);
			Chunk c14 = new Chunk(managerLastName, font_courier_14_italic_black);


			Chunk c15 = new Chunk("MANAGER EMAIL:          ", font_courier_14_italic_black);
			Chunk c16 = new Chunk(managerEmailAddress, font_courier_14_italic_black);


			Chunk c17 = new Chunk("VALUE CREATOR:          ", font_courier_14_italic_black);
			Chunk c18 = new Chunk(valueCreator, font_courier_14_italic_black);


			Chunk c19 = new Chunk("PEOPLE DEVELOPER:          ", font_courier_14_italic_black);
			Chunk c20 = new Chunk(peopleDeveloper, font_courier_14_italic_black);


			Chunk c21 = new Chunk("BUSINESS OPERATOR:          ", font_courier_14_italic_black);
			Chunk c22 = new Chunk(businessOperator, font_courier_14_italic_black);


			Chunk c23 = new Chunk("COMMUNICATION SKILLS:          ", font_courier_14_italic_black);
			Chunk c24 = new Chunk(communicationSkills, font_courier_14_italic_black);


			Chunk c25 = new Chunk("TECHNICAL SKILLS:          ", font_courier_14_italic_black);
			Chunk c26 = new Chunk(technicalSkills, font_courier_14_italic_black);


			Chunk c27 = new Chunk("TASK COMPLETION SKILLS:          ", font_courier_14_italic_black);
			Chunk c28 = new Chunk(taskCompletionSkills, font_courier_14_italic_black);


			Chunk c29 = new Chunk("UNINFORMED LEAVES:          ", font_courier_14_italic_black);
			Chunk c30 = new Chunk(uninformedLease, font_courier_14_italic_black);


			Chunk c31 = new Chunk("EFFICIENT AREAS:          ", font_courier_14_italic_black);
			Chunk c32 = new Chunk(efficientAreas, font_courier_14_italic_black);


			Chunk c33 = new Chunk("IMPROVEMENT AREAS:          ", font_courier_14_italic_black);
			Chunk c34 = new Chunk(improvementAreas, font_courier_14_italic_black);

			document.add(prg1);
			document.add(prg2);
			document.add(phr1);
			document.add(prg2);
			document.add(c1);
			document.add(c2);

			document.add(prg2);
			document.add(c35);
			document.add(c4);
			document.add(c5);

			document.add(prg2);
			document.add(c6);
			document.add(c7);

			document.add(prg2);
			document.add(c8);
			document.add(c9);
			document.add(prg2);

			document.add(phr8);
			document.add(prg2);
			document.add(c10);
			document.add(c11);

			document.add(prg2);
			document.add(c12);
			document.add(c13);
			document.add(c14);

			document.add(prg2);
			document.add(c15);
			document.add(c16);

			document.add(prg2);
			document.add(prg2);
			document.add(prg2);
			document.add(c17);
			document.add(c18);

			document.add(prg2);
			document.add(c19);
			document.add(c20);

			document.add(prg2);
			document.add(c21);
			document.add(c22);

			document.add(prg2);
			document.add(c23);
			document.add(c24);

			document.add(prg2);
			document.add(c25);
			document.add(c26);

			document.add(prg2);
			document.add(c27);
			document.add(c28);

			document.add(prg2);
			document.add(c29);
			document.add(c30);

			document.add(prg2);
			document.add(c31);
			document.add(c32);

			document.add(prg2);
			document.add(c33);
			document.add(c34);
			//  document.close();
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			if (document != null) {
				document.close();
			}
		}


		//AWSCredentials credentials = new BasicAWSCredentials(
			//	"AKIAJMX7VAVY4SJMVLEA",
			//	"S77g7c9Wf1k8Gj0E/gE16lyDHDQsVVYjp4+zHEx+");
		// create a client connection based on credentials
		AmazonS3 s3client = new AmazonS3Client((createAwsCredentials()));

		// create bucket - name must be unique for all S3 users
		String bucketName = "test-cloud-computing-new-new-new";
		s3client.createBucket(bucketName);

		String fileName = request.getAttribute("empID") + ".pdf";
		InputStream inputStream = new ByteArrayInputStream(outfile.toByteArray());
		ObjectMetadata objectMetadata = new ObjectMetadata();

		// Write content type and also length (determined via byte array).
		objectMetadata.setContentLength(outfile.toByteArray().length);

		PutObjectRequest putObj = new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata);

		//making the object Public
		putObj.setCannedAcl(CannedAccessControlList.PublicRead);

		s3client.putObject(putObj);
	}
}
