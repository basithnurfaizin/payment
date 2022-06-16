# Java Payment SDK for BRI Virtual Account


## Todo
1. Create documentation

## How to use 
1. Add dependency to your `pom.xml`
   <br/>
   ```
   <dependency>
   <groupId>io.github.basithnurfaizin</groupId>
   <artifactId>payment</artifactId>
   <version>0.0.2-SNAPSHOT</version>
   </dependency>
   ```
   
2. Create configuration <br />
   ```
   String clientId = "urClientId";
   String clinetSecret = "urClientSecret";
   //url sanbox 
   String urlOuth = "https://sandbox.partner.api.bri.co.id/oauth/client_credential/accesstoken?grant_type=client_credentials";
   
   String relativeUrlBriva = "/v1/briva";
   Strubg baseUrlBriva = "https://sandbox.partner.api.bri.co.id";
   AuthenticationBRIVAServiceImpl authenticationBRIVAService = new AuthenticationBRIVAServiceImpl(
                clientId,
                clientSecret,
                UrlOauth
        );
   
   BrivaService brivaService = new BRIVAServiceImpl(authenticationBRIVAService, relateiveUrlBriva, baseUrlBriva);

   
   ```
3. Integration with spring boot , you have to create  configuration class with BrivaService Bean
     ```

    @Bean
    public BRIVAServiceImpl brivaService() throws IOException, InterruptedException {

        AuthenticationBRIVAServiceImpl authenticationBRIVAService = new AuthenticationBRIVAServiceImpl(
                "b7u14HQYzgdJTP88uoLXqV561S9zyMB2",
                "sBHEDztKD6XrVpeY",
                "https://sandbox.partner.api.bri.co.id/oauth/client_credential/accesstoken?grant_type=client_credentials"
        );

        return new BRIVAServiceImpl(authenticationBRIVAService, "/v1/briva","https://sandbox.partner.api.bri.co.id");
    }


   ```
4. Create Virtual Account
5. Get Virtual Account
6. Update Status Payment
7. Update Virtual Account Information
8. Get Report
9. Delete
