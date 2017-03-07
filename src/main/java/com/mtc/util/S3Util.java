package com.mtc.util;

import java.io.File;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class S3Util {

	public static final String S3_BUCKET_NAME = "client-uploaded-projects-bucket";
	public static final String FILE_DOWNLOAD_PATH = "/Users/etouch/Downloads/AWSDOWNLOADS/";
	public static final String FILE_EXTENSION = ".apk";
	public static final AmazonS3 s3;
    public static void main(String[] args) throws Exception {
    	S3Util s3Util = new S3Util();
    			//s3Util.uploadApp("211",new File("/Users/Lenovo/Desktop/app.apk"));
    			s3Util.downloadApp("1");
    
    }
    
	static {
		 s3 = new AmazonS3Client();
		 Region usWest2 = Region.getRegion(Regions.US_WEST_2);
		 s3.setRegion(usWest2);
		 createBucket();
	 }
    	
	public static void createBucket(){
		if(!s3.doesBucketExist(S3_BUCKET_NAME))
		  s3.createBucket(S3_BUCKET_NAME);
	}
  
   	public static boolean uploadApp(String objectKey, File uploadFile){
   		System.out.println("in uploadApp");
   		boolean uploadedSuccess = false;
    		try{
    	        PutObjectRequest putObjectRequest = new PutObjectRequest(S3_BUCKET_NAME, objectKey, uploadFile); // object key is the projectId
    	        s3.putObject(putObjectRequest);
    	        uploadedSuccess= true;
    	        System.out.println("Successfully uploaded the object into bucket : " + S3_BUCKET_NAME);
    		}catch(Exception exp){
    			System.out.println("An exception occurred while uploading the file into S3 " + exp);
    		}
    	return uploadedSuccess;
    }
    	
	public static File downloadApp(String objectKey){
		boolean downlodedSuccess = false;
		File localFile=null;
    		try{
    	        GetObjectRequest getObjectRequest = new GetObjectRequest(S3_BUCKET_NAME, objectKey); // object key is the projectId
    	       
    	         localFile = new File(FILE_DOWNLOAD_PATH+objectKey+FILE_EXTENSION);
    	        s3.getObject(getObjectRequest, localFile);
    	        downlodedSuccess = localFile.exists() && localFile.canRead();
    	        System.out.println("Successfully downloaded the file into the following location :" + FILE_DOWNLOAD_PATH);
    	   
    		}catch(Exception exp){
    			System.out.println("An exception occurred while downloading the file from S3 " + exp);
    		}
    	return localFile;
    }
}
