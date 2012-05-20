package hr.java2012.sonar.rest;

import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@Result(name = Action.SUCCESS, type = "tiles", location = "index")
public class IndexController extends ActionSupport {

	private static final long serialVersionUID = 1L;

}
