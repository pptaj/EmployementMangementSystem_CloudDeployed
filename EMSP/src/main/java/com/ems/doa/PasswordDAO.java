package com.ems.doa;

import com.ems.exception.AdException;
import com.ems.pojo.Person;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 * Created by Christopher Dsouza on 3/18/2017.
 */
public class PasswordDAO extends DAO {
    public int updateTempPassword(String empMail, String tempPassword){
        int result = 0;
        try{
            begin();
            String hql = "UPDATE Person set tempPwd = :tempPassword where emailAddress = :empMail";
            Query q = getSession().createQuery(hql);
            q.setString("tempPassword", tempPassword);
            q.setString("empMail", empMail);

            result = q.executeUpdate();
            commit();
        }catch(HibernateException e){
            rollback();
            e.printStackTrace();
        }
        return result;
    }


    public boolean chkResetPassword(String empMail, String tempPassword)throws AdException{
        boolean flag = false;
        Person person = new Person();
        try{
            begin();
            Query q = getSession().createQuery("from Person where emailAddress = :empMail and tempPwd = :tempPassword");
            q.setString("empMail", empMail);
            q.setString("tempPassword", tempPassword);
            person = (Person) q.uniqueResult();
            commit();
        }catch(HibernateException e){
            rollback();
            throw new AdException("Exception while fetching checking Reset Password: " + e.getMessage());
        }
        if(person!=null)
            flag=true;
        return flag;
    }

    public int updatePassword(String empMail, String password){
        int result = 0;
        try{
            begin();
            String hql = "UPDATE Person set password = :password where emailAddress = :empMail";
            Query q = getSession().createQuery(hql);
            q.setString("password", password);
            q.setString("empMail", empMail);

            result = q.executeUpdate();
            commit();
        }catch(HibernateException e){
            rollback();
            e.printStackTrace();
        }
        return result;
    }
}
