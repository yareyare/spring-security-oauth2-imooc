package com.ivy.security.controller.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@Slf4j
@RestController
@RequestMapping("/orders")
public class AsyncController {

    @Autowired
    private MockQueue queue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @GetMapping
    public DeferredResult<String> order() {
        log.info("主线程开始...");

        String placeOrder = RandomStringUtils.randomNumeric(6);

        // 同步
//		String result = placeOrder;
//		try {
//			Thread.sleep(2000L);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

        // 异步1： Callable<T>形式
//		Callable<String> result = new Callable<String>() {
//			@Override
//			public String call() throws Exception {
//				logger.info("副线程开始...");
//				Thread.sleep(2000L);
//				logger.info("副线程结束...");
//				return "place order success：" + placeOrder;
//			}
//		};

        // 异步2：DeferredResult<T>形式
        queue.setPlaceOrder(placeOrder);
        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(placeOrder, result);
        log.info("主线程开启线程来异步处理下单...");
        log.info("主线程结束...");
        return result;
    }
}
