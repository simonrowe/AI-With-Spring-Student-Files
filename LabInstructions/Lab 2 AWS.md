## Lab 2 - AWS Bedrock

In this exercise you will create a simple Spring Boot application which can make calls to Amazon Bedrock.  Bedrock is a managed service which gives us access to several different text and image generation models.  The first thing we will need to do is create an AWS account (if we don't have one) and obtain AWS credentials (access key ID and secret key).  Once we start writing our Spring Boot application, the hard part will behind us.  Let's jump in.

---
**Part 1 - Obtain an AWS Account**

NOTE:  The AI industry changes rapidly.  The instructions here were valid as of June 2024.  You may find the screens and terminology have updated since these instructions were recorded.

1. If you already have an AWS account for personal use - and AWS credentials - you can skip this section.  If you have access to an account through your employer, you _may_ be able to use it, but you could find that you have insufficient permission to use the services described in this lab.  We always recommend having your own account for the benefit of your own education.

1. Go to https://aws.amazon.com/ and follow the process to create an AWS account.  There are many resources that can help guide you through this process such as [this video](https://www.youtube.com/watch?v=xi-JDeceLeI).


1. The account you have just created gives you access to "root" credentials which cannot be limited.  AWS strongly recommends avoiding root credentials for day-to-day use.  Instead create an "IAM user" within this account.  [This video](https://www.youtube.com/watch?v=ubrE4xq9_9c) provides a good guide.   
    - Ignore recommendations to use Identity Center.  This provides a more proper way to manage identities in your AWS account, but it tangental to our purposes here.
    - Give your user the `AmazonBedrockFullAccess` policy.  This follows the _grant least privilege_ security approach to allow only what is needed for this lab.
    - Be sure to "Download .csv file" containing the user's password.
4. Select the new user from the user list and access the security credentials.  Create an access key.  Choose any use case, but ignore the recommended alternatives.  Be sure to download the resulting CSv file; this contains the Access Key ID and Secret access key "credentials" that we need.
- On AWS, don't confuse Access Key ID / Secret access key with user name and password.  The former are "credentials" used for programatic access, the latter is only for human access to the web interface    

---
**Part 2 - Set Credentials**

Next, provide the access key ID and secret key to our software.  

AWS recommends (and we agree) that the best way to provide credentials to our code is IAM Roles and the [EC2 Instance Metadata service](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/configuring-instance-metadata-service.html), or the local `~/.aws/credentials file`.  However, to keep things simple and consistent with other labs, we will use environment variables.

4. Set the following environment variables using the values obtained from the CSV file.  On Windows you can run: 
```
setx SPRING_AI_BEDROCK_AWS_ACCESS_KEY "YOUR_ACCESS_KEY"
setx SPRING_AI_BEDROCK_AWS_SECRET_KEY "YOUR_SECRET_KEY"
setx SPRING_AI_BEDROCK_AWS_REGION "us-west-2"
```
On Linux or Mac you can run:
```
export SPRING_AI_BEDROCK_AWS_ACCESS_KEY="YOUR_ACCESS_KEY"
export SPRING_AI_BEDROCK_AWS_SECRET_KEY="YOUR_SECRET_KEY"
export SPRING_AI_BEDROCK_AWS_REGION="us-west-2"
```
- The region code points to US West 2, Oregon.  Feel free to choose a different [region](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/using-regions-availability-zones.html#concepts-regions), though some regions may require you to "opt-in" to use them, and may not provide the same Bedrock features.

