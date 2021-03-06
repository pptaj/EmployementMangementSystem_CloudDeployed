package com.ems.doa;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.TableCollection;
import com.amazonaws.services.dynamodbv2.model.*;
import com.ems.pojo.Manager;
import org.hibernate.Query;

import java.io.IOException;
import java.util.*;

/**
 * Created by dleti on 3/19/2017.
 */
public class RequestDynamoDB {
    static AmazonDynamoDBClient client;
    static String tablename = "workrequests";
    static DynamoDB dynamoDatabase;
    DynamoDBMapper mapper;

    public RequestDynamoDB() throws IOException {
        /* new RequestDynamoDB().clientdynamo(); */
    }

   public List<String> listOptions(){
       ArrayList<String> list = new ArrayList<String>();
       list.add("Open");
       list.add("In Progress");
       list.add("Closed");
       return list;
   }
    public void clientdynamo() throws IOException {
        client = new AmazonDynamoDBClient(createAwsCredentials());
        client.setEndpoint("dynamodb.us-east-1.amazonaws.com");
        dynamoDatabase = new DynamoDB(client);
        mapper = new DynamoDBMapper(client);

    }

    private AWSCredentials createAwsCredentials() throws IOException {
        Properties properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream("aws.properties"));
        AWSCredentials credentials = new BasicAWSCredentials(properties.getProperty("accessKey"), properties.getProperty("secretKey"));
        return credentials;
    }

    private static void removeRequest() {
        try {
            Map<String, AttributeValue> key = new HashMap<String, AttributeValue>();
            key.put("workRequestID", new AttributeValue().withN("456"));
            DeleteItemRequest deleteItemRequest;
            deleteItemRequest = new DeleteItemRequest(tablename, key);
            DeleteItemResult ItemResult = client.deleteItem(deleteItemRequest);
            System.out.println("deleted item");

        } catch (AmazonServiceException ase) {
            System.out.println("Failed to create items " + tablename);
        }
    }



    public  List getRequest(
            int value) throws Exception {

        System.out.println("Scan enteries");

        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withN(String.valueOf(value)));
        eav.put(":val2", new AttributeValue().withS("Open"));
        eav.put(":val3", new AttributeValue().withS("In Progress"));


        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("managerid = :val1 and ( requestStatus = :val2 or requestStatus = :val3)")
                .withExpressionAttributeValues(eav);

        List<WorkRequest> scanResult = mapper.scan(WorkRequest.class, scanExpression);
           return scanResult;

    }
    public  List getRequest() throws Exception {

        System.out.println("Scan enteries");

        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
         eav.put(":val1", new AttributeValue().withS("Open"));
         eav.put(":val2", new AttributeValue().withS("In Progress"));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("requestStatus = :val1 or requestStatus = :val2")
                .withExpressionAttributeValues(eav);

        List<WorkRequest> scanResult = mapper.scan(WorkRequest.class, scanExpression);
        return scanResult;

    }
    @DynamoDBTable(tableName = "workrequests")
    public static class WorkRequest {
        private String workRequestID;
        private int managerid;
        private int employeeID;
        private String designation;
        private String emailAddress;
        private String firstName;
        private String lastName;
        private String userRole;
        private String managerComments;
        private String adminComments;
        private String requestStatus;

        @DynamoDBHashKey(attributeName = "workRequestID")
        @DynamoDBAutoGeneratedKey
        public String getWorkRequestID() {
            return workRequestID;
        }
        public void setWorkRequestID(String id) {
            this.workRequestID = id;
        }
        @DynamoDBAttribute(attributeName = "managerid")
        public int getManagerid() {
            return managerid;
        }

        public void setManagerid(int managerid) {
            this.managerid = managerid;
        }
        @DynamoDBHashKey(attributeName = "employeeID")
        public int getEmployeeID() {
            return employeeID;
        }

        public void setEmployeeID(int employeeID) {
            this.employeeID = employeeID;
        }
        @DynamoDBHashKey(attributeName = "designation")
        public String getDesignation() {
            return designation;
        }

        public void setDesignation(String designation) {
            this.designation = designation;
        }
        @DynamoDBHashKey(attributeName = "emailaddress")
        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }
        @DynamoDBHashKey(attributeName = "firstName")
        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
        @DynamoDBHashKey(attributeName = "lastName")
        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
        @DynamoDBHashKey(attributeName = "userRole")
        public String getUserRole() {
            return userRole;
        }

        public void setUserRole(String userRole) {
            this.userRole = userRole;
        }
        @DynamoDBHashKey(attributeName = "managerComments")
        public String getManagerComments() {
            return managerComments;
        }

        public void setManagerComments(String managerComments) {
            this.managerComments = managerComments;
        }
        @DynamoDBHashKey(attributeName = "adminComments")
        public String getAdminComments() {
            return adminComments;
        }

        public void setAdminComments(String adminComments) {
            this.adminComments = adminComments;
        }
        @DynamoDBHashKey(attributeName = "requestStatus")
        public String getRequestStatus() {
            return requestStatus;
        }

        public void setRequestStatus(String requestStatus) {
            this.requestStatus = requestStatus;
        }
    }

    public void createWorkrequests(String designation, String emailAddress, String firstName, String lastName, String userRole, String managerComments,String adminComments, String requestStatus, int employeeID, int managerID) {

        try {
             WorkRequest workReq= new WorkRequest();
            workReq.setDesignation(designation);
            workReq.setEmailAddress(emailAddress);
            workReq.setFirstName(firstName);
            workReq.setLastName(lastName);
            workReq.setUserRole(userRole);
            workReq.setManagerComments(managerComments);
            workReq.setRequestStatus(requestStatus);
            workReq.setEmployeeID(employeeID);
            workReq.setManagerid(managerID);
            workReq.setAdminComments(adminComments);
            mapper.save(workReq);

        } catch (AmazonServiceException ase) {
            System.out.println("Failed to create items " + tablename);
        }
    }
    public int mgrUpdateTasks(String workRequestID, String managerComments, String status){
        int result = 0;
        try{
            Map<String, AttributeValueUpdate> updates = new HashMap<String, AttributeValueUpdate>();
            Map<String, AttributeValue> key = new HashMap<String, AttributeValue>();
            key.put("workRequestID", new AttributeValue().withS(workRequestID));
            updates.put("managerComments", new AttributeValueUpdate().withValue(new AttributeValue().withS(managerComments)));// Update item's Setting attribute
            updates.put("requestStatus", new AttributeValueUpdate().withValue(new AttributeValue().withS(status)));// Update item's Setting attribute
            UpdateItemRequest updateItemRequest;
            updateItemRequest = new UpdateItemRequest(tablename, key, updates);
            UpdateItemResult updateItemResult = client.updateItem(updateItemRequest);

            result =1;

        }catch(AmazonServiceException ase){
            result =0;
            System.out.println("Failed to update comments " + tablename);
        }
        return result;
    }
    public int adminUpdateTasks(String workRequestID, String adminComments, String status){
        int result = 0;
        try{
            Map<String, AttributeValueUpdate> updates = new HashMap<String, AttributeValueUpdate>();
            Map<String, AttributeValue> key = new HashMap<String, AttributeValue>();
            key.put("workRequestID", new AttributeValue().withS(workRequestID));
            updates.put("adminComments", new AttributeValueUpdate().withValue(new AttributeValue().withS(adminComments)));// Update item's Setting attribute
            updates.put("requestStatus", new AttributeValueUpdate().withValue(new AttributeValue().withS(status)));// Update item's Setting attribute
            UpdateItemRequest updateItemRequest;
            updateItemRequest = new UpdateItemRequest(tablename, key, updates);
            UpdateItemResult updateItemResult = client.updateItem(updateItemRequest);

            result =1;

        }catch(AmazonServiceException ase){
            result =0;
            System.out.println("Failed to update comments " + tablename);
        }
        return result;
    }
}







