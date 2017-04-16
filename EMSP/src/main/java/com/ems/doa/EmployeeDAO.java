/**
 * 
 */
package com.ems.doa;

import com.ems.pojo.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 * @author Christopher Dsouza
 *
 */
public class EmployeeDAO extends DAO{
	public Employee getEmployee(int empID){
		Employee emp = new Employee();
		try{
			begin();
			Query q = getSession().createQuery("from Employee where empID = :empID");
			q.setInteger("empID", empID);
            emp = (Employee) q.uniqueResult();
            commit();			
		}catch(HibernateException e){
			rollback();
			e.printStackTrace();
		}
		return emp;
	}
}