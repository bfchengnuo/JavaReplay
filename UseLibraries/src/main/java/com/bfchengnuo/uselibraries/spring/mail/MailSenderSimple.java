package com.bfchengnuo.uselibraries.spring.mail;

import com.google.common.collect.Maps;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * 使用 {@link JavaMailSender} 发送邮件的示例
 *
 * @author Created by 冰封承諾Andy on 2019/9/16.
 */
@Component
@Slf4j
public class MailSenderSimple {
    @Value("${spring.mail.username}")
    private String mailFrom;

    @Value("${mail.to}")
    private String mailToAddress;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    /**
     * 使用 freemarker 模板的例子
     */
    public void sendNoticeMail() {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(mailFrom);
            helper.setTo(mailToAddress);
            helper.setSubject("平台情况通知");

            Map<String, Object> model = Maps.newHashMapWithExpectedSize(1);
            model.put("time", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
            String text = getMailTextByTemplateName("mail.ftl", model);
            helper.setText(text, true);

            mailSender.send(mimeMessage);

        } catch (MessagingException | TemplateException | IOException e) {
            log.error("发送邮件失败，{}", e.toString());
        }
    }

    /**
     * 简单邮件发送
     * @param to 收件地址
     * @param subject 主题
     * @param text 内容
     */
    public void sendSimpleMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailFrom);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }

    /**
     * 发送带附件的邮件
     */
    public void sendAttachmentsMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(mailFrom);
        helper.setTo(mailToAddress);
        helper.setSubject("主题：有附件");
        helper.setText("有附件的邮件");

        FileSystemResource file = new FileSystemResource(new File("mps.jpg"));
        helper.addAttachment("附件-1.jpg", file);
        helper.addAttachment("附件-2.jpg", file);

        mailSender.send(mimeMessage);
    }

    /**
     * 邮件中插入静态资源
     */
    public void sendInlineMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(mailFrom);
        helper.setTo(mailToAddress);
        helper.setSubject("主题：嵌入静态资源");
        helper.setText("<html><body><img src=\"cid:mps\" ></body></html>", true);

        FileSystemResource file = new FileSystemResource(new File("mps.jpg"));
        helper.addInline("mps", file);
        mailSender.send(mimeMessage);
    }

    /**
     * 根据模板名 获取邮件内容
     * @param templateName 模板全名称
     * @param params 填充的参数Map
     * @return 填充好的字符串（HTML）
     */
    private String getMailTextByTemplateName(String templateName, Map<String,Object> params) throws IOException, TemplateException {
        String mailText = "";
        //通过指定模板名获取FreeMarker模板实例
        Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
        //FreeMarker通过Map传递动态数据
        //注意动态数据的key和模板标签中指定的属性相匹配
        //解析模板并替换动态数据，最终code将替换模板文件中的${code}标签。
        mailText = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
        return mailText;
    }
}
