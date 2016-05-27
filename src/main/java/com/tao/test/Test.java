package com.tao.test;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.tao.util.ForMd5;

public class Test extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private Logger log = Logger.getLogger(this.getClass());
     
    public String  login() {
        log.info("hello  i am limanman");
        return "success";
         
    }
    
}