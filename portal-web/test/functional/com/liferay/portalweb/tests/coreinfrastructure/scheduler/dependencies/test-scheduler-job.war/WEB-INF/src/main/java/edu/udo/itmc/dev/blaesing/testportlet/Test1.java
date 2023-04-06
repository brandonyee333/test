
package edu.udo.itmc.dev.blaesing.testportlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;

public class Test1 implements MessageListener {

    @Override
    public void receive(Message msg) throws MessageListenerException {
        _log.info("Trigger 1");
    }

    private static final Log _log = LogFactoryUtil.getLog(Test1.class);

}
