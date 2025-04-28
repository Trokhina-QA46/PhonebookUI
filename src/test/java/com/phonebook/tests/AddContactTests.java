package com.phonebook.tests;

import com.phonebook.data.ContactData;
import com.phonebook.data.UserData;
import com.phonebook.models.Contact;
import com.phonebook.models.User;
import com.phonebook.utils.DataProviders;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddContactTests extends TestBase{

    @BeforeMethod
   // public void precondition(){
        public void ensurePrecondition(){
            if(!app.getUser().isLoginLinkPresent()){
                app.getUser().clickOnSignOutButton();
            }

        app.getUser().clickOnLoginLink();
        app.getUser().fillRegisterLoginForm(new User().setMail(UserData.EMAIL).setPassword(UserData.PASSWORD));
        app.getUser().clickOnLoginButton();

    }
@Test
    public void addContactPositiveTest(){
    //click on link ADD
    app.getContact().clickOnAddLink();
    //enter name
    app.getContact().fillContactForm(new Contact().setName(ContactData.Name)
            .setLastName(ContactData.LastName)
            .setPhone(ContactData.Phone)
            .setEmail(ContactData.Email)
            .setAddress(ContactData.Address)
            .setDescription(ContactData.Description));
    app.getContact().clickOnSaveButton();
    //verify contact add
    Assert.assertTrue(app.getContact().isContactAdded(ContactData.Name));
    }

@Test(dataProvider = "addNewContact",dataProviderClass = DataProviders.class)
    public void addContactPositiveFromDataProviderTest(String name,String lastName,String phone,String email,String address,String description){
    app.getContact().clickOnAddLink();
    app.getContact().fillContactForm(new Contact().setName(name)
            .setLastName(lastName)
            .setPhone(phone)
            .setEmail(email)
            .setAddress(address)
            .setDescription(description));
    app.getContact().clickOnSaveButton();
    Assert.assertTrue(app.getContact().isContactAdded(name));
    }

    @Test(dataProvider = "addNewContactWithCsv",dataProviderClass = DataProviders.class)
    public void addContactPositiveFromDataProviderWithCsvFileTest(Contact contact){
    app.getContact().clickOnAddLink();
    app.getContact().fillContactForm(contact);
           // .setName(name)
        //    .setLastName(lastName)
         //   .setPhone(phone)
         //   .setEmail(email)
          //  .setAddress(address)
           // .setDescription(description));
    app.getContact().clickOnSaveButton();
    Assert.assertTrue(app.getContact().isContactAdded(contact.getName()));
    }

    @AfterMethod
    public void postCondition(){
    app.getContact().deleteContact();
}
}



