package com.ivy.security.controller.async;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @author ivy
 */
@Slf4j
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

    private final ThreadFactory threadFactory = new ThreadFactoryBuilder()
            .setNameFormat("queueListener-thread-%d")
            .setDaemon(true)
            .build();
    private final ExecutorService executorService =
            new ThreadPoolExecutor(1,
                    2,
                    100L,
                    TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>(),
                    threadFactory);
    @Autowired
    private MockQueue queue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        executorService.execute(() -> {
            while (true) {
                if (StringUtils.isNotBlank(queue.getCompleteOrder())) {
                    String completeOrder = queue.getCompleteOrder();

                    log.info("监听到调用方已成功接收订单服务的处理结果，正在通知客户端显示，订单号：" + completeOrder);
                    deferredResultHolder.getMap().get(completeOrder).setResult("place order success：" + completeOrder);

                    queue.setCompleteOrder(null);
                } else {
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
