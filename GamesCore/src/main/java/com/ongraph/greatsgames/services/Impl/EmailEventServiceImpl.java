package com.ongraph.greatsgames.services.Impl;

import com.ongraph.greatsgames.dao.EmailEventRepository;
import com.ongraph.greatsgames.entities.EmailEvent;
import com.ongraph.greatsgames.enums.Enumeration.*;
import com.ongraph.greatsgames.services.IConfigService;
import com.ongraph.greatsgames.services.IEmailEventService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@Transactional
public class EmailEventServiceImpl extends AbstractService implements IEmailEventService{

    private static final Logger _log = LogManager.getLogger(EmailEventServiceImpl.class);

    @Autowired
    private TemplateEngine templateEngine;


    @Autowired
    EmailEventRepository _emailEventRepository;


    private Context context = new Context();

    @Override
    public Long queueAccountVerificationEmail(String email, String verificationToken) throws Exception
    {
        EmailEvent emailEvent = new EmailEvent();

        emailEvent.setReciever(email);
        emailEvent.setSubject(EmailSubjects.VERIFICATION_EMAIL);
        emailEvent.setStatus(EmailEventStatus.PENDING);

        String resetLink = StringUtils.join(_configService.getValue(ConfigKeys.APP_BASE_URL),
                _configService.getValue(ConfigKeys.EMAIL_VERIFY_LINK),verificationToken);

        _log.info("Generated Email Verification Link : {}", resetLink);
        context.setVariable("link", resetLink);
        emailEvent.setContent(templateEngine.process("ConfirmEmail", context));
        Long eventId = _emailEventRepository.save(emailEvent).getId();

        return eventId;
    }

    @Override
    public Long queuePasswordRestEmail(String email, String verificationToken) throws Exception
    {
        EmailEvent emailEvent = new EmailEvent();
        emailEvent.setReceiver(email);
        emailEvent.setSubject(EmailSubjects.PASSWORD_RESET_EMAIL);
        emailEvent.setStatus(EmailEventStatus.PENDING);

        String resetLink = StringUtils.join(_configService.getValue(ConfigKeys.APP_BASE_URL),
                _configService.getValue(ConfigKeys.RESET_LINK),verificationToken);

        _log.info("Generated Email Verification Link : {}", resetLink);
        context.setVariable("link", resetLink);
        emailEvent.setContent(templateEngine.process("ForgetPassword", context));
        Long eventId =  _emailEventRepository.save(emailEvent).getId();
        return eventId;
    }
}
