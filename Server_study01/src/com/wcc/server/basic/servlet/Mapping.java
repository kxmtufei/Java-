package com.wcc.server.basic.servlet;
/**
 * <servlet-mapping>
  <servlet-name>login</servlet-name>
  <url-pattern>/login</url-pattern> 
  <url-pattern>/g</url-pattern> 
 </servlet-mapping>  
 * @author wcc
 * @Date 2019年12月24日 下午11:07:47
 */

import java.util.HashSet;
import java.util.Set;

public class Mapping {
	private String name;
	private Set<String> patterns ;//多个URL
	public Mapping() {
		patterns  = new HashSet<String>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<String> getPatterns() {
		return patterns ;
	}
	public void setPatterns(Set<String> patterns) {
		this.patterns  = patterns ;
	}
	
	public void addPattern(String pattern) {
		this.patterns.add(pattern);
	}
}
