package com.lanqiao.shop.utils;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendJMail {
	
		public static boolean sendMail(String email, String emailMsg) {
			System.out.println("emailMsg"+emailMsg);
		//lidongmaogodlike@163.com
			String from = "18078454542@163.com"; 				// �ʼ������˵��ʼ���ַ
			String to = email; 										// �ʼ������˵��ʼ���ַ
			final String username = "18078454542@163.com";  	//�����˵��ʼ��ʻ�
			final String password = "qq354731612";   	  					//�����˵��ʼ�����
			final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

		//����Properties����,���û�����Ϣ
		Properties props = new Properties();

		//�����ʼ��������ĵ�ַ
		props.setProperty("mail.transport.protocol", "smtp");//���÷����ʼ�ʹ�õ�Э��
		props.setProperty("mail.smtp.host", "smtp.163.com"); // ָ����smtp������
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", "465");
		//����Session����,session�����ʾ�����ʼ��Ļ�����Ϣ
        
		Session session = Session.getInstance(props);
		//�������������Ϣ
		session.setDebug(true);
		try {
			//Message��ʵ�������ʾһ������ʼ�
			MimeMessage message = new MimeMessage(session);
			//���÷����˵ĵ�ַ
			message.setFrom(new InternetAddress(from));
			//��������
			message.setSubject("�û�����");
			//�����ʼ����ı�����
			//message.setText("Welcome to JavaMail World!");
			message.setContent(emailMsg,"text/html;charset=utf-8");// ע��ɹ�����<a href='http://www.product.com?activeCode=ffddff14'>����</a>���¼
			//��session�Ļ����л�ȡ�����ʼ��Ķ���
			Transport transport=session.getTransport();
			//�����ʼ�������
			transport.connect("smtp.163.com",username, password);
			//�����ռ��˵�ַ,��������Ϣ
			transport.sendMessage(message,new Address[]{new InternetAddress(to)});
			transport.close();
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
