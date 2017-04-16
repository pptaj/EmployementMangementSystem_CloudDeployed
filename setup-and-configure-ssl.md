# SSL (Secure Sockets Layer) certificate protection

**Steps to Activate the SSL**

 - Namecheap offers one year SSL certificate for free with Github Student Developer pack 
 - After purchasing the SSL we need to have a Certificate Signing Request (or CSR) code generated locally.
 - We generated our CSR on Tomcat using a keytool.
  - Steps to Generate CSR :
     
      1. A terminal or console and run the command below:
     
         keytool -genkey -keysize 2048 -keyalg RSA -alias tomcat -keystore yourkeystore.jks
     
         this above command generates a keystore and Private key.
     
     2. To generate the CSR using the keystore with the Private Key you established in the step above:
     
         keytool -certreq -alias tomcat -file your.csr -keystore yourkeystore.jks
     
     3. The CSR generated is in the file your.csr
 - We need to enter the CSR and use the email validation method to activate the SSL.
 
 - Import that certificate as well as the root and intermediate certificate in the following order. If you dont respect the order it will fails:
 * keytool -import --alias root -trustcacerts -file root.crt  -keystore myKeyStore
 * keytool -import --alias inter1 -trustcacerts -file inter1.crt  -keystore myKeyStore
 * keytool -import --alias inter2 -trustcacerts -file inter2.crt  -keystore myKeyStore 
 * keytool -import --alias tomcat   -trustcacerts -file myCertificate.crt  -keystore myKeyStore


# Tomcat 

```
<Connector port="9443" protocol="HTTP/1.1" SSLEnabled="true"
               maxThreads="150" scheme="https" secure="true"
               keystoreFile="myKeyStore"
               keystorePass="password"
               keyAlias="tomcat"
               clientAuth="false" sslProtocol="TLS" />
```



