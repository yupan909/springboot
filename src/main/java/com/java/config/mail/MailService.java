package com.java.config.mail;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

/**
 * 邮件任务
 *
 * @author yupan@yijiupi.cn
 * @date 2019/2/14 10:53
 */
@Service
public class MailService {

    private static final Logger LOG = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送者邮箱
     */
    @Value("${spring.mail.username}")
    private String userName;

    /**
     * 发送简单邮件
     *
     * @param toMail  接收者邮箱
     * @param title   邮件标题
     * @param content 邮件内容
     */
    public void sendSimpleMail(String toMail, String title, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(userName);
        message.setTo(toMail);
        message.setSubject(title);
        message.setText(content);
        javaMailSender.send(message);
        LOG.info("发送邮件成功: {}", JSON.toJSONString(message));
    }

    /**
     * 发送带附件的邮件
     *
     * @param toMail  接收者邮箱
     * @param title   邮件标题
     * @param content 邮件内容
     * @param files   附件
     */
    public void sendMail(String toMail, String title, String content, List<File> files) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
        message.setFrom(userName);
        message.setTo(toMail);
        message.setSubject(title);
        message.setText(content);
        // 附件
        if (!CollectionUtils.isEmpty(files)) {
            for (File file : files) {
                message.addAttachment(file.getName(), file);
            }
        }
        javaMailSender.send(mimeMessage);
        LOG.info("发送邮件成功: {}", mimeMessage.toString());
    }
}
