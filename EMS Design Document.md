**EMPLOYEE MANAGEMENT SYSTEM**

**Description** 
:
We have built our employee management software with both management efficiency and employee satisfaction in mind. If you run a medium-to-large business, you probably know how difficult it is to keep track and manage important information regarding your employees, and you also know how difficult it is to permanently communicate with your staff, especially if your organization employs dozens, or hundreds of individuals. Dealing with such a large volume of diversified information can put a strain on the human resources department, thus lowering the quality of care your staff gets, and it can have a negative impact upon employee satisfaction and motivation. Because employees are the backbone of every successful business, it is very important that the human resources department is not ignored or overlooked.

**Database Diagram**

![database diagram](https://cloud.githubusercontent.com/assets/22307672/23042075/3f2920d0-f465-11e6-9d90-6a2df33b5cfb.png)


**Application Features:**

1. Session handling –  Login/Logout and user accounts
2. Leave Management – Approvals and Leave Application
3. Code Review – Reviews and Approvals
4. Status Update – Work Load update
5. KPI (Key Performance Indicator) – Ratings, PDF

**Storage in S3**
- Every Employee is given a feedback from their respective managers. Once the manager submits the feedback, a pdf is generated for each   employee, and uploaded to S3
- The employee can view the pdf stored in S3 by requesting to view his/her performance

**Technology Stack:**

1. User Interface – Responsive Website (IntelliJ IDE with Maven as the build manager)
2. Database – RDBMS (MySQL) &amp; NoSQL (Cassandra. Switched to DynamoDB)
3. Uploads – Resumes, Knowledge transfer documents, account profile picture
4. Programming Language – JAVA (Backend), JavaScript/JQuery (Frontend)
5. AJAX &amp; JSON

**SES**
- Web app allows users to reset password by providing their user id
- Web app sends email to the address user provided when the account is created
- Email is sent using Amazon Simple Email Service (Amazon SES)
- Emails sent by Amazon SES is authenticated using DKIM and SPF

**Application Development**
- Application development work is running on cloud using EC2, RDS, DynamoDB, etc
- Application only answers to HTTPS requests. HTTP requests redirects to HTTPS

**Website Link**
http://www.neu-csye6225-spring2017-team-8.site

**Team Members:**

- Christopher Dsouza ( [dsouza.chr@husky.neu.edu](mailto:dsouza.chr@husky.neu.edu))
- Letitia Dsouza ( [dsouza.le@husky.neu.edu](mailto:dsouza.le@husky.neu.edu))
- Taj Poovaiah ( [palecanda.t@husky.neu.edu](mailto:palecanda.t@husky.neu.edu))
- Vignesh Karthikeyan ( [karthikeyan.v@husky.neu.edu](mailto:karthikeyan.v@husky.neu.edu))

