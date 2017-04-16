package com.ems.doa;

import com.ems.pojo.Employee;
import com.ems.doa.EmployeeDAO;
import com.ems.pojo.Tasks;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Christopher Dsouza on 3/16/2017.
 */
public class TasksDAOTest {
    TasksDAO ts = new TasksDAO();

    @Test
    public void createTasks() throws Exception {
        EmployeeDAO empdao = new EmployeeDAO();
        Employee emp = empdao.getEmployee(10629020);
        Tasks tsk = new Tasks(emp,"Complete UI","Started","First Thing to complete","");
        Tasks testTask = ts.createTasks("abc","complete","xyz","ghj",emp);
        Assert.assertNotSame(tsk, testTask);
    }
}