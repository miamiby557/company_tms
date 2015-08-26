package com.lnet.tms.web.report;


import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.model.sys.SysFile;
import com.lnet.tms.service.rpt.RptTestService;
import com.lnet.tms.service.rpt.RptViewCheckIncomeService;
import com.lnet.tms.service.rpt.RptViewOtdTransportService;
import com.lnet.tms.service.sys.SysFileService;
import com.lnet.tms.utility.UUIDConverter;
import com.lnet.tms.web.FileManager;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.InputStream;
import java.lang.String;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/report")
public class JasperReportController {

	protected static Logger logger = Logger.getLogger("controller");
	@Autowired
	public RptViewOtdTransportService rptViewOtdTransportService;
	@Autowired
	public RptTestService rptTestService;
	@Autowired
	public SysFileService sysFileService;
	@Autowired
	public RptViewCheckIncomeService rptViewCheckIncomeService;
	@Autowired
	public DataSource dataSource;
	@Autowired
	private FileManager fileManager;

	public String UploadId=null;

	public List<DataSourceRequest.FilterDescriptor> filterDescriptors=new ArrayList<>();

	@RequestMapping("/hello")
	public ModelAndView helloWorld() {
		return new ModelAndView("report/index");
	}

	@RequestMapping("/transportReportIndex")
	public ModelAndView transportReport() {
		return new ModelAndView("report/transportReportIndex");
	}

	@RequestMapping("/reportIndex")
	public ModelAndView reportIndex() {
		return new ModelAndView("report/reportIndex");
	}
    /**
     * Retrieves the PDF report file
     * 
     * @return
     */
	@RequestMapping(value = "/getPdfReport", method = RequestMethod.GET)
    public String doSalesReportPDF(Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 // 报表数据源
		if(filterDescriptors!=null && filterDescriptors.size()>0){
			for(DataSourceRequest.FilterDescriptor filter: filterDescriptors){
				if(("beginDate".equals(filter.getField()))||"endDate".equals(filter.getField())){
					filter.setField("orderDate");
					try {
						filter.setValue(sdf.parse(filter.getValue().toString()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				if("clientId".equals(filter.getField())){
					filter.setValue(UUID.fromString(filter.getValue().toString()));
				}
			}
		}
			DataSourceRequest request=new DataSourceRequest();
			request.getFilter().setFilters(filterDescriptors);
			request.getFilter().setLogic("and");
			JRDataSource jrDataSource= new JRBeanCollectionDataSource(rptViewCheckIncomeService.getDataSource(request).getData());
			filterDescriptors.clear();
		// 动态指定报表模板url
		model.addAttribute("url", "/WEB-INF/jasper/CheckIncomeReport.jasper");
		model.addAttribute("format", "pdf"); // 报表格式
		model.addAttribute("jrMainDataSource", jrDataSource);
		return "reportView";
	}
	@RequestMapping(value = "/getFilters",method = RequestMethod.POST)
	public  @ResponseBody
	JsonResult getFilters(@RequestBody List<DataSourceRequest.FilterDescriptor> filters){
		for (DataSourceRequest.FilterDescriptor filter:filters){
			filter.setLogic("and");
			filterDescriptors.add(filter);
		}
		return JsonResult.success();
	}
	@RequestMapping(value = "/pdfReport",method = RequestMethod.GET)
	public void getPdfReport(@RequestParam("id") String id,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		UUID uuid  = UUIDConverter.toUUID(id);
		File file=sysFileService.getFileById(uuid);
		FileInputStream in=null;
		try {
			in= new FileInputStream(file);
			ServletOutputStream servletOutputStream= response.getOutputStream();
			HashMap parameters = new HashMap();
			JasperRunManager.runReportToPdfStream(in,
					servletOutputStream, parameters, dataSource.getConnection());
			response.setContentType("application/pdf");
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	@RequestMapping(value = "/pdfReportBak",method = RequestMethod.GET)
	public void getPdfReportBak(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		try {
			ServletOutputStream servletOutputStream= response.getOutputStream();
			InputStream inputStream=session.getServletContext().getResourceAsStream("/WEB-INF/jasper/CheckIncomeReport.jasper");
			HashMap parameters = new HashMap();
			JasperRunManager.runReportToPdfStream(inputStream,
					servletOutputStream, parameters, dataSource.getConnection());
			response.setContentType("application/pdf");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/upload/{fileName}",method = RequestMethod.POST)
	public @ResponseBody JsonResult upload (@RequestParam MultipartFile file,@PathVariable String fileName, Model model,HttpServletResponse response){
		SysFile sysFile=new SysFile();
		try {
			sysFile=sysFileService.getByField("fileName",file.getOriginalFilename());
			if(sysFile!=null) {
				return JsonResult.error("该报表已存在！");
			}
			else {
				sysFile = fileManager.ReportUpload(file, fileName);
				return JsonResult.success();
			}
		} catch (IOException e) {
			return JsonResult.error("上传失败！");
		}
	}
}
