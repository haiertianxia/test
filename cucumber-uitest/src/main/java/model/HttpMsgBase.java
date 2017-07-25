package model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpMsgBase {
//	private static final String otherValue="otherValue";
	/**
	 * uri
	 */
	private String uri;

	private String jsonBody;

	private String contentType;	
	
	/**
	 * 如chrome 请求中的query string params
	 */
	private List<NameValuePair> queryParams;

	/**
	 * 如chrome POST 请求中的FormData
	 */
	private List<NameValuePair> formParams;

	/**
	 * cookie
	 */
	private List<Cookie> cookies;

	private ArrayList<String> cookie;
	
	/**
	 * 请求头
	 */
	private Header[] headers;
	
	/**
	 * json，xml，等
	 */
	private String type;

	/**
	 * 请求类型get,post,...
	 */
	private String method;

	//----------------------------以上是request
	
	//----------------------------以下是resoonse
	private int statusCode;
	private String respBody;
	/**
	 * 响应头
	 */
	private Header[] rspHeaders;
	private List<Cookie>rspCookies;
	private Object rspJsonBody;
	
	
	private CloseableHttpResponse resp;

	private CloseableHttpClient client;

	private HttpUriRequest request;
	
	public HttpMsgBase execute() {
		getConn();
		CreateReqestAndSet();
		reqInfo();
		exec();
		respInfo();
		closeConn();
		return this;
	}
	
	public HttpMsgBase executeWithInfo() {
		getConn();
		CreateReqestAndSet();
		exec();
		info();
		closeConn();
		return this;
	}
	public HttpMsgBase execute_with_json() {
		getConn();
		CreateReqestAndSet();
		exec_json();
		closeConn();
		return this;
	}
	public void info(){
		reqInfo();
		respInfo();
	}
	private void reqInfo(){
		System.out.println("********************reqInfo**********************");
		System.out.println(request.getRequestLine());
		if(method.equalsIgnoreCase("get")){
//			System.out.println(request.getURI());
		}else if(method.equalsIgnoreCase("post")){
			HttpEntity entity=((HttpPost) request).getEntity();
			try {
				if (entity != null) {
					String reqBody = EntityUtils.toString(entity);
					System.out.println(reqBody);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		Header[] cookies = request.getHeaders("Set-Cookie");
		if (cookies != null&&cookies.length>0) {
			System.out.println("");
			System.out.println("cookies：");

			for (Header cookie : cookies) {
				String v = cookie.getValue();
				String arry[] = v.split(";");
				String name = arry[0].split("=")[0];
				String value="";
				if(arry[0].split("=").length==2){
					 value = arry[0].split("=")[1];
				}
				System.out.println(name + "=" + value);
			}
		}
		
		Header[] reqheaders=request.getAllHeaders();
		if(reqheaders!=null&&reqheaders.length>0){
			System.out.println("");
			System.out.println("headers：");
			for (Header header : reqheaders) {
				if(!"Set-Cookie".equals(header.getName())){
					String name=header.getName();
					String value= header.getValue();
					System.out.println(name+"="+value);
				}
			}
		}
		
		
		System.out.println("");
		System.out.println("********************reqInfo**********************");
	}
	
	private void respInfo(){
		System.out.println("********************respInfo**********************");
		System.out.println(resp.getStatusLine());
		System.out.println(respBody);
		System.out.println(resp.getEntity().getContentLength());
		if(resp.getEntity().getContentEncoding()
				!=null){
			System.out.println(resp.getEntity().getContentEncoding().getName());
		}
		
		if(rspCookies!=null&&rspCookies.size()>0){
			System.out.println("");
			System.out.println("response cookies：");
			for (Cookie cookie : rspCookies) {
				String name =cookie.getName();
				String value = cookie.getValue();
				System.out.println(name + "=" + value);
			}
		}
		
		if(rspHeaders!=null&&rspHeaders.length>0){
			System.out.println("");
			System.out.println("response headers:");
			for(Header header:rspHeaders){
				if(!header.getName().equals("Set-Cookie")){
					System.out.println(header.getName()+"="+header.getValue());
				}
				
			}
		}
		
		System.out.println("");
		System.out.println("********************respInfo**********************");
	}
	
	private void getConn() {
		client = HttpClients.createDefault();
	}
	public void closeConn() {
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void CreateReqestAndSet() {
		String str = null;
		try {
			if (queryParams != null
					&& !queryParams.isEmpty()) {
				str = EntityUtils
						.toString(new UrlEncodedFormEntity(queryParams));
			}

			if (method != null
					&& method.equalsIgnoreCase("get")) {
				// Get请求
				request = new HttpGet(uri);
				// 设置参数
				((HttpGet) request).setURI(new URI(request.getURI().toString() + "?" + str));

			} else if (method != null
					&& method.equalsIgnoreCase("post")) {
				// Post请求
				request = new HttpPost(uri);
				if (str != null) {
					// 设置参数
					((HttpPost) request).setURI(new URI(request.getURI().toString() + "?" + str));
				}
				if (formParams != null && !formParams.isEmpty()) {
					// 设置formData
					((HttpPost) request).setEntity(
							new UrlEncodedFormEntity(formParams, Charset.forName("UTF-8")));
				}
			} else {
				System.err.println("not impl");
			}
			request.setHeaders(headers);
			if (cookies != null
					&& !cookies.isEmpty()
					&& cookies.size() > 0) {
//				request.addHeader("Cookie", EntityUtils.toString(new UrlEncodedFormEntity(
//						conventCookie2Nvps(cookies))));
				
				request.addHeader("Cookie", conventCookie2Str(cookies));
			}
			if (contentType != null) {
				if (contentType.equalsIgnoreCase("json")) {
					request.addHeader("content-type", "application/json; charset=utf-8");
					request.setHeader("Accept", "application/json");

				}
			}
			if (jsonBody != null && !"".equals(jsonBody.trim())) {
				((HttpPost) request).setEntity(new StringEntity(jsonBody, Charset.forName("UTF-8")));
			}
			// TODO HTTPS 处理，在此处理或者在responseFacade里处理
			//String type = specification.getHttpRequestCfg().getType();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private String conventCookie2Str(List<Cookie> cookies2) {
		String str="";
		for (Cookie cookie : cookies2) {
			String name=cookie.getName();
			String value=cookie.getValue();
			str+=name+"=";
			str+=value+";";
			String Domain= cookie.getDomain();
			if(Domain!=null&&!Domain.isEmpty()){
				str+="Domain="+Domain+";";
			}
			
		}
//		System.out.println("cookie string:"+str);
		return str;
	}

	@SuppressWarnings("deprecation")
	private void exec() {
		HttpEntity entity = null;
		try {
			// 打印请求信息
//			System.out.println("request line:" + request.getRequestLine());

			long startTime = System.currentTimeMillis();
			resp = client.execute(request);
			long endTime = System.currentTimeMillis();
			System.out.println("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
			// 打印响应信息
//			System.out.println("response status line：" + resp.getStatusLine());
			// 获取status code
			 statusCode = resp.getStatusLine().getStatusCode();
			if (statusCode >= 200 && statusCode < 300) {
				entity = resp.getEntity();
			}
			if (entity != null) {

				respBody = EntityUtils.toString(entity);
			}
			rspHeaders= resp.getAllHeaders();
			// cookie
			Header[] cookies = resp.getHeaders("Set-Cookie");
			
			List<Cookie> cookieLst = new ArrayList<Cookie>();
			ArrayList<String>cookieStrList = new ArrayList<String>();
			for (Header cookie : cookies) {
				String v = cookie.getValue();
				cookieStrList.add(v);
				//sessionid=40063F4972896D41AE281CE8063CB01A991D6E3C3C0B271F0CCC7F69B3EF2C936B2226659B1925E5DE08D58FFCB3BE30811CDF0293500E032397500678090255; PATH=/; DOMAIN=xunlei.com;
//				System.out.println("cookie attr:"+v);
				String arry[] = v.split(";");
				String name = arry[0].split("=")[0];
				String value="";
				if(arry[0].split("=").length==2){
					 value = arry[0].split("=")[1];
				}
				BasicClientCookie c=	new BasicClientCookie(name,value);
				cookieLst.add(c);
			}
			cookie=cookieStrList;
			rspCookies=cookieLst;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (entity != null) {
			try {
				entity.consumeContent();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@SuppressWarnings("deprecation")
	private void exec_json() {
		HttpEntity entity = null;
		try {
			// 打印请求信息
//			System.out.println("request line:" + request.getRequestLine());

			long startTime = System.currentTimeMillis();
			resp = client.execute(request);

			long endTime = System.currentTimeMillis();
			System.out.println("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
			// 打印响应信息
//			System.out.println("response status line：" + resp.getStatusLine());
			// 获取status code
			statusCode = resp.getStatusLine().getStatusCode();
			if (statusCode >= 200 && statusCode < 300) {
				entity = resp.getEntity();
			}
			if (entity != null) {

				respBody = EntityUtils.toString(entity);
				rspJsonBody=respBody;
			}
			rspHeaders = resp.getAllHeaders();
			// cookie
			Header[] cookies = resp.getHeaders("Set-Cookie");
			rspCookies=convent2cookieLst(cookies);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (entity != null) {
			try {
				entity.consumeContent();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	
	public void addQueryPara(String para, String value) {
		if (queryParams == null || queryParams.isEmpty()) {
			NameValuePair q = new BasicNameValuePair(para, value);
			List<NameValuePair> n = new ArrayList<NameValuePair>();
			n.add(q);
			queryParams = n;
		} else {
			queryParams.add(new BasicNameValuePair(para, value));
		}
	}

	public void addHeader(String name, String value) {
		if (headers == null || headers.length == 0) {
			Header h = new BasicHeader(name, value);
			Header[] ary = { h };
			setHeaders(ary);
		} else {
			int length = headers.length;
			Header[] old = headers;
			Header[] ary = new Header[length + 1];
			for (int i = 0; i < old.length; i++) {
				ary[i] = old[i];
			}
			ary[length] = new BasicHeader(name, value);
			setHeaders(ary);
		}

	}

	public void addFormPara(String para, String value) {
		if (formParams == null || formParams.isEmpty()) {
			NameValuePair q = new BasicNameValuePair(para, value);
			List<NameValuePair> n = new ArrayList<NameValuePair>();
			n.add(q);
			formParams = n;
		} else {
			formParams.add(new BasicNameValuePair(para, value));
		}
	}

	public void addCookie(String key, String value) {
		Cookie cookie = new BasicClientCookie(key, value);
		((BasicClientCookie) cookie).setDomain("xunlei.com");
		if (cookies == null || cookies.isEmpty()) {
			List<Cookie> c = new ArrayList<Cookie>();
			c.add(cookie);
			cookies = c;
		} else {
			cookies.add(cookie);
		}
	}
	
	public String getCookieAttr(String name) {
		String value="";
		for (Cookie ck: rspCookies) {
			if(name!=null&&name.equalsIgnoreCase(ck.getName())){
				value =ck.getValue();
			}
		}
		//System.out.println("cookie attr ： "+name +"="+value);
		return value;
	}
	
	public String geCookiePostRes(String split_key,String key) {
		String value="";
		for (Cookie ck: rspCookies) {
			String name=ck.getName();
			if(name!=null&&name.trim().startsWith("post_res_")){
				value=ck.getValue();
				try {
					value=URLDecoder.decode(value,"UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(value.contains(split_key)){
					String arr[]=value.split(split_key);
					if(arr.length==2&&arr[0].equalsIgnoreCase(key)){
						value=arr[1];
					}
				}
			}
		}
		return value;
	}
	
	public String geCookieAttrBySplitKey(String name,String split_key,String key) {
		String value="";
		value =getCookieAttr(name);
		if(value!=null&&value.startsWith(key)){
			try {
				value=URLDecoder.decode(value,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		if(value.contains(split_key)){
			String arr[]=value.split(split_key);
			if(arr.length==2){
				value=arr[1];
			}
		}
		return value;
	}
	
//	public String getHeader(String headerName) {
//		String value="";
//		for (Header header : headers) {
//			if(header.getName().equals(headerName)){
//				value=header.getValue();
//			}
//		}
//		return value;
//	}
	
	public String getRspHeader(String headerName) {
		String value="";
		for (Header header : rspHeaders) {
			if(header.getName().equals(headerName)){
				value=header.getValue();
			}
		}
		return value;
	}
	
	public String getRspHeaderBySplitKey(String headerName,String splitKey) {
		String value="";
		if(splitKey.isEmpty()){
			splitKey=":";
		}
		String headValue=getRspHeader(headerName);
		if(headValue.contains(splitKey)){
			if(headValue.split(splitKey).length==2){
				value=headValue.split(splitKey)[1];
			}
		}
		return value;
	}
	public String getRspCookieBySplitKey(String cookieName, String splitKey) {
		String value="";
		value =getCookieAttr(cookieName);
//		if(value!=null&&value.startsWith("0")){
//			try {
//				value=URLDecoder.decode(value,"UTF-8");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//		}
		
		if(value.contains(splitKey)){
			String arr[]=value.split(splitKey);
			if(arr.length==2){
				value=arr[1];
			}
		}
		return value;
	}
	
	public  List<Cookie> convent2cookieLst(Header[] cookies) {
		List<Cookie> cookieLst = new ArrayList<Cookie>();
		for (int i = 0; i < cookies.length; i++) {
			cookieLst.add(new BasicClientCookie(cookies[i].getName(), cookies[i].getValue()));
//			System.out.println(cookies[i].getName() + "=" + cookies[i].getValue());
		}
		return cookieLst;
	}

	public  List<? extends NameValuePair> conventCookie2Nvps(List<Cookie> list) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (int i = 0; i < list.size(); i++) {
			String name=list.get(i).getName();
			String value = list.get(i).getValue();
			nvps.add(new BasicNameValuePair(name,value));
		}
		return nvps;
	}

	
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public List<NameValuePair> getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(List<NameValuePair> queryParams) {
		this.queryParams = queryParams;
	}

	public List<Cookie> getCookies() {
		return cookies;
	}

	public void setCookies(List<Cookie> cookies) {
		this.cookies = cookies;
	}

	public Header[] getHeaders() {
		return headers;
	}

	public void setHeaders(Header[] headers) {
		this.headers = headers;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getJsonBody() {
		return jsonBody;
	}

	public void setJsonBody(String jsonBody) {
		this.jsonBody = jsonBody;
	}
	
	//----------------------------以上是request
	
		//----------------------------以下是resoonse
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getRespBody() {
		return respBody;
	}
	public void setRespBody(String respBody) {
		this.respBody = respBody;
	}

	public Header[] getRspHeaders() {
		return rspHeaders;
	}

	public void setRspHeaders(Header[] rspHeaders) {
		this.rspHeaders = rspHeaders;
	}

	public List<Cookie> getRspCookies() {
		return rspCookies;
	}

	public void setRspCookies(List<Cookie> rspCookies) {
		this.rspCookies = rspCookies;
	}

	public Object getRspJsonBody() {
		return rspJsonBody;
	}

	public void setRspJsonBody(Object rspJsonBody) {
		this.rspJsonBody = rspJsonBody;
	}
	public List<NameValuePair> getFormParams() {
		return formParams;
	}
	public void setFormParams(List<NameValuePair> formParams) {
		this.formParams = formParams;
	}

	public HttpMsgBase executeWithInfo(String type) {
		//TODO imple
//		getConn();
//		CreateReqestAndSet();
//		exec(type);
//		info();
//		closeConn();
		return this;
	}

	public ArrayList<String> getCookie() {
		return cookie;
	}

	public void setCookie(ArrayList<String> cookie) {
		this.cookie = cookie;
	}



}
