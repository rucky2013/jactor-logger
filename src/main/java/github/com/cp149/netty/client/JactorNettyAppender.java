package github.com.cp149.netty.client;

import java.util.ArrayList;
import java.util.List;

import org.agilewiki.jactor.JAMailboxFactory;
import org.agilewiki.jactor.MailboxFactory;
import org.jboss.netty.channel.Channel;

import ch.qos.logback.classic.spi.ILoggingEvent;

public class JactorNettyAppender extends NettyAppender{
	private  final MailboxFactory mailboxFactory = JAMailboxFactory.newMailboxFactory(10);
	
	

	@Override
	protected void append(ILoggingEvent eventObject) {
		try {
			if (isStarted()) {
				
				NettyActor actor = new NettyActor(eventObject, getChannel());				
				actor.initialize(mailboxFactory.createMailbox());
				NettyRequest.req.sendEvent(actor);
			}

		} catch (Exception e) {
			addError(e.getMessage());
		}
	}

	
	

}