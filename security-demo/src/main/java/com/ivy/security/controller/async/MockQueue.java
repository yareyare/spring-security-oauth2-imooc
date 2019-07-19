package com.ivy.security.controller.async;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @author ivy
 */

@Slf4j
@Component
public class MockQueue {

    private final ThreadFactory threadFactory = new ThreadFactoryBuilder()
            .setNameFormat("queue-thread-%d")
            .setDaemon(true)
            .build();
    private final ExecutorService executorService =
            new ThreadPoolExecutor(1,
                    2,
                    100L,
                    TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>(),
                    threadFactory);

    private String placeOrder;
    private String completeOrder;

    public String getPlaceOrder() {
        return placeOrder;
    }

    void setPlaceOrder(String placeOrder) {
        executorService.execute(() -> {
            this.placeOrder = placeOrder;
            log.info("调用方正在发送消息通知订单服务处理，订单号：" + placeOrder);
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeOrder = placeOrder;
            log.info("调用方接收到订单服务的处理成功的结果，订单号：" + placeOrder);
        });
    }

    String getCompleteOrder() {
        return completeOrder;
    }

    void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
