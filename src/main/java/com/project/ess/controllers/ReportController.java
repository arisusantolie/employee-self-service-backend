package com.project.ess.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.ess.services.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("api/v1/report")
public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping("absence")
    public void getAbsenceReport(@RequestParam(value = "startDate",required = true) String startDate,
                                 @RequestParam(value = "endDate",required = true) String endDate,
                                 @RequestParam(value = "status",required = false) String status,
                                 @RequestParam(value = "employeeNo",required = false) Long empNo, HttpServletResponse httpServletResponse) throws IOException, JRException {

        reportService.getAbsenceReport(startDate,endDate,status,empNo,httpServletResponse);
    }

    @GetMapping("attempdaily")
    public void getAttempdailyReport(@RequestParam(value = "startDate",required = true) String startDate,
                                 @RequestParam(value = "endDate",required = true) String endDate,
                                 @RequestParam(value = "status",required = false) String status,
                                 @RequestParam(value = "employeeNo",required = false) Long empNo, HttpServletResponse httpServletResponse) throws IOException, JRException {

        reportService.getAttempdailyReport(startDate,endDate,status,empNo,httpServletResponse);
    }

    @GetMapping("benefit")
    public void getBenefitReport(@RequestParam(value = "startDate",required = true) String startDate,
                                     @RequestParam(value = "endDate",required = true) String endDate,
                                     @RequestParam(value = "status",required = false) String status,
                                     @RequestParam(value = "employeeNo",required = false) Long empNo, HttpServletResponse httpServletResponse) throws IOException, JRException {

        reportService.getBenefitReport(startDate,endDate,status,empNo,httpServletResponse);
    }

    @GetMapping("employee")
    public void getEmployeeReport(@RequestParam(value = "startDate",required = true) String startDate,
                                 @RequestParam(value = "endDate",required = true) String endDate,
                                 @RequestParam(value = "status",required = false) String status,
                                 @RequestParam(value = "employeeNo",required = false) Long empNo, HttpServletResponse httpServletResponse) throws IOException, JRException {

        reportService.getEmployeeReport(startDate,endDate,status,empNo,httpServletResponse);
    }

    @GetMapping("address")
    public void getAddressReport(@RequestParam(value = "startDate",required = true) String startDate,
                                  @RequestParam(value = "endDate",required = true) String endDate,
                                  @RequestParam(value = "status",required = false) String status,
                                  @RequestParam(value = "employeeNo",required = false) Long empNo, HttpServletResponse httpServletResponse) throws IOException, JRException {

        reportService.getAddressReport(startDate,endDate,status,empNo,httpServletResponse);
    }

    @GetMapping("family")
    public void getFamilyReport(@RequestParam(value = "startDate",required = true) String startDate,
                                 @RequestParam(value = "endDate",required = true) String endDate,
                                 @RequestParam(value = "status",required = false) String status,
                                 @RequestParam(value = "employeeNo",required = false) Long empNo, HttpServletResponse httpServletResponse) throws IOException, JRException {

        reportService.getFamilyReport(startDate,endDate,status,empNo,httpServletResponse);
    }
}
