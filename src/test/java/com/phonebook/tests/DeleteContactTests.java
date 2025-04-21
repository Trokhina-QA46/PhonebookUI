package com.phonebook.tests;

import com.phonebook.data.ContactData;
import com.phonebook.data.UserData;
import com.phonebook.models.Contact;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteContactTests extends TestBase {
    //precondition

      @BeforeMethod
    public void precondition(){
          if(!app.getUser().isLoginLinkPresent()){
           app.getUser().clickOnSignOutButton();
              }

        //login
          app.getUser().clickOnLoginLink();
          app.getUser().fillRegisterLoginForm(new User()
                  .setMail(UserData.EMAIL)
                  .setPassword(UserData.PASSWORD));
          app.getUser().clickOnLoginButton();
          //add
          app.getContact().clickOnAddLink();
          app.getContact().fillContactForm(new Contact().setName(ContactData.Name)
                  .setLastName(ContactData.LastName)
                  .setPhone(ContactData.Phone)
                  .setEmail(ContactData.Email)
                  .setAddress(ContactData.Address)
                  .setDescription(ContactData.Description));
          app.getContact().clickOnSaveButton();


      }
    @Test
    public void deleteContactTest(){
          int sizeBefore= app.getContact().sizeOfContacts();
        //click on the card
        app.getContact().deleteContact();
        app.getContact().pause(1000);
        int sizeAfter = app.getContact().sizeOfContacts();
        Assert.assertEquals(sizeAfter,sizeBefore-1);
    }

    //click on Remove button
    //verify contact is deleted(by size)
}
