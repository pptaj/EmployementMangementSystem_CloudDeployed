package com.ems.doa;

import com.ems.pojo.Employee;
import com.ems.pojo.Tasks;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.ems.pojo.Leaves;
import java.sql.Date;

import java.text.SimpleDateFormat;


import static org.junit.Assert.*;

/**
 * Created by taj on 3/20/2017.
 */
public class
LeavesDAOTest {
    LeavesDAO lv = new LeavesDAO();

    @Test
    public void addLeaves() throws Exception {
        EmployeeDAO empdao = new EmployeeDAO();
        Employee emp = empdao.getEmployee(10629020);
        Date d1 = new Date(2016-04-25 );
        Date d2 = new Date(2016-04-25 );
        Leaves leave = new Leaves(emp,d1 ,d2, 'R',"Rejected");
        Leaves testLeave = lv.addLeaves(d1,d2,emp);

        Assert.assertEquals(leave.getLeaveStartDate(), testLeave.getLeaveStartDate());
        Assert.assertEquals(leave.getLeaveEndDate(), testLeave.getLeaveEndDate());
    }
}