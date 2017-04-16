1. **Team member information**

  * Christopher Dsouza &lt;dsouza.chr@husky.neu.edu&gt;
  * Letitia Dsouza &lt;dsouza.le@husky.neu.edu&gt;
  * Taj Poovaiah Palecanda &lt;palecanda.t@husky.neu.edu&gt;
  * Vignesh Karthikeyan &lt;karthikeyan.v@husky.neu.edu&gt;
  

2. **Prerequisites for building and deploying your application locally and on cloud platform .**

  Local Machine
  * Installation of Ubuntu
  * Creating a non-root login
  * Installation of Apache Tomcat 7

  Cloud Platform:
  * Installation of Apache Tomcat 7
  * Account credentials in tomcat server i.e. tomcat-users.xml
  * Create an Ubuntu instance on AWS.


3. **Build and Deploy instructions for web application.**
  1. Create AWS account ( [http://aws.amazon.com/](http://aws.amazon.com/) )
    * Create an IAM user aws iam create-user --user-name Tejas
    * Give user access to AWS Management Console
    * Grant Programmatic access if the users require access to the API, AWS CLI
    * Grant IAM user access to AWS CloudFormation
    * Install and Configure AWS CLI
    * Create a Service Role to grant permissions to AWS service to access AWS resources to AWS deploy code.
    * Create an IAM Instance Profile for Amazon EC2 instance
  
  2. Create a GitHub account (  [https://github.com/join](https://github.com/) )
  3. Create a GitHub Repository
  4. Commit the application into the Repository
  5. Provision an instance
  6. Create an Amazon EC2 instance running on Ubuntu Server
  7. Deploy Application to the instance


4. **Instructions to run unit and/or integration tests.**

    Running Unit Tests:
  - Isolate parts of programs
  - Verify that independent part of programs are working correctly
  - Write a unit test for every case (positive, negative, null, not null, whitespaces, etc.)
  - Run each unit test and make sure that it passes before submitting the code

 Running Integration Tests:
  - Write tests to verify that interaction between multiple components (applications, services, modules, etc.) is working as    expected.
  - Write an integration test for every data exchange between the data base and the application
  - Run each integration test and make sure that it passes before submitting the code
 
5. **Link to TravisCI setup.**
( [https://travis-ci.com/dsouzachr/neu-csye6225-4] )


6. **Domain Name**
neu-csye6225-spring2017-team-8.site

7. **IAM Console Link**
   https://neu-csye6225-spring2017-team-8.signin.aws.amazon.com/console
   
8. **Change in Technology being used**

   - The project was initially designed to use gradle, but has now been changed to use maven
   - Cassandra was chosen to be the NoSQL database in the project, but has been replaced by DynamoDB due to AWS management, 
     disaster recovery, and cost related issues
     
9. **Progress in the application**
   - Added functionalities such as 
   * Session Handling
   * Leave Management
   * Status Update
   * Task Assignment
   * KPI (Key Performance Inidcator)
   
10. **Shell Scripts**
    - Added Shell Scripts 
    - setups3,websecurity group, dbsecurity group,launchec2instance,mysqlrdsinstance,createdynamo Table
    - Worked on test cases, DynamoDB connectionn and UI of the application

11. **Editing the git config file**
- $ git config --list --local
- $ git config --local --edit

12. **Test Cases**
- Test cases written for Person (Checking if person exists in the Database - Valid or Invalid entry)
- Test cases written for Leaves (Checking if entry is valid or invalid)
- Test cases written for Tasks (Checking if entry is valid or invalid)

13. **File Storage**
- Pdfs are uploaded to S3 with employee feedbacks
- Employees can view their feedback from S3

14. **Penetration Testing and Vector attacks**
- SQL Map: sqlmap is an open source penetration testing tool that automates the process of detecting and exploiting SQL injection flaws   and taking over of database servers
- Kali Linux penetration testing tools
- Wireshark: world’s foremost and widely-used network protocol analyzer
- We plan on using Grabber, XSS and Wapiti as well

