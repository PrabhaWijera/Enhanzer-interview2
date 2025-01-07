package assignment;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Test_Form {

    @org.testng.annotations.Test(priority = 1)
    public  void testallfields() {
        // import the web driver
        WebDriver driver=new ChromeDriver();
        // give link what need to test
        driver.get("https://demoqa.com/automation-practice-form");
        // selenium web browser window size max
        driver.manage().window().maximize();

        // get application title
        String application_title=driver.getTitle();
        System.out.println("Application Title: "+ application_title);

        // check Form Name actual and expected result
        try{
            String actual_FormName= driver.findElement(By.xpath("//h5[normalize-space()='Student Registration Form']")).getText();
            String expect_FormName="job portal";
            System.out.println("Actual Form Name: "+actual_FormName);

            // Assertion Form Name
            if(actual_FormName.equals(expect_FormName)){
                System.out.println("Test Passed: Form Name Matches the Expected Result.");
            }else{
                System.out.println("Test Failed : "+expect_FormName+" but found "+actual_FormName);
            }
        }catch (AssertionError err){
            System.out.println("Form Name failed: "+err);
        }


        // Checking person details and Validation Form inputs

        //first name
        try{
            WebElement F_Name= driver.findElement(By.id("firstName"));
            F_Name.sendKeys("Prabhash");
            assertEquals("Prabhash",F_Name.getAttribute("value"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        // last name
        try{
            WebElement L_Name= driver.findElement(By.id("lastName"));
            L_Name.sendKeys("Wijerathna");
            assertEquals("Wijerathna",L_Name.getAttribute("value"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        //email
        try{
            WebElement E_mail = driver.findElement(By.id("userEmail"));
            E_mail.sendKeys("prabhashwijerathna2001@gmail.com");
            assertEquals("prabhashwijerathna2001@gmail.com",E_mail.getAttribute("value"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }



        // list gender  all check boxes
        try{
            List<WebElement> gender_checkBoxes=driver.findElements(By.className("//input[@id='custom-control-input']"));
            //check all checkboxes are first unchecked or not
            for(WebElement g_checkBoxes:gender_checkBoxes){
                if(g_checkBoxes.isSelected()){
                    System.out.println("Checkboxes are Already selected: ");
                }else {
                    System.out.println("Checkboxes are Not selected: ");
                }
            }
            // select all checkboxes
            for (int i=0; i<gender_checkBoxes.size(); i++){
                gender_checkBoxes.get(i).click();
            }
            // i need select Male Gender box
            WebElement maleGenderBox=driver.findElement(By.xpath("//label[@for='gender-radio-1']"));
            maleGenderBox.click();

            WebElement maleGBox= driver.findElement(By.id("gender-radio-1"));
            if (maleGBox.isSelected()) {
                System.out.println("Male checkbox selected successfully.");
            }


        }catch (AssertionError error){
            System.out.println("Form Gender select failed: "+error);
        }

        // Enter phone number
        try{
            WebElement mobileNumber= driver.findElement(By.id("userNumber"));
       mobileNumber.sendKeys("9876543210");
       assertEquals(mobileNumber.getText(),"Mobile Number is Required");
        }catch (AssertionError err){
            System.out.println(err.getMessage());
        }




        // Select date of birth
        try{
            driver.findElement(By.id("dateOfBirthInput")).click();
            WebElement dateInput = driver.findElement(By.id("dateOfBirthInput"));
            dateInput.clear(); // Clear any  value and set value
            dateInput.sendKeys("04 Aug 2001");
            String enteredDate=dateInput.getAttribute("value");
            assertEquals(enteredDate, "04 Aug 2001", "The date of birth entered does not match the expected value!");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        //Subjects
    try{
        try{
            WebElement subjectField = driver.findElement(By.xpath("//div[@class='subjects-auto-complete__input']//input"));
            subjectField.sendKeys("Maths");
            System.out.println("Check execution 1");
            WebDriverWait waiting = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement suggestion = waiting.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'subjects-auto-complete__option') and text()='Maths']")));

            suggestion.click();

            //Assertion
            String selectedSub=subjectField.getAttribute("value");
            assertEquals(selectedSub,"Maths","Subject selected wrong");

        }catch (AssertionError error){
            System.out.println("Subject selection assertion failed: " + error.getMessage());
        }
    }catch (Exception e){
        System.out.println(e.getMessage());
    }


        //Hobbies
        try {
            WebElement readBox = driver.findElement(By.xpath("(//div[@class='custom-control custom-checkbox custom-control-inline'])[2]"));
            readBox.click();
            WebElement ReadCBox = driver.findElement(By.id("hobbies-checkbox-1"));
            Assert.assertTrue("ReadBox checkbox is not selected", ReadCBox.isSelected());
        } catch (AssertionError e) {
            System.out.println("Hobbies checkbox assertion failed: " + e.getMessage());
        }


        //address
        driver.findElement(By.id("currentAddress")).sendKeys("123 Main St, Springfield");
        //address   Assertion
        try{
            String enteredAddress = driver.findElement(By.id("currentAddress")).getAttribute("value");
            assertEquals(enteredAddress,"123 Main St, Springfield","Address Entered Wrong");
        }catch (AssertionError err){
            System.out.println("address  assertion failed: " + err.getMessage());
        }

        // image select click the choose file btn
        WebElement chooseImageOption=driver.findElement(By.id("uploadPicture"));

        // send the image path from you device
        String filepath="E:\\Enhanzer\\Enhanzer_jobportal\\src\\assets\\node.webp";
        chooseImageOption.sendKeys(filepath);

        //Assertion image
        try{
           String uploadIMage= chooseImageOption.getAttribute("value");
           if(uploadIMage.isEmpty()){
               System.out.println("Image Upload assertion failed: ");
           }
        }catch (AssertionError err){
            System.out.println("IMage Upload assertion failed: " + err.getMessage());
        }




        // State and City if click state select the city from drop box
        try{
            // State
            WebDriverWait waits=new WebDriverWait(driver,Duration.ofSeconds(10));


            // State drop down
            WebElement stateDrop = driver.findElement(By.xpath("//div[@id='state']//div[contains(@class,'css-1hwfws3')]"));
            stateDrop.click();
            //   Select the ' Haryana ' value from state
            WebElement stateDrop_option = waits.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'css-11unzgr')]//div[text()='Haryana']")));
            stateDrop_option.click();


            // country dropdown
            WebElement countryDrop=driver.findElement(By.xpath(("//div[@id='city']//div[contains(@class,'css-1hwfws3')]")));
            countryDrop.click();

            // select the ' Karnal ' from country drop down
            WebElement countryDrop_option=waits.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'css-11unzgr')]//div[text()='Karnal']")));
            countryDrop_option.click();

            //check state and city Assertion
            try{
                //state
            String stateValue=stateDrop_option.getAttribute("value");
            assertEquals(stateValue,"Haryana","State value select is wrong");


            //city
                String cityValue=countryDrop_option.getAttribute("value");
                assertEquals(cityValue,"Karnal","City value select is wrong");
            }catch (AssertionError  err){
                System.out.println("State or city selection failed: " + err.getMessage());
            }
        }catch (Exception er){
            System.out.println("State or city selection failed: " + er);
        }



        //submit the form
        driver.findElement(By.xpath("//button[@id='submit']")).click();


        // after submit check the Success message
        // wait for the Success message modal
        WebDriverWait modalWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement modalDialog= modalWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-dialog")));

        // verify the modal title
        WebElement modalTitle=driver.findElement(By.id("example-modal-sizes-title-lg"));
        String actualTitle= modalTitle.getText();
        String expectedTitle="Thanks for submitting the form";

        if(actualTitle.equals(expectedTitle)){
            System.out.println("Modal Title Verified");
        }else{
            System.out.println("Modal Title Not Verified");
        }


        driver.findElement(By.id("closeLargeModal")).click();


        // close the browser
        driver.quit();
    }


    @org.testng.annotations.Test(priority = 2)
    public void testMandatoryFields(){

        WebDriver driver=new ChromeDriver();
        // give link what need to test
        driver.get("https://demoqa.com/automation-practice-form");
        // selenium web browser window size max
        driver.manage().window().maximize();


        // Form submission with mandatory fields left blank
        try{
            try{
                driver.findElement(By.xpath("//button[@id='submit']")).click();

                // application has no actual result alert so i empty it
                WebElement firstNameError = driver.findElement(By.id("firstName"));
                assertEquals(firstNameError.getText(), "First Name is Required", "");

                WebElement lastNameError = driver.findElement(By.id("lastName"));
                assertEquals(lastNameError.getText(), "Last Name is Required", "");


                WebElement mobileNumber= driver.findElement(By.id("userNumber"));
                assertEquals(mobileNumber.getText(),"Mobile Number is Required");

                WebElement emailError = driver.findElement(By.id("userEmail"));
                assertEquals(emailError.getText(), "Email is Required", "");
            }catch (AssertionError err){
                System.out.println("Error messages  not appear : " + err.getMessage());
            }finally {
                driver.quit();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }


     @org.testng.annotations.Test(priority = 3)
    public void testInvalidEmail(){

        WebDriver driver=new ChromeDriver();
        // give link what need to test
        driver.get("https://demoqa.com/automation-practice-form");
        // selenium web browser window size max
        driver.manage().window().maximize();

        // Form submission with Invalid Email
        try{
            try{
                WebElement F_Name= driver.findElement(By.id("firstName"));
                F_Name.sendKeys("Prabhash");
                assertEquals("Prabhash",F_Name.getAttribute("value"));

                WebElement L_Name= driver.findElement(By.id("lastName"));
                L_Name.sendKeys("Wijerathna");
                assertEquals("Wijerathna",L_Name.getAttribute("value"));


                driver.findElement(By.id("userEmail")).sendKeys("prabhash.2001@com");
                driver.findElement(By.xpath("//button[@id='submit']")).click();

                WebElement emailError = driver.findElement(By.id("userEmail"));
                assertEquals(emailError.getText(),"Email is Required");

            }catch (AssertionError err){
                System.out.println("Email is  not valid and not appear error messages: " + err.getMessage());
            } finally {
                driver.quit();
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }




    @org.testng.annotations.Test(priority = 4)
    public void testFileupload(){
        // 1  valid file types

        // image select click the choose file btn
        WebElement chooseImageOption=driver.findElement(By.id("uploadPicture"));

        // send the image path from you device
        String filepath="E:\\Enhanzer\\Enhanzer_jobportal\\src\\assets\\node.webp";
        chooseImageOption.sendKeys(filepath);

        //submit the form
        driver.findElement(By.xpath("//button[@id='submit']")).click();

        //Assertion image
        try{
            String uploadIMage= chooseImageOption.getAttribute("value");
            if(uploadIMage.isEmpty()){
                System.out.println("Image Upload assertion failed: ");
            }
        }catch (AssertionError err){
            System.out.println("IMage Upload assertion failed: " + err.getMessage());
        }




        // 2  in-valid file types

        // image select click the choose file btn
        WebElement chooseImageOption=driver.findElement(By.id("uploadPicture"));

        // send the pdf path from you device
        String filepath="E:\\Enhanzer - Interview2\\Enhanzer_jobportal\\src\\assets\\Prabhash Wijerathna CV (1).pdf";
        chooseImageOption.sendKeys(filepath);

        //submit the form
        driver.findElement(By.xpath("//button[@id='submit']")).click();

        //Assertion pdf
        try{
            String uploadIMage= chooseImageOption.getAttribute("value");
            if(uploadIMage.isEmpty()){
                System.out.println("Image Upload assertion failed: ");
            }
        }catch (AssertionError err){
            System.out.println("IMage Upload assertion failed: " + err.getMessage());
        }

    }





    @org.testng.annotations.Test(priority = 5)
    public void testEdgeCases(){

        WebDriver driver=new ChromeDriver();
        // give link what need to test
        driver.get("https://demoqa.com/automation-practice-form");
        // selenium web browser window size max
        driver.manage().window().maximize();

        // Form submission with Invalid Email
        try{
            try{
                WebElement F_Name= driver.findElement(By.id("firstName"));
                F_Name.sendKeys("<script>alert('XSS Attack!')</script>");
                assertEquals("Prabhash",F_Name.getAttribute("value"));

                WebElement L_Name= driver.findElement(By.id("lastName"));
                L_Name.sendKeys("!*#&@(*#&@&#(!#)&@*(Q)#(*@");
                assertEquals("Wijerathna",L_Name.getAttribute("value"));

                //submit the form
                driver.findElement(By.xpath("//button[@id='submit']")).click();


            }catch (AssertionError err){
                capturetheScreenShot(driver);
                System.out.println("Email is  not valid and not appear error messages: " + err.getMessage());
            } finally {
                driver.quit();
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }



    @org.testng.annotations.Test(priority = 6)
    public void capturetheScreenShot(WebDriver driver){

        try {
            // Take the screenshot
            TakesScreenshot ts = (TakesScreenshot) driver;
            File sourceFile = ts.getScreenshotAs(OutputType.FILE);

            // Define the path where the screenshot will be saved
            String filePath = "./screenshot.png"; // You can customize this path as needed

            // Save the screenshot to the specified path
            File destinationFile = new File(filePath);
            FileHandler.copy(sourceFile, destinationFile);

            System.out.println("Screenshot saved at: " + filePath);
        } catch (IOException e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }

    }



















}
