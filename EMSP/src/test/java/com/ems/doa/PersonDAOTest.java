package com.ems.doa;

import com.ems.pojo.Person;
import org.junit.Test;
import org.junit.Assert;

/**
 * Created by Christopher Dsouza on 3/16/2017.
 */
public class PersonDAOTest {
    PersonDAO pd = new PersonDAO();
    Person person = new Person(166018,"Christopher","Dsouza","christopherdsouza14@gmail.com",6176521616L,"TeamLead","1175 Boylston Street","Boston","Massachusetts",2215,"welcome123","ADMIN","'ppd65yf34d'");

    @Test
    public void getUserRole() throws Exception {
        Person testPerson = pd.getUserRole("christopherdsouza14@gmail.com","welcome123");
        Person testPersonNot = pd.getUserRole("ujjwal@abc.com","welcome123");
        Assert.assertEquals(person.getEmpID(), testPerson.getEmpID());
        Assert.assertNotNull(testPerson);
        Assert.assertNotSame(testPersonNot,person);
    }

    @Test
    public void searchUser() throws Exception {
        Person testPerson = pd.searchUser("christopherdsouza14@gmail.com");
        Assert.assertEquals(person.getEmpID(), testPerson.getEmpID());
        Assert.assertNotNull(testPerson);
    }
}