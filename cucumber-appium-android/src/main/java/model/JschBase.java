package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class JschBase {
	private String charset = "UTF-8"; // 设置编码格式
	private String user; // 用户名
	private String passwd; // 登录密码
	private String host; // 主机IP
	private int port=22;
	private String privateKey=null;
	private String passphrase=null;
	private String cmmd;
	private String shellCommand;
	private JSch jsch;
	private Session session;
	private int retCode;
	
	public JschBase execCmmd(){
		try {
			connect();
			execCmd();
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}
	
	/**
	 * 利用JSch包实现远程主机SHELL命令执行
	 * @param ip 主机IP
	 * @param user 主机登陆用户名
	 * @param psw  主机登陆密码
	 * @param port 主机ssh2登陆端口，如果取默认值，传-1
	 * @param privateKey 密钥文件路径
	 * @param passphrase 密钥的密码
	 * @param shellCommand shell命令，请以\n 结尾 ，表述回车
	 * @throws Exception
	 */
	public  JschBase sshShell() throws Exception{
		Session session = null;
		Channel channel = null;

		JSch jsch = new JSch();
		// 设置密钥和密码
		if (privateKey != null && !"".equals(privateKey)) {
			if (passphrase != null && "".equals(passphrase)) {
				// 设置带口令的密钥
				jsch.addIdentity(privateKey, passphrase);
			} else {
				// 设置不带口令的密钥
				jsch.addIdentity(privateKey);
			}
		}

		if (port <= 0) {
			// 连接服务器，采用默认端口
			session = jsch.getSession(user, host);
		} else {
			// 采用指定的端口连接服务器
			session = jsch.getSession(user, host, port);
		}

		//如果服务器连接不上，则抛出异常
		if (session == null) {
			throw new Exception("session is null");
		}
		
		//设置登陆主机的密码
		session.setPassword(passwd);//设置密码   
		//设置第一次登陆的时候提示，可选值：(ask | yes | no)
		session.setConfig("StrictHostKeyChecking", "no");
		//设置登陆超时时间   
		session.connect(30000);
		try {
			//创建sftp通信通道
			channel = (Channel) session.openChannel("shell");
			channel.connect(1000);
			//System.out.println(i++);
			//获取输入流和输出流
			InputStream instream = channel.getInputStream();
			OutputStream outstream = channel.getOutputStream();
			
			//发送需要执行的SHELL命令，需要用\n结尾，表示回车
//			shellCommand = "ls \n";
			if(!shellCommand.endsWith("\n")){
				shellCommand=shellCommand+" \n";
			}
			outstream.write(shellCommand.getBytes());
			outstream.flush();
			Thread.currentThread();
			Thread.sleep(1000*5);
			//获取命令执行的结果
			if (instream.available() > 0) {
				byte[] data = new byte[instream.available()];
				int nLen = instream.read(data);
				
				if (nLen < 0) {
					throw new Exception("network error.");
				}
				
				//转换输出结果并打印出来
				String temp = new String(data, 0, nLen,charset);
//				String temp = new String(data, 0, nLen,"iso8859-1");
				System.out.println(temp);
			}
			retCode=channel.getExitStatus();
//			setRetCode(channel.getExitStatus());
		    outstream.close();
		    instream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.disconnect();
			channel.disconnect();
		}
		return this;
	}
	
	/**
	 * 连接到指定的IP
	 * 
	 * @throws JSchException
	 */
	private void connect() throws JSchException {
		jsch = new JSch();
		session = jsch.getSession(user, host, 22);
		session.setPassword(passwd);
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
	}
	/**
	 * 执行相关的命令
	 */
	private void execCmd( ) {
		
		BufferedReader reader = null;
		Channel channel = null;
		try {
			while (cmmd  != null&&!cmmd.isEmpty()) {
				channel = session.openChannel("exec");
				((ChannelExec) channel).setCommand(cmmd);
				channel.setInputStream(null);
				((ChannelExec) channel).setErrStream(System.err);
				System.out.println(cmmd);
				channel.connect();
				InputStream in = channel.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in,
						Charset.forName(charset)));
				String buf = null;
				while ((buf = reader.readLine()) != null) {
					System.out.println(buf);
				}
				reader.close();
				if(channel.isClosed()){
					retCode=channel.getExitStatus();
				}
				break;
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSchException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			channel.disconnect();
			session.disconnect();
		}
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getRetCode() {
		return retCode;
	}
	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}
	public String getCmmd() {
		return cmmd;
	}
	public void setCmmd(String cmmd) {
		this.cmmd = cmmd;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getPassphrase() {
		return passphrase;
	}

	public void setPassphrase(String passphrase) {
		this.passphrase = passphrase;
	}

	public String getShellCommand() {
		return shellCommand;
	}

	public void setShellCommand(String shellCommand) {
		this.shellCommand = shellCommand;
	}
	
	
}
