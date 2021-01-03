package com.project.ess.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ess.model.report.*;
import com.project.ess.repository.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    AbsenceRepository absenceRepository;

    @Autowired
    AttempdailyRepository attempdailyRepository;

    @Autowired
    BenefitRequestRepository benefitRequestRepository;

    @Autowired
    EmployeeRequestRepository employeeRequestRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    FamilyRepository familyRepository;


    @Value("${report.location}")
    String reportLocation;



    public void getAbsenceReport(String startDate, String endDate, String status, Long employeeNo, HttpServletResponse httpServletResponse) throws IOException, JRException {

        List<AbsenceReport> listAbsenceReport=new ArrayList<>();

        absenceRepository.getAbsenceReport(startDate,endDate,status,employeeNo).forEach(x->{
            AbsenceReport absenceReport=new AbsenceReport();

            absenceReport.setEmployeeNo(x.get("employee_no").toString());
            absenceReport.setFullName(x.get("fullname").toString());
            absenceReport.setPosition(x.get("position").toString());
            absenceReport.setRequestNo(x.get("request_no").toString());
            absenceReport.setRequestType(x.get("request_type").toString());
            absenceReport.setRequestDate(x.get("request_date").toString());
            absenceReport.setStartDate(x.get("start_date").toString());
            absenceReport.setEndDate(x.get("end_date").toString());
            absenceReport.setDays(x.get("days").toString());
            absenceReport.setStatus(x.get("status").toString());
            absenceReport.setActionBy(x.get("action_by").toString());
            absenceReport.setApprovedDatetime(x.get("approved_datetime")==null ? "-" :x.get("approved_datetime").toString());
            absenceReport.setRemark(x.get("remark")==null ? "-" : x.get("remark").toString());

            listAbsenceReport.add(absenceReport);

        });


        AbsenceReportResult absenceReportResult=new AbsenceReportResult();
        absenceReportResult.setResult(listAbsenceReport);

        ObjectMapper newObjectMapper=new ObjectMapper();
        String writeValueAsString = newObjectMapper.writeValueAsString(listAbsenceReport);
//        System.out.println(writeValueAsString);

        JasperReport report = (JasperReport) JRLoader.loadObject(new File(reportLocation+"AbsenceReport.jasper"));

        final JsonDataSource jsonSource= new JsonDataSource(new ByteArrayInputStream(writeValueAsString.getBytes()));

        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Ari Susanto");
        //response.setHeader("Authorization", "Bearer db0dca62-d56c-4efe-b28a-9ba05327285f");
        // Filling the report with the employee data and additional parameters information.
        final JasperPrint print = JasperFillManager.fillReport(report, parameters, jsonSource);
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        //configuration.setOnePagePerSheet(true);
        configuration.setIgnoreGraphics(false);

        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        //ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //response.setContentType("application/x-msdownload");
        //response.setHeader("Content-Disposition", "attachment; filename=" + "test.pdf");
        httpServletResponse.setContentType("application/x-msdownload");
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + "absencereport.xlsx");
        //JasperExportManager.exportReportToPdfStream(print, responseOutputStream);


        Exporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(responseOutputStream));
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        //byteArrayOutputStream.writeTo(fileOutputStream);

        responseOutputStream.flush();
        responseOutputStream.close();

    }

    public void getAttempdailyReport(String startDate, String endDate, String status, Long employeeNo, HttpServletResponse httpServletResponse) throws IOException, JRException {

        List<AttempdailyReport> attempdailyReportList =new ArrayList<>();

        attempdailyRepository.getReportAttempdaily(startDate,endDate,status,employeeNo).forEach(x->{
            AttempdailyReport attempdailyReport=new AttempdailyReport();

            attempdailyReport.setEmployeeNo(x.get("employee_no").toString());
            attempdailyReport.setFullName(x.get("fullname").toString());
            attempdailyReport.setPosition(x.get("position").toString());
            attempdailyReport.setRequestNo(x.get("request_no").toString());
            attempdailyReport.setDateCheck(x.get("date_check").toString());
            attempdailyReport.setClock(x.get("clock").toString());
            attempdailyReport.setRemarkRequest(x.get("remark_request")==null ? "-" : x.get("remark_request").toString());
            attempdailyReport.setStatus(x.get("status").toString());
            attempdailyReport.setActionBy(x.get("action_by").toString());
            attempdailyReport.setApprovedDatetime(x.get("approved_datetime")==null ? "-" :x.get("approved_datetime").toString());
            attempdailyReport.setRemarkApproved(x.get("remark_approved")==null ? "-" : x.get("remark_approved").toString());
            attempdailyReportList.add(attempdailyReport);

        });


        AttempdailyReportResult attempdailyReportResult=new AttempdailyReportResult();
        attempdailyReportResult.setResult(attempdailyReportList);

        ObjectMapper newObjectMapper=new ObjectMapper();
        String writeValueAsString = newObjectMapper.writeValueAsString(attempdailyReportList);
//        System.out.println(writeValueAsString);

        JasperReport report = (JasperReport) JRLoader.loadObject(new File(reportLocation+"AttempdailyReport.jasper"));

        final JsonDataSource jsonSource= new JsonDataSource(new ByteArrayInputStream(writeValueAsString.getBytes()));

        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Ari Susanto");
        //response.setHeader("Authorization", "Bearer db0dca62-d56c-4efe-b28a-9ba05327285f");
        // Filling the report with the employee data and additional parameters information.
        final JasperPrint print = JasperFillManager.fillReport(report, parameters, jsonSource);
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        //configuration.setOnePagePerSheet(true);
        configuration.setIgnoreGraphics(false);

        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        //ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //response.setContentType("application/x-msdownload");
        //response.setHeader("Content-Disposition", "attachment; filename=" + "test.pdf");
        httpServletResponse.setContentType("application/x-msdownload");
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + "attempdailyreport.xlsx");
        //JasperExportManager.exportReportToPdfStream(print, responseOutputStream);


        Exporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(responseOutputStream));
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        //byteArrayOutputStream.writeTo(fileOutputStream);

        responseOutputStream.flush();
        responseOutputStream.close();

    }


    public void getBenefitReport(String startDate, String endDate, String status, Long employeeNo, HttpServletResponse httpServletResponse) throws IOException, JRException {

        List<BenefitReport> benefitReportList =new ArrayList<>();

        benefitRequestRepository.getBenefitReport(startDate,endDate,status,employeeNo).forEach(x->{
            BenefitReport benefitReport=new BenefitReport();

            benefitReport.setEmployeeNo(x.get("employee_no").toString());
            benefitReport.setFullName(x.get("fullname").toString());
            benefitReport.setPosition(x.get("position").toString());
            benefitReport.setRequestNo(x.get("request_no").toString());
            benefitReport.setTransactionDate(x.get("transaction_date").toString());
            benefitReport.setClaimType(x.get("claim_type").toString());
            benefitReport.setRemarkRequest(x.get("remark_request")==null ? "-" : x.get("remark_request").toString());
            benefitReport.setStatus(x.get("status").toString());
            benefitReport.setBenefitReason(x.get("benefit_reason").toString());
            benefitReport.setAmount(x.get("amount").toString());
            benefitReport.setRequestDateTime(x.get("request_date_time")==null ? "-" : x.get("request_date_time").toString());
            benefitReport.setActionBy(x.get("action_by").toString());
            benefitReport.setApprovedDatetime(x.get("approved_datetime")==null ? "-" :x.get("approved_datetime").toString());
            benefitReport.setRemarkApproved(x.get("remark_approved")==null ? "-" : x.get("remark_approved").toString());
            benefitReportList.add(benefitReport);

        });


        BenefitReportResult benefitReportResult=new BenefitReportResult();
        benefitReportResult.setResult(benefitReportList);

        ObjectMapper newObjectMapper=new ObjectMapper();
        String writeValueAsString = newObjectMapper.writeValueAsString(benefitReportList);
//        System.out.println(writeValueAsString);

        JasperReport report = (JasperReport) JRLoader.loadObject(new File(reportLocation+"BenefitReport.jasper"));

        final JsonDataSource jsonSource= new JsonDataSource(new ByteArrayInputStream(writeValueAsString.getBytes()));

        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Ari Susanto");
        //response.setHeader("Authorization", "Bearer db0dca62-d56c-4efe-b28a-9ba05327285f");
        // Filling the report with the employee data and additional parameters information.
        final JasperPrint print = JasperFillManager.fillReport(report, parameters, jsonSource);
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        //configuration.setOnePagePerSheet(true);
        configuration.setIgnoreGraphics(false);

        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        //ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //response.setContentType("application/x-msdownload");
        //response.setHeader("Content-Disposition", "attachment; filename=" + "test.pdf");
        httpServletResponse.setContentType("application/x-msdownload");
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + "benefitreport.xlsx");
        //JasperExportManager.exportReportToPdfStream(print, responseOutputStream);


        Exporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(responseOutputStream));
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        //byteArrayOutputStream.writeTo(fileOutputStream);

        responseOutputStream.flush();
        responseOutputStream.close();

    }

    public void getEmployeeReport(String startDate, String endDate, String status, Long employeeNo, HttpServletResponse httpServletResponse) throws IOException, JRException {

        List<EmployeeReport> employeeReportList =new ArrayList<>();

        employeeRequestRepository.getEmployeeReport(startDate,endDate,status,employeeNo).forEach(x->{
            EmployeeReport employeeReport=new EmployeeReport();

            employeeReport.setEmployeeNo(x.get("employee_no").toString());
            employeeReport.setFullName(x.get("fullname").toString());
            employeeReport.setPosition(x.get("position").toString());
            employeeReport.setRequestNo(x.get("request_no").toString());
            employeeReport.setRequestDatetime(x.get("request_date_time")==null ? "-" : x.get("request_date_time").toString());
            employeeReport.setRemarkRequest(x.get("remark_request")==null ? "-" : x.get("remark_request").toString());
            employeeReport.setStatus(x.get("status").toString());
            employeeReport.setActionBy(x.get("action_by").toString());
            employeeReport.setApprovedDatetime(x.get("approved_datetime")==null ? "-" :x.get("approved_datetime").toString());
            employeeReport.setRemarkApproved(x.get("remark_approved")==null ? "-" : x.get("remark_approved").toString());
            employeeReportList.add(employeeReport);

        });


        EmployeeReportResult employeeReportResult=new EmployeeReportResult();
        employeeReportResult.setResult(employeeReportList);

        ObjectMapper newObjectMapper=new ObjectMapper();
        String writeValueAsString = newObjectMapper.writeValueAsString(employeeReportList);
//        System.out.println(writeValueAsString);

        JasperReport report = (JasperReport) JRLoader.loadObject(new File(reportLocation+"EmployeeReport.jasper"));

        final JsonDataSource jsonSource= new JsonDataSource(new ByteArrayInputStream(writeValueAsString.getBytes()));

        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Ari Susanto");
        //response.setHeader("Authorization", "Bearer db0dca62-d56c-4efe-b28a-9ba05327285f");
        // Filling the report with the employee data and additional parameters information.
        final JasperPrint print = JasperFillManager.fillReport(report, parameters, jsonSource);
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        //configuration.setOnePagePerSheet(true);
        configuration.setIgnoreGraphics(false);

        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        //ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //response.setContentType("application/x-msdownload");
        //response.setHeader("Content-Disposition", "attachment; filename=" + "test.pdf");
        httpServletResponse.setContentType("application/x-msdownload");
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + "employeereport.xlsx");
        //JasperExportManager.exportReportToPdfStream(print, responseOutputStream);


        Exporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(responseOutputStream));
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        //byteArrayOutputStream.writeTo(fileOutputStream);

        responseOutputStream.flush();
        responseOutputStream.close();

    }

    public void getAddressReport(String startDate, String endDate, String status, Long employeeNo, HttpServletResponse httpServletResponse) throws IOException, JRException {

        List<AddressReport> addressReportList =new ArrayList<>();

        addressRepository.getAddressReport(startDate,endDate,status,employeeNo).forEach(x->{
            AddressReport addressReport=new AddressReport();

            addressReport.setEmployeeNo(x.get("employee_no").toString());
            addressReport.setFullName(x.get("fullname").toString());
            addressReport.setPosition(x.get("position").toString());
            addressReport.setRequestNo(x.get("request_no").toString());
            addressReport.setRequestDatetime(x.get("request_date_time")==null ? "-" : x.get("request_date_time").toString());
//            addressReport.setRemarkRequest(x.get("remark_request")==null ? "-" : x.get("remark_request").toString());
            addressReport.setStatus(x.get("status").toString());
            addressReport.setActionBy(x.get("action_by").toString());
            addressReport.setApprovedDatetime(x.get("approved_datetime")==null ? "-" :x.get("approved_datetime").toString());
            addressReport.setRemarkApproved(x.get("remark_approved")==null ? "-" : x.get("remark_approved").toString());
            addressReportList.add(addressReport);

        });


        AddressReportResult addressReportResult=new AddressReportResult();
        addressReportResult.setResult(addressReportList);

        ObjectMapper newObjectMapper=new ObjectMapper();
        String writeValueAsString = newObjectMapper.writeValueAsString(addressReportList);
//        System.out.println(writeValueAsString);

        JasperReport report = (JasperReport) JRLoader.loadObject(new File(reportLocation+"AddressReport.jasper"));

        final JsonDataSource jsonSource= new JsonDataSource(new ByteArrayInputStream(writeValueAsString.getBytes()));

        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Ari Susanto");
        //response.setHeader("Authorization", "Bearer db0dca62-d56c-4efe-b28a-9ba05327285f");
        // Filling the report with the employee data and additional parameters information.
        final JasperPrint print = JasperFillManager.fillReport(report, parameters, jsonSource);
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        //configuration.setOnePagePerSheet(true);
        configuration.setIgnoreGraphics(false);

        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        //ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //response.setContentType("application/x-msdownload");
        //response.setHeader("Content-Disposition", "attachment; filename=" + "test.pdf");
        httpServletResponse.setContentType("application/x-msdownload");
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + "addressreport.xlsx");
        //JasperExportManager.exportReportToPdfStream(print, responseOutputStream);


        Exporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(responseOutputStream));
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        //byteArrayOutputStream.writeTo(fileOutputStream);

        responseOutputStream.flush();
        responseOutputStream.close();

    }

    public void getFamilyReport(String startDate, String endDate, String status, Long employeeNo, HttpServletResponse httpServletResponse) throws IOException, JRException {

        List<FamilyReport> familyReportList =new ArrayList<>();

        familyRepository.getFamilyReport(startDate,endDate,status,employeeNo).forEach(x->{
            FamilyReport familyReport=new FamilyReport();

            familyReport.setEmployeeNo(x.get("employee_no").toString());
            familyReport.setFullName(x.get("fullname").toString());
            familyReport.setPosition(x.get("position").toString());
            familyReport.setRequestNo(x.get("request_no").toString());
            familyReport.setRequestDatetime(x.get("request_date_time")==null ? "-" : x.get("request_date_time").toString());
//            addressReport.setRemarkRequest(x.get("remark_request")==null ? "-" : x.get("remark_request").toString());
            familyReport.setStatus(x.get("status").toString());
            familyReport.setActionBy(x.get("action_by").toString());
            familyReport.setApprovedDatetime(x.get("approved_datetime")==null ? "-" :x.get("approved_datetime").toString());
            familyReport.setRemarkApproved(x.get("remark_approved")==null ? "-" : x.get("remark_approved").toString());
            familyReportList.add(familyReport);

        });


        FamilyReportResult familyReportResult=new FamilyReportResult();
        familyReportResult.setResult(familyReportList);

        ObjectMapper newObjectMapper=new ObjectMapper();
        String writeValueAsString = newObjectMapper.writeValueAsString(familyReportList);
//        System.out.println(writeValueAsString);

        JasperReport report = (JasperReport) JRLoader.loadObject(new File(reportLocation+"FamilyReport.jasper"));

        final JsonDataSource jsonSource= new JsonDataSource(new ByteArrayInputStream(writeValueAsString.getBytes()));

        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Ari Susanto");
        //response.setHeader("Authorization", "Bearer db0dca62-d56c-4efe-b28a-9ba05327285f");
        // Filling the report with the employee data and additional parameters information.
        final JasperPrint print = JasperFillManager.fillReport(report, parameters, jsonSource);
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        //configuration.setOnePagePerSheet(true);
        configuration.setIgnoreGraphics(false);

        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        //ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //response.setContentType("application/x-msdownload");
        //response.setHeader("Content-Disposition", "attachment; filename=" + "test.pdf");
        httpServletResponse.setContentType("application/x-msdownload");
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + "familyreport.xlsx");
        //JasperExportManager.exportReportToPdfStream(print, responseOutputStream);


        Exporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(responseOutputStream));
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        //byteArrayOutputStream.writeTo(fileOutputStream);

        responseOutputStream.flush();
        responseOutputStream.close();

    }


}
