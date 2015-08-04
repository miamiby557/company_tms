package com.lnet.tms.web.sys;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.model.sys.SysFile;
import com.lnet.tms.model.sys.SysList;
import com.lnet.tms.service.sys.SysFileService;
import com.lnet.tms.service.sys.SysListItemService;
import com.lnet.tms.service.sys.SysListService;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import com.lnet.tms.utility.UUIDConverter;
import com.lnet.tms.web.CrudController;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/sysFile")
public class SysFileController extends CrudController<SysFile, UUID, SysFileService> {

    private static final Logger logger = LoggerFactory.getLogger(SysFileController.class);

    @Autowired
    public void setBaseService(SysFileService service) {
        super.setService(service);
    }

    @RequestMapping(value="/download",method = RequestMethod.GET)
    @ResponseBody
    public SysFile download(@RequestParam("id")  String id,HttpServletResponse response){
        UUID uuid  = UUIDConverter.toUUID(id);
        SysFile sysFile = null;
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try {
            sysFile =service.get(uuid);
            File f = service.getFileById(uuid);
            response.reset();
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(sysFile.getFileName().getBytes("gbk"), "iso-8859-1"));
            response.setContentType("application/octet-stream; charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            in = new BufferedInputStream(new FileInputStream(f));
            out = new BufferedOutputStream(response.getOutputStream());
            byte[] data = new byte[1024];
            int len = 0;
            while (-1 != (len=in.read(data, 0, data.length))) {
                out.write(data, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @RequestMapping(value="/getFileList/{id}")
    @ResponseBody
    public List<Map<String, Object>> getFileList(@PathVariable("id")UUID id){
        return service.getFileList(id);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult deleteById(@PathVariable("id") UUID id) {
        try {
            service.deleteById(id);
            return JsonResult.success();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }
}